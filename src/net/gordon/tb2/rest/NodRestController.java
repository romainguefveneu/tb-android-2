package net.gordon.tb2.rest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonStreamParser;

public abstract class NodRestController<T> {

	private static final String PATH = "http://data.nantes.fr/api/%s/1.0/%s/?output=json";
	private static final String API_KEY = "39W9VSNCSASEOGV";
	private static final String[] OPENDATA_FIRST_PATH = new String[] { "opendata", "answer", "data" };

	/**
	 * Récupérer le type de la collection.
	 * 
	 * @return le type de la collection
	 */
	protected abstract Type getCollectionType();

	/**
	 * Récupérer tous les éléments sous forme de liste d'object définie.
	 * 
	 * @param apiSection
	 * @param jsonPath
	 * @return La liste des éléments.
	 * @throws IOException
	 *             si le réseau bug.
	 */
	protected T getAll(String apiSection, String... jsonPath) throws IOException {
		final URL url = new URL(String.format(PATH, apiSection, API_KEY));
		final URLConnection connection = url.openConnection();

		final JsonStreamParser jsonParser = new JsonStreamParser(new InputStreamReader(connection.getInputStream()));
		if (jsonParser.hasNext()) {
			final JsonElement element = jsonParser.next().getAsJsonObject();
			JsonObject jsonObject = element.getAsJsonObject();
			JsonArray jsonArray = null;

			// Ajouter le chemin par défaut Open Data
			for (String path : OPENDATA_FIRST_PATH) {
				jsonObject = jsonObject.getAsJsonObject(path);
			}
			// Ajouter le chemin spécifique
			for (String path : jsonPath) {
				if (jsonObject.get(path).isJsonObject()) {
					jsonObject = jsonObject.getAsJsonObject(path);
				} else if (jsonObject.get(path).isJsonArray()) {
					jsonArray = jsonObject.getAsJsonArray(path);
				}
			}

			return (T) parseJson(jsonArray, getCollectionType());
		}
		return null;
	}

	/**
	 * Parser un élément JsonElement.
	 * 
	 * @param element
	 *            le json
	 * @param type
	 *            le type de l'objet à créer
	 * @return Un object de type T rempli avec plein de truc du json
	 */
	protected T parseJson(JsonElement element, Type type) {
		final Gson gson = new Gson();
		T result;
		result = gson.fromJson(element, type);
		return result;
	}

}

package net.gordon.tb2.rest.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import net.gordon.tb2.bean.parking.ParkingPublic;
import net.gordon.tb2.bean.parking.ParkingPublicStatut;
import net.gordon.tb2.rest.NodRestController;
import android.util.SparseArray;

import com.google.gson.reflect.TypeToken;

public class ParkingPublicsController extends NodRestController<List<ParkingPublic>> {
	private static final String API = "getDisponibiliteParkingsPublics";
	private SparseArray<ParkingPublicStatut> statuts;

	public ParkingPublicsController() {
		statuts = new SparseArray<ParkingPublicStatut>();
		for (final ParkingPublicStatut statut : ParkingPublicStatut.values()) {
			statuts.put(statut.getValue(), statut);
		}
	}

	@Override
	protected Type getCollectionType() {
		return new TypeToken<List<ParkingPublic>>() {
		}.getType();
	}

	public List<ParkingPublic> getAll() throws IOException {
		final List<ParkingPublic> parkings = super.getAll(API, "Groupes_Parking", "Groupe_Parking");
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		for (ParkingPublic parkingPublic : parkings) {
			parkingPublic.setStatut(statuts.get(parkingPublic.getStatutValue()));
			try {
				parkingPublic.setUpdateDate(dateFormat.parse(parkingPublic.getHorodatage()));
			} catch (ParseException e) {
				// Tant pis
			}
		}

		return parkings;
	}

}

package net.gordon.tb2;

import java.io.IOException;
import java.util.List;

import net.gordon.tb2.adapter.ParkingAdapter;
import net.gordon.tb2.bean.parking.ParkingPublic;
import net.gordon.tb2.rest.controller.ParkingPublicsController;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	protected ListView listview;
	protected ProgressBar progress;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		listview = (ListView) findViewById(android.R.id.list);
		progress = (ProgressBar) findViewById(android.R.id.progress);

		new ParkingLoaderTask().execute();
	}

	/**
	 * Classe de chargement des parkings publics.
	 * 
	 * @author romain
	 * 
	 */
	private class ParkingLoaderTask extends AsyncTask<Void, Void, ListAdapter> {

		@Override
		protected void onPreExecute() {
			listview.setVisibility(View.GONE);
			progress.setVisibility(View.VISIBLE);
			progress.setIndeterminate(true);
		}

		@Override
		protected ListAdapter doInBackground(Void... arg0) {

			final ParkingPublicsController controller = new ParkingPublicsController();
			ListAdapter adapter = null;

			try {
				final List<ParkingPublic> parkings = controller.getAll();
				adapter = new ParkingAdapter(MainActivity.this, parkings);
			} catch (IOException e) {
				Log.e("MainActivity", "Erreur", e);
			}

			return adapter;
		}

		@Override
		protected void onPostExecute(ListAdapter result) {
			listview.setAdapter(result);
			listview.setVisibility(View.VISIBLE);
			progress.setVisibility(View.GONE);
		}

	}

}
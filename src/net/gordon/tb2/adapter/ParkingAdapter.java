package net.gordon.tb2.adapter;

import java.util.List;

import net.gordon.tb2.R;
import net.gordon.tb2.bean.parking.ParkingPublic;
import net.gordon.tb2.bean.parking.ParkingPublicStatut;
import net.gordon.tb2.utils.ParkingUtils;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ParkingAdapter extends ArrayAdapter<ParkingPublic> {

	private ViewHolder holder;

	public ParkingAdapter(Context context, List<ParkingPublic> data) {
		super(context, R.layout.list_item_parking, data);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout newView;
		final ParkingPublic item = getItem(position);
		String details;
		int couleur;

		// Gestion de la vue
		if (convertView == null) {
			final LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);

			newView = new LinearLayout(getContext());
			layoutInflater.inflate(R.layout.list_item_parking, newView, true);

			holder = new ViewHolder();
			holder.itemTitle = (TextView) newView.findViewById(R.id.itemTitle);
			holder.itemDescription = (TextView) newView.findViewById(R.id.itemDescription);
			holder.itemSymbole = (ImageView) newView.findViewById(R.id.itemSymbole);

			newView.setTag(holder);
		} else {
			newView = (LinearLayout) convertView;
			holder = (ViewHolder) convertView.getTag();
		}

		// L'important c'est les valeurs
		final Resources resources = getContext().getResources();
		if (item.getStatut() == ParkingPublicStatut.OUVERT) {
			final int placesDisponibles = item.getPlacesDisponibles();
			couleur = resources.getColor(ParkingUtils.getSeuilCouleurId(placesDisponibles));
			if (placesDisponibles > 0) {
				details = resources.getQuantityString(R.plurals.parking_places_disponibles, placesDisponibles,
						placesDisponibles);
			} else {
				details = getContext().getString(R.string.parking_places_disponibles_zero);
			}
		} else {
			details = getContext().getString(item.getStatut().getTitleRes());
			couleur = resources.getColor(item.getStatut().getColorRes());
		}

		// On affiche tout ça au bon endroit et hop !
		holder.itemTitle.setText(item.getNom());
		holder.itemDescription.setText(details);
		holder.itemSymbole.setBackgroundColor(couleur);

		return newView;
	}

	private class ViewHolder {
		TextView itemTitle;
		TextView itemDescription;
		ImageView itemSymbole;
	}
}

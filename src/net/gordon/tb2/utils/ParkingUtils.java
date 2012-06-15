/**
 *  Copyright (C) 2011 Romain Guefveneu
 *  
 *  This file is part of naonedbus.
 *  
 *  Naonedbus is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Naonedbus is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.gordon.tb2.utils;

import net.gordon.tb2.R;

/**
 * @author romain.guefveneu
 * 
 */
public class ParkingUtils {
	/**
	 * C'est plein.
	 */
	private static final double SEUIL_ROUGE = 1;
	/**
	 * C'est presque plein.
	 */
	private static final double SEUIL_ORANGE = 15;

	/**
	 * @param placesDisponibles
	 *            bah... le nombre de places disponibles tiens !
	 * @return La resource correspondant à la couleur du seuil correspondant aux
	 *         places disponibles.
	 */
	public static int getSeuilCouleurId(int placesDisponibles) {
		int couleurResId;
		if (placesDisponibles < SEUIL_ROUGE) {
			couleurResId = R.color.parking_state_red;
		} else if (placesDisponibles < SEUIL_ORANGE) {
			couleurResId = R.color.parking_state_orange;
		} else {
			couleurResId = R.color.parking_state_blue;
		}

		return couleurResId;
	}
}

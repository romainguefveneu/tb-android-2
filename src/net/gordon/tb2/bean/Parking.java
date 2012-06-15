package net.gordon.tb2.bean;

import java.io.Serializable;

/**
 * Description d'un parking.
 * 
 * @author romain
 * 
 */
public interface Parking extends Serializable {

	Integer getId();

	void setId(Integer id);

	String getNom();

	void setNom(String nom);

	String getAdresse();

	void setAdresse(String adresse);

	String getTelephone();

	void setTelephone(String telephone);

	String getUrl();

	void setUrl(String url);

	Double getLatitude();

	void setLatitude(Double latitude);

	Double getLongitude();

	void setLongitude(Double longitude);

}

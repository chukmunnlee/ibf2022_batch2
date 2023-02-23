package ibf2022.batch2.ssf.day18.models;

import java.util.LinkedList;
import java.util.List;

public class Weather {

	private String city;
	private String country;
	private List<String> description = new LinkedList<>();
	private float latitude;
	private float longitude;

	public void setCity(String city) { this.city = city; }
	public String getCity() { return this.city; }

	public void setCountry(String country) { this.country = country; }
	public String getCountry() { return this.country; }

	public void setDescription(List<String> description) { this.description = description; }
	public List<String> getDescription() { return this.description; }
	public void addDescription(String desc) { this.description.add(desc); }

	public void setLatitude(float latitude) { this.latitude = latitude; }
	public float getLatitude() { return this.latitude; }

	public void setLongitude(float longitude) { this.longitude = longitude; }
	public float getLongitude() { return this.longitude; }

}

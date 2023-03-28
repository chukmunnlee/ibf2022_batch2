package ibf2022.batch2.paf.day36.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Restaurant {

	private String name;
	private String address;
	private String cuisine;
	private float rating;

	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }

	public void setAddress(String address) { this.address = address; }
	public String getAddress() { return this.address; }

	public void setCuisine(String cuisine) { this.cuisine = cuisine; }
	public String getCuisine() { return this.cuisine; }

	public void setRating(float rating) { this.rating = rating; }
	public float getRating() { return this.rating; }

	@Override
	public String toString() {
		return "Restaurant{name=%s, address=%s, cuisine=%s}"
			.formatted(name, address, cuisine);
	}

	public static Restaurant toRestaurant(String jsonStr) {
		Restaurant restaurant = new Restaurant();
		JsonReader reader = Json.createReader(new StringReader(jsonStr));
		JsonObject o = reader.readObject();
		restaurant.setName(o.getString("name"));
		restaurant.setAddress(o.getString("address"));
		restaurant.setCuisine(o.getString("type_of_food"));
		try {
			restaurant.setRating((float)o.getJsonNumber("rating").doubleValue());
		} catch (Exception ex) {
			// If rating is not a number, try string
			restaurant.setRating(-1f);
		}
		return restaurant;
	}

}

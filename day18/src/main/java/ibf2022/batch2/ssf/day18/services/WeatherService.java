package ibf2022.batch2.ssf.day18.services;

import java.io.StringReader;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2022.batch2.ssf.day18.models.Weather;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class WeatherService {

	public final static String URL = "https://api.openweathermap.org/data/2.5/weather";

	@Value("${weathermap.key}")
	private String apiKey;

	public Optional<Weather> getWeather(String city) {

		// Build the URL
		String url = UriComponentsBuilder.fromUriString(URL)
			.queryParam("q", city)
			.queryParam("appid", apiKey)
			.toUriString();

		System.out.printf("NEVER DO THIS!!!!: WeatherMap URL: %s\n", url);

		// Make the request 
		RequestEntity<Void> req = RequestEntity.get(url)
				.accept(MediaType.APPLICATION_JSON)
				.build();

		// Make the call to OpenWeather Map 
		RestTemplate template = new RestTemplate();

		ResponseEntity<String> resp = null;

		String payload = "";
		int statusCode = 500;
		try {
			resp = template.exchange(req, String.class);
			payload = resp.getBody();
			statusCode = resp.getStatusCode().value();

		} catch (HttpClientErrorException ex) {
			payload = ex.getResponseBodyAsString();
			//statusCode = ex.getRawStatusCode();
			statusCode = ex.getStatusCode().value();
			return Optional.empty();
		} finally {
			System.out.printf(">>> status code: %d\n", statusCode);
			System.out.printf(">>> payload: \n%s\n", payload);
		}

		Weather weather = new Weather();
		weather.setCity(city);

		// Parse the result to Weather
		JsonReader reader = Json.createReader(new StringReader(payload));
		JsonObject weatherJson = reader.readObject();
		JsonObject jo = weatherJson.getJsonObject("coord");

		// Set the lat and lng
		float floatValue = (float)jo.getJsonNumber("lon").doubleValue();
		weather.setLongitude(floatValue);
		floatValue = (float)jo.getJsonNumber("lat").doubleValue();
		weather.setLatitude(floatValue);

		// Read weather
		JsonArray arr = weatherJson.getJsonArray("weather");
		for (int i = 0; i < arr.size(); i++) {
			jo = arr.getJsonObject(i);
			String desc = "%s - %s".formatted(
				jo.getString("main"), jo.getString("description"));
			weather.addDescription(desc);
		}

		// Get the country
		jo = weatherJson.getJsonObject("sys");
		weather.setCountry(jo.getString("country").toLowerCase());

		return Optional.of(weather);
	}
}

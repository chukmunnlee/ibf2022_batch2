package ibf2022.batch2.ssf.day18.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {

	public final static String URL = "https://api.openweathermap.org/data/2.5/weather";

	@Value("${weathermap.key}")
	private String apiKey;

	public void getWeather(String city) {

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

		String payload;
		int statusCode;
		try {
			resp = template.exchange(req, String.class);
			payload = resp.getBody();
			statusCode = resp.getStatusCode().value();

		} catch (HttpClientErrorException ex) {
			payload = ex.getResponseBodyAsString();
			//statusCode = ex.getRawStatusCode();
			statusCode = ex.getStatusCode().value();
		}

		System.out.printf(">>> status code: %d\n", statusCode);
		System.out.printf(">>> payload: \n%s\n", payload);
	}
}

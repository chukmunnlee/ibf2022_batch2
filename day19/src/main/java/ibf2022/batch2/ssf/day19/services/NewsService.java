package ibf2022.batch2.ssf.day19.services;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonArray;

import ibf2022.batch2.ssf.day19.models.News;

@Service
public class NewsService {

	public static final String EVERYTHING = "https://newsapi.org/v2/everything";

	@Value("${newsapi.key}")
	private String apiKey;

	public List<News> getNews(String q) {

		// GET https://newsapi.org/v2/everything?q=q&apiKey=apiKey
		String url = UriComponentsBuilder
				.fromUriString(EVERYTHING)
				.queryParam("q", q)
				.queryParam("pageSize", 10)
				.toUriString();

		RequestEntity<Void> req = RequestEntity.get(url)
				.header("X-Api-Key", apiKey)
				.accept(MediaType.APPLICATION_JSON)
				.build();

		RestTemplate template = new RestTemplate();
		ResponseEntity<String> resp = template.exchange(req, String.class);

		String body = resp.getBody();
		JsonReader reader = Json.createReader(new StringReader(body));
		JsonObject obj = reader.readObject();
		JsonArray arr = obj.getJsonArray("articles");

		return arr.stream()
			.map(v -> v.asJsonObject())
			.map(News::create)
			.toList();
	}
}

package ibf2022.batch2.paf.day27;

import java.io.StringReader;

import org.bson.Document;
import org.springframework.util.MultiValueMap;

import ibf2022.batch2.paf.day27.models.Comment;
import ibf2022.batch2.paf.day27.models.TvShow;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Utils {

	public static Document toDocument(Comment comment) {
		Document doc = new Document();
		doc.put("username", comment.getUsername());
		doc.put("rating", comment.getRating());
		doc.put("comment", comment.getComment());
		return doc;
	}

	public static JsonObject toJson(Document doc) {
		JsonReader reader = Json.createReader(new StringReader(doc.toJson()));
		return reader.readObject();
	}

	public static TvShow toTvShow(JsonObject obj) {
		TvShow tvShow = new TvShow();
		tvShow.setId(obj.getInt("id"));
		tvShow.setName(obj.getString("name"));
		tvShow.setSummary(obj.getString("summary"));
		return tvShow;
	}

	public static Comment toComment(MultiValueMap<String, String> form) {
		Comment comment = new Comment();
		comment.setId(Integer.parseInt(form.getFirst("id")));
		comment.setUsername(form.getFirst("username"));
		comment.setRating(Integer.parseInt(form.getFirst("rating")));
		comment.setComment(form.getFirst("comment"));
		return comment;
	}
}

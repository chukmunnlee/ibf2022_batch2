package ibf2022.batch2.paf.day27.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;

import ibf2022.batch2.paf.day27.Utils;
import ibf2022.batch2.paf.day27.models.Comment;
import ibf2022.batch2.paf.day27.models.TvShow;
import jakarta.json.JsonObject;

@Repository
public class TvShowRepository {

	public static final String C_TVSHOWS = "tvshows";

	@Autowired
	private MongoTemplate template;

	public List<TvShow> findTvShowByName(String name) {
		Criteria criteria = Criteria.where("name")
				.regex(name, "i");

		Query query = Query.query(criteria)
				.with(Sort.by(Sort.Direction.ASC, "name"));

		query.fields()
			.include("name", "id", "summary")
			.exclude("_id");

		return template.find(query, Document.class, C_TVSHOWS).stream()
				.map(Utils::toJson)
				.map(Utils::toTvShow)
				.toList();
	}

	/*
	 * db.tvshows.update(
	 * 	{ id: 123 },
	 * 	{ $push: { comments: { comment_object } } }
	 * )
	 */
	public void insertComment(Comment comment) {

		Document doc = Utils.toDocument(comment);

		Criteria criteria = Criteria.where("id").is(comment.getId());
		Query query = Query.query(criteria);

		Update updateOps = new Update()
				.push("comments", doc);

		UpdateResult result = template.upsert(query, updateOps, Document.class, C_TVSHOWS);

		System.out.printf("matched: %d\n", result.getMatchedCount());
		System.out.printf("modified: %d\n", result.getModifiedCount());
	}

}

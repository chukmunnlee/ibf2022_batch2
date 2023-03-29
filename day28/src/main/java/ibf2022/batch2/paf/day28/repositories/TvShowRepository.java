package ibf2022.batch2.paf.day28.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.MongoExpression;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationExpression;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

@Repository
public class TvShowRepository {

	public static final String C_TVSHOWS = "tvshows";

	@Autowired
	private MongoTemplate template;

	/*
	 * db.tvshows.aggregate([
	 * 	{ $match: { type: 'a type' } }
	 * ])
	 */
	public void findTvShowsByType(String type) {

		// stages
		MatchOperation matchType = Aggregation.match(
				Criteria.where("type").regex(type, "i"));

		ProjectionOperation selectFields = Aggregation.project("id", "name", "summary")
				.andExclude("_id");

		// create the pipeline from stages
		Aggregation pipeline = Aggregation.newAggregation(matchType, selectFields);

		// perform the query
		AggregationResults<Document> results = template.aggregate(
				pipeline, C_TVSHOWS, Document.class);

		printIt(results);
	}

	/*
	 * db.tvshows.aggreate([
	 * 	$group: {
	 * 		_id: "$network.country.timezone",
	 * 		total_shows: { $sum: 1 },
	 * 		titles: { $push: "$name" }
	 * 	}
	 * ])
	 */
	public void groupShowsByTimezone() {

		// Stage
		GroupOperation tzGroup = Aggregation.group("network.country.timezone")
			.count().as("total_shows")
			.push("name").as("titles");

		Aggregation pipeline = Aggregation.newAggregation(tzGroup);

		AggregationResults<Document> results = template.aggregate(
				pipeline, C_TVSHOWS, Document.class);

		printIt(results);
	}

	public void summarizeTvShows(String type) {
		// Stage
		MatchOperation filterByType = Aggregation.match(
				Criteria.where("type").regex(type, "i"));

		ProjectionOperation summarizeFields = Aggregation.project("id", "type")
				.and(
					AggregationExpression.from(
						MongoExpression.create(""" 
							$concat: [ "$name", " (", { $toString: "$runtime" }, "mins)" ] 
						""") // MongoExpression
					 ) // AggregationExpression
				).as("title")
				.andExclude("_id");

		SortOperation orderByTitle = Aggregation.sort(
				Sort.by(Direction.ASC, "title"));

		// Create pipeline 
		Aggregation pipeline = Aggregation.newAggregation(
				filterByType, summarizeFields, orderByTitle);

		AggregationResults<Document> results = template.aggregate(
				pipeline, C_TVSHOWS, Document.class);

		printIt(results);
	}

	public void showCategories() {
		// stages
		UnwindOperation unwindGenres = Aggregation.unwind("genres");

		GroupOperation groupGenres = Aggregation.group("abc")
				.addToSet("genres").as("categories");

		// Pipeline
		Aggregation pipeline = Aggregation.newAggregation(unwindGenres, groupGenres);

		AggregationResults<Document> results = template.aggregate(
				pipeline, C_TVSHOWS, Document.class);

		printIt(results);
	}


	private void printIt(AggregationResults<Document> results) {
		for (Document d: results)
			System.out.printf(">>> %s\n", d.toJson());
	}
}

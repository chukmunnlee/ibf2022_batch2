package ibf2022.batch2.paf.day28.repositories;

import java.util.*;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Repository;

@Repository
public class AccomodationRepository {

	private static final String C_LOCATIONS = "locations";

	@Autowired
	private MongoTemplate template;

	public List<String> getCountries() {
		return template.findDistinct(
				new Query(), "address.country", C_LOCATIONS, String.class);
	}

	public List<String> searchAccomodationByCountry(String country, String searchTerms) {

		String[] terms = searchTerms.split(" ");
		MatchOperation searchByDescription = Aggregation.match(
				TextCriteria.forDefaultLanguage().matchingAny(terms));

		MatchOperation filterCountry = Aggregation.match(
				Criteria.where("address.country").is(country));

		ProjectionOperation propertyName = Aggregation.project("name")
				.andExclude("_id");

		SortOperation orderByName = Aggregation.sort(
				Sort.by(Direction.ASC,  "name"));

		Aggregation pipeline = Aggregation.newAggregation(
					searchByDescription, filterCountry, propertyName, orderByName);

		AggregationResults<Document> results = template.aggregate(
				pipeline, C_LOCATIONS, Document.class);

		List<String> nameList = new ArrayList<>();
		for (Document d: results)
			nameList.add(d.getString("name"));

		return nameList;
	}

}

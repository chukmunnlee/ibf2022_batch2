package ibf2022.batch2.paf.day36.repositories;

import java.util.Collections;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ibf2022.batch2.paf.day36.models.Restaurant;

@Repository
public class RestaurantRepository {

	public static final String C_RESTAURANT = "restaurant";

	@Autowired
	private MongoTemplate mongoTemplate;

	// db.restaurant.distinct("type_of_food")
	public List<String> getCuisines() {
		List<String> cuisines =  mongoTemplate.findDistinct(
			new Query(), "type_of_food" , C_RESTAURANT, String.class);
		Collections.sort(cuisines);
		return cuisines;
	}

	// db.restaurant.find({
	// 	type_of_food: {
	// 		$regex: 'cuisine', $options: "i"
	// 	}
	// })
	// .sort({ name: -1 })
	// .projection({ name: 1, address: 1, type_of_food: 1, _id: 0 })
	public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
		Criteria criteria = Criteria.where("type_of_food").regex(cuisine, "i");

		Query query = Query.query(criteria)
			.with(Sort.by(Direction.ASC, "name"));
		query.fields()
			.include("name", "address", "type_of_food", "rating")
			.exclude("_id");

		return mongoTemplate.find(query, Document.class, C_RESTAURANT)
			.stream()
			.map(doc -> doc.toJson())
			.map(Restaurant::toRestaurant)
			.toList();
	}
}

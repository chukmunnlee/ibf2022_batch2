package ibf2022.batch2.paf.day36.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.batch2.paf.day36.models.Restaurant;
import ibf2022.batch2.paf.day36.repositories.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepo;

	public List<String> getCuisines() {
		return restaurantRepo.getCuisines();
	}

	public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
		return restaurantRepo.getRestaurantsByCuisine(cuisine);
	}
}

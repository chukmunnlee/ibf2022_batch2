package ibf2022.batch2.paf.day36.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2022.batch2.paf.day36.models.Restaurant;
import ibf2022.batch2.paf.day36.services.RestaurantService;

@Controller
@RequestMapping
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantSvc;

	@GetMapping(path={ "/" })
	public String getCuisines(Model model) {
		List<String> cuisines = restaurantSvc.getCuisines();
		model.addAttribute("cuisines", cuisines);
		return "cuisines";
	}

	@GetMapping(path="/cuisines")
	public String getRestaurantByCuisinesQS(@RequestParam String cuisine, Model model) {
		return getRestaurantByCuisine(cuisine, model);
	}

	@GetMapping(path="/{cuisine}/restaurants")
	public String getRestaurantByCuisine(@PathVariable String cuisine, Model model) {

		List<Restaurant> restaurants = restaurantSvc.getRestaurantsByCuisine(cuisine);

		model.addAttribute("restaurants", restaurants);
		model.addAttribute("cuisine", cuisine);

		return "restaurants";
	}
}

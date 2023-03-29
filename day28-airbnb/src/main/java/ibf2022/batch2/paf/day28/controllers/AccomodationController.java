package ibf2022.batch2.paf.day28.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2022.batch2.paf.day28.repositories.AccomodationRepository;

@Controller
@RequestMapping
public class AccomodationController {

	@Autowired
	private AccomodationRepository accRepo;

	@GetMapping(path={"/", "/index.html"})
	public String getIndex(Model model) {
		List<String> countries = accRepo.getCountries();
		model.addAttribute("countries", countries);
		return "index";
	}

	@GetMapping(path="/search")
	public String search(Model model, @RequestParam MultiValueMap<String, String> form) {
		String searchTerms = form.getFirst("searchTerms");
		String country = form.getFirst("country");
		System.out.printf("++++ searchTerms: %s, countr: %s\n", searchTerms, country);

		List<String> results = accRepo.searchAccomodationByCountry(country, searchTerms);
		model.addAttribute("names", results);

		return "results";
	}
}

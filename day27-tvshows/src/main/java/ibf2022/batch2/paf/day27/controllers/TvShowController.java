package ibf2022.batch2.paf.day27.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2022.batch2.paf.day27.Utils;
import ibf2022.batch2.paf.day27.models.Comment;
import ibf2022.batch2.paf.day27.models.TvShow;
import ibf2022.batch2.paf.day27.services.TvShowService;

@Controller
@RequestMapping
public class TvShowController {

	@Autowired
	private TvShowService tvshowSvc;

	@GetMapping("/search")
	public String getTvShow(@RequestParam String showName, Model model) {

		Optional<TvShow> opt = tvshowSvc.find(showName);

		if (opt.isEmpty()) {
			model.addAttribute("message", "No TV show with the name '%s'".formatted(showName));
			return "notfound";
		}

		model.addAttribute("tvshow", opt.get());
		return "comment";
	}

	@PostMapping("/comment")
	//public String postComment(@RequestBody MultiValueMap<String, String> form, Model model) {
	public String postComment(@ModelAttribute Comment comment, Model model) {

		//Comment comment = Utils.toComment(form);
		//Comment comment = Utils.toComment(form);

		System.out.printf("---->> comment: %s\n", comment);

		tvshowSvc.insertComment(comment);

		return "redirect:/index.html";
	}
}

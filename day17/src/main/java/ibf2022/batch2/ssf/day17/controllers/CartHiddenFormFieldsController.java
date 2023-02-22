package ibf2022.batch2.ssf.day17.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(path="/hff/cart")
public class CartHiddenFormFieldsController {

	@PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String postMapping(@RequestBody MultiValueMap<String, String> form,
			Model model) {

		String name = form.getFirst("name");
		String item = form.getFirst("item");
		String previousCart = form.getFirst("previousCart");

		List<String> cart = new LinkedList<>();

		if (null != previousCart) {
			String[] items = previousCart.split(",");
			for (String i: items)
				cart.add(i);
		}

		cart.add(item);

		System.out.printf(">>> previousCart: %s\n", previousCart);

		String cartAsStr = cart.stream().collect(Collectors.joining(","));

		model.addAttribute("cart", cart);
		model.addAttribute("name", name);
		model.addAttribute("previousCart", cartAsStr);

		return "index-hff";
	}
}

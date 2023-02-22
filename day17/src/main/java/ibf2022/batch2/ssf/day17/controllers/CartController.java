package ibf2022.batch2.ssf.day17.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf2022.batch2.ssf.day17.services.CartService;

@Controller
@RequestMapping(path="/cart")
public class CartController {

	@Autowired
	private CartService cartSvc;

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postCart(@RequestBody MultiValueMap<String, String> form , Model model) {
        String item = form.getFirst("item");
        String name = form.getFirst("name");

		  // Get the item from the database
        List<String> cart = cartSvc.getFromCart(name);
        cart.add(item);

		  // Save back to database
		  cartSvc.addToCart(name, cart);

        model.addAttribute("cart", cart);
        model.addAttribute("name", name);
    
        return "index";
    }

    
}

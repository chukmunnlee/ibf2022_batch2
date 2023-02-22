package ibf2022.batch2.ssf.day17.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.batch2.ssf.day17.repositories.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepo;

	public void addToCart(String name, List<String> cart) {

		// convert the list to a CSV
		String items = cart.stream()
			.collect(Collectors.joining(","));

		System.out.printf(">>> service %s, %s\n", name, items);
		cartRepo.addToCart(name, items);
	}

	public List<String> getFromCart(String name) {
		Optional<String> opt = cartRepo.getFromCart(name);

		List<String> cart = new LinkedList<>();

		// If the box is empty, return an empty list
		if (opt.isEmpty())
			return cart;

		// Get the value from the box
		String value = opt.get();
		String[] items = value.split(",");
		for (int i = 0; i < items.length; i++)
			cart.add(items[i]);
		return cart;
	}
}

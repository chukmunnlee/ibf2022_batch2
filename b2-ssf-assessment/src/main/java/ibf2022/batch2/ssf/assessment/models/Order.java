package ibf2022.batch2.ssf.assessment.models;

import java.util.List;
import java.util.LinkedList;

public class Order {

	private List<Item> contents = new LinkedList<>();

	public void setContents(List<Item> contents) { this.contents = contents; }
	public List<Item> getContents() { return this.contents; }
	public void add(Item item) { 
		List<Item> found = this.contents.stream()
			.filter(i -> i.getItem().equals(item.getItem()))
			.toList();
		if (found.isEmpty())
			this.contents.add(item); 
		else 
			found.get(0).add(item.getQuantity());
	}

}

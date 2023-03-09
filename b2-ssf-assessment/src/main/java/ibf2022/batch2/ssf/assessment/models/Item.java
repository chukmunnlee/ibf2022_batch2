package ibf2022.batch2.ssf.assessment.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Item {

	@NotBlank(message="You must select an item")
	private String item;

	@Min(value=1, message="You must add atleast 1 item")
	private int quantity;

	public void setItem(String item) { this.item = item; }
	public String getItem() { return this.item; }

	public void setQuantity(int quantity) { this.quantity = quantity; }
	public int getQuantity() { return this.quantity; }
	public void add(int quantity) { this.quantity += quantity; }
	public void add() { this.quantity++; }

	@Override
	public String toString() {
		return "Item{item=%s, quantity=%d}".formatted(item, quantity);
	}
}

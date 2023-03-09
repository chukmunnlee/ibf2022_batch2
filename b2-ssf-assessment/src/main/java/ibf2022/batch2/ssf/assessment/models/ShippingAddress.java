package ibf2022.batch2.ssf.assessment.models;

import jakarta.validation.constraints.NotBlank;

public class ShippingAddress {

	@NotBlank(message="Please state your name")
	private String name;

	@NotBlank(message="Please specify the shipping address")
	private String address;

	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }

	public void setAddress(String address) { this.address = address; }
	public String getAddress() { return this.address; }

	@Override
	public String toString() {
		return "ShippingAddress{name=%s, address=%s}".formatted(name, address);
	}
}

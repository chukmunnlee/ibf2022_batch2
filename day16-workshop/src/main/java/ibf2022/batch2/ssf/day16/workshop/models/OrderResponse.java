package ibf2022.batch2.ssf.day16.workshop.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class OrderResponse {

	private String orderId;
	private String message;

	public void setOrderId(String orderId) { this.orderId = orderId; }
	public String getOrderId() { return this.orderId; }

	public void setMessage(String message) { this.message = message; }
	public String getMessage() { return this.message; }

	public JsonObject toJson() {
		return Json.createObjectBuilder()
			.add("orderId", orderId)
			.add("message", message)
			.build();
	}
}

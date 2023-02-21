package ibf2022.batch2.ssf.day16.workshop.models;

import java.util.LinkedList;
import java.util.List;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

public class Order {

    private String orderId;
    private String name;
    private String email;
    private String deliveryDate;
    private List<LineItem> lineItems = new LinkedList<>();

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(String deliveryDate) { this.deliveryDate = deliveryDate; }

    public List<LineItem> getLineItems() { return lineItems; }
    public void setLineItems(List<LineItem> lineItems) { this.lineItems = lineItems; }
    public void addLineItem(LineItem li) { this.lineItems.add(li); }

	 public static Order create(JsonObject json) {
		 Order order = new Order();

		 if (json.containsKey("orderId"))
			 order.setOrderId(json.getString("orderId"));

		 order.setName(json.getString("name"));
		 order.setEmail(json.getString("email"));
		 order.setDeliveryDate(json.getString("deliveryDate"));

		 JsonArray arr = json.getJsonArray("lineItems");
		 for (int i = 0; i < arr.size(); i++) {
			 JsonObject jo = arr.getJsonObject(i);
			 LineItem lineItem = LineItem.create(jo);
			 order.addLineItem(lineItem);
		 }

		 return order;
	 }
    
}

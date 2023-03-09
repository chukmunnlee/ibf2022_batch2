package ibf2022.batch2.ssf.assessment.services;

import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ibf2022.batch2.ssf.assessment.models.Invoice;
import ibf2022.batch2.ssf.assessment.models.Item;
import ibf2022.batch2.ssf.assessment.models.Order;
import ibf2022.batch2.ssf.assessment.models.Quotation;
import ibf2022.batch2.ssf.assessment.models.ShippingAddress;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class PurchaseOrderService {

	public static final String QUOTATION = "https://quotation.chuklee.com/quotation";

	public static final List<String> ITEMS = Arrays.asList(
		"apple", "orange", "bread", "cheese", "chicken",
		"mineral_water", "instant_noodles");

	public boolean isInString(Item item) {
		return ITEMS.stream()
			.anyMatch(v -> v.equals(item.getItem()));
	}

	public Quotation getQuotations(Order order) throws Exception {
		return getQuotations(
				order.getContents().stream()
					.map(v -> v.getItem())
					.toList()
				);
	}

	public Invoice createInvoice(ShippingAddress address, Order order, Quotation quotation) {

		float total = 0f;

		Invoice invoice = new Invoice();
		invoice.setInvoiceId(quotation.getQuoteId());

		for (Item item: order.getContents())
			total += quotation.getQuotation(item.getItem()) * item.getQuantity();
		invoice.setTotal(total);

		invoice.setShippingAddress(address);

		return invoice;
	}

	public Quotation getQuotations(List<String> order) throws Exception {
		JsonArrayBuilder arrBuilder = Json.createArrayBuilder(order);

		RequestEntity<String> req = RequestEntity.post(QUOTATION)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.body(arrBuilder.build().toString());

		ResponseEntity<String> resp;
		RestTemplate template = new RestTemplate();
		try {
			resp = template.exchange(req, String.class);
		} catch (Exception ex) {
			throw ex;
		}

		String payload = resp.getBody();
		JsonReader reader = Json.createReader(new StringReader(payload));
		JsonObject json = reader.readObject();

		Quotation quotation = new Quotation();
		quotation.setQuoteId(json.getString("quoteId"));

		json.getJsonArray("quotations").stream()
			.map(i -> i.asJsonObject())
			.forEach(i -> {
				quotation.addQuotation(i.getString("item"), (float)i.getJsonNumber("unitPrice").doubleValue());
			});

		return quotation;
	}


}

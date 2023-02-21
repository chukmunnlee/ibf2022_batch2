package ibf2022.batch2.ssf.day16;

import java.io.Reader;
import java.io.StringReader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;

@SpringBootApplication
public class Day16Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Day16Application.class, args);
	}

	@Override
	public void run(String... args) {

		// { "name": "fred", "email": "fred@gmail.com" }
		// JsonObject json = Json.createObjectBuilder()
		// 		.add("name", "fred")
		// 		.add("email", "fred@gmail.com")
		// 		.add("age", 30)
		// 		.add("subscribe", false)
		// 		.build();

		// System.out.printf("++++  json: %s\n", json.toString()); 

		// JsonArray array = Json.createArrayBuilder()
		// 	.add(json)
		// 	.add(json)
		// 	.build();

		// System.out.printf("++++  array: %s\n", array.toString()); 

		// String jsonStr = """
		// 	{ 
		// 		"orderId": 1234, "address": "10 Bedrock Ave",
		// 		"lineItems": [
		// 			{ "name": "apple", "quantity": 10 },
		// 			{ "name": "orage", "quantity": 20 }
		// 		]
		// 	}
		// """;
		// System.out.printf(">>>>> jsonStr: \n%s\n", jsonStr);

		// Reader reader = new StringReader(jsonStr);
		// JsonReader jsonReader = Json.createReader(reader);
		// json = jsonReader.readObject();

		// System.out.println("\n=================================");
		// System.out.printf("orderId: %d\n", json.getInt("orderId"));
		// System.out.printf("address: %s\n", json.getString("address"));
		// array = json.getJsonArray("lineItems");
		// for (int i = 0; i < array.size(); i++) {
		// 	json = array.getJsonObject(i);
		// 	System.out.printf("\tname: %s, quantity: %d\n"
		// 			, json.getString("name"), json.getInt("quantity"));
		// }
	}
}

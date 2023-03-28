package ibf2022.batch2.paf.serverstub.controllers;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@Controller
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class FundsTransferController {

	private Random rand = new SecureRandom();

	@PostMapping(path="/transfer")
	@ResponseBody
	public ResponseEntity<String> postTransfer() {

		String txId = Long.toHexString(rand.nextLong()).toString();


		JsonObject resp = Json.createObjectBuilder()
			.add("transactionId", txId)
			.build();
		return ResponseEntity.ok(resp.toString());

		/*
		JsonObject resp = Json.createObjectBuilder()
			.add("message", "some random error message")
			.build();
		return ResponseEntity.status(400).body(resp.toString());
		*/
	}
}

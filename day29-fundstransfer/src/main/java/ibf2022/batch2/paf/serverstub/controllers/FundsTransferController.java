package ibf2022.batch2.paf.serverstub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ibf2022.batch2.paf.serverstub.Utils;
import ibf2022.batch2.paf.serverstub.models.Transfer;
import ibf2022.batch2.paf.serverstub.services.FundsTransferException;
import ibf2022.batch2.paf.serverstub.services.FundsTransferService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@Controller
@RequestMapping(path="/api")
public class FundsTransferController {

	@Autowired
	private FundsTransferService fundsXferSvc;

	@PostMapping(path="/transfer", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> postTransfer(@RequestBody String payload) {

		System.out.printf(">>> payload: %s\n", payload);

		Transfer transfer = Utils.toTransfer(payload);

		if (!fundsXferSvc.accountExists(transfer.getFromAccount())) {
			JsonObject err = Json.createObjectBuilder()
					.add("message", "Account %s does not exits".formatted(transfer.getFromAccount()))
				.build();
			return ResponseEntity.status(400).body(err.toString());
		}
		if (!fundsXferSvc.accountExists(transfer.getToAccount())) {
			JsonObject err = Json.createObjectBuilder()
					.add("message", "Account %s does not exits".formatted(transfer.getToAccount()))
				.build();
			return ResponseEntity.status(400).body(err.toString());
		}

		try {
			String txId = fundsXferSvc.transfer(transfer);

			JsonObject resp = Json.createObjectBuilder()
				.add("transactionId", txId)
				.build();
			return ResponseEntity.ok(resp.toString());

		} catch (FundsTransferException ex) {
			JsonObject err = Json.createObjectBuilder()
				.add("message", ex.getMessage())
				.build();
			return ResponseEntity.status(400).body(err.toString());
		}

		// Transfer successful return the following JSON object
		// { "transactionId", "aTransactionId" }
		//
		// Transfer failed return the following JSON object
		// { "message", "Error message" }
	}
}

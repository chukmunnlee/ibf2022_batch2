package ibf2022.batch2.paf.serverstub;

import java.io.StringReader;
import java.util.Date;

import org.bson.Document;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import ibf2022.batch2.paf.serverstub.models.Account;
import ibf2022.batch2.paf.serverstub.models.Transfer;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Utils {

	public static Account toAccount(SqlRowSet rs) {
		Account account = new Account();
		account.setName(rs.getString("acct_name"));
		account.setBalance(rs.getFloat("balance"));
		return account;
	}

	public static Transfer toTransfer(String jsonStr) {
		Transfer transfer = new Transfer();
		JsonReader reader = Json.createReader(new StringReader(jsonStr));
		JsonObject json = reader.readObject();
		transfer.setFromAccount(json.getString("srcAcct"));
		transfer.setToAccount(json.getString("destAcct"));
		transfer.setAmount((float)json.getJsonNumber("amount").doubleValue());
		return transfer;
	}

	public static Document toDocument(Transfer transfer, String transactionId) {
		Document doc = new Document();
		doc.put("transactionId", transactionId);
		doc.put("date", new Date());
		doc.put("srcAcct", transfer.getFromAccount());
		doc.put("destAcct", transfer.getToAccount());
		doc.put("amount", transfer.getAmount());
		return doc;
	}
}

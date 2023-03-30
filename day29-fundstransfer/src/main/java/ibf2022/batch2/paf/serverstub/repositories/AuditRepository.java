package ibf2022.batch2.paf.serverstub.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.batch2.paf.serverstub.Utils;
import ibf2022.batch2.paf.serverstub.models.Transfer;

@Repository
public class AuditRepository {

	@Autowired
	private MongoTemplate template;

	public void audit(Transfer transfer, String transactionId) {

		Document doc = Utils.toDocument(transfer, transactionId);

		template.insert(doc, "fundstransfer");

	}
}

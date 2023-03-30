package ibf2022.batch2.paf.serverstub.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ibf2022.batch2.paf.serverstub.models.Transfer;
import ibf2022.batch2.paf.serverstub.repositories.AccountsRepository;
import ibf2022.batch2.paf.serverstub.repositories.AuditRepository;

@Service
public class FundsTransferService {

	@Autowired
	private AccountsRepository acctRepo;

	@Autowired
	private AuditRepository auditRepo;

	public boolean accountExists(String name) {
		return acctRepo.findAccountByName(name).isPresent();
	}

	@Transactional(rollbackFor = FundsTransferException.class)
	public String transfer(Transfer transfer) throws FundsTransferException {

		String txId = UUID.randomUUID().toString().substring(0, 8);

		acctRepo.withdraw(transfer.getFromAccount(), transfer.getAmount());

		//throw new FundsTransferException("Faking an error");
		acctRepo.deposit(transfer.getToAccount(), transfer.getAmount());

		auditRepo.audit(transfer, txId);

		return txId;
	}
}

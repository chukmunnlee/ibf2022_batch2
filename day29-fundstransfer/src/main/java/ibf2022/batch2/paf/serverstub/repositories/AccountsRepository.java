package ibf2022.batch2.paf.serverstub.repositories;

import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2022.batch2.paf.serverstub.Utils;
import ibf2022.batch2.paf.serverstub.models.Account;
import ibf2022.batch2.paf.serverstub.services.FundsTransferException;

@Repository
public class AccountsRepository {

	public static final String SQL_UPDATE_ACCOUNT = """
		update accounts set balance = balance + ? where acct_name = ?
		""";

	public static final String SQL_FIND_USER_BY_NAME = """
		select * from accounts where acct_name = ?
		""";

	@Autowired
	private JdbcTemplate template;

	public Optional<Account> findAccountByName(String name) {
		SqlRowSet rs = template.queryForRowSet(SQL_FIND_USER_BY_NAME, name);
		if (!rs.next())
			return Optional.empty();
		return Optional.of(Utils.toAccount(rs));
	}

	public void deposit(String acctName, float amount) throws FundsTransferException{
		updateBalance(acctName, amount);
	}
	public void withdraw(String acctName, float amount) throws FundsTransferException{
		updateBalance(acctName, -amount);
	}
	public void updateBalance(String acctName, float amount) throws FundsTransferException{
		if (template.update(SQL_UPDATE_ACCOUNT, amount, acctName) <= 0)
			throw new FundsTransferException("cannot update %s".formatted(acctName));
	}
}

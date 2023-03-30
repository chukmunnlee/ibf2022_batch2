package ibf2022.batch2.paf.serverstub.models;

public class Transfer {

	private String fromAcct;
	private String toAcct;
	private float amount;

	public void setFromAccount(String fromAcct) { this.fromAcct = fromAcct; }
	public String getFromAccount() { return this.fromAcct; }

	public void setToAccount(String toAcct) { this.toAcct = toAcct; }
	public String getToAccount() { return this.toAcct; }

	public void setAmount(float amount) { this.amount = amount; }
	public float getAmount() { return this.amount; }
}

package ibf2022.batch2.paf.serverstub.services;

import ibf2022.batch2.paf.serverstub.models.Transfer;

public class FundsTransferException extends Exception {

	private Transfer transferInfo;

	public FundsTransferException() {
		super();
	}

	public FundsTransferException(String msg) {
		super(msg);
	}

	public void setTransferInfo(Transfer transferInfo) { this.transferInfo = transferInfo; }
	public Transfer getTransferInfo() { return this.transferInfo; }

}

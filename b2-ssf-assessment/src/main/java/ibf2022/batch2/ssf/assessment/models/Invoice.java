package ibf2022.batch2.ssf.assessment.models;

public class Invoice {
	private String invoiceId;
	private ShippingAddress shippingAddress;
	private float total;

	public void setInvoiceId(String invoiceId) { this.invoiceId = invoiceId; }
	public String getInvoiceId() { return this.invoiceId; }

	public void setShippingAddress(ShippingAddress shippingAddress) { this.shippingAddress = shippingAddress; }
	public ShippingAddress getShippingAddress() { return this.shippingAddress; }

	public void setTotal(float total) { this.total = total; }
	public float getTotal() { return this.total; }
}

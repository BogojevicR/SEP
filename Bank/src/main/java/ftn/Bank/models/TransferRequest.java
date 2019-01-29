package ftn.Bank.models;

public class TransferRequest {
	
	private BankAccount buyer;
	private PaymentModel payment;
	public TransferRequest() {
		super();
	}
	public TransferRequest(BankAccount buyer, PaymentModel payment) {
		super();
		this.buyer = buyer;
		this.payment = payment;
	}
	public BankAccount getBuyer() {
		return buyer;
	}
	public void setBuyer(BankAccount buyer) {
		this.buyer = buyer;
	}
	public PaymentModel getPayment() {
		return payment;
	}
	public void setPayment(PaymentModel payment) {
		this.payment = payment;
	}
	@Override
	public String toString() {
		return "TransferRequest [buyer=" + buyer + ", payment=" + payment + "]";
	}
	
	
	
}

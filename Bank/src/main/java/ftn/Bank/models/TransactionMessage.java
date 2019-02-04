package ftn.Bank.models;

public class TransactionMessage {
	
	private String message;
	private Transaction transaction;
	public TransactionMessage() {
		super();
	}
	
	
	public TransactionMessage(String message, Transaction transaction) {
		super();
		this.message = message;
		this.transaction = transaction;
	}


	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	@Override
	public String toString() {
		return "TransactionMessage [message=" + message + ", transaction=" + transaction + "]";
	}
	
	
}

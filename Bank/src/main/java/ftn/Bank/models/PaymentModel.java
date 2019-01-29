package ftn.Bank.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class PaymentModel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long paymentId;
	@Column
	private String paymentUrl;
	@OneToOne
	private PaymentRequest paymentRequest;
	public PaymentModel() {
		super();
	}
	public long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}
	public String getPaymentUrl() {
		return paymentUrl;
	}
	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}
	public PaymentRequest getPaymentRequest() {
		return paymentRequest;
	}
	public void setPaymentRequest(PaymentRequest paymentRequest) {
		this.paymentRequest = paymentRequest;
	}
	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", paymentUrl=" + paymentUrl + ", paymentRequest=" + paymentRequest
				+ "]";
	}
	
	
}

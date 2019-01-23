package ftn.Bank.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class Payment implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long payment_id;
	@Column
	private String payment_url;
	@OneToOne
	private PaymentRequest payment_request;
	
	public Payment() {
		super();
	}
	public Payment(long payment_id, String payment_url, PaymentRequest payment_request) {
		super();
		this.payment_id = payment_id;
		this.payment_url = payment_url;
		this.payment_request = payment_request;
	}
	public long getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(long payment_id) {
		this.payment_id = payment_id;
	}
	public String getPayment_url() {
		return payment_url;
	}
	public void setPayment_url(String payment_url) {
		this.payment_url = payment_url;
	}
	public PaymentRequest getPayment_request() {
		return payment_request;
	}
	public void setPayment_request(PaymentRequest payment_request) {
		this.payment_request = payment_request;
	}
	@Override
	public String toString() {
		return "Payment [payment_id=" + payment_id + ", payment_url=" + payment_url + ", payment_request="
				+ payment_request + "]";
	}
	
	
	
	
}

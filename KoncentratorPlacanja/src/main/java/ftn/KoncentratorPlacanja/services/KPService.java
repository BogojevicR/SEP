package ftn.KoncentratorPlacanja.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.KoncentratorPlacanja.models.PaymentRequest;
import ftn.KoncentratorPlacanja.repositories.PaymentRequestRepository;


@Service
public class KPService {

	@Autowired
	private  PaymentRequestRepository paymentRequestRep;

	public  PaymentRequest getPaymentRequest(Long merchantId) {
		System.out.println(merchantId);
		PaymentRequest pr=paymentRequestRep.getOne(merchantId);
		return pr;
	}
	
	
	
}

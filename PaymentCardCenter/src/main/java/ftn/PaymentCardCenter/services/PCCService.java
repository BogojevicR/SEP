package ftn.PaymentCardCenter.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import ftn.PaymentCardCenter.models.MerchantAccount;
import ftn.PaymentCardCenter.models.PCCRequest;
import ftn.PaymentCardCenter.models.PCCResponse;
import ftn.PaymentCardCenter.repositories.MerchantAccountRepository;
import ftn.PaymentCardCenter.repositories.PCCRequestRepository;
import ftn.PaymentCardCenter.repositories.PaymentRequestRepository;

@Service
public class PCCService {
	
	@Autowired
	public PCCRequestRepository pccRequestRep;
	@Autowired
	public MerchantAccountRepository merchantAccountRep;
	@Autowired
	public PaymentRequestRepository paymentRequestRep;
	
	public void savePayment(PCCRequest paymentRequest) {
		pccRequestRep.save(paymentRequest);
	}
	
	public MerchantAccount getMerchantAccount(PCCResponse pccResponse) {
		
		return merchantAccountRep.getOne(paymentRequestRep.getOne(pccResponse.getMerchantOrderId()).getMerchantId());
		
	}
}

package org.scaler.paymentmicroservice.services;

import com.razorpay.RazorpayException;
import org.scaler.paymentmicroservice.paymentgateway.PaymentGateway;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    PaymentGateway paymentGateway;

    public PaymentService(PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }
    public String initiatePayment(Long orderId, Long amount) throws RazorpayException {
        return paymentGateway.generatePaymentLink(orderId, amount);
    }

}

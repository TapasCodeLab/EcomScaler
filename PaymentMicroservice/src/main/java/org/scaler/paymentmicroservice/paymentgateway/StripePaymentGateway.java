package org.scaler.paymentmicroservice.paymentgateway;

import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements PaymentGateway{
    @Override
    public String generatePaymentLink(Long orderId, Long amount) {
        //Make a call to Stripe Payment Gateway to generate this payment link
        return null;
    }
}

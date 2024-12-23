package org.scaler.paymentmicroservice.paymentgateway;

import com.razorpay.PaymentLink;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.json.JSONObject;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
@Primary
public class RazorPayPaymentGateway implements PaymentGateway{

    private RazorpayClient razorpayClient;

    public RazorPayPaymentGateway(RazorpayClient razorpayClient){
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String generatePaymentLink(Long orderId, Long amount) throws RazorpayException {
        //Make a call to RazorPay Payment Gateway to generate this payment link
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",amount);
        paymentLinkRequest.put("currency","INR");
//        paymentLinkRequest.put("accept_partial",true);
//        paymentLinkRequest.put("first_min_partial_amount",100);
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime TweentyMinsFromNow = currentDateTime.plusMinutes(20L);
        Long epocSeconds = TweentyMinsFromNow.toEpochSecond(ZoneOffset.UTC);
        paymentLinkRequest.put("expire_by",epocSeconds);
        paymentLinkRequest.put("reference_id",orderId.toString());
        paymentLinkRequest.put("description","Payment for policy order id: "+orderId.toString());
        JSONObject customer = new JSONObject();
        customer.put("name","+919168174455");
        customer.put("contact","Tapas Saha");
        customer.put("email","tapas.jsaha@gmail.com");
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);
        JSONObject notes = new JSONObject();
        notes.put("Order id",orderId.toString());
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://www.scaler.com/academy/mentee-dashboard/todos");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);

        return payment.toString();
    }
}

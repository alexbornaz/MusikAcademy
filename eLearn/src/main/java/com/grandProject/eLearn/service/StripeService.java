package com.grandProject.eLearn.service;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentMethod;
import com.stripe.model.Source;
import com.stripe.model.Token;
import com.stripe.param.PaymentMethodCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {
    @Value("${stripe.secret.key}")
    private String secretKey;

    public String createPaymentIntent(Long amount,String tokenId) throws StripeException {
        Stripe.apiKey = secretKey;
        //amount in dollars not cents
        Long newAmount = amount * 100;


        Token token = Token.retrieve(tokenId);

        Map<String, Object> paymentMethodParams = new HashMap<>();
        paymentMethodParams.put("type", "card");
        paymentMethodParams.put("card[token]", token.getId());

        PaymentMethod paymentMethod = PaymentMethod.create(paymentMethodParams);

        Map<String, Object> paymentIntentParams = new HashMap<>();
        paymentIntentParams.put("amount", newAmount);
        paymentIntentParams.put("currency", "usd");
        paymentIntentParams.put("payment_method", paymentMethod.getId());

        PaymentIntent paymentIntent = PaymentIntent.create(paymentIntentParams);
        paymentIntent.confirm();

        return paymentIntent.getId();
    }

}

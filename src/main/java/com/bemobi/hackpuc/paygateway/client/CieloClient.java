package com.bemobi.hackpuc.paygateway.client;

import com.bemobi.hackpuc.paygateway.client.model.*;
import com.bemobi.hackpuc.paygateway.dto.PaymentRequestDTO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by alessandro.santos on 7/21/17.
 */

@Component
public class CieloClient {

    @Value("${application.clielo.host}")
    private String host;

    private final String PAY_PATH = "1/sales/";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Gson gson;

    public PaymentResponse pay(PaymentRequestDTO request) {

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("MerchantId", request.getMerchantId());
        headers.set("MerchantKey", request.getMerchantKey());

        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setMerchantOrderId(request.getMerchantOrderId());

        Customer customer = new Customer();
        customer.setName(request.getCustomer().getName());
        paymentRequest.setCustomer(customer);

        Payment payment = new Payment();
        payment.setType(request.getPayment().getType());
        payment.setAmount(request.getPayment().getAmount());
        payment.setInstallments(request.getPayment().getInstallments());
        payment.setSoftDescriptor(request.getPayment().getSoftDescriptor());

        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber(request.getPayment().getCreditCard().getCardNumber());
        creditCard.setHolder(request.getPayment().getCreditCard().getHolder());
        creditCard.setExpirationDate(request.getPayment().getCreditCard().getExpirationDate());
        creditCard.setSecurityCode(request.getPayment().getCreditCard().getSecurityCode());
        creditCard.setBrand(request.getPayment().getCreditCard().getBrand());

        payment.setCreditCard(creditCard);
        paymentRequest.setPayment(payment);


        HttpEntity<PaymentResponse> entity = new HttpEntity(paymentRequest, headers);

        final ResponseEntity<String> responseEntity = restTemplate.postForEntity(host + PAY_PATH, entity, String.class);

        return gson.fromJson(responseEntity.getBody(), PaymentResponse.class);
    }




}

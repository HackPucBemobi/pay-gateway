package com.bemobi.hackpuc.paygateway.client;

import com.bemobi.hackpuc.paygateway.client.model.*;
import com.bemobi.hackpuc.paygateway.dto.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by alessandro.santos on 7/21/17.
 */

@Component
public class CieloClient {

    @Value("${application.clielo.host}")
    private String host;
    @Value("${application.clielo.tokenHost}")
    private String tokenHost;

    private final String SALES_PATH = "1/sales/";
    private final String CARD_PATH = "1/card/";

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

        final ResponseEntity<String> responseEntity = restTemplate.postForEntity(host + SALES_PATH, entity, String.class);

        return gson.fromJson(responseEntity.getBody(), PaymentResponse.class);
    }


    public TokenizeCreditCardResult tokenizeCreditCard(CreditCardDTO creditCardDTO, MerchantDTO merchantDTO) {

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("MerchantId", merchantDTO.getMerchantId());
        headers.set("MerchantKey", merchantDTO.getMerchantKey());

        final CreditCard creditCard = new CreditCard();
        creditCard.setCustomerName(creditCardDTO.getCustomerName());
        creditCard.setCardNumber(creditCardDTO.getCardNumber());
        creditCard.setHolder(creditCardDTO.getHolder());
        creditCard.setExpirationDate(creditCardDTO.getExpirationDate());
        creditCard.setBrand(creditCardDTO.getBrand());

        final HttpEntity<CreditCard> httpEntity = new HttpEntity(creditCard, headers);

        final ResponseEntity<String> result = restTemplate.postForEntity(host + CARD_PATH,
                httpEntity, String.class);

        return gson.fromJson(result.getBody(), TokenizeCreditCardResult.class);

    }

    public SimpleCreditCardDTO findCreditCardByToken(String token, MerchantDTO merchantDTO) {

        final HttpHeaders headers = new HttpHeaders();
        headers.set("MerchantId", merchantDTO.getMerchantId());
        headers.set("MerchantKey", merchantDTO.getMerchantKey());

        HttpEntity httpEntity = new HttpEntity(headers);

        ResponseEntity<String> entity = null;

        try {
            entity = restTemplate.exchange(tokenHost + CARD_PATH + token, HttpMethod.GET, httpEntity, String.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return null;
            }
            throw e;
        }
        return gson.fromJson(entity.getBody(), SimpleCreditCardDTO.class);
    }

    public PaymentResponse paymentByToken(PaymentRequestDTO request, MerchantDTO merchantDTO) {

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("MerchantId", merchantDTO.getMerchantId());
        headers.set("MerchantKey", merchantDTO.getMerchantKey());

        final PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setMerchantOrderId(request.getMerchantOrderId());

        final Customer customer = new Customer();
        customer.setName(request.getCustomer().getName());
        paymentRequest.setCustomer(customer);

        final Payment payment = new Payment();
        payment.setType(request.getPayment().getType());
        payment.setAmount(request.getPayment().getAmount());
        payment.setInstallments(request.getPayment().getInstallments());
        payment.setSoftDescriptor(request.getPayment().getSoftDescriptor());
        paymentRequest.setPayment(payment);

        final CreditCard creditCard = new CreditCard();
        creditCard.setCardToken(request.getPayment().getCreditCard().getCardToken());
        creditCard.setSecurityCode(request.getPayment().getCreditCard().getSecurityCode());
        creditCard.setBrand(request.getPayment().getCreditCard().getBrand());
        payment.setCreditCard(creditCard);

        final HttpEntity httpEntity = new HttpEntity(paymentRequest, headers);

        final ResponseEntity<String> responseEntity = restTemplate.postForEntity(host + SALES_PATH, httpEntity, String.class);

        return gson.fromJson(responseEntity.getBody(), PaymentResponse.class);
    }


}

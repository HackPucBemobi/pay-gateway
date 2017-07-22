package com.bemobi.hackpuc.paygateway.client;

import com.bemobi.hackpuc.paygateway.PayGatewayApplication;
import com.bemobi.hackpuc.paygateway.client.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * Created by alessandro.santos on 7/21/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PayGatewayApplication.class)
public class CieloClientTest {

    @Autowired
    private CieloClient cieloClient;


    @Test
    public void paySucessTest() throws Exception {

        PayRequest payRequest = new PayRequest();
        payRequest.setMerchantOrderId("2014111703");

        Customer customer = new Customer();
        customer.setName("Comprador crédito simples");
        payRequest.setCustomer(customer);

        Payment payment = new Payment();
        payment.setType("CreditCard");
        payment.setAmount(15700);
        payment.setInstallments(1);
        payment.setSoftDescriptor("123456789ABCD");
        payRequest.setPayment(payment);

        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber("4551870000000181");
        creditCard.setExpirationDate("12/2030");
        creditCard.setSecurityCode("123");
        creditCard.setBrand("Visa");
        payment.setCreditCard(creditCard);

        final PayResponse payResponse = cieloClient.pay(payRequest);

        assertEquals("Operation Successful", payResponse.getPayment().getReturnMessage());
    }

    @Test
    public void payFailTest() throws Exception {

        PayRequest payRequest = new PayRequest();
        payRequest.setMerchantOrderId("2014111703");

        Customer customer = new Customer();
        customer.setName("Comprador crédito simples");
        payRequest.setCustomer(customer);

        Payment payment = new Payment();
        payment.setType("CreditCard");
        payment.setAmount(15700);
        payment.setInstallments(1);
        payment.setSoftDescriptor("123456789ABCD");
        payRequest.setPayment(payment);

        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber("4551870000000182");
        creditCard.setExpirationDate("12/2030");
        creditCard.setSecurityCode("123");
        creditCard.setBrand("Visa");
        payment.setCreditCard(creditCard);

        final PayResponse payResponse = cieloClient.pay(payRequest);

        assertEquals("Not Authorized", payResponse.getPayment().getReturnMessage());
    }

}
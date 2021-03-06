package com.bemobi.hackpuc.paygateway.client;

import com.bemobi.hackpuc.paygateway.PayGatewayApplication;
import com.bemobi.hackpuc.paygateway.client.model.*;
import com.bemobi.hackpuc.paygateway.dto.CreditCardDTO;
import com.bemobi.hackpuc.paygateway.dto.CustomerDTO;
import com.bemobi.hackpuc.paygateway.dto.PaymentDTO;
import com.bemobi.hackpuc.paygateway.dto.PaymentRequestDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

        PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
        paymentRequestDTO.setMerchantOrderId("2014111703");
        paymentRequestDTO.setMerchantId("872fbdc6-4ff7-441a-94ce-536c3f1500f8");
        paymentRequestDTO.setMerchantKey("XPIUJIUHHVSIQWBPUMXRZJBERBOIAIOZNRRWCHEY");

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Comprador crédito simples");
        paymentRequestDTO.setCustomer(customerDTO);

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setType("CreditCard");
        paymentDTO.setAmount(15700);
        paymentDTO.setInstallments(1);
        paymentDTO.setSoftDescriptor("123456789ABCD");
        paymentRequestDTO.setPayment(paymentDTO);

        CreditCardDTO creditCardDTO = new CreditCardDTO();
        creditCardDTO.setCardNumber("4551870000000181");
        creditCardDTO.setExpirationDate("12/2030");
        creditCardDTO.setSecurityCode("123");
        creditCardDTO.setBrand("Visa");
        paymentDTO.setCreditCard(creditCardDTO);

        final PaymentResponse paymentResponse = cieloClient.pay(paymentRequestDTO);

        assertEquals("Operation Successful", paymentResponse.getPayment().getReturnMessage());
    }

    @Test
    public void payFailTest() throws Exception {

        PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
        paymentRequestDTO.setMerchantId("872fbdc6-4ff7-441a-94ce-536c3f1500f8");
        paymentRequestDTO.setMerchantKey("XPIUJIUHHVSIQWBPUMXRZJBERBOIAIOZNRRWCHEY");

        paymentRequestDTO.setMerchantOrderId("2014111703");

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Comprador crédito simples");
        paymentRequestDTO.setCustomer(customerDTO);

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setType("CreditCard");
        paymentDTO.setAmount(15700);
        paymentDTO.setInstallments(1);
        paymentDTO.setSoftDescriptor("123456789ABCD");
        paymentRequestDTO.setPayment(paymentDTO);

        CreditCardDTO creditCardDTO = new CreditCardDTO();
        creditCardDTO.setCardNumber("4551870000000182");
        creditCardDTO.setExpirationDate("12/2030");
        creditCardDTO.setSecurityCode("123");
        creditCardDTO.setBrand("Visa");
        paymentDTO.setCreditCard(creditCardDTO);

        final PaymentResponse paymentResponse = cieloClient.pay(paymentRequestDTO);

        assertEquals("Not Authorized", paymentResponse.getPayment().getReturnMessage());
    }

}
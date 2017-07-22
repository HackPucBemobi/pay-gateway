package com.bemobi.hackpuc.paygateway.controller;

import com.bemobi.hackpuc.paygateway.PayGatewayApplication;
import com.bemobi.hackpuc.paygateway.client.model.CreditCard;
import com.bemobi.hackpuc.paygateway.client.model.Customer;
import com.bemobi.hackpuc.paygateway.client.model.Payment;
import com.bemobi.hackpuc.paygateway.client.model.PaymentRequest;
import com.bemobi.hackpuc.paygateway.dto.CreditCardDTO;
import com.bemobi.hackpuc.paygateway.dto.CustomerDTO;
import com.bemobi.hackpuc.paygateway.dto.PaymentDTO;
import com.bemobi.hackpuc.paygateway.dto.PaymentRequestDTO;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by alessandro.santos on 7/21/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = PayGatewayApplication.class)
@Ignore
public class PaymentControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private PaymentController paymentController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(paymentController).build();
    }

    @Test
    public void paymentWithCreditCard() throws Exception {

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

        Gson gson = new Gson();
        final String json = gson.toJson(paymentRequestDTO);


        mockMvc.perform(post("/paymentDTO")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isCreated());



    }

}
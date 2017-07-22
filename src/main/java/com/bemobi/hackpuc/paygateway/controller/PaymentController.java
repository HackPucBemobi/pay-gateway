package com.bemobi.hackpuc.paygateway.controller;

import com.bemobi.hackpuc.paygateway.client.CieloClient;
import com.bemobi.hackpuc.paygateway.client.model.PaymentResponse;
import com.bemobi.hackpuc.paygateway.client.model.PaymentRequest;
import com.bemobi.hackpuc.paygateway.dto.PaymentRequestDTO;
import com.bemobi.hackpuc.paygateway.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by alessandro.santos on 7/21/17.
 */

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

    @Autowired
    private CieloClient cieloClient;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Result> paymentWithCreditCard(@Valid @RequestBody PaymentRequestDTO request, BindingResult bindingResult)
            throws URISyntaxException {

        if(!bindingResult.hasErrors()) {
            final PaymentResponse paymentResponse = cieloClient.pay(request);

            if(paymentResponse.getPayment().getReturnCode().equals("4")){
                return ResponseEntity.created(new URI("/payment")).body(new Result("success"));
            }
        }

        return ResponseEntity.ok().body(new Result("failed"));
    }
}

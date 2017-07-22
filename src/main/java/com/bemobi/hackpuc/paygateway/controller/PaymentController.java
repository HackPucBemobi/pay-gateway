package com.bemobi.hackpuc.paygateway.controller;

import com.bemobi.hackpuc.paygateway.client.CieloClient;
import com.bemobi.hackpuc.paygateway.client.model.PaymentResponse;
import com.bemobi.hackpuc.paygateway.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by alessandro.santos on 7/21/17.
 */

@RestController
public class PaymentController {

    @Autowired
    private CieloClient cieloClient;

    @PostMapping(value = "/payment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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


    @PostMapping(value = "/token", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenizeCreditCardResult> createToken(@Valid @RequestBody CreditCardDTO creditCardDTO, BindingResult bindingResult,
                                              @RequestHeader("MerchantId") String merchantId, @RequestHeader("MerchantKey") String merchantKey) throws URISyntaxException {

        if(!bindingResult.hasErrors()) {
            final TokenizeCreditCardResult result = cieloClient.tokenizeCreditCard(creditCardDTO, new MerchantDTO(merchantId, merchantKey));
            return ResponseEntity.created(new URI("/token")).body(result);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping(value = "/token/{tokenId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SimpleCreditCardDTO> getCreditCardByToken(@PathVariable("tokenId") String tokenId,
                @RequestHeader("MerchantId") String merchantId, @RequestHeader("MerchantKey") String merchantKey) throws URISyntaxException {

        final SimpleCreditCardDTO creditCard = cieloClient.findCreditCardByToken(tokenId, new MerchantDTO(merchantId, merchantKey));

        if(creditCard == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(creditCard);
    }

    @PostMapping(value = "/token/payment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Result> paymentByToken(@Valid @RequestBody PaymentRequestDTO request, BindingResult bindingResult,
                 @RequestHeader("MerchantId") String merchantId, @RequestHeader("MerchantKey") String merchantKey) throws URISyntaxException {

        if(!bindingResult.hasErrors()) {
            final PaymentResponse paymentResponse = cieloClient.paymentByToken(request, new MerchantDTO(merchantId, merchantKey));

            if(paymentResponse.getPayment().getReturnCode().equals("4")){
                return ResponseEntity.created(new URI("/token/payment")).body(new Result("success"));
            }
        }
        return ResponseEntity.ok().body(new Result("failed"));
    }
}

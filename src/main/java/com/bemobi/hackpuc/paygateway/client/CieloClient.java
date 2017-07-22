package com.bemobi.hackpuc.paygateway.client;

import com.bemobi.hackpuc.paygateway.client.model.PayRequest;
import com.bemobi.hackpuc.paygateway.client.model.PayResponse;
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

    public PayResponse pay(PayRequest request) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("MerchantId", "872fbdc6-4ff7-441a-94ce-536c3f1500f8");
        headers.set("MerchantKey", "XPIUJIUHHVSIQWBPUMXRZJBERBOIAIOZNRRWCHEY");

        HttpEntity<PayResponse> entity = new HttpEntity(request, headers);

        final ResponseEntity<String> responseEntity = restTemplate.postForEntity(host + PAY_PATH, entity, String.class);

        return gson.fromJson(responseEntity.getBody(), PayResponse.class);
    }




}

package com.bemobi.hackpuc.paygateway.dto;

import com.bemobi.hackpuc.paygateway.client.model.Customer;
import com.bemobi.hackpuc.paygateway.client.model.Payment;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by alessandro.santos on 7/22/17.
 */

@Data
public class PaymentRequestDTO {
    @SerializedName("merchantId")
    @Expose
    private String merchantId;
    @SerializedName("merchantKey")
    @Expose
    private String merchantKey;
    @SerializedName("merchantOrderId")
    @Expose
    private String merchantOrderId;
    @SerializedName("customer")
    @Expose
    private CustomerDTO customer;
    @SerializedName("payment")
    @Expose
    private PaymentDTO payment;
}

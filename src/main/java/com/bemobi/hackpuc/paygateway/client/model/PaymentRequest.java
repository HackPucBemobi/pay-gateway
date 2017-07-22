package com.bemobi.hackpuc.paygateway.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by alessandro.santos on 7/21/17.
 */

@Data
public class PaymentRequest {

    @SerializedName("MerchantOrderId")
    @Expose
    private String merchantOrderId;
    @SerializedName("Customer")
    @Expose
    private Customer customer;
    @SerializedName("Payment")
    @Expose
    private Payment payment;
}

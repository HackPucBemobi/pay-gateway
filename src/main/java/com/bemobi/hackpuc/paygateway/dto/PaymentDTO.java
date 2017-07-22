package com.bemobi.hackpuc.paygateway.dto;

import com.bemobi.hackpuc.paygateway.client.model.CreditCard;
import com.bemobi.hackpuc.paygateway.client.model.Link;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * Created by alessandro.santos on 7/22/17.
 */
@Data
public class PaymentDTO {
    @SerializedName("serviceTaxAmount")
    @Expose
    private Integer serviceTaxAmount;
    @SerializedName("installments")
    @Expose
    private Integer installments;
    @SerializedName("interest")
    @Expose
    private Integer interest;
    @SerializedName("capture")
    @Expose
    private Boolean capture;
    @SerializedName("authenticate")
    @Expose
    private Boolean authenticate;
    @SerializedName("recurrent")
    @Expose
    private Boolean recurrent;
    @SerializedName("creditCard")
    @Expose
    private CreditCardDTO creditCard;
    @SerializedName("tid")
    @Expose
    private String tid;
    @SerializedName("proofOfSale")
    @Expose
    private String proofOfSale;
    @SerializedName("authorizationCode")
    @Expose
    private String authorizationCode;
    @SerializedName("softDescriptor")
    @Expose
    private String softDescriptor;
    @SerializedName("provider")
    @Expose
    private String provider;
    @SerializedName("paymentId")
    @Expose
    private String paymentId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("receivedDate")
    @Expose
    private String receivedDate;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("returnCode")
    @Expose
    private String returnCode;
    @SerializedName("returnMessage")
    @Expose
    private String returnMessage;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;
}

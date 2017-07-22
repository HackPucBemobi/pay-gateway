package com.bemobi.hackpuc.paygateway.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * Created by alessandro.santos on 7/21/17.
 */

@Data
public class Payment {


    @SerializedName("ServiceTaxAmount")
    @Expose
    private Integer serviceTaxAmount;
    @SerializedName("Installments")
    @Expose
    private Integer installments;
    @SerializedName("Interest")
    @Expose
    private Integer interest;
    @SerializedName("Capture")
    @Expose
    private Boolean capture;
    @SerializedName("Authenticate")
    @Expose
    private Boolean authenticate;
    @SerializedName("Recurrent")
    @Expose
    private Boolean recurrent;
    @SerializedName("CreditCard")
    @Expose
    private CreditCard creditCard;
    @SerializedName("Tid")
    @Expose
    private String tid;
    @SerializedName("ProofOfSale")
    @Expose
    private String proofOfSale;
    @SerializedName("AuthorizationCode")
    @Expose
    private String authorizationCode;
    @SerializedName("SoftDescriptor")
    @Expose
    private String softDescriptor;
    @SerializedName("Provider")
    @Expose
    private String provider;
    @SerializedName("PaymentId")
    @Expose
    private String paymentId;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("Amount")
    @Expose
    private Integer amount;
    @SerializedName("ReceivedDate")
    @Expose
    private String receivedDate;
    @SerializedName("Currency")
    @Expose
    private String currency;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("ReturnCode")
    @Expose
    private String returnCode;
    @SerializedName("ReturnMessage")
    @Expose
    private String returnMessage;
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Links")
    @Expose
    private List<Link> links = null;
}

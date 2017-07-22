package com.bemobi.hackpuc.paygateway.client.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by alessandro.santos on 7/21/17.
 */

@Data
public class CreditCard {

    @SerializedName("CardNumber")
    @Expose
    private String cardNumber;
    @SerializedName("Holder")
    @Expose
    private String holder;
    @SerializedName("ExpirationDate")
    @Expose
    private String expirationDate;
    @SerializedName("SaveCard")
    @Expose
    private Boolean saveCard;
    @SerializedName("Brand")
    @Expose
    private String brand;
    @SerializedName("SecurityCode")
    @Expose
    private String securityCode;
}

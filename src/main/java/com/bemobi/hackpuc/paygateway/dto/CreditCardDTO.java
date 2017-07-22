package com.bemobi.hackpuc.paygateway.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by alessandro.santos on 7/22/17.
 */
@Data
public class CreditCardDTO {

    @SerializedName("cardNumber")
    @Expose
    private String cardNumber;
    @SerializedName("holder")
    @Expose
    private String holder;
    @SerializedName("expirationDate")
    @Expose
    private String expirationDate;
    @SerializedName("saveCard")
    @Expose
    private Boolean saveCard;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("securityCode")
    @Expose
    private String securityCode;
}

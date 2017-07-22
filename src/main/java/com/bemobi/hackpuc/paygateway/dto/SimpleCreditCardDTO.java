package com.bemobi.hackpuc.paygateway.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by alessandro.santos on 7/22/17.
 */

@Data
public class SimpleCreditCardDTO {
    @SerializedName("CardNumber")
    @Expose
    private String cardNumber;

    @SerializedName("Holder")
    @Expose
    private String holder;

    @SerializedName("ExpirationDate")
    @Expose
    private String expirationDate;
}

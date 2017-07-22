package com.bemobi.hackpuc.paygateway.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by alessandro.santos on 7/21/17.
 */

@Data
public class Customer {

    @SerializedName("Name")
    @Expose
    private String name;
}

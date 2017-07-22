package com.bemobi.hackpuc.paygateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by alessandro.santos on 7/22/17.
 */
@Data
@AllArgsConstructor
public class MerchantDTO {

    private String merchantId;
    private String merchantKey;
}

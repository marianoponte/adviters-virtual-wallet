package com.adviters.virtualwallet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuoteBtcResponse {

    private String lprice;

    private String curr1;

    private String curr2;
}

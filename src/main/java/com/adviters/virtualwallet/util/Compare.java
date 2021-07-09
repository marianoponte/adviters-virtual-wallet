package com.adviters.virtualwallet.util;

import com.adviters.virtualwallet.model.Quotation;

public class Compare {

    public static Quotation max(Quotation a, Quotation b) {
        return a.getPrice() > b.getPrice() ? a : b;
    }
}

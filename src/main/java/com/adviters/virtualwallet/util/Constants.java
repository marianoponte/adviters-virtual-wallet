package com.adviters.virtualwallet.util;

import java.math.BigDecimal;

public class Constants {

    //Constante que define el valor inicial de las cuentas asociadas a la billetera
    public static final BigDecimal INIT_AMOUNT = new BigDecimal("0.00");

    //Constante que define el tipo de transaccion de acumulo
    public static final String TRANSACTION_TYPE_ACU = "ACU";

    //Constante que define el tipo de transaccion de extraccion
    public static final String TRANSACTION_TYPE_EXT = "EXT";

    //URL cotizacion BTC en base al dolar
    public static final String URL_BTC_QUOTE = "https://cex.io/api/last_price/BTC/USD";
}

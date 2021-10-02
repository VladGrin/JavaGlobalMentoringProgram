package com.concurrency.task5.model;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class ExchangeRates {

    private static final Map<Currency, Map<Currency, BigDecimal>> EXCHANGE_RATES;

    static {
        EXCHANGE_RATES = new EnumMap<Currency, Map<Currency, BigDecimal>>(Currency.class) {{
            put(Currency.UAH, new EnumMap<Currency, BigDecimal>(Currency.class) {{
                put(Currency.UAH, BigDecimal.ONE);
                put(Currency.USD, BigDecimal.valueOf(0.038));
                put(Currency.EUR, BigDecimal.valueOf(0.032));
            }});
            put(Currency.EUR, new HashMap<Currency, BigDecimal>() {{
                put(Currency.UAH, BigDecimal.valueOf(30.88));
                put(Currency.USD, BigDecimal.valueOf(1.16));
                put(Currency.EUR, BigDecimal.ONE);
            }});
            put(Currency.USD, new HashMap<Currency, BigDecimal>() {{
                put(Currency.UAH, BigDecimal.valueOf(26.63));
                put(Currency.USD, BigDecimal.ONE);
                put(Currency.EUR, BigDecimal.valueOf(0.86));
            }});
        }};
    }

    public BigDecimal getExchangeRateByBaseAndTargetCurrency(Currency baseCurrency, Currency targetCurrency) {
        return EXCHANGE_RATES.get(baseCurrency).get(targetCurrency);
    }
}

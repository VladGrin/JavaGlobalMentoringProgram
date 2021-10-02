package com.concurrency.task5.util;

import com.concurrency.task5.model.Currency;

import java.math.BigDecimal;
import java.util.*;

public class Utils {

    private Utils() {
    }

    public static String createFilePath(long accountId) {
        return "account" + accountId + ".txt";
    }

    public static List<String> formatCurrencyValues(Map<Currency, BigDecimal> currencyValues) {
        Objects.requireNonNull(currencyValues);
        List<String> values = new ArrayList<>(currencyValues.size());
        for (Map.Entry<Currency, BigDecimal> entry : currencyValues.entrySet()) {
            values.add(entry.getKey() + ":" + entry.getValue());
        }
        return values;
    }

    public static Map<Currency, BigDecimal> formatCurrencyValues(List<String> values) {
        Map<Currency, BigDecimal> currencyValues = new EnumMap<>(Currency.class);
        for (String value : values) {
            String[] val = value.split(":");
            currencyValues.put(Currency.valueOf(val[0]), BigDecimal.valueOf(Double.parseDouble(val[1])));
        }
        return currencyValues;
    }
}

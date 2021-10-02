package com.concurrency.task5.model;

import com.concurrency.task5.util.AccountException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum Currency {
    EUR("EUR"),
    UAH("UAH"),
    USD("USD");

    private final String symbol;

    Currency(final String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    private static final Map<String, Currency> lookup = new HashMap<>();

    static {
        for (Currency c : Currency.values()) {
            lookup.put(c.toString(), c);
        }
    }

    public static Currency get(String symbol) throws AccountException {
        Currency currency = lookup.get(symbol);
        if (Objects.isNull(currency)) {
            throw new AccountException("No such currency");
        }
        return currency;
    }
}

package com.concurrency.task5.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Account {

    private final long accountId;
    private final Map<Currency, BigDecimal> currencyValues;
}

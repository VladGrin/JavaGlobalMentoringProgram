package com.concurrency.task5.util;

import com.concurrency.task5.model.Currency;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AccountValidator {

    private AccountValidator() {
    }

    public static void validIsAccountExist(List<String> allAccountIds, String accountId) throws AccountException {
        if (!allAccountIds.contains(accountId)) {
            throw new AccountException("No such account id");
        }
    }

    public static void validBaseAmount(Currency baseCurr, Currency targetCurr, Map<Currency, BigDecimal> currencyValues, String amount) throws AccountException {
        if (baseCurr == targetCurr) {
            throw new AccountException("Base and target currencies the same. Not sense exchange smth.");
        }
        BigDecimal value = currencyValues.get(baseCurr);
        if (Objects.isNull(value)) {
            throw new AccountException("Account do not have money by base currency.");
        }
        try {
            BigDecimal amountMoney = BigDecimal.valueOf(Double.parseDouble(amount));
            if (value.compareTo(amountMoney) < 0) {
                throw new AccountException("Account do not have enough money by base currency.");
            }
        } catch (NumberFormatException e) {
            throw new AccountException("Incorrect value for amount of money");
        }
    }
}

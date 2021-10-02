package com.concurrency.task5.service.impl;

import com.concurrency.task5.dao.AccountDao;
import com.concurrency.task5.model.Account;
import com.concurrency.task5.model.Currency;
import com.concurrency.task5.model.ExchangeRates;
import com.concurrency.task5.service.AccountService;
import com.concurrency.task5.util.AccountException;
import com.concurrency.task5.util.AccountValidator;
import com.concurrency.task5.util.Utils;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AccountServiceImpl implements AccountService {

    private static final Logger LOG = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountDao accountDao;
    private final ExchangeRates exchangeRates;

    public AccountServiceImpl(AccountDao accountDao, ExchangeRates exchangeRates) {
        this.accountDao = accountDao;
        this.exchangeRates = exchangeRates;
    }

    @Override
    public Account findAccountByAccountId(long accountId) throws AccountException {
        String filePath = Utils.createFilePath(accountId);
        List<String> values = accountDao.findAllCurrencyValuesByAccount(filePath);
        Map<Currency, BigDecimal> currencyValues = Utils.formatCurrencyValues(values);

        Account account = new Account(accountId, currencyValues);
        LOG.info("Found account {}", account);
        return account;
    }

    @Override
    public List<String> getAllAccountIds() throws AccountException {
        final List<String> accounts = accountDao.getAllAccounts().stream()
                .map(str -> str.replace("account", ""))
                .map(str -> str.replace(".txt", ""))
                .collect(Collectors.toList());
        LOG.info("Found account ids: {}", accounts);
        return accounts;
    }

    @Override
    public void exchangeCurrency(@NonNull String baseCurrency, @NonNull String targetCurrency,
                                 @NonNull Account account, @NonNull String amount) throws AccountException {
        LOG.info("Base currency: {}, target currency: {}, amount money:{}", baseCurrency, targetCurrency, amount);

        Map<Currency, BigDecimal> currencyValues = account.getCurrencyValues();

        Currency baseCurr = Currency.get(baseCurrency);
        Currency targetCurr = Currency.get(targetCurrency);

        AccountValidator.validBaseAmount(baseCurr, targetCurr, currencyValues, amount);

        BigDecimal baseValue = currencyValues.get(baseCurr);
        BigDecimal targetValue = currencyValues.get(targetCurr);
        BigDecimal amountMoney = BigDecimal.valueOf(Double.parseDouble(amount));
        currencyValues.put(baseCurr, baseValue.subtract(amountMoney));
        BigDecimal rate = exchangeRates.getExchangeRateByBaseAndTargetCurrency(baseCurr, targetCurr);
        currencyValues.put(targetCurr, targetValue.add(amountMoney.multiply(rate)));

        Account newAccount = new Account(account.getAccountId(), currencyValues);
        LOG.info("New account for saving: {}", newAccount);
        save(newAccount);
    }

    private void save(Account account) throws AccountException {
        String filePath = Utils.createFilePath(account.getAccountId());
        List<String> values = Utils.formatCurrencyValues(account.getCurrencyValues());

        accountDao.save(filePath, values);
    }
}

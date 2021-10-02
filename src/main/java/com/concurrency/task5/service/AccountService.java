package com.concurrency.task5.service;

import com.concurrency.task5.model.Account;
import com.concurrency.task5.util.AccountException;

import java.util.List;

public interface AccountService {

    Account findAccountByAccountId(long accountId) throws AccountException;

    List<String> getAllAccountIds() throws AccountException;

    void exchangeCurrency(String baseCurrency, String targetCurrency, Account account, String amount) throws AccountException;
}

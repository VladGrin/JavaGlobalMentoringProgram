package com.concurrency.task5.dao;

import com.concurrency.task5.util.AccountException;

import java.util.List;

public interface AccountDao {

    void save(String file, List<String> currencyValues) throws AccountException;

    List<String> findAllCurrencyValuesByAccount(String file) throws AccountException;

    List<String> getAllAccounts() throws AccountException;
}

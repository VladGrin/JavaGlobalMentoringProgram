package com.concurrency.task5.ui;

import com.concurrency.task5.dao.impl.AccountDaoImpl;
import com.concurrency.task5.model.Account;
import com.concurrency.task5.model.ExchangeRates;
import com.concurrency.task5.service.AccountService;
import com.concurrency.task5.service.impl.AccountServiceImpl;
import com.concurrency.task5.util.AccountException;
import com.concurrency.task5.util.AccountValidator;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class AccountUI {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();
    private final ReentrantLock locking = new ReentrantLock();
    private final AccountService service;

    public AccountUI() {
        AccountDaoImpl accountDao = new AccountDaoImpl();
        ExchangeRates exchangeRates = new ExchangeRates();
        this.service = new AccountServiceImpl(accountDao, exchangeRates);
    }

    public synchronized void exchangeCurrency() {
        while (true) {
            List<String> allAccountIds = null;
            try {
                allAccountIds = service.getAllAccountIds();
            } catch (AccountException e) {
                System.out.println(e.getMessage());
                continue;
            }
            System.out.println("Available account ids: " + allAccountIds + ". Select an account: ");
            String accountId = SCANNER.next();
            try {
                AccountValidator.validIsAccountExist(allAccountIds, accountId);
            } catch (AccountException e) {
                System.out.println(e.getMessage());
                continue;
            }
            long accId = Long.parseLong(accountId);
            Account account = null;
            try {
                account = service.findAccountByAccountId(accId);
            } catch (AccountException e) {
                System.out.println(e.getMessage());
                continue;
            }
            System.out.println("Available amount by currency:" + account.getCurrencyValues());
            System.out.println("Specify the base currency: ");
            String baseCurrency = SCANNER.next();
            System.out.println("Specify the target currency: ");
            String targetCurrency = SCANNER.next();
            System.out.println("Enter the amount of money to exchange: ");
            final String amount = SCANNER.next();
            try {
                service.exchangeCurrency(baseCurrency, targetCurrency, account, amount);
            } catch (AccountException e) {
                System.out.println(e.getMessage());
                continue;
            }
            System.out.println("Press 'Q' for exit or 'C' for continue");
            if (SCANNER.next().equalsIgnoreCase("Q")) break;
        }
    }

    public void hardcore() {
        try {
            locking.lock();
            Account account = service.findAccountByAccountId(1);
            String value = String.valueOf(1 + RANDOM.nextInt(5));
            service.exchangeCurrency(getRandomCurrency(), getRandomCurrency(), account, value);
        } catch (AccountException e) {
            e.printStackTrace();
        } finally {
            locking.unlock();
        }
    }

    private String getRandomCurrency() {
        switch (new Random().nextInt(3)) {
            case 0:
                return "UAH";
            case 1:
                return "EUR";
            default:
                return "USD";
        }
    }
}

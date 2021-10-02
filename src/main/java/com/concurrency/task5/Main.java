package com.concurrency.task5;

import com.concurrency.task5.ui.AccountUI;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        AccountUI accountUI = new AccountUI();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(new Exchanger(accountUI));
        executor.submit(new Exchanger(accountUI));

        TimeUnit.MINUTES.sleep(10);
        executor.shutdown();
    }
}
class Exchanger implements Runnable {

    private final AccountUI accountUI;

    public Exchanger(AccountUI accountUI) {
        this.accountUI = accountUI;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            accountUI.hardcore();
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
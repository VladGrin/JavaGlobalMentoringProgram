package com.concurrency.test;

public class TestJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new MyRunnable(), "t1");
        Thread t2 = new Thread(new MyRunnable(), "t2");
        Thread t3 = new Thread(new MyRunnable(), "t3");

        t1.start();
        //стартуем второй поток только после 2-секундного ожидания первого потока (или когда он умрет/закончит выполнение)
        t1.join(2000);
        t2.start();

        //стартуем 3-й поток только после того, как 1 поток закончит свое выполнение
        t1.join();

        t3.start();

        //даем всем потокам возможность закончить выполнение перед тем, как программа (главный поток) закончит свое выполнение
        t1.join();
        t2.join();
        t3.join();

        System.out.println("Все потоки отработали, завершаем программу");
    }
}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Поток начал работу:::" + Thread.currentThread().getName());
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Поток отработал:::" + Thread.currentThread().getName());
    }

}
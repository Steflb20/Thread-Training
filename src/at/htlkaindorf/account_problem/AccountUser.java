package at.htlkaindorf.account_problem;

import java.util.Random;

public class AccountUser implements Runnable {

    private final Account account;
    private Random random;

    public AccountUser(Account account) {
        this.account = account;
        this.random = new Random();
    }

    @Override
    public void run() {

        System.out.printf("%s started\n", Thread.currentThread().getName());

        OUTER:
        for (int i = 0; i < 20; i++) {
            synchronized (this.account) {
                boolean deposit = random.nextBoolean();
                double value = random.nextInt(10000) + 1 / 100.0;

                if (deposit) {
                    this.account.makeDeposit(value);
                    System.out.printf("%s made deposit\n", Thread.currentThread().getName());
                } else {
                    int count = 0;

                    while (this.account.getMoney() - value < 0) {
                        try {
                            System.out.printf("%s has to wait\n", Thread.currentThread().getName());
                            this.account.wait(1000);
                            count++;

                            if (count == 5) {
                                System.out.printf("%s stopped because of deadlock!\n", Thread.currentThread().getName());
                                break OUTER;
                            }
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    System.out.printf("%s made withdraw\n", Thread.currentThread().getName());
                    this.account.makeWithdraw(value);
                }
            }

            if (i % 2 == 0) {
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        System.out.printf("%s finished\n", Thread.currentThread().getName());
    }
}

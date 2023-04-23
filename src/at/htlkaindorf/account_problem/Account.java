package at.htlkaindorf.account_problem;

public class Account {
    private double money;

    public Account(double money) {
        this.money = money;
    }

    public void makeWithdraw(double value) {
        this.money -= value;
    }

    public void makeDeposit(double value) {
        this.money += value;
    }

    public double getMoney() {
        return money;
    }
}

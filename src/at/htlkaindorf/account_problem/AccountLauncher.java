package at.htlkaindorf.account_problem;

public class AccountLauncher {
    public void launch() {
        Account account = new Account(200);

        for (int i = 0; i < 5; i++) {
            new Thread(new AccountUser(account), String.format("User%02d", i)).start();
        }
    }

    public static void main(String[] args) {
        new AccountLauncher().launch();
    }
}

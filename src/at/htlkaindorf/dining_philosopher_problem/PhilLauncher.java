package at.htlkaindorf.dining_philosopher_problem;

public class PhilLauncher {
    public void launch() {
        boolean[] forks = { false, false, false, false, false };

        for (int i = 0; i < 5; i++) {
            new Thread(new DiningPhil(i, forks)).start();
        }
    }

    public static void main(String[] args) {
        new PhilLauncher().launch();
    }
}

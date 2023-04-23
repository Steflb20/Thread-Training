package at.htlkaindorf.highway;

public class Main {
    public static void main(String[] args) {
        Toilbooth toilbooth = new Toilbooth();

        for (int i = 0; i < 7; i++) {
            Car car = new Car(toilbooth);
            new Thread(car, String.format("Car%02d", i + 1)).start();
        }
    }
}

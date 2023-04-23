package at.htlkaindorf.car_problem.city;

public class Main {
    public static void main(String[] args) {
        ParkingArea area = new ParkingArea();

        for (int i = 0; i < 5; i++) {
            Car car = new Car(area, 2);
            new Thread(car, String.format("Car%02d", i + 1)).start();
        }
    }
}

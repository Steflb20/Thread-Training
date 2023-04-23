package at.htlkaindorf.car_problem.city;

public class Car implements Runnable {

    private final ParkingArea area;
    private int count;

    public Car(ParkingArea area, int count) {
        this.area = area;
        this.count = count;
    }

    @Override
    public void run() {
        System.out.printf("%s started\n", Thread.currentThread().getName());

        for (int i = 0; i < count; i++) {
            synchronized (this.area) {
                int index;

                while (area.getFirstFreePlace() == -1) {
                    try {
                        this.area.wait();
                        System.out.printf("%s has to wait...\n", Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                index = area.getFirstFreePlace();

                this.area.updateStatusOfPlace(index, true);
                System.out.printf("Place %d is occupied by %s\n", index + 1, Thread.currentThread().getName());

                // Tell the other cars to find the next one
                try {
                    this.area.wait(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                this.area.updateStatusOfPlace(index, false);
                System.out.printf("Place %d is is free!\n", index + 1);

                this.area.notifyAll();
            }

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.printf("%s finished!\n", Thread.currentThread().getName());
    }
}

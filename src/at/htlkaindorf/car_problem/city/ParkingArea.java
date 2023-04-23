package at.htlkaindorf.car_problem.city;

public class ParkingArea {
    private static final int PLACES = 3;

    private ParkingPlace[] area;

    public ParkingArea() {
        this.area = new ParkingPlace[PLACES];

        for (int i = 1; i <= PLACES; i++) {
            this.area[i - 1] = new ParkingPlace(String.format("P%02d", i), false);
        }
    }

    public int getFirstFreePlace() {
        int index = -1;

        for (int i = 0; i < area.length; i++) {
            if (!this.area[i].isOccupied()) {
                index = i;
                break;
            }
        }
        
        return index;
    }

    public void updateStatusOfPlace(int index, boolean occupied) {
        this.area[index].setOccupied(occupied);
    }
}
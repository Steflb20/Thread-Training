package at.htlkaindorf.car_problem.city;

public class ParkingPlace {
    private String name;
    private boolean occupied;

    public ParkingPlace(String name, boolean occupied) {
        this.name = name;
        this.occupied = occupied;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}

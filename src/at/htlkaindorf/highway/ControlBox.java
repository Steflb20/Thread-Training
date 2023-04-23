package at.htlkaindorf.highway;

public class ControlBox {
    private String name;
    private boolean free;

    public ControlBox(String name, boolean free) {
        this.name = name;
        this.free = free;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }
}
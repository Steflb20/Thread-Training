package at.htlkaindorf.highway;

public class Toilbooth {
    private static final int SIZE = 4;

    private ControlBox[] controlBoxes;

    public Toilbooth() {
        this.controlBoxes = new ControlBox[SIZE];

        for (int i = 0; i < this.controlBoxes.length; i++) {
            this.controlBoxes[i] = new ControlBox(
                    String.format("Box%02d", i + 1),
                    true
            );
        }
    }

    public int searchForFreeBox() {
        int index = -1;

        for (int i = 0; i < this.controlBoxes.length; i++) {
            if (this.controlBoxes[i].isFree()) {
                index = i;
                break;
            }
        }

        return index;
    }

    public void setStatusOfBox(int index, boolean isFree) {
        this.controlBoxes[index].setFree(isFree);
    }
}

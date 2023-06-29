public class Field {
    int fieldXStart;
    int fieldYStart;
    int sizeX;
    int sizeY;
    int size = 1;

    int sizeOfCell;

    public Field(Frame frame) {
        this.sizeX = this.size * 12;
        this.sizeY = this.size * 11;

        this.fieldXStart = frame.getWidth() / 20;
        this.fieldYStart = frame.getHeight() / 10 + frame.getHeight() / 22;

        this.sizeOfCell = (frame.getWidth() - frame.getWidth() / 10) / sizeX;
    }
}

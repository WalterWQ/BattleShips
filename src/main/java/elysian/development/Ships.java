package elysian.development;

public class Ships {
    private int length;
    private boolean vertical; // true = vertical, false = horizontal
    private int startX;
    private int startY;

    public Ships(int length, boolean vertical, int startX, int startY) {
        this.length = length;
        this.vertical = vertical;
        this.startX = startX;
        this.startY = startY;
    }

    public int getLength() { return length; }
    public boolean isVertical() { return vertical; }
    public int getStartX() { return startX; }
    public int getStartY() { return startY; }
}
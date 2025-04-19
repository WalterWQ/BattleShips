package elysian.development;

public class Move {
    private int row;
    private int col;
    private boolean hit;

    public Move(int row, int col, boolean hit) {
        this.row = row;
        this.col = col;
        this.hit = hit;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public boolean wasHit() { return hit; }

    @Override
    public String toString() {
        return "Position move at (" + (row + 1) + ", " + (col + 1) + ") - " + (hit ? "HIT" : "MISS");
    }
}
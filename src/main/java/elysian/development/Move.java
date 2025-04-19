package elysian.development;

public class Move {
    private int row;
    private int col;
    private boolean hit;
    private char previousTile;

    public Move(int row, int col, boolean hit, char previousTile) {
        this.row = row;
        this.col = col;
        this.hit = hit;
        this.previousTile = previousTile;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public boolean getHit() { return hit; }
    public char getPreviousTile() { return previousTile; }

    @Override
    public String toString() {
        return "Position move at (" + (row + 1) + ", " + (col + 1) + ") - " + (hit ? "HIT" : "MISS");
    }
}
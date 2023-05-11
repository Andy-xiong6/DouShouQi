package model;

/**
 * This class represents positions on the checkerboard, such as (0, 0), (0, 7), and so on
 * Where, the upper left corner is (0, 0), the lower left corner is (7, 0), the upper right corner is (0, 7), and the lower right corner is (7, 7).
 */
public class ChessboardPoint {
    private final int row;
    private final int col;
    private boolean isTrap = false;
    private boolean isDen = false;
    private boolean isRiver = false;
    private boolean isBlueSide = false;

    public boolean isBlueSide() {
        return isBlueSide;
    }

    public void setBlueSide(boolean blueSide) {
        isBlueSide = blueSide;
    }

    public boolean isRiver() {
        return isRiver;
    }


    public boolean isTrap() {
        return isTrap;
    }

    public boolean isDen() {
        return isDen;
    }

    public ChessboardPoint(int row, int col) {
        this.row = row;
        this.col = col;
        // set traps
        if((row == 0 && col == 2) || (row == 0 && col == 4) || row == 1 && col == 3){
            isTrap = true;
            isBlueSide = false;
        }else if ((row == 8 && col == 2) || (row == 8 && col == 4) || row == 7 && col == 3){
            isTrap = true;
            isBlueSide = true;
        }
        
        // set dens
        if(row == 0 && col == 3){
            isDen = true;
            isBlueSide = false;

        }else if(row == 8 && col == 3){
            isDen = true;
            isBlueSide = true;
        }

        // set rivers
        if((row == 3 && col == 1) || (row == 3 && col == 2) || (row == 3 && col == 4) || (row == 3 && col == 5) ||
                (row == 4 && col == 1) || (row == 4 && col == 2) || (row == 4 && col == 4) || (row == 4 && col == 5) ||
                (row == 5 && col == 1) || (row == 5 && col == 2) || (row == 5 && col == 4) || (row == 5 && col == 5)){
            isRiver = true;
        }

        }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public int hashCode() {
        return row + col;
    }

    @Override
    @SuppressWarnings("ALL")
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        ChessboardPoint temp = (ChessboardPoint) obj;
        return (temp.getRow() == this.row) && (temp.getCol() == this.col);
    }

    @Override
    public String toString() {
        return "("+row + ","+col+") " + "on the chessboard is clicked!";
    }
}

package model;

/**
 * This class store the real chess information.
 * The Chessboard has 9*7 cells, and each cell has a position for chess
 */
public class Chessboard {
    public Cell[][] grid;

    public Chessboard() {
        this.grid =
                new Cell[Constant.CHESSBOARD_ROW_SIZE.getNum()][Constant.CHESSBOARD_COL_SIZE.getNum()];//19X19

        initGrid();
        initPieces();
    }

    private void initGrid() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public void initPieces() {
        grid[6][0].setPiece(new ChessPiece(PlayerColor.BLUE, "Elephant",8));
        grid[2][6].setPiece(new ChessPiece(PlayerColor.RED, "Elephant",8));
        grid[8][6].setPiece(new ChessPiece(PlayerColor.BLUE, "Lion",7));
        grid[0][0].setPiece(new ChessPiece(PlayerColor.RED, "Lion",7));
        grid[8][0].setPiece(new ChessPiece(PlayerColor.BLUE, "Tiger",6));
        grid[0][6].setPiece(new ChessPiece(PlayerColor.RED, "Tiger",6));
        grid[6][4].setPiece(new ChessPiece(PlayerColor.BLUE, "Leopard",5));
        grid[2][2].setPiece(new ChessPiece(PlayerColor.RED, "Leopard",5));
        grid[6][2].setPiece(new ChessPiece(PlayerColor.BLUE, "Wolf",4));
        grid[2][4].setPiece(new ChessPiece(PlayerColor.RED, "Wolf",4));
        grid[7][5].setPiece(new ChessPiece(PlayerColor.BLUE, "Dog",3));
        grid[1][1].setPiece(new ChessPiece(PlayerColor.RED, "Dog",3));
        grid[7][1].setPiece(new ChessPiece(PlayerColor.BLUE, "Cat",2));
        grid[1][5].setPiece(new ChessPiece(PlayerColor.RED, "Cat",2));
        grid[6][6].setPiece(new ChessPiece(PlayerColor.BLUE, "Mouse",1));
        grid[2][0].setPiece(new ChessPiece(PlayerColor.RED, "Mouse",1));
    }

    public ChessPiece getChessPieceAt(ChessboardPoint point) {
        return getGridAt(point).getPiece();
    }

    public  Cell getGridAt(ChessboardPoint point) {
        return grid[point.getRow()][point.getCol()];
    }

    public int calculateDistance(ChessboardPoint src, ChessboardPoint dest) {
        return Math.abs(src.getRow() - dest.getRow()) + Math.abs(src.getCol() - dest.getCol());
    }

    public ChessPiece removeChessPiece(ChessboardPoint point) {
        ChessPiece chessPiece = getChessPieceAt(point);
        getGridAt(point).removePiece();
        return chessPiece;
    }

    public void setChessPiece(ChessboardPoint point, ChessPiece chessPiece) {
        getGridAt(point).setPiece(chessPiece);
    }

    public void moveChessPiece(ChessboardPoint src, ChessboardPoint dest) {
        if (!isValidMove(src, dest)) {
            throw new IllegalArgumentException("Illegal chess move!");
        }
        setChessPiece(dest, removeChessPiece(src));
    }

    public void captureChessPiece(ChessboardPoint src, ChessboardPoint dest) {
        if (isValidCapture(src, dest)) {
            throw new IllegalArgumentException("Illegal chess capture!");
        }else if(getChessPieceAt(src).canCapture(getChessPieceAt(dest))){
            setChessPiece(dest, removeChessPiece(src));
        }
    }

    public Cell[][] getGrid() {
        return grid;
    }
    public PlayerColor getChessPieceOwner(ChessboardPoint point) {
        return getGridAt(point).getPiece().getOwner();
    }

    public boolean isValidMove(ChessboardPoint src, ChessboardPoint dest) {
        if (getChessPieceAt(src) == null || getChessPieceAt(dest) != null) {
            return false;
        }

        if(getChessPieceAt(src).getName() == "Mouse" && dest.isRiver() && getChessPieceAt(dest) == null){
            return true;
        }

        if(getChessPieceAt(src).getName() != "Mouse" && dest.isRiver()){
            return false;
        }

        //Lions and Tigers jump over the river
        if(getChessPieceAt(src).getName() == "Lion" || getChessPieceAt(src).getName() == "Tiger"){

            //Jump over the river horizontally
            if(src.getRow() == 2 && dest.getRow() == 6 && src.getCol() == dest.getCol()){

                //Check if the animal'rank in dest is higher
                if(getChessPieceAt(src).getRank() < getChessPieceAt(dest).getRank()){
                    return false;
                }

                //Check if there is any chess piece between the src and dest
                for (int i = 2; i <= 6; i++) {
                    ChessboardPoint temp = new ChessboardPoint(i, src.getCol());
                    if(getChessPieceAt(temp) != null){
                        return false;
                    }
                }
                return true;
                
            }else if (src.getRow() == 6 && dest.getRow() == 2 && src.getCol() == dest.getCol()){

                //Check if the animal'rank in dest is higher
                if(getChessPieceAt(src).getRank() < getChessPieceAt(dest).getRank()){
                    return false;
                }

                //Check if there is any chess piece between the src and dest
                for (int i = 2; i <= 6; i++) {
                    ChessboardPoint temp = new ChessboardPoint(i, src.getCol());
                    if(getChessPieceAt(temp) != null){
                        return false;
                    }
                }
                return true;
                
            }

            //Jump over the left river vertically
            if(src.getCol() == 0 && dest.getCol() == 3 && src.getRow() == dest.getRow()){

                //Check if the animal'rank in dest is higher
                if(getChessPieceAt(src).getRank() < getChessPieceAt(dest).getRank()){
                    return false;
                }

                //Check if there is any chess piece between the src and dest
                for (int i = 1; i <= 2; i++) {
                    ChessboardPoint temp = new ChessboardPoint(src.getRow(), i);
                    if(getChessPieceAt(temp) != null){
                        return false;
                    }
                }
                return true;

            }else if(src.getCol() == 3 && dest.getCol() == 0 && src.getRow() == dest.getRow()){
                //Check if the animal'rank in dest is higher
                if(getChessPieceAt(src).getRank() < getChessPieceAt(dest).getRank()){
                    return false;
                }

                //Check if there is any chess piece between the src and dest
                for (int i = 1; i <= 2; i++) {
                    ChessboardPoint temp = new ChessboardPoint(src.getRow(), i);
                    if(getChessPieceAt(temp) != null){
                        return false;
                    }
                }
                return true;
            }

            //Jump over the right river vertically
            if(src.getCol() == 3 && dest.getCol() == 6 && src.getRow() == dest.getRow()){

                //Check if the animal'rank in dest is higher
                if(getChessPieceAt(src).getRank() < getChessPieceAt(dest).getRank()){
                    return false;
                }

                //Check if there is any chess piece between the src and dest
                for (int i = 4; i <= 5; i++) {
                    ChessboardPoint temp = new ChessboardPoint(src.getRow(), i);
                    if(getChessPieceAt(temp) != null){
                        return false;
                    }
                }
                return true;

            }else if(src.getCol() == 6 && dest.getCol() == 3 && src.getRow() == dest.getRow()){
                //Check if the animal'rank in dest is higher
                if(getChessPieceAt(src).getRank() < getChessPieceAt(dest).getRank()){
                    return false;
                }

                //Check if there is any chess piece between the src and dest
                for (int i = 4; i <= 5; i++) {
                    ChessboardPoint temp = new ChessboardPoint(src.getRow(), i);
                    if(getChessPieceAt(temp) != null){
                        return false;
                    }
                }

                return true;
            }

        }
        return calculateDistance(src, dest) == 1;
    }


    public boolean isValidCapture(ChessboardPoint src, ChessboardPoint dest) {
        if (getChessPieceAt(src) == null || getChessPieceAt(dest) == null) {
            return false;
        }
        return false;
    }
}

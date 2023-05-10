package model;


public class ChessPiece {
    
    private PlayerColor owner;
    private String name;
    private int rank;

    public ChessPiece(PlayerColor owner, String name, int rank) {
        this.owner = owner;
        this.name = name;
        this.rank = rank;
    }

    public boolean canCapture(ChessPiece target) {
        if(target.getOwner() == this.owner) {
            return false;
        }else if(target.getRank() <= this.rank){
            if(target.getRank() == 1 && this.rank == 8){
                return false;
            }
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public PlayerColor getOwner() {
        return owner;
    }

    public int getRank() {
        return rank;
    }
}

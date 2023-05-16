package model;

import java.io.Serializable;

public class ChessPiece implements Serializable {
    
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
        }else if (target.getRank() == 8 && this.rank == 1){
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

    public void setRank(int rank){
        this.rank = rank;
    }
}

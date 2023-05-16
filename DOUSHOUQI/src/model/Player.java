package model;

import java.io.Serializable;

public class Player implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private PlayerColor color;

    public Player(String name, PlayerColor color){
        this.name = name;
        this.color = color;
    }

    public String getName(){
        return name;
    }

    public PlayerColor getColor(){
        return color;
    }
    
}

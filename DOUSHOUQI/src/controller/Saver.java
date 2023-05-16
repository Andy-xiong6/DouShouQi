package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.GameState;

public class Saver {
    private Saver(){} // Prevents instantiation

    // Save the game state to a file
    public static void save(GameState gameState, String filename){
        try(FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(gameState);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load the game state from a file
    public static GameState load(String filename){
        GameState gameState = null;
        try(FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            gameState = (GameState) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return gameState;
    }
}

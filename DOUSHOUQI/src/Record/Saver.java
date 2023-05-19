package Record;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Saver {
    private Saver(){} // Prevents instantiation

    // Save the game state to a file
    public static void save(GameRecorder gameStates, String filename){
        try(FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(gameStates);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load the game state from a file
    public static GameRecorder load(String filename){
        GameRecorder gameStates = null;
        try(FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            gameStates = (GameRecorder) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return gameStates;
    }
}

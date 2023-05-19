package Record;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.GameState;

public class GameRecorder implements Serializable{
    public List<GameState> gameStates = new ArrayList<>();
    public GameState currentGameState = null;
    public int index = 0;

    public GameRecorder(GameState gameState) {
        this.currentGameState = gameState;
    }

    public void add(GameState gameState){
        gameStates.add(gameState);
        index++;
        currentGameState = gameState;
    }

    public GameState get(int index){
        if(index < 0 || index >= gameStates.size()){
            return null;
        }
        return gameStates.get(index);
    }

}

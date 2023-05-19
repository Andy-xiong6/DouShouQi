package model;
import controller.GameController;


import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;
    private Chessboard chessboard = new Chessboard();
    private Player player1;
    private Player player2;
    private PlayerColor currentPlayer;
    private int moveCount;
    private List<GameController> controllers = new ArrayList<>();

    //initialize the game state
    public GameState(Chessboard chessboard, Player player1, Player player2, PlayerColor currentPlayer, int moveCount) {
        this.chessboard = chessboard;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = currentPlayer;
        this.moveCount = moveCount;
        controllers = new ArrayList<>();
    }

    public void registerController(GameController controller) {
        controllers.add(controller); // 将控制器添加到列表
    }

    public void restartGame(){
        this.chessboard = new Chessboard();
        this.currentPlayer = player1.getColor();
        this.moveCount = 0;
    }

    public GameState() {
    }

    //get the chessboard
    public Chessboard getChessboard() {
        return chessboard;
    }

    //get player1
    public Player getPlayer1() {
        return player1;
    }

    //get player2
    public Player getPlayer2(){
        return player2;
    }


    public PlayerColor getCurrentPlayer(){
        return currentPlayer;
    }

    //get the move count
    public int getMoveCount() {
        return moveCount;
    }

    public void setCurrentPlayer(PlayerColor currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}

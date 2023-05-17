package model;
import controller.GameController;


import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;
    private Chessboard chessboard;
    private Player player1;
    private Player player2;
    private boolean isPlayer1Turn;
    private int moveCount;
    private List<GameController> controllers = new ArrayList<>();

    //initialize the game state
    public GameState(Chessboard chessboard, Player player1, Player player2, boolean isPlayer1Turn, int moveCount) {
        this.chessboard = chessboard;
        this.player1 = player1;
        this.player2 = player2;
        this.isPlayer1Turn = isPlayer1Turn;
        this.moveCount = moveCount;
        controllers = new ArrayList<>();
    }

    public void registerController(GameController controller) {
        controllers.add(controller); // 将控制器添加到列表
    }

    public void restartGame(){
        this.chessboard = new Chessboard();
        this.isPlayer1Turn = true;
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

    //get current player
    public boolean isPlayer1Turn(){
        return isPlayer1Turn;
    }

    public PlayerColor getCurrentPlayer(){
        if(isPlayer1Turn){
            return player1.getColor();
        return player2.getColor(); 
        }

    //get the move count
    public int getMoveCount() {
        return moveCount;
    }
}

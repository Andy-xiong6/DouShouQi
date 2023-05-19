package controller;

import java.io.Serializable;

import javax.swing.JOptionPane;

import Record.GameRecorder;
import audio.Sound;
import frame.ChessGameFrame;
import listener.GameListener;
import model.PlayerColor;
import model.Chessboard;
import model.ChessboardPoint;
import model.Constant;
import model.GameState;
import view.CellComponent;
import view.JChessComponent;
import view.ChessboardComponent;
import model.Player;

/**
 * Controller is the connection between model and view,
 * when a Controller receive a request from a view, the Controller
 * analyzes and then hands over to the model for processing
 * [in this demo the request methods are onPlayerClickCell() and onPlayerClickChessPiece()]
 *
*/
public class GameController implements GameListener, Serializable{


    public Chessboard model;
    public ChessboardComponent view;
    public ChessGameFrame chessGameFrame;
    private PlayerColor currentPlayer;
    private Player player1;
    private Player player2;
    public GameRecorder gameRecorder;
    private GameState gameState;

    // Record whether there is a selected piece before
    private ChessboardPoint selectedPoint;

    public GameController(ChessboardComponent view, Chessboard model, Player player1, Player player2, PlayerColor currentPlayer, GameRecorder gameRecorder) {
        this.view = view;
        this.model = model;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = currentPlayer;
        this.gameState = new GameState();
        if(gameRecorder == null){
            this.gameRecorder = new GameRecorder(gameState);
        }else{
            this.gameRecorder = gameRecorder;
        }
        
        view.registerController(this);
        initialize(model);
        view.repaint();
    }

    public void setChessGameFrame(ChessGameFrame chessGameFrame) {
        this.chessGameFrame = chessGameFrame;
    }

    private void initialize(Chessboard model) {
        if(gameRecorder.index == 0){
            model.initPieces();
            System.out.println("init Pieces");
            System.out.println("index: " + gameRecorder.index);
        }else {
            model = gameRecorder.currentGameState.getChessboard();
            model.initPieces(model);
            System.out.println("load pieces");
            System.out.println("index: " + gameRecorder.index);
        }
        view.initiateChessComponent(model);
    }
    
    // after a valid move swap the player
    private void swapColor() {
        if(currentPlayer == PlayerColor.RED) {
            currentPlayer = PlayerColor.BLUE;
        }else {
            currentPlayer = PlayerColor.RED;
        }
        gameState.setCurrentPlayer(currentPlayer);
        chessGameFrame.timerLabel.switchPlayer();
        chessGameFrame.switchPlayer();
        view.repaint();
    }

    public PlayerColor checkWin() {
        if(model.getChessPieceAt(new ChessboardPoint(0, 3)) != null){
            chessGameFrame.timerLabel.stop();
            return PlayerColor.BLUE;
        }
        if(model.getChessPieceAt(new ChessboardPoint(8, 3)) != null){
            chessGameFrame.timerLabel.stop();
            return PlayerColor.RED;
        }
        boolean allRedDead = true;
        boolean allBlueDead = true;
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                if(model.grid[i][j].getPiece() != null) {
                    if(model.grid[i][j].getPiece().getOwner() == PlayerColor.BLUE) {
                        allBlueDead = false;
                    }else if(model.grid[i][j].getPiece().getOwner() == PlayerColor.RED){
                        allRedDead = false;
                    }
                }
            }
        }
        if(allRedDead) {
            return PlayerColor.BLUE;
        }else if( allBlueDead) {
            return PlayerColor.RED;
        }
        return null;
    }

    public PlayerColor getWinner(){
        return checkWin();
    }

    public void setState(GameState gameState){
        this.gameState = gameState;
        this.model = gameState.getChessboard();
        this.player1 = gameState.getPlayer1();  
        this.player2 = gameState.getPlayer2();  
        this.currentPlayer = gameState.getCurrentPlayer();
        this.selectedPoint = null; 
    }

    // click an empty cell
    @Override
    public void onPlayerClickCell(ChessboardPoint point, CellComponent component) {
        if (selectedPoint != null && model.isValidMove(selectedPoint, point) && getWinner() == null) {

            //Enter the traps
            if(point.isTrap()){
                if(model.getChessPieceAt(selectedPoint) != null){
                    if(point.isBlueSide() && (model.getChessPieceOwner(selectedPoint) == PlayerColor.RED) ){
                        model.getChessPieceAt(selectedPoint).setRank(0);
                    }else if (!point.isBlueSide() && (model.getChessPieceOwner(selectedPoint) == PlayerColor.BLUE)){
                        model.getChessPieceAt(selectedPoint).setRank(0);
                    }
                }
            }

            //Get out of the trap, reset the rank of the chess
            if(selectedPoint.isTrap()){
                if(model.getChessPieceOwner(selectedPoint) != null){
                    if(model.getChessPieceAt(selectedPoint).getName() == "Elephant"){
                        model.getChessPieceAt(selectedPoint).setRank(8);
                    }else if (model.getChessPieceAt(selectedPoint).getName() == "Lion"){
                        model.getChessPieceAt(selectedPoint).setRank(7);
                    }else if (model.getChessPieceAt(selectedPoint).getName() == "Tiger"){
                        model.getChessPieceAt(selectedPoint).setRank(6);
                    }else if (model.getChessPieceAt(selectedPoint).getName() == "Leopard"){
                        model.getChessPieceAt(selectedPoint).setRank(5);
                    }else if (model.getChessPieceAt(selectedPoint).getName() == "Wolf"){
                        model.getChessPieceAt(selectedPoint).setRank(4);
                    }else if (model.getChessPieceAt(selectedPoint).getName() == "Dog"){
                        model.getChessPieceAt(selectedPoint).setRank(3);
                    }else if (model.getChessPieceAt(selectedPoint).getName() == "Cat"){
                        model.getChessPieceAt(selectedPoint).setRank(2);
                    }else if (model.getChessPieceAt(selectedPoint).getName() == "Mouse"){
                        model.getChessPieceAt(selectedPoint).setRank(1);
                    }
                }
            }
            Sound.move();
            model.moveChessPiece(selectedPoint, point);
            view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
            selectedPoint = null;
            swapColor();
            gameRecorder.index++;
            System.out.println("index: " + gameRecorder.index);
            view.setTrap();
            view.revalidate();
            view.repaint();
            if(getWinner() == PlayerColor.BLUE) {
                JOptionPane.showMessageDialog(null, "Blue wins!");
            }else if(getWinner() == PlayerColor.RED) {
                JOptionPane.showMessageDialog(null, "Red wins!");
            }
        }
    }

    // click a cell with a chess
    @Override
    public void onPlayerClickChessPiece(ChessboardPoint point, JChessComponent component) {
        if(getWinner() == null){
            if (selectedPoint == null) {
                if(model.getChessPieceAt(point) != null){
                    if (model.getChessPieceOwner(point).equals(currentPlayer)) {
                        selectedPoint = point;
                        component.setSelected(true);
                        component.repaint();
                    }
                }
            } else if (selectedPoint.equals(point)) {
                selectedPoint = null;
                component.setSelected(false);
                component.repaint();
            }
    
            if(selectedPoint != null && point != null && !selectedPoint.equals(point) && model.isValidMove(selectedPoint, point)){
                if(model.getChessPieceAt(selectedPoint).canCapture(model.getChessPieceAt(point))){
                    Sound.eat();
                    view.removeChessComponentAtGrid(point);
                    model.removeChessPiece(point);
                    model.moveChessPiece(selectedPoint, point);
                    view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
                    selectedPoint = null;
                    swapColor();
                    gameRecorder.index++;
                    System.out.println("index: " + gameRecorder.index);
                    view.revalidate();
                    view.repaint();
                    getWinner();
                }
            }

        }
        
    }
}

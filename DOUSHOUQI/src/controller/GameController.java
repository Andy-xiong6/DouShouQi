package controller;


import java.awt.event.MouseListener;

import listener.GameListener;
import model.Constant;
import model.PlayerColor;
import model.ChessPiece;
import model.Chessboard;
import model.ChessboardPoint;
import view.CatChessComponent;
import view.CellComponent;
import view.JChessComponent;
import view.LeopardChessComponent;
import view.LionChessComponent;
import view.MouseChessComponent;
import view.TigerChessComponent;
import view.WolfChessComponent;
import view.ChessboardComponent;
import view.DogChessComponent;
import view.ElephantChessComponent;

/**
 * Controller is the connection between model and view,
 * when a Controller receive a request from a view, the Controller
 * analyzes and then hands over to the model for processing
 * [in this demo the request methods are onPlayerClickCell() and onPlayerClickChessPiece()]
 *
*/
public class GameController implements GameListener {


    private Chessboard model;
    private ChessboardComponent view;
    private PlayerColor currentPlayer;

    // Record whether there is a selected piece before
    private ChessboardPoint selectedPoint;

    public GameController(ChessboardComponent view, Chessboard model) {
        this.view = view;
        this.model = model;
        this.currentPlayer = PlayerColor.BLUE;

        view.registerController(this);
        initialize();
        view.initiateChessComponent(model);
        view.repaint();
    }

    private void initialize() {
        model.initPieces();
    }
    

    // after a valid move swap the player
    private void swapColor() {
        currentPlayer = currentPlayer == PlayerColor.BLUE ? PlayerColor.RED : PlayerColor.BLUE;
    }

    public boolean checkWin() {
        if(model.getChessPieceAt(new ChessboardPoint(0, 3)) != null || model.getChessPieceAt(new ChessboardPoint(8, 3)) != null){
            return true;
        }
        return false;
    }

    public PlayerColor getWinner(){
        if(model.getChessPieceAt(new ChessboardPoint(0, 3)) != null){
            return PlayerColor.BLUE;
        }else if (model.getChessPieceAt(new ChessboardPoint(8, 3)) != null){
            return PlayerColor.RED;
        }
        return null;
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

            model.moveChessPiece(selectedPoint, point);
            view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
            selectedPoint = null;
            swapColor();
            view.repaint();
            getWinner();
            
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
    
            if(selectedPoint != null && point != null && !selectedPoint.equals(point)){
                if(model.getChessPieceAt(selectedPoint).canCapture(model.getChessPieceAt(point))){
                    view.removeChessComponentAtGrid(point);
                    model.removeChessPiece(point);
                    model.moveChessPiece(selectedPoint, point);
                    view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
                    selectedPoint = null;
                    swapColor();
                    view.repaint();
                    getWinner();
                }
            }

        }
        
    }
}

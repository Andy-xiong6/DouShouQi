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

    private boolean win() {
        if(model.getChessPieceOwner(new ChessboardPoint(0, 3)).equals(PlayerColor.BLUE) &&
                model.getChessPieceOwner(new ChessboardPoint(8, 3)).equals(PlayerColor.RED)) {
            return true;
        }
        return false;
    }

    // click an empty cell
    @Override
    public void onPlayerClickCell(ChessboardPoint point, CellComponent component) {
        if (selectedPoint != null && model.isValidMove(selectedPoint, point)) {
            //Enter the traps
            if(point.isTrap()){
                if(point.isBlueSide() && (model.getChessPieceOwner(point) == PlayerColor.RED) ){
                    model.getChessPieceAt(point).setRank(0);
                }else if (!point.isBlueSide() && (model.getChessPieceOwner(point) == PlayerColor.BLUE)){
                    model.getChessPieceAt(point).setRank(0);
                }
            }

            //Get out of the trap, reset the rank of the chess
            if(selectedPoint.isTrap()){
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

            model.moveChessPiece(selectedPoint, point);
            view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint, selectedPoint.getClass()));
            selectedPoint = null;
            swapColor();
            view.repaint();
            
        }
    }

    // click a cell with a chess
    @Override
    public void onPlayerClickChessPiece(ChessboardPoint point, JChessComponent component) {
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
        // TODO:Implement capture function
    }
}

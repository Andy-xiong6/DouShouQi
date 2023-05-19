package view;

import controller.GameController;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

import static model.Constant.CHESSBOARD_COL_SIZE;
import static model.Constant.CHESSBOARD_ROW_SIZE;

/**
 * This class represents the checkerboard component object on the panel
 */
public class ChessboardComponent extends JComponent {
    public CellComponent[][] gridComponents = new CellComponent[CHESSBOARD_ROW_SIZE.getNum()][CHESSBOARD_COL_SIZE.getNum()];
    public final int CHESS_SIZE;
    private final Set<ChessboardPoint> riverCell = new HashSet<>();

    private GameController gameController;
    public ChessboardComponent(int chessSize) {
        CHESS_SIZE = chessSize;
        int width = CHESS_SIZE * 7;
        int height = CHESS_SIZE * 9;
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);// Allow mouse events to occur
        setLayout(null); // Use absolute layout.
        setSize(width, height);
        System.out.printf("chessboard width, height = [%d : %d], chess size = %d\n", width, height, CHESS_SIZE);

        initiateGridComponents();
    }

    public void clear(){
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                gridComponents[i][j].removeAll();
            }
        }
    }


    /**
     * This method represents how to initiate ChessComponent
     * according to Chessboard information
     */
    public void initiateChessComponent(Chessboard chessboard) {
        Cell[][] grid = chessboard.getGrid();
        for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                if(grid[i][j].getPiece() != null){
                    if (grid[i][j].getPiece().getName().equals("Elephant")) {
                        ChessPiece chessPiece = grid[i][j].getPiece();
                        System.out.println(chessPiece.getOwner());
                        gridComponents[i][j].add(
                                new ElephantChessComponent(chessPiece.getOwner(),CHESS_SIZE));                  
                    } else if(grid[i][j].getPiece().getName().equals("Cat")){
                        ChessPiece chessPiece = grid[i][j].getPiece();
                        System.out.println(chessPiece.getOwner());
                        gridComponents[i][j].add(
                                new CatChessComponent(chessPiece.getOwner(),CHESS_SIZE));
                    } else if(grid[i][j].getPiece().getName().equals("Dog")){
                        ChessPiece chessPiece = grid[i][j].getPiece();
                        System.out.println(chessPiece.getOwner());
                        gridComponents[i][j].add(
                                new DogChessComponent(chessPiece.getOwner(),CHESS_SIZE));
                    } else if(grid[i][j].getPiece().getName().equals("Lion")){
                        ChessPiece chessPiece = grid[i][j].getPiece();
                        System.out.println(chessPiece.getOwner());
                        gridComponents[i][j].add(
                                new LionChessComponent(chessPiece.getOwner(),CHESS_SIZE));
                    } else if(grid[i][j].getPiece().getName().equals("Tiger")){
                        ChessPiece chessPiece = grid[i][j].getPiece();
                        System.out.println(chessPiece.getOwner());
                        gridComponents[i][j].add(
                                new TigerChessComponent(chessPiece.getOwner(),CHESS_SIZE));
                    } else if(grid[i][j].getPiece().getName().equals("Mouse")){
                        ChessPiece chessPiece = grid[i][j].getPiece();
                        System.out.println(chessPiece.getOwner());
                        gridComponents[i][j].add(
                                new MouseChessComponent(chessPiece.getOwner(),CHESS_SIZE));
                    }else if(grid[i][j].getPiece().getName().equals("Leopard")){
                        ChessPiece chessPiece = grid[i][j].getPiece();
                        System.out.println(chessPiece.getOwner());
                        gridComponents[i][j].add(
                                new LeopardChessComponent(chessPiece.getOwner(),CHESS_SIZE));
                    }else if(grid[i][j].getPiece().getName().equals("Wolf")){
                        ChessPiece chessPiece = grid[i][j].getPiece();
                        System.out.println(chessPiece.getOwner());
                        gridComponents[i][j].add(
                                new WolfChessComponent(chessPiece.getOwner(),CHESS_SIZE));
                    }
                }else if (new ChessboardPoint(i, j).isTrap()){
                    if(new ChessboardPoint(i, j).isBlueSide()){
                        gridComponents[i][j].add(
                            new TrapComponent(PlayerColor.BLUE));
                    }else if (!new ChessboardPoint(i, j).isBlueSide()) {
                        gridComponents[i][j].add(
                            new TrapComponent(PlayerColor.RED));
                    }
                }else if (new ChessboardPoint(i, j).isDen()) {
                    if(new ChessboardPoint(i, j).isBlueSide()){
                        gridComponents[i][j].add(
                            new DenComponent(PlayerColor.BLUE));
                    }else if (!new ChessboardPoint(i, j).isBlueSide()) {
                        gridComponents[i][j].add(
                            new DenComponent(PlayerColor.RED));
                    }
                }
            }
        }
    }

    

    public void initiateGridComponents() {

        riverCell.add(new ChessboardPoint(3,1));
        riverCell.add(new ChessboardPoint(3,2));
        riverCell.add(new ChessboardPoint(4,1));
        riverCell.add(new ChessboardPoint(4,2));
        riverCell.add(new ChessboardPoint(5,1));
        riverCell.add(new ChessboardPoint(5,2));

        riverCell.add(new ChessboardPoint(3,4));
        riverCell.add(new ChessboardPoint(3,5));
        riverCell.add(new ChessboardPoint(4,4));
        riverCell.add(new ChessboardPoint(4,5));
        riverCell.add(new ChessboardPoint(5,4));
        riverCell.add(new ChessboardPoint(5,5));

        for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                ChessboardPoint temp = new ChessboardPoint(i, j);
                CellComponent cell;
                if (riverCell.contains(temp)) {
                    cell = new CellComponent(Color.CYAN, calculatePoint(i, j), CHESS_SIZE);
                    this.add(cell);
                } else {
                    if(temp.isTrap()){
                        cell = new CellComponent(null, calculatePoint(i, j), CHESS_SIZE);
                    }else if(temp.isDen()){
                        cell = new CellComponent(null, calculatePoint(i, j), CHESS_SIZE);
                    }else{
                        cell = new CellComponent(Color.BLACK, calculatePoint(i, j), CHESS_SIZE);
                    }
                    this.add(cell);
                }
                gridComponents[i][j] = cell;
            }
        }
    }

    public void setTrap() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                if(new ChessboardPoint(i, j).isTrap() && gridComponents[i][j].getComponentCount() == 0){
                    if(new ChessboardPoint(i, j).isBlueSide()){
                        gridComponents[i][j].add(new TrapComponent(PlayerColor.BLUE));
                    }else if (!new ChessboardPoint(i, j).isBlueSide()) {
                        gridComponents[i][j].add(new TrapComponent(PlayerColor.RED));
                    }
                    
                }
            }
        }
    }

    public void registerController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setChessComponentAtGrid(ChessboardPoint point, JChessComponent chess) {
        if(chess != null){
            getGridComponentAt(point).add(chess);
        }
    }


    public JChessComponent removeChessComponentAtGrid(ChessboardPoint point) {
        JChessComponent chess = null;
        if(getGridComponentAt(point).getComponentCount() ==1) {
            chess = (JChessComponent) getGridComponentAt(point).getComponents()[0];
        }else if (getGridComponentAt(point).getComponentCount() ==2) {
            chess = (JChessComponent) getGridComponentAt(point).getComponents()[1];
        }
        
        getGridComponentAt(point).removeAll();
        getGridComponentAt(point).revalidate();
        chess.setSelected(false);
        return chess;
    }

    public CellComponent getGridComponentAt(ChessboardPoint point) {
        return gridComponents[point.getRow()][point.getCol()];
    }

    private ChessboardPoint getChessboardPoint(Point point) {
        System.out.println("[" + point.y/CHESS_SIZE +  ", " +point.x/CHESS_SIZE + "] Clicked");
        return new ChessboardPoint(point.y/CHESS_SIZE, point.x/CHESS_SIZE);
    }
    
    private Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE, row * CHESS_SIZE);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    @Override
    protected void processMouseEvent(MouseEvent e) {
        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            JComponent clickedComponent = (JComponent) getComponentAt(e.getX(), e.getY());
            if (clickedComponent.getComponentCount() == 0) {
                System.out.print("None chess here and ");
                gameController.onPlayerClickCell(getChessboardPoint(e.getPoint()), (CellComponent) clickedComponent);
            } else if(clickedComponent.getComponentCount() == 1){
                if(clickedComponent.getComponents()[0].getClass() == ElephantChessComponent.class 
                || clickedComponent.getComponents()[0].getClass() == CatChessComponent.class
                || clickedComponent.getComponents()[0].getClass() == DogChessComponent.class
                || clickedComponent.getComponents()[0].getClass() == LionChessComponent.class
                || clickedComponent.getComponents()[0].getClass() == WolfChessComponent.class
                || clickedComponent.getComponents()[0].getClass() == MouseChessComponent.class
                || clickedComponent.getComponents()[0].getClass() == TigerChessComponent.class
                || clickedComponent.getComponents()[0].getClass() == LeopardChessComponent.class
                ){
                    System.out.print("One chess here and ");
                if(clickedComponent.getComponents()[0] instanceof ElephantChessComponent){
                    gameController.onPlayerClickChessPiece(getChessboardPoint(e.getPoint()), (ElephantChessComponent) clickedComponent.getComponents()[0]);
                }else if(clickedComponent.getComponents()[0] instanceof CatChessComponent){
                    gameController.onPlayerClickChessPiece(getChessboardPoint(e.getPoint()), (CatChessComponent) clickedComponent.getComponents()[0]);
                }else if(clickedComponent.getComponents()[0] instanceof DogChessComponent){
                    gameController.onPlayerClickChessPiece(getChessboardPoint(e.getPoint()), (DogChessComponent) clickedComponent.getComponents()[0]);
                }else if(clickedComponent.getComponents()[0] instanceof LionChessComponent){
                    gameController.onPlayerClickChessPiece(getChessboardPoint(e.getPoint()), (LionChessComponent) clickedComponent.getComponents()[0]);
                }else if(clickedComponent.getComponents()[0] instanceof LeopardChessComponent){
                    gameController.onPlayerClickChessPiece(getChessboardPoint(e.getPoint()), (LeopardChessComponent) clickedComponent.getComponents()[0]);
                }else if(clickedComponent.getComponents()[0] instanceof MouseChessComponent){
                    gameController.onPlayerClickChessPiece(getChessboardPoint(e.getPoint()), (MouseChessComponent) clickedComponent.getComponents()[0]);
                }else if(clickedComponent.getComponents()[0] instanceof TigerChessComponent){
                    gameController.onPlayerClickChessPiece(getChessboardPoint(e.getPoint()), (TigerChessComponent) clickedComponent.getComponents()[0]);
                }else if(clickedComponent.getComponents()[0] instanceof WolfChessComponent){
                    gameController.onPlayerClickChessPiece(getChessboardPoint(e.getPoint()), (WolfChessComponent) clickedComponent.getComponents()[0]);
                }
                }else{
                    System.out.print("Not chess here and ");
                    gameController.onPlayerClickCell(getChessboardPoint(e.getPoint()), (CellComponent) clickedComponent);
                }
            } else {
                System.out.print("One chess here and ");
                if(clickedComponent.getComponents()[1] instanceof ElephantChessComponent){
                    gameController.onPlayerClickChessPiece(getChessboardPoint(e.getPoint()), (ElephantChessComponent) clickedComponent.getComponents()[1]);
                }else if(clickedComponent.getComponents()[1] instanceof CatChessComponent){
                    gameController.onPlayerClickChessPiece(getChessboardPoint(e.getPoint()), (CatChessComponent) clickedComponent.getComponents()[1]);
                }else if(clickedComponent.getComponents()[1] instanceof DogChessComponent){
                    gameController.onPlayerClickChessPiece(getChessboardPoint(e.getPoint()), (DogChessComponent) clickedComponent.getComponents()[1]);
                }else if(clickedComponent.getComponents()[1] instanceof LionChessComponent){
                    gameController.onPlayerClickChessPiece(getChessboardPoint(e.getPoint()), (LionChessComponent) clickedComponent.getComponents()[1]);
                }else if(clickedComponent.getComponents()[1] instanceof LeopardChessComponent){
                    gameController.onPlayerClickChessPiece(getChessboardPoint(e.getPoint()), (LeopardChessComponent) clickedComponent.getComponents()[1]);
                }else if(clickedComponent.getComponents()[1] instanceof MouseChessComponent){
                    gameController.onPlayerClickChessPiece(getChessboardPoint(e.getPoint()), (MouseChessComponent) clickedComponent.getComponents()[1]);
                }else if(clickedComponent.getComponents()[1] instanceof TigerChessComponent){
                    gameController.onPlayerClickChessPiece(getChessboardPoint(e.getPoint()), (TigerChessComponent) clickedComponent.getComponents()[1]);
                }else if(clickedComponent.getComponents()[1] instanceof WolfChessComponent){
                    gameController.onPlayerClickChessPiece(getChessboardPoint(e.getPoint()), (WolfChessComponent) clickedComponent.getComponents()[1]);
                }
            }
        }
    }
}

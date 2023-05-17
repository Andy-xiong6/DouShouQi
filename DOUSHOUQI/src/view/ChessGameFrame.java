package view;

import javax.swing.*;

import controller.GameController;
import controller.Saver;
import model.Chessboard;
import model.Constant;
import model.GameState;
import model.Player;
import model.PlayerColor;

import java.awt.*;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;

    private final int ONE_CHESS_SIZE;

    private JButton saveButton;
    private JButton loadButton;

    private GameState gameState;
    private GameController gameController;

    private ChessboardComponent chessboardComponent;
    public ChessGameFrame(int width, int height, GameState gameState) {
        setTitle("天天趣味斗兽棋"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.ONE_CHESS_SIZE = (HEIGTH * 4 / 5) / 9;
        this.gameState = gameState;

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);


        addChessboard();
        addLabel();
        addLoadButton();

        
        gameController = new GameController(chessboardComponent, gameState.getChessboard() ,gameState.getPlayer1(), gameState.getPlayer2());
        gameController.setState(gameState);

        saveButton = new JButton("保存游戏");
        saveButton.addActionListener(e -> saveGameState());
        saveButton.setBounds(800, 700, 150, 50);
        add(saveButton);

        loadButton = new JButton("加载游戏");
        loadButton.addActionListener(e -> loadGameState());
        loadButton.setBounds(950, 500, 150, 50);
        add(loadButton);

        JButton restartButton = new JButton("重新开始");
        restartButton.addActionListener(e -> restartGame());
        restartButton.setBounds(950, 600, 150, 50);
        add(restartButton);

        
    }

    private void saveGameState(){
        Saver.save(gameState, "gamestate.ser");
        JOptionPane.showMessageDialog(this,"保存成功！");
    }

    private void loadGameState(){
        gameController.model.clear();
        chessboardComponent.clear();
        GameState newGameState = Saver.load("gamestate.ser");
        if(newGameState == null){
            JOptionPane.showMessageDialog(this, "没有发现存档");
            return;
        }
        gameState = newGameState;

        //FIXME: 重新加载棋盘 ; 已经确定chessboard、grid、chesspiece都是没有问题的，应该是添加component的地方有问题,且之前的grid没有删除
        
        //chessboardComponent.initiateChessComponent(gameState.getChessboard());
        gameController = new GameController(chessboardComponent, gameState.getChessboard(), gameState.getPlayer1(), gameState.getPlayer2());
        chessboardComponent.revalidate();
        chessboardComponent.repaint();
        JOptionPane.showMessageDialog(this, "加载成功！");
        
    }

    private void restartGame(){
        GameState gameState = new GameState(new Chessboard(), new Player("玩家1", PlayerColor.BLUE), new Player("玩家2", PlayerColor.RED), true, 0);
        chessboardComponent.clear();
        chessboardComponent.revalidate();
        chessboardComponent.repaint();
        GameController gameController = new GameController(chessboardComponent, gameState.getChessboard(), gameState.getPlayer1(), gameState.getPlayer2());
    }

    public ChessboardComponent getChessboardComponent() {
        return chessboardComponent;
    }

    public void setChessboardComponent(ChessboardComponent chessboardComponent) {
        this.chessboardComponent = chessboardComponent;
    }

    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard() {
        chessboardComponent = new ChessboardComponent(ONE_CHESS_SIZE);
        chessboardComponent.setLocation(HEIGTH / 5, HEIGTH / 10);
        add(chessboardComponent);
    }

    /**
     * 在游戏面板中添加标签
     */
    private void addLabel() {
        JLabel statusLabel = new JLabel("这是标签！");
        statusLabel.setLocation(HEIGTH, HEIGTH / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(statusLabel);
    }

    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
     */

    private void addLoadButton() {
        JButton button = new JButton("Hello");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "保存成功！"));
        button.setLocation(HEIGTH, HEIGTH / 10 + 120);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
}

package frame;
import javax.naming.TimeLimitExceededException;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import audio.Sound;
import controller.GameController;
import controller.Saver;
import listener.GameListener;
import model.Chessboard;
import model.GameState;
import model.Player;
import model.PlayerColor;
import theme.BlueTheme;
import theme.GreenTheme;
import theme.RedTheme;
import theme.Theme;
import view.ChessTimeLabel;
import view.ChessboardComponent;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    public final int WIDTH;
    public final int HEIGTH;

    private final int ONE_CHESS_SIZE;

    private JButton saveButton;
    private JButton loadButton;
    private JButton restartButton;
    private JButton changeThemeButton;

    private GameState gameState;
    private GameController gameController;

    private ChessboardComponent chessboardComponent;
    private Theme theme;
    private JLabel backgroundLabel;
    public JLabel playerLabel;
    public ChessTimeLabel timerLabel;
    public JLabel roundLabel;
    public JLabel statusLabel;

    public void setTheme(Theme theme) {
        this.theme = theme;
        Image backgroundImage = theme.getBackgroundImage();
        if(backgroundImage != null){
            backgroundLabel = new JLabel();
            ImageIcon backgroundIcon = new ImageIcon(backgroundImage);
            Image scaledImage = backgroundIcon.getImage().getScaledInstance(WIDTH, HEIGTH, Image.SCALE_DEFAULT);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            backgroundLabel.setIcon(scaledIcon);
            JPanel contentPanel = (JPanel) getContentPane();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(backgroundLabel, BorderLayout.CENTER);
            System.out.println("set background image");
        }else {
            backgroundLabel.setIcon(null);
            backgroundLabel.setBackground(theme.getBackgroundColor());
            System.out.println("set background color");
        }
        theme.changeButtonAppearance(saveButton);
        theme.changeButtonAppearance(loadButton);
        theme.changeButtonAppearance(restartButton);
        theme.changeButtonAppearance(changeThemeButton);
        theme.changeLabelAppearance(playerLabel);
        theme.changeLabelAppearance(roundLabel);
        theme.changeLabelAppearance(timerLabel);
        theme.changeLabelAppearance(statusLabel);
        
    }

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
        addPlayerLabel();
        

        gameController = new GameController(chessboardComponent, gameState.getChessboard() ,gameState.getPlayer1(), gameState.getPlayer2());
        gameController.setChessGameFrame(this);
        gameController.setState(gameState);

        saveButton = new JButton("保存游戏");
        saveButton.addActionListener(e -> saveGameState());
        saveButton.setBounds(800, 500, 150, 50);
        add(saveButton);

        loadButton = new JButton("加载游戏");
        loadButton.addActionListener(e -> loadGameState());
        loadButton.setBounds(800, 600, 150, 50);
        add(loadButton);
        
        restartButton = new JButton("重新开始");
        restartButton.addActionListener(e -> restartGame());
        restartButton.setBounds(800, 700, 150, 50);
        add(restartButton);

        changeThemeButton = new JButton("切换主题");
        changeThemeButton.addActionListener(e -> showThemeDialog());
        changeThemeButton.setBounds(800, 400, 150, 50);
        add(changeThemeButton);
        Sound.background();
        setTheme(new BlueTheme());
        
    }

    private void showThemeDialog() {
        JDialog themeDialog = new JDialog(this, "选择主题", true);
        themeDialog.setSize(300, 200);
        themeDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JButton blueThemeButton = new JButton("蓝色主题");
        blueThemeButton.setForeground(Color.BLUE);
        blueThemeButton.setBackground(Color.WHITE);
        blueThemeButton.addActionListener(e -> {
            this.backgroundLabel.setIcon(null);
            setTheme(new BlueTheme());
            themeDialog.dispose();
            repaint();
        });
        JButton redThemeButton = new JButton("红色主题");
        redThemeButton.setForeground(Color.RED);
        redThemeButton.setBackground(Color.WHITE);
        redThemeButton.addActionListener(e -> {
            this.backgroundLabel.setIcon(null);
            setTheme(new RedTheme());
            themeDialog.dispose();
            repaint();
        });
        JButton greenThemeButton = new JButton("绿色主题");
        greenThemeButton.setForeground(Color.GREEN);
        greenThemeButton.setBackground(Color.WHITE);
        greenThemeButton.addActionListener(e -> {
            this.backgroundLabel.setIcon(null);
            setTheme(new GreenTheme());
            themeDialog.dispose();
            repaint();
        });
        JPanel themePanel = new JPanel();
        themePanel.setLayout(new GridLayout(3, 1));
        themePanel.add(blueThemeButton);
        themePanel.add(redThemeButton);
        themePanel.add(greenThemeButton);
        themeDialog.add(themePanel);
        themeDialog.setLocationRelativeTo(null);
        themeDialog.setVisible(true);


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
        GameState gameState = new GameState(new Chessboard(), new Player("玩家1", PlayerColor.BLUE), new Player("玩家2", PlayerColor.RED), false, 0);
        chessboardComponent.clear();
        chessboardComponent.revalidate();
        chessboardComponent.repaint();
        GameController gameController = new GameController(chessboardComponent, gameState.getChessboard(), gameState.getPlayer1(), gameState.getPlayer2());
        gameController.setChessGameFrame(this);
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
        statusLabel = new JLabel("计时器");
        statusLabel.setLocation(920, HEIGTH / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(statusLabel);

        timerLabel = new ChessTimeLabel(61,gameState);
        timerLabel.setLocation(850, HEIGTH / 10 + 55);
        timerLabel.setSize(200, 60);
        timerLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        timerLabel.start();
        timerLabel.setForeground(Color.RED);
        add(timerLabel);
        
    }

    public void addPlayerLabel() {
        playerLabel = new JLabel("当前玩家：" + gameState.getCurrentPlayer().toString());
        playerLabel.setLocation(870, HEIGTH / 10 -50);
        playerLabel.setSize(200, 60);
        playerLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(playerLabel);

        roundLabel = new JLabel("回合数：" /*+ gameState.getRound()*/);
        roundLabel.setLocation(870, HEIGTH / 10 + -85);
        roundLabel.setSize(200, 60);
        roundLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(roundLabel);
    }

    public void switchPlayer() {
        playerLabel.setText("当前玩家：" + gameState.getCurrentPlayer().toString());
    }
} 

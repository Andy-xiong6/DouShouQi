package view;

import java.awt.Dimension;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import model.GameState;
import model.PlayerColor;

public class ChessTimeLabel extends JLabel implements Runnable{
    
    private static final long serialVersionUID = 1L;

    private int initialTime;
    private int remainingTime;
    private boolean isRunning;
    private PlayerColor currentPlayer;

    private SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");

    public ChessTimeLabel(int initialTime, GameState gameState) {
        super("", SwingConstants.CENTER);
        this.setLayout(null);
        this.setBounds(400, 30, 50, 50);
        this.initialTime = initialTime;
        this.remainingTime = initialTime;
        this.isRunning = false;
        this.currentPlayer = gameState.getCurrentPlayer();
    }

    public void reset() {
        this.remainingTime = initialTime;
        this.isRunning = false;
        this.currentPlayer = PlayerColor.BLUE;
    }

    public void start() {
        isRunning = true;
        new Thread(this).start();
    }

    public void stop() {
        isRunning = false;
    }

    public void switchPlayer() {
        this.remainingTime = initialTime;
    }

    @Override
    public void run() {
        while(isRunning) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            remainingTime--;
            if(remainingTime == 0) {
                stop();
            }
            setText(timeFormat.format(remainingTime * 1000));
            
        }
    }
}

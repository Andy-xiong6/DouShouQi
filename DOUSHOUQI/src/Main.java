import model.Chessboard;
import model.GameState;
import model.Player;
import model.PlayerColor;
import view.ChessGameFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            
            Player player1 = new Player("Little Rer Hat", PlayerColor.RED);
            Player player2 = new Player("Big Blue Hat", PlayerColor.BLUE);

            GameState gameState = new GameState(new Chessboard(), player1, player2, true, 0);
            
            ChessGameFrame mainFrame = new ChessGameFrame(1100, 810, gameState);
            mainFrame.setVisible(true);
        });

    }
}

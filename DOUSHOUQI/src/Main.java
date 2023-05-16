import controller.GameController;
import model.Chessboard;
import model.Player;
import model.PlayerColor;
import view.ChessGameFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);
            Player player1 = new Player("Little Rer Hat", PlayerColor.RED);
            Player player2 = new Player("Big Blue Hat", PlayerColor.BLUE);
            GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard(), player1, player2);
            mainFrame.setVisible(true);
        });

    }
}

import model.Chessboard;
import model.GameState;
import model.Player;
import model.PlayerColor;
import javax.swing.*;
import frame.MainMenuFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            
            Player player1 = new Player("Little Rer Hat", PlayerColor.RED);
            Player player2 = new Player("Big Blue Hat", PlayerColor.BLUE);

            GameState gameState = new GameState(new Chessboard(), player1, player2, player1.getColor(), 0);
            
            MainMenuFrame mainMenuFrame = new MainMenuFrame(gameState);
            mainMenuFrame.setVisible(true);
        });

    }
}

package view;

import model.ChessboardPoint;
import model.PlayerColor;

import javax.swing.*;
import java.awt.*;

public class DogChessComponent extends JChessComponent {
    private PlayerColor owner;
    private boolean selected;

    public DogChessComponent(PlayerColor owner, int size) {
        super(owner, size);
    }

    public DogChessComponent(ChessboardPoint point, PlayerColor owner) {
        super(point, owner);
        this.selected = false;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("楷体", Font.PLAIN, getWidth() / 2);
        graphics2D.setFont(font);
        if(owner != null){
            graphics2D.setColor(owner.getColor());
        }
        graphics2D.drawString("狗", getWidth() / 4, getHeight() * 5 / 8); // FIXME: Use library to find the correct offset.
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
    
}

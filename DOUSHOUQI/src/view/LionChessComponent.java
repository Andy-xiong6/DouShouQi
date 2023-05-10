package view;


import model.ChessboardPoint;
import model.PlayerColor;

import javax.swing.*;
import java.awt.*;

public class LionChessComponent extends JChessComponent{
    private PlayerColor owner;
    private boolean selected;

    public LionChessComponent(PlayerColor owner, int size) {
        super(owner, size);
    }

    public LionChessComponent(ChessboardPoint point, PlayerColor owner) {
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
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("楷体", Font.PLAIN, getWidth() / 2);
        g2.setFont(font);
        if(owner != null){
            g2.setColor(owner.getColor());
        }
        g2.drawString("狮", getWidth() / 4, getHeight() * 5 / 8); // FIXME: Use library to find the correct offset.
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}

package view;

import java.awt.*;

import javax.swing.*;

import model.ChessboardPoint;
import model.PlayerColor;

public class JChessComponent extends JComponent {
    protected ChessboardPoint point;
    protected PlayerColor owner;
    private boolean selected;
    
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}

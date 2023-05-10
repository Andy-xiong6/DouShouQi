package view;

import java.awt.*;

import javax.swing.*;

import model.ChessboardPoint;
import model.PlayerColor;

public class JChessComponent extends JComponent {
    private ChessboardPoint point;
    private PlayerColor owner;
    private boolean selected;

    public JChessComponent(PlayerColor owner, int size) {
        if(owner != null){
            this.owner = owner;
        }else{
            this.owner = PlayerColor.BLUE;
        }
        this.selected = false;
        setSize(size/2, size/2);
        setLocation(0,0);
        setVisible(true);
    }

    public JChessComponent(ChessboardPoint point, PlayerColor owner){
        this.point = point;
        if(owner != null){
            this.owner = owner;
        }else{
            this.owner = PlayerColor.BLUE;
        }
    }
    
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}

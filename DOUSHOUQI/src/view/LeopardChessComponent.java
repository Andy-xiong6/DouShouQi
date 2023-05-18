package view;

import model.ChessboardPoint;
import model.PlayerColor;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LeopardChessComponent extends JChessComponent{
    private PlayerColor owner;
    private boolean selected;

    public LeopardChessComponent(PlayerColor owner, int size) {
        this.owner = owner;
        this.selected = false;
        setSize(size/2, size/2);
        setLocation(0,0);
        setVisible(true);
    }

    public LeopardChessComponent(ChessboardPoint point, PlayerColor owner) {
        this.owner = owner;
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
        String filePath = null;
        if(owner == PlayerColor.BLUE){
            filePath = "DOUSHOUQI\\resource\\leopard-blue.png";
        }else if (owner == PlayerColor.RED){
            filePath = "DOUSHOUQI\\resource\\leopard-red.png";
        }
        
        File file = new File(filePath);
        
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(image, 5, 5, getWidth()*8/9, getHeight()*8/9, null);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
    
}

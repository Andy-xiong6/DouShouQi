package view;


import model.ChessboardPoint;
import model.PlayerColor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TigerChessComponent extends JChessComponent{
    private PlayerColor owner;
    private boolean selected;

    public TigerChessComponent(PlayerColor owner, int size) {
        this.owner = owner;
        this.selected = false;  
        setSize(size/2, size/2);
        setLocation(0,0);
        setVisible(true);

    }

    public TigerChessComponent(ChessboardPoint point, PlayerColor owner) {
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
            filePath = "D:\\Users\\xiong\\Desktop\\DouShouQi\\DOUSHOUQI\\resource\\tiger-blue.png";
        }else if (owner == PlayerColor.RED){
            filePath = "D:\\Users\\xiong\\Desktop\\DouShouQi\\DOUSHOUQI\\resource\\tiger-red.png";
        }
        
        File file = new File(filePath);

        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}

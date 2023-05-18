package view;

import javax.swing.JComponent;

import model.ChessboardPoint;
import model.PlayerColor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DenComponent extends JComponent {
    public ChessboardPoint point;
    public PlayerColor owner;

    public DenComponent(PlayerColor owner) {
        this.owner = owner;
    }

    @Override
    protected void paintComponent(Graphics g) {
        String filePath = null;
        if(owner == PlayerColor.BLUE){
            filePath = "DOUSHOUQI\\resource\\den-blue.png";
        }else if (owner == PlayerColor.RED){
            filePath = "DOUSHOUQI\\resource\\den-red.png";
        }
        
        File file = new File(filePath);

        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

    }
    
}

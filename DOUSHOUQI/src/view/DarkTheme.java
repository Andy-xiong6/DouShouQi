package view;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class DarkTheme implements Theme{
    @Override
    public Color getBackgroundColor() {
        return Color.BLACK;
    }

    @Override
    public void changeButtonAppearance(JButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(Color.DARK_GRAY);
    }

    @Override
    public Image getBackgroundImage() {
        String filePath = "DOUSHOUQI\\resource\\background.png";
        File file = new File(filePath);
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    
}

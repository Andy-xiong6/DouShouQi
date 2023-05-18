package theme;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class RedTheme implements Theme{
    @Override
    public Color getBackgroundColor() {
        return Color.BLACK;
    }

    @Override
    public void changeButtonAppearance(JButton button) {
        button.setForeground(Color.WHITE);
        Color darkRed = new Color(139, 0, 0);
        button.setBackground(darkRed);
    }

    @Override
    public Image getBackgroundImage() {
        String filePath = "DOUSHOUQI\\resource\\background1.png";
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

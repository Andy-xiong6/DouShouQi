package theme;
import java.awt.Color;
import java.awt.Image;

import javax.swing.JButton;

public interface Theme {
    public Color getBackgroundColor();

    public void changeButtonAppearance(JButton button);

    public Image getBackgroundImage();

}

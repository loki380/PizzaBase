package viewandcontroller;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel{
    private Image imga;

    BackgroundPanel(ImageIcon img) {
        imga = img.getImage();
        repaint();

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imga, 0, 0,getWidth(), getHeight(), this);
    }
}

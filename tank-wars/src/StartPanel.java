import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartPanel extends JPanel {


    JButton start;
    BufferedImage background;



    public StartPanel() throws IOException {
        start = new JButton("Start");
        start.setFocusable(false);
        add(start);
        background = ImageIO.read(new File("src\\start.jpg"));
    }



    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g.setFont(new Font("Jokerman", Font.PLAIN, 44));
        g.setColor(new Color(98, 36, 137));
        g.drawString("Tank", 460, 280);
        g.setFont(new Font("Algerian", Font.PLAIN, 88));
        g.setColor(new Color(203, 26, 107));
        g.drawString("WARS", 460, 358);

        start.setBounds(390, 430, 390, 100);
      start.setFont(new Font("Jokerman", Font.PLAIN, 50));
      start.setForeground(new Color(98, 36, 137));
       start.setContentAreaFilled(false);
        start.setFocusPainted(false);
       start.setBorderPainted(false);

    }
}

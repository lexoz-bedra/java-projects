import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Landscape {

    static int groundY = Panel.frameHeight / 40 * 29 - Tank.height;
    BufferedImage background;
    static Boolean[][] exists = new Boolean[Panel.frameWidth][Panel.frameHeight];


    public Landscape() throws IOException {
        for (int i = 0; i < Panel.frameWidth; i++) {
            for (int j = 0; j < groundY + Tank.height; j++) {
                exists[i][j] = false;
            }
            for (int j = groundY + Tank.height; j < Panel.frameHeight; j++) {
                exists[i][j] = true;
            }
        }
        background = ImageIO.read(new File("src\\background.png"));
    }

    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, Panel.frameWidth, Panel.frameHeight, null);
        g.setColor(new Color(76, 87, 55));
        for (int i = 0; i < Panel.frameWidth; i++) {
            for (int j = groundY + Tank.height; j < Panel.frameHeight; j++) {
                if (exists[i][j]) {
                    g.fillRect(i, j, 1, 1);
                }
            }
        }
    }

}

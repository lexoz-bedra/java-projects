import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tank {


    static int width = 50;
    static int height = 30;
    BufferedImage tank1;
    BufferedImage tank2;
    BufferedImage dulo1;
    BufferedImage dulo2;
    int x;
    int y;
    int q;
    int duloWidth = 150;
    int duloHeight = 150;
    int duloX = (x - width / 3) * q;
    int duloY = y - duloHeight;
    int healthX;
    int healthY;
    double alpha;
    int health = 1000;




    public Tank(int x, int y, int q) throws IOException {

        dulo1 = ImageIO.read(new File("src\\leftdulo1.png"));
        dulo2 = ImageIO.read(new File("src\\rightdulo1.png"));

        tank1 = ImageIO.read(new File("src\\lefttank1.png"));
        tank2 = ImageIO.read(new File("src\\righttank1.png"));
        this.x = x;
        this.y = y;
        if (q == 1) {
            healthX = 100;
        } else if (q == -1) {
            healthX = Panel.frameWidth - 100;
        }
        healthY = 60;
        this.q = q;
        alpha = 0;
    }



    public void draw(Graphics g) {
        if (q == 1) {
            g.drawImage(tank1, x, y - height * 2, width * 3, height * 3, null);
            double angleInDegrees = -alpha;
            double angleInRadians = Math.toRadians(angleInDegrees);
            double locationX = (dulo1.getWidth() / 2 - 30);
            double locationY = dulo1.getHeight() / 2;
            AffineTransform tx = AffineTransform.getRotateInstance(angleInRadians, locationX, locationY);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
            g.drawImage(op.filter(dulo1, null), x + width / 3, y - duloHeight, duloWidth, duloHeight, null);
        } else if (q == -1) {
            g.drawImage(tank2, x, y - height * 2, width * 3, height * 3, null);
            double angleInDegrees = -alpha;
            double angleInRadians = Math.toRadians(angleInDegrees);
            double locationX = dulo2.getWidth() / 2 - 30;
            double locationY = dulo2.getHeight() / 2;
            AffineTransform tx = AffineTransform.getRotateInstance(angleInRadians, locationX, locationY);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

            g.drawImage(op.filter(dulo2, null), x - width / 3, y - duloHeight, duloWidth, duloHeight, null);
        }

        if (q == -1) {
            g.setColor(new Color(19, 157, 113));
        } else if (q == 1) {
            g.setColor(new Color(54, 25, 157));
        }
        if (q == 1) {
            g.fillRect(healthX, healthY, health / 7, 30);
            g.setColor(Color.BLACK);
            g.drawRect(healthX, healthY, 142, 30);
        } else if (q == -1) {
            g.fillRect(healthX - health / 7, healthY, health / 7, 30);
            g.setColor(Color.BLACK);
            g.drawRect(healthX - 142, healthY, 142, 30);
        }

            if (q == 1) {
                g.drawImage(tank1, x, y - height * 2, width * 3, height * 3, null);
            } else if (q == -1) {
                g.drawImage(tank2, x, y - height * 2, width * 3, height * 3, null);
            }
    }



    public void crash(Shoot shoot) {
        if (shoot.X >= x - 29 && shoot.X <= x + width && shoot.Y >= y && shoot.Y <= y + height) {
            health -= shoot.damage;
        }
    }
}
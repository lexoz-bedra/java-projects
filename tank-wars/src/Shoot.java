import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Shoot {


    BufferedImage bullet;
    static final double G = 6.8;
    double v0;
    double vx;
    double vy;
    double x;
    double y;
    double x0;
    double y0;
    double alpha;
    long t0;
    double v;
    int damage;
    double X;
    double Y;
    double distance;
    double phi;


    public Shoot(double x0, double y0, double v0, double alpha) throws IOException {//даем значения скоростям, углу, координатам, времени и урону
        this.x0 = x0;
        this.x = x0;
        this.y0 = y0;
        this.y = y0;
        this.v0 = v0;
        X = x0;
        Y = y0;
        this.alpha = Math.toRadians(alpha);
        damage = (int) v0 * 13 / 3;
        bullet = ImageIO.read(new File("src\\bullet.png"));
        vx = this.v0 * Math.cos(this.alpha);
        vy = this.v0 * Math.sin(this.alpha);
        t0 = (int) (2 * v0 * Math.sin(this.alpha) / G);
        phi = this.alpha * Math.PI / 2;
    }


    public void update(double dt) {
        vy -= G * dt;
        x += vx * dt;
        y = y0 - vy * dt + G * dt / 2;
        v = Math.sqrt(vx * vx + vy * vy);
        X = x;
        Y = y;
        damage = (int) v * 13 / 3;
        phi = Math.toDegrees(Math.atan(vx / vy)) * Math.PI / 2;
    }


    public boolean shoot(Graphics g, double dt) {
        System.out.println(3);
        if (!checkCollision()) {
            System.out.println(1 + "  " + (Y <= y0));
            update(dt);
            draw(g);
            return true;
        }
        return false;
    }


    public void destroy() {
        for (int i = 0; i < Landscape.exists.length; i++) {
            for (int j = Landscape.groundY + Tank.height; j < Panel.frameHeight; j++) {
                distance = Math.sqrt(Math.pow((x - i), 2) + Math.pow((y - j), 2));
                if ((distance < 65) && (Landscape.exists[i][j])) {
                    Landscape.exists[i][j] = false;
                }
            }
        }
    }


    public void start() {
        x = x0;
        y = y0;
        v = v0;
        vx = this.v0 * Math.cos(this.alpha);
        vy = this.v0 * Math.sin(this.alpha);
    }


    public void draw(Graphics g) {
        double angleInDegrees = this.phi;
        double angleInRadians = Math.toRadians(angleInDegrees);
        double locationX = bullet.getWidth() / 2;
        double locationY = bullet.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(angleInRadians, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        g.drawImage(op.filter(bullet, null), (int) this.x, (int) this.y, 60, 60, null);
    }


    public boolean checkCollision() {
        if (x >= 0 && x <= Panel.frameWidth && y >= 0 && y <= Panel.frameHeight) {
            if (Landscape.exists[(int) x + 15][(int) (y + 30)]) {
                return true;
            }
        }
        return false;
    }
}


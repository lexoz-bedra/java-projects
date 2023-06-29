import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Panel extends JPanel implements KeyEventDispatcher {


    Graphics g;
    BufferedImage over;
    Tank tank1 = new Tank(200, Landscape.groundY, 1);
    Tank tank2 = new Tank(Panel.frameWidth - 300, Landscape.groundY, -1);
    Landscape landscape = new Landscape();
    static Shoot shoot1;
    static Shoot shoot2;
    double dt;
    double t1 = 0;
    double t2 = 0;
    boolean left = false;
    boolean right = false;
    boolean a = false;
    boolean d = false;
    boolean w = false;
    boolean s = false;
    boolean up = false;
    boolean down = false;
    JButton restart;
    JButton exit;
    static int frameWidth = 1200;
    static int frameHeight = 800;


    public Panel() throws IOException {

        over = ImageIO.read(new File("src\\over.jpg"));
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(this);
        dt = 0.1;
        restart = new JButton("restart");
        restart.addActionListener(e -> {
            try {
                tank1 = new Tank(200, Landscape.groundY, 1);
            } catch (IOException ioException) {
            }
            tank1.health = 1000;
            try {
                tank2 = new Tank(Panel.frameWidth - 300, Landscape.groundY, -1);
            } catch (IOException ioException) {
            }
            tank2.health = 1000;
            try {
                landscape = new Landscape();
            } catch (IOException ioException) {
            }
            restart.setVisible(false);
            exit.setVisible(false);
            if (g != null) {
                landscape.draw(g);
                g.setColor(Color.BLUE);
                tank1.draw(g);
                tank2.draw(g);
            }
        });
        add(restart);
        exit = new JButton("Exit");

        exit.addActionListener(e -> System.exit(0));
        add(exit);
        restart.setVisible(false);
        exit.setVisible(false);
    }


    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        landscape.draw(g);
        g.setColor(Color.BLUE);
        tank1.draw(g);
        tank2.draw(g);

        exit.setBounds(650, 390, 150, 75);
        restart.setBounds(400, 390, 150, 75);

        if (a) {
            tank1.x -= 2;
        }
        if (!a && !d) {
        }
        if (d) {
            tank1.x += 2;
        }
        if (left) {
            tank2.x -= 2;
        }
        if (right) {
            tank2.x += 2;
        }
        if (!left && !right) {
        }
        if (up) {
            if (tank2.alpha > -90 && tank2.alpha <= 0) {
                tank2.alpha--;
            }
        }
        if (down) {
            if (tank2.alpha >= -90 && tank2.alpha < 0) {
                tank2.alpha++;
            }
        }

        if (w) {
            if (tank1.alpha >= 0 && tank1.alpha < 90) {
                tank1.alpha++;
            }
        }

        if (s) {
            if (tank1.alpha > 0 && tank1.alpha <= 90) {
                tank1.alpha--;
            }
        }

        if (tank1.health <= 0 || tank2.health <= 0) {
            g.fillRect(0, 0, getWidth(), getHeight());
            g.drawImage(over, 0, 0, getWidth(), getHeight(), null);
            g.setColor(new Color(214, 44, 194));
            g.setFont(new Font("Jokerman", Font.PLAIN, 80));
            g.drawString("Game over!", 420, 290);
            restart.setVisible(true);
            exit.setVisible(true);

        } else {
            g.setColor(Color.BLACK);
            if (shoot1 != null) {
                shoot1.start();
            }

            if (shoot2 != null) {
                shoot2.start();
            }
            if (shoot1 != null) {
                if (!shoot1.checkCollision()) {
                    shoot1.shoot(g, t1);
                    shoot1.destroy();
                    if (tank1.health > 0 && tank2.health > 0) {
                        tank2.crash(shoot1);
                    }
                }
                t1 += dt;
            }
        }
        if (shoot2 != null) {
            if (!shoot2.checkCollision()) {
                shoot2.shoot(g, t2);
                shoot2.destroy();
                if (tank1.health > 0 && tank2.health > 0) {
                    tank1.crash(shoot2);
                }
                t2 += dt;
            }
        }
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                a = true;
                repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                d = true;
                repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                left = true;
                repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                right = true;
                repaint();
            }

            if (e.getKeyCode() == KeyEvent.VK_W) {
                w = true;
                repaint();
            }

            if (e.getKeyCode() == KeyEvent.VK_S) {
                s = true;
                repaint();
            }

            if (e.getKeyCode() == KeyEvent.VK_UP) {
                up = true;
                repaint();
            }

            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                down = true;
                repaint();
            }
        }

        if (e.getID() == KeyEvent.KEY_RELEASED) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                a = false;
                repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                d = false;
                repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                left = false;
                repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                right = false;
                repaint();
            }

            if (e.getKeyCode() == KeyEvent.VK_W) {
                w = false;
                repaint();
            }

            if (e.getKeyCode() == KeyEvent.VK_S) {
                s = false;
                repaint();
            }

            if (e.getKeyCode() == KeyEvent.VK_UP) {
                up = false;
                repaint();
            }

            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                down = false;
                repaint();
            }

            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                t1 = 0;
                try {
                    shoot1 = new Shoot(tank1.x, tank1.y - 100, 100, tank1.alpha);
                } catch (IOException ioException) {
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                t2 = 0;
                try {
                    shoot2 = new Shoot(tank2.x, tank2.y - 100, -100, tank2.alpha);
                } catch (IOException ioException) {
                }
            }
        }
        return false;
    }
}

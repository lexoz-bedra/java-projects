import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

public class MainFrame extends JFrame implements MouseListener, MouseMotionListener {
    ArrayList<Integer> xs = new ArrayList<>();
    ArrayList<Integer> ys = new ArrayList<>();
    ArrayList<Line> lines = new ArrayList<>();
    //ArrayList<Integer> xss = new ArrayList<>();
    //ArrayList<Integer> yss = new ArrayList<>();
    Point firstPoint;
    Point secondPoint;
    //JButton changeColor = new JButton("color");


    int stroke = 4;
    Random random = new Random();
    Color color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));




    public MainFrame() {
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        //setLayout(new BorderLayout());

    }

    public static void drawLine(Line l, Graphics g) {
        g.drawLine(l.firstPoint.x, l.firstPoint.y, l.secondPoint.x, l.secondPoint.y);
    }

    @Override
    public void paint(Graphics g) {
        BufferStrategy bufferStrategy = getBufferStrategy();        // Обращаемся к стратегии буферизации
        if (bufferStrategy == null) {                               // Если она еще не создана
            createBufferStrategy(2);                                // то создаем ее
            bufferStrategy = getBufferStrategy();                   // и опять обращаемся к уже наверняка созданной стратегии
        }
        g = bufferStrategy.getDrawGraphics();                       // Достаем текущую графику (текущий буфер)
        g.setColor(getBackground());                                // Выставялем цвет в цвет фона
        g.fillRect(0, 0, getWidth(), getHeight());


        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < xs.size(); i++) {
            g2d.setStroke(new BasicStroke(4));
            g.setColor(color);
            g.drawOval(xs.get(i), ys.get(i), 10, 10);
        }

        //lines.add(new Line(new Point(-1, -1), new Point(-1, -1)));
        for (int i = 1; i < lines.size(); i++) {
            g2d.setStroke(new BasicStroke(lines.get(i).stroke));
            color = lines.get(i).color;
            g.setColor(color);
            drawLine(lines.get(i), g);
        }


        g.dispose();
        bufferStrategy.show();
    }

    public static void main(String[] args) {
     MainFrame frame = new MainFrame();
        //frame.add(frame.changeColor, BorderLayout.SOUTH);
    //frame.add(strokeIncrease);
    //frame.add(strokeDecrease);
    //frame.add(changeColor);
    //strokeIncrease.setPreferredSize(new Dimension(50, 20));
    //strokeIncrease.setPreferredSize(new Dimension(50, 20));
    //changeColor.setPreferredSize(new Dimension(50, 20));


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX());
        xs.add(e.getX());
        System.out.println(e.getY());
        ys.add(e.getY());
        //System.out.println(xs);
        //System.out.println(ys);
        repaint();

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("Pressed!");
        firstPoint = new Point(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //firstPoint = null;
        //secondPoint = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    @Override
    public void mouseDragged(MouseEvent e) {
        //xss.add(e.getX());
        //System.out.println("Dragged!");
        //yss.add(e.getY());
        secondPoint = new Point(e.getX(), e.getY());
        lines.add(new Line(firstPoint, secondPoint, color, stroke));
        firstPoint = secondPoint;
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}

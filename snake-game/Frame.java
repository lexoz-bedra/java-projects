import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Frame extends JFrame {

    BufferedImage snakeLogo = ImageIO.read(new File("logo.png"));
    Field field;
    Fruit fruit;
    LinkedList<Cell> snake = new LinkedList<>();
    Direction direction;
    int points = 0;
    boolean isGameOver = false;

    Frame(Direction direction) throws IOException {
        this.setSize(800, 820);
        this.setLocation(600, 100);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Snikk");
        this.setIconImage(snakeLogo);
        this.setVisible(true);

        this.direction = direction;

        field = new Field(this);
        fruit = new Fruit(this);

        for (int i = 0; i < 50; i++)
            snake.add(new Cell((int) (field.sizeOfCell * 2.7 * field.size + field.fieldXStart - 1.3 / field.size * i), field.fieldYStart + field.sizeY / 2 * field.sizeOfCell));

        createBufferStrategy(2);
    }
    Random random2 = new Random();
    public int c1 = random2.nextInt(255);
    public int c2 = random2.nextInt(255);
    public int c3 = random2.nextInt(255);
    public int c4 = random2.nextInt(255);
    public int c5 = random2.nextInt(255);
    public int c6 = random2.nextInt(255);

    @Override
    public void paint(Graphics g) {

        BufferStrategy bufferStrategy = getBufferStrategy();        // Обращаемся к стратегии буферизации
        if (bufferStrategy == null) {                               // Если она еще не создана
            createBufferStrategy(2);                                // то создаем ее
            bufferStrategy = getBufferStrategy();                   // и опять обращаемся к уже наверняка созданной стратегии
        }
        g = bufferStrategy.getDrawGraphics();                      // Достаем текущую графику (текущий буфер)
        //Random random = new Random();
        //Color color = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
        g.setColor(Color.red);                                // Выставялем цвет в цвет фона
        g.fillRect(0, 0, getWidth(), getHeight());                  // Зачищаем все окно фоновым цветом

        // поле
        for (int m = 0; m < field.sizeX; m++)
            for(int n = 0; n < field.sizeY; n++) {
                if ((m % 2 == 0 && n % 2 != 0) || (m % 2 != 0 && n % 2 == 0))
                    g.setColor(new Color(c1, c3, c5));
                else g.setColor(new Color(c2, c4, c6));

                int x = field.fieldXStart + m * field.sizeOfCell;
                int y = field.fieldYStart + n * field.sizeOfCell;

                g.fillRect(x, y, field.sizeOfCell, field.sizeOfCell);
            }

        // верхнняя полоска с меню
        g.setColor(new Color(61, 208, 226));
        g.fillRect(0,0, this.getWidth(), this.getHeight() / 10);

        g.setColor(new Color(6, 227, 146));
        // верхння граница
        g.fillRect(0, this.getHeight() / 10, this.getWidth(), this.getHeight() / 22);
        // левая граница
        g.fillRect(0, this.getHeight() / 10, this.getWidth() / 20, this.getHeight() - this.getHeight() / 10);
        // нижняя граница
        g.fillRect(0,  this.getHeight() - this.getHeight() / 20, this.getWidth(), this.getHeight() / 20);
        // правая граница
        g.fillRect(this.getWidth() - this.getWidth() / 20, this.getHeight() / 10, this.getWidth() / 20, this.getHeight() - this.getHeight() / 10);

        // фрукт
        g.drawImage(fruit.fruitImage, fruit.xapple - 10 / field.size, fruit.yapple - 15 / field.size, 80 / field.size, 80 / field.size, null);

        // змейка
        g.setColor(Color.red);
        double a = 0;
        for (Cell cell: snake) {
            g.fillOval(cell.x, (int) (cell.y + 4 + a * 8 / 14), (int) (field.sizeOfCell - 10 - a), (int) (field.sizeOfCell - 10 - a));
            if (a < 20) a += 0.025;
        }

        Cell cell = snake.getFirst();

                switch (direction) {
                    case RIGHT:
                        g.fillOval(cell.x - 5, cell.y - 12 / field.size, 40 / field.size, 40 / field.size);
                        g.fillOval(cell.x - 5, cell.y + 28 / field.size, 40 / field.size, 40 / field.size);
                        g.setColor(Color.WHITE);
                        g.fillOval(cell.x, cell.y - 7 / field.size, 30 / field.size, 30 / field.size);
                        g.fillOval(cell.x, cell.y + 33 / field.size, 30 / field.size, 30 / field.size);

                        g.setColor(Color.BLACK);
                        g.fillOval(cell.x + 19 / field.size, cell.y + 3 / field.size, 10 / field.size, 10 / field.size);
                        g.fillOval(cell.x + 19 / field.size, cell.y + 43 / field.size, 10 / field.size, 10 / field.size);
                        break;
                    case LEFT:
                        g.fillOval(cell.x + 15 / field.size, cell.y - 12 / field.size, 40 / field.size, 40 / field.size);
                        g.fillOval(cell.x + 15 / field.size, cell.y + 28 / field.size, 40 / field.size, 40 / field.size);
                        g.setColor(Color.WHITE);
                        g.fillOval(cell.x + 20 / field.size, cell.y - 7 / field.size, 30 / field.size, 30 / field.size);
                        g.fillOval(cell.x + 20 / field.size, cell.y + 33 / field.size, 30 / field.size, 30 / field.size);

                        g.setColor(Color.BLACK);
                        g.fillOval(cell.x + 21 / field.size, cell.y + 3 / field.size, 10 / field.size, 10 / field.size);
                        g.fillOval(cell.x + 21 / field.size, cell.y + 43 / field.size, 10 / field.size, 10 / field.size);
                        break;
                    case UP:
                        g.fillOval(cell.x - 13 / field.size, cell.y + 15 / field.size, 40 / field.size, 40 / field.size);
                        g.fillOval(cell.x + 25 / field.size, cell.y + 15 / field.size, 40 / field.size, 40 / field.size);
                        g.setColor(Color.WHITE);
                        g.fillOval(cell.x - 8 / field.size, cell.y + 20 / field.size, 30 / field.size, 30 / field.size);
                        g.fillOval(cell.x + 30 / field.size, cell.y + 20 / field.size, 30 / field.size, 30 / field.size);

                        g.setColor(Color.BLACK);
                        g.fillOval(cell.x + 2, cell.y + 21 / field.size, 10 / field.size, 10 / field.size);
                        g.fillOval(cell.x + 40 / field.size, cell.y + 21 / field.size, 10 / field.size, 10 / field.size);
                        break;
                    case DOWN:
                        g.fillOval(cell.x - 15 / field.size, cell.y, 40 / field.size, 40 / field.size);
                        g.fillOval(cell.x + 25 / field.size, cell.y, 40 / field.size, 40 / field.size);
                        g.setColor(Color.WHITE);
                        g.fillOval(cell.x - 10 / field.size, cell.y + 5 / field.size, 30 / field.size, 30 / field.size);
                        g.fillOval(cell.x + 30 / field.size, cell.y + 5 / field.size, 30 / field.size, 30 / field.size);

                        g.setColor(Color.BLACK);
                        g.fillOval(cell.x, cell.y + 23 / field.size, 10 / field.size, 10 / field.size);
                        g.fillOval(cell.x + 40 / field.size, cell.y + 23 / field.size, 10 / field.size, 10 / field.size);
                        break;
            }




        g.drawImage(fruit.fruitImage, 20,40, 35,35,null);
        g.setColor(Color.white);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 35));
        g.drawString(Integer.toString(points), 60, 70);

        g.dispose();                // Освободить все временные ресурсы графики (после этого в нее уже нельзя рисовать)
        bufferStrategy.show();      // Сказать буферизирующей стратегии отрисовать новый буфер (т.е. поменять показываемый и обновляемый буферы местами)
    }

    public void setDirection(Direction direction){
        this.direction = direction;
    }
}

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Fruit {
    BufferedImage fruitImage;

    int xapple;
    int yapple;

    Fruit(Frame frame) throws IOException {
        fruitImage = ImageIO.read(new File("apple.png"));

        xapple = frame.field.fieldXStart + (frame.field.sizeX - 2 * frame.field.size) * frame.field.sizeOfCell;
        yapple = frame.field.fieldYStart + frame.field.sizeY / 2 * frame.field.sizeOfCell;
    }



    public void eatingApple(Frame frame, Direction direction){
        //System.out.println(frame.snake.getFirst().x == x);
        if (((frame.snake.getFirst().x >= xapple-15)&&(frame.snake.getFirst().x <= xapple+15)) && ((frame.snake.getFirst().y >= yapple-15)&&(frame.snake.getFirst().y <= yapple+15))){
            System.out.println(frame.snake.getFirst().x == xapple);
            changingLocation(frame);
            frame.points+=1;
            for (int i = 0; i < 20; i++)
            frame.snake.addFirst(new Cell(frame.snake.getFirst().x + 2 * direction.dx,frame.snake.getFirst().y + 2 * direction.dy));
        }
    }
    public void changingLocation(Frame frame) {
        for(int i=frame.snake.size()-1;i>0;i--) {
            if ((frame.snake.getFirst().x != xapple)&&(frame.snake.getFirst().y != yapple)) {
                xapple = frame.field.fieldXStart + new Random().nextInt(frame.field.sizeX) * frame.field.sizeOfCell - 5;
                yapple = frame.field.fieldYStart + new Random().nextInt(frame.field.sizeY) * frame.field.sizeOfCell - 5;
            }
        }
    }
}

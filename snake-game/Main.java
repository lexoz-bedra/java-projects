import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Direction direction = Direction.RIGHT;
        Frame frame = new Frame(direction);

        KeyboardFocusManager keyboardManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        KeyEventDispatcher keyEventDispatcher = new KeyEventDispatcher(frame);
        keyboardManager.addKeyEventDispatcher(keyEventDispatcher);

        while (true){
            if (frame.isGameOver) keyEventDispatcher.isMoving = false;
            if (keyEventDispatcher.isMoving) {
                frame.snake.removeLast();
                if ((frame.snake.getFirst().x - 6 - frame.field.fieldXStart) % frame.field.sizeOfCell == 0 &&
                        (frame.snake.getFirst().y - frame.field.fieldYStart) % frame.field.sizeOfCell == 0)
                    direction = keyEventDispatcher.direction;

                frame.snake.addFirst(new Cell(frame.snake.getFirst().x + 2 * direction.dx,frame.snake.getFirst().y + 2 * direction.dy));
                checkCollision(frame);
                frame.repaint();
                frame.fruit.eatingApple(frame, direction);
            }
            Thread.sleep(4);
        }
    }

    private static void checkCollision(Frame frame){
        if (frame.snake.getFirst().x + 48 >= frame.getWidth() - frame.field.fieldXStart ||
        frame.snake.getFirst().x + 1 <= frame.field.fieldXStart || frame.snake.getFirst().y + 5 <= frame.field.fieldYStart ||
        frame.snake.getFirst().y + 50 >= frame.getHeight() - frame.getHeight() / 20)
        {
            frame.isGameOver = true;
            System.exit(1);

        }
        for(int i=frame.snake.size()-1;i>0;i--){
            if ((frame.snake.getFirst().x == frame.snake.get(i).x)&&(frame.snake.getFirst().y == frame.snake.get(i).y)){
                frame.isGameOver = true;
                System.exit(1);
            }
        }
    }
}

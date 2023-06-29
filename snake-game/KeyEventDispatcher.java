import java.awt.event.KeyEvent;

public class KeyEventDispatcher implements java.awt.KeyEventDispatcher {

    Frame frame;
    boolean isMoving = false;
    Direction direction;

    KeyEventDispatcher(Frame frame){
        this.frame = frame;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && e.getID() == KeyEvent.KEY_PRESSED && direction != Direction.LEFT) {
            isMoving = true;
            direction = Direction.RIGHT;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && e.getID() == KeyEvent.KEY_PRESSED && direction != Direction.RIGHT && isMoving) {
            isMoving = true;
            direction = Direction.LEFT;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN && e.getID() == KeyEvent.KEY_PRESSED && direction != Direction.UP) {
            isMoving = true;
            direction = Direction.DOWN;
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP && e.getID() == KeyEvent.KEY_PRESSED && direction != Direction.DOWN) {
            isMoving = true;
            direction = Direction.UP;
        }

        frame.setDirection(direction);
        return false;
    }
}

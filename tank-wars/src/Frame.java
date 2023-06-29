import javax.swing.*;
import java.io.IOException;

public class Frame extends JFrame {


    int frameWidth = Panel.frameWidth;
    int frameHeight = Panel.frameHeight;
    Panel panel;


    public Frame() throws IOException {
        setSize(frameWidth, frameHeight);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        StartPanel st = new StartPanel();
        st.start.addActionListener(e -> {
            try {
                panel = new Panel();
                add(panel);
            } catch (IOException ioException) {
                System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
            }
            st.setVisible(false);
            repaint();
        });
        add(st);
        setVisible(true);

    }
}

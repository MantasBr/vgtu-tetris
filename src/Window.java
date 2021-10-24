import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class Window {
    private static JFrame window;
    private static Board board;

    private static Window instance;

    private Window(String name, int width, int height) {
        window = new JFrame(name);
        window.setSize(width, height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);

        board = new Board();

        window.add(board);
        window.setVisible(true);
        board.setFocusable(true);
        board.addKeyListener(new KeyController());
    }

    public static Window getInstance() {
        assert instance == null : "Window doesn't exist";

        return instance;
    }

    public static void repaint() {
        board.requestFocus(true);
        board.repaint();
    }

    public static Window createInstance(String name, int width, int height) {
        assert instance != null : "Window already exists";

        instance = new Window(name, width, height);
        return instance;
    }

}

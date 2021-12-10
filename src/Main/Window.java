package Main;

import javax.swing.*;

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
        PieceObserverManager.addObserver(board);

        KeyController keyController = new KeyController();

        board.addKeyListener(keyController);
        PieceObserverManager.addObserver(keyController);
    }

    public static Window getInstance() {
        assert instance == null : "Main.Window doesn't exist";

        return instance;
    }

    public static Window createInstance(String name, int width, int height) {
        assert instance != null : "Main.Window already exists";

        instance = new Window(name, width, height);
        return instance;
    }

    public static void repaint() {
        board.requestFocus(true);
        board.repaint();
    }
}

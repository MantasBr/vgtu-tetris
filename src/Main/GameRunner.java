package Main;

import Pieces.Piece;

public class GameRunner implements CurrentPieceObserver {
    private final static double CHECKS_PER_SECOND = 30;

    private static double secondsForPieceToFall = 1;
    private static double lastPieceFall = System.currentTimeMillis();
    private static boolean running = false;
    private static double elapsedTimeInSeconds;
    private static GameRunner instance;
    private static Piece currentPiece = null;

    public static void main(String[] args) throws InterruptedException {
        int numberOfRows = Board.getRows();
        int numberOfColumns = Board.getColumns();
        int blockSizePx = Board.getBlockSizePx();
        int topBarPx = 24;
        int windowWidth = blockSizePx * numberOfColumns + blockSizePx/2;
        int windowHeight = blockSizePx * numberOfRows + topBarPx + blockSizePx/2;

        Window newWindow = Window.createInstance("Tetris", windowWidth, windowHeight);
        createInstance();
        PieceObserverManager.addObserver(instance);
        PieceFactory.createInstance();
        Board.createPiece();
        run();
    }

    public static void run() throws InterruptedException {
        running = true;
        while (running) {
            elapsedTimeInSeconds = (System.currentTimeMillis() - lastPieceFall) / 1000;
            if (elapsedTimeInSeconds > secondsForPieceToFall) {
                lastPieceFall = System.currentTimeMillis();
                currentPiece.fall();
                update();
            }
            Thread.sleep((long) ((1 / CHECKS_PER_SECOND) * 1000));
        }
    }

    public static void update() {
        Window.repaint();
    }

    public static void stopTheGame() {
        running = false;
    }

    public static boolean isGameRunning() {
        return running;
    }

    public static void setPieceFallTime(double seconds) {
        secondsForPieceToFall = seconds;
    }

    public static GameRunner getInstance() {
        assert instance == null : "Game loop doesn't exist";

        return instance;
    }

    public static GameRunner createInstance() throws InterruptedException {
        assert instance != null : "Game loop already exists";

        instance = new GameRunner();
        return instance;
    }

    @Override
    public void updateCurrentPiece(Piece piece) {
        currentPiece = piece;
    }
}

public class GameRunner {
    // Usual tetris is 10 x 20
    private final static double CHECKS_PER_SECOND = 30;

    private static double secondsForPieceToFall = 1;
    private static double lastPieceFall = System.currentTimeMillis();
    private static boolean running = false;
    private static double elapsedTimeInSeconds;

    public static void main(String[] args) throws InterruptedException {
        Window newWindow = Window.createInstance("Tetris", 32 * 11, 32 * 22);
        run();
    }

    public static void run() throws InterruptedException {
        running = true;
        while (running) {
            elapsedTimeInSeconds = (System.currentTimeMillis() - lastPieceFall) / 1000;
            if (elapsedTimeInSeconds > secondsForPieceToFall) {
                lastPieceFall = System.currentTimeMillis();
                Board.getCurrentPiece().fall();
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
}

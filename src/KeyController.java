import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class KeyController extends KeyAdapter {

    public void keyPressed(KeyEvent e) {
        if (GameRunner.isGameRunning() == false) {
            return;
        }

        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                Board.getCurrentPiece().shiftRight();
                break;
            case KeyEvent.VK_LEFT:
                Board.getCurrentPiece().shiftLeft();
                break;
            case KeyEvent.VK_DOWN:
                GameRunner.setPieceFallTime(0.1);
                break;
            case KeyEvent.VK_R:
                Board.getCurrentPiece().rotate();
                break;
            default:
                break;
                //speed up
        }

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            GameRunner.setPieceFallTime(1);
        }
    }
}

package Main;

import Pieces.Piece;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class KeyController extends KeyAdapter implements CurrentPieceObserver {
    Piece currentPiece = Board.getCurrentPiece();

    @Override
    public void keyPressed(KeyEvent e) {
        if (GameRunner.isGameRunning() == false) {
            return;
        }

        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                currentPiece.shiftRight();
                break;
            case KeyEvent.VK_LEFT:
                currentPiece.shiftLeft();
                break;
            case KeyEvent.VK_DOWN:
                GameRunner.setPieceFallTime(0.1);
                break;
            case KeyEvent.VK_R:
                currentPiece.rotate();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (GameRunner.isGameRunning() == false) {
            return;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            GameRunner.setPieceFallTime(1);
        }
    }

    @Override
    public void updateCurrentPiece(Piece piece) {
        currentPiece = piece;
    }
}

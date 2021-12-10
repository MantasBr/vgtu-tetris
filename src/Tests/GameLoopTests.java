package Tests;

import Main.Board;
import Main.GameRunner;
import org.junit.Test;

public class GameLoopTests {
    @Test
    public void testGameOver() {
        Board board = new Board();
        board.chooseRandomPiece();
        board.createPiece();
        assert GameRunner.isGameRunning() == false : "GameOver test failed";
    }
}

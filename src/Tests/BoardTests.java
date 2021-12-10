package Tests;

import Main.Board;
import Pieces.Piece;
import org.junit.Test;

public class BoardTests {
    @Test
    public void testPieceCreating() {
        Board board = new Board();
        Piece randomPiece = board.chooseRandomPiece(0, 0);
        board.createPiece();
        int [][] pieceArray = randomPiece.getPieceArray();
        int [][] boardArray = board.getBoard();

        for (int i = 0; i < randomPiece.getPieceHeight(); i++) {
            for (int j = 0; j < randomPiece.getPieceWidth(); j++) {
                assert boardArray[i][j] == pieceArray[i][j] : "Failed to create a Piece";
            }
            System.out.println("");
        }
    }

    @Test
    public void lineClearTest() {
        Board board = new Board();
        int [][] boardArray = board.getBoard();

        for (int i = 0; i < board.getColumns(); i++) {
            boardArray[board.getRows()-1][i] = 1;
        }

        board.eraseLine(board.getRows()-1);

        for (int i = 0; i < board.getColumns(); i++) {
            assert boardArray[board.getRows()-1][i] == 0 : "Line erase failed";
        }
    }
}

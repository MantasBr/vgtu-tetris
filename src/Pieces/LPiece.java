package Pieces;

import Main.Board;
import Main.GameRunner;

public class LPiece extends Piece {
    private int[][] pieceArray = {{0, 0, 3}, {3, 3, 3}};

    public LPiece(int x, int y) {
        super(x, y);
        if (super.canBePlaced(pieceArray) == false) {
            GameRunner.stopTheGame();
            return;
        }
        Board.placePiece(this);
    }

    @Override
    public int[][] getPieceArray() {
        return pieceArray;
    }

    @Override
    public void setPieceArray(int[][] newPieceArray) {
        this.pieceArray = newPieceArray;
    }
}

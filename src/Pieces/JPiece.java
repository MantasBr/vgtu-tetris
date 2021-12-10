package Pieces;

import Main.Board;
import Main.GameRunner;

public class JPiece extends Piece {
    private int[][] pieceArray = {{2, 0, 0}, {2, 2, 2}};

    public JPiece(int x, int y) {
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

package Pieces;

import Main.Board;
import Main.GameRunner;

public class ZPiece extends Piece {
    private int[][] pieceArray = {{7, 7, 0}, {0, 7, 7}};

    public ZPiece(int x, int y) {
        super(x, y);
        if (super.canBePlaced(pieceArray) == false) {
            GameRunner.stopTheGame();
            return;
        }
        Board.placePiece(this);
    }

    @Override
    public int[][] getPieceArray() {
        return this.pieceArray;
    }

    @Override
    public void setPieceArray(int[][] newPieceArray) {
        this.pieceArray = newPieceArray;
    }
}

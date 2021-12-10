package Pieces;

import Main.Board;
import Main.GameRunner;

public class SPiece extends Piece {
    private int[][] pieceArray = {{0, 5, 5}, {5, 5, 0}};

    public SPiece(int x, int y) {
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

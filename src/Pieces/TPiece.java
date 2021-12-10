package Pieces;

import Main.Board;
import Main.GameRunner;

public class TPiece extends Piece {
    private int[][] pieceArray = {{0, 6, 0}, {6, 6, 6}};

    public TPiece(int x, int y) {
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

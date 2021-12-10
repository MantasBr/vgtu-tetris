package Pieces;

import Main.Board;
import Main.GameRunner;

public class OPiece extends Piece {
    private int[][] pieceArray = {{4, 4}, {4, 4}};

    public OPiece(int x, int y) {
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

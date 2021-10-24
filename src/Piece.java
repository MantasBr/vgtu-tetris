import java.util.Random;

public class Piece {
    private int posX = 0;
    private int posY = 0;
    private int[][] pieceArray;

    private static Random randomGenerator = new Random();

    public Piece(int x, int y) {
        this.posX = x;
        this.posY = y;
        int randomPieceIndex = chooseRandomPiece(0, Board.getAmountOfPieces() - 1);
        this.pieceArray = Board.getPieceTemplate(randomPieceIndex);

        if (canBePlaced(pieceArray) == false) GameRunner.stopTheGame();
        Board.placePiece(this);
    }

    public static int chooseRandomPiece(int min, int max) {
        return randomGenerator.nextInt((max - min) + 1) + min;
    }


    public void fall() {
        if (collidesBelow() == true) {
            Board.createPiece(4, 0);
            return;
        }
        Board.erasePiece(this);
        setY(posY+1);
        Board.placePiece(this);

    }

    public void rotate() {
        Board.erasePiece(this);
        int height = pieceArray.length;
        int width = pieceArray[0].length;

        int[][] rotatedPiece = new int[width][height];

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                rotatedPiece[column][height-1-row] = pieceArray[row][column];
            }
        }

        if (canBePlaced(rotatedPiece) == true) {
            pieceArray = rotatedPiece;
        }

        Board.placePiece(this);
        GameRunner.update();
    }

    public boolean canBePlaced(int [][] pieceArray) {
        int[][] board = Board.getBoard();
        int pieceHeight = pieceArray.length;
        int pieceWidth = pieceArray[0].length;

        if (pieceWidth + posX > Board.getColumns() || pieceHeight + posY > Board.getRows()) {
            return false;
        }

        for (int i = 0; i < pieceHeight; i++) {
            for (int j = 0; j < pieceWidth; j++) {
                if (board[posY + i][posX + j] != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public void shiftRight() {
        if (collidesRight() == true) return;
        Board.erasePiece(this);
        setX(posX+1);
        Board.placePiece(this);
        GameRunner.update();
    }

    public void shiftLeft() {
        if (collidesLeft() == true) return;
        Board.erasePiece(this);
        setX(posX-1);
        Board.placePiece(this);
        GameRunner.update();
    }

    private void place() {

    }


    private boolean collidesBelow() {
        if (posY + pieceArray.length >= Board.getRows()) {
            return true;
        }

        int[][] board = Board.getBoard();
        int pieceWidth = pieceArray[0].length;
        int pieceHeight = pieceArray.length;
        int offset = 0;

        for (int i = 0; i < pieceWidth; i++) {
            if (pieceArray[pieceHeight-1][i] == 0) {
                offset = pieceHeight-1;
                while (offset >= 0 && pieceArray[offset][i] == 0) {
                    offset--;
                }
                offset = (pieceHeight-1) - offset;
            }

            if (board[posY + pieceHeight - offset][posX + i] != 0) {
                return true;
            }
            offset = 0;
        }

        return false;
    }

    private boolean collidesRight() {
        if (posX + pieceArray[0].length >= Board.getColumns()) {
            return true;
        }

        int[][] board = Board.getBoard();
        int pieceWidth = pieceArray[0].length;
        int pieceHeight = pieceArray.length;
        int offset = 0;

        for (int i = 0; i < pieceHeight; i++) {
            if (pieceArray[i][pieceWidth - 1] == 0) {
                offset = pieceWidth-1;
                while (offset >= 0 && pieceArray[i][offset] == 0) {
                    offset--;
                }
                offset = (pieceWidth-1) - offset;
            }

            if (board[posY + i][posX + pieceWidth - offset] != 0) {
                return true;
            }
            offset = 0;
        }

        return false;
    }

    private boolean collidesLeft() {
        if (posX <= 0) {
            return true;
        }

        int[][] board = Board.getBoard();
        int pieceWidth = pieceArray[0].length;
        int pieceHeight = pieceArray.length;
        int offset = 0;

        for (int i = 0; i < pieceHeight; i++) {
            if (pieceArray[i][0] == 0) {
                while (offset <= pieceWidth-1 && pieceArray[i][offset] == 0) {
                    offset++;
                }
            }

            if (board[posY + i][posX - 1 + offset] != 0) {
                return true;
            }

            offset = 0;
        }

        return false;
    }

    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
    }

    public void setY(int newY) {
        posY = newY;
    }

    public void setX(int newX) {
        posX = newX;
    }

    public int[][] getPieceArray() {
        return pieceArray;
    }


}

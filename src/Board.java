import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

public class Board extends JComponent {

    private final static int ROWS = 20;
    private final static int COLUMNS = 10;
    private final static int BLOCK_SIZE_PX = 32;

    private static int [][][] LIST_OF_PIECES = new int[][][] {
            {{1, 1, 1, 1}},

            {{2, 0, 0},
             {2, 2, 2}},

            {{0, 0, 3},
             {3, 3, 3}},

            {{4, 4},
             {4, 4}},

            {{0, 5, 5},
             {5, 5, 0}},

            {{0, 6, 0},
             {6, 6, 6}},

            {{7, 7, 0},
             {0, 7, 7}}
    };

    private static int[][] boardArray = new int[ROWS][COLUMNS];
    private static BufferedImage allBlocks;
    private static Piece currentPiece;

    public Board() {
        try {
            allBlocks = ImageIO.read(getClass().getResource("blockList.png"));
        }
        catch (IOException e) {
           e.printStackTrace();
        }

        createPiece(4, 0);

        //boardArray[0][0] = 1;
        //boardArray[5][5] = 1;
    }

    public static void createPiece(int x, int y) {
        if (currentPiece != null) {
            int pieceHeight = currentPiece.getPieceArray().length;
            findErasableLines(currentPiece.getY() + pieceHeight - 1, currentPiece.getY());
        }

        currentPiece = new Piece(x, y);
    }

    public static void placePiece(Piece piece) {
        int[][] pieceArray = piece.getPieceArray();
        int x = piece.getX();
        int y = piece.getY();

        for (int row = 0; row < pieceArray.length; row++) {
            for (int column = 0; column < pieceArray[row].length; column++) {
                if (pieceArray[row][column] != 0) {
                    boardArray[y + row][x + column] = pieceArray[row][column];
                }
            }
        }
    }

    public static void erasePiece(Piece piece) {
        int[][] pieceArray = piece.getPieceArray();
        int x = piece.getX();
        int y = piece.getY();

        for (int row = 0; row < pieceArray.length; row++) {
            for (int column = 0; column < pieceArray[row].length; column++) {
                if (pieceArray[row][column] != 0) {
                    boardArray[y + row][x + column] = 0;
                }
            }
        }
    }

    public static void eraseLine(int lineIndex) {
        for (int i = 0; i < boardArray[lineIndex].length; i++) {
            boardArray[lineIndex][i] = 0;
        }

        shiftLines(lineIndex);
    }

    public static void shiftLines(int erasedLineIndex) {
        for (int row = erasedLineIndex; row > 0; row--) {
            for (int column = 0; column < boardArray[0].length; column++) {
                boardArray[row][column] = boardArray[row-1][column];
            }
        }

        for (int column = 0; column < boardArray[0].length; column++) {
            boardArray[0][column] = 0;
        }
    }

    public static void findErasableLines(int startPoint, int endPoint) {
        boolean erase = false;
        int row = 0;
        for (row = startPoint; row >= endPoint; row--) {
            for (int column = 0; column < getColumns(); column++) {
                if (boardArray[row][column] == 0) break;
                if (column == getColumns() - 1) erase = true;
            }

            if (erase == true) {
                erase = false;
                eraseLine(row);
                row++;
            }
        }
    }

    public static int[][] getPieceTemplate(int index) {
        return LIST_OF_PIECES[index];
    }

    public static int getAmountOfPieces() {
        return LIST_OF_PIECES.length;
    }

    public static int getRows() {
        return ROWS;
    }

    public static int getColumns() { return COLUMNS; }

    public static int[][] getBoard() {
        return boardArray;
    }

    public static Piece getCurrentPiece() {
        return currentPiece;
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        for (int column = 1; column <= COLUMNS; column++) {
            graphics.drawLine(BLOCK_SIZE_PX * column, 0, BLOCK_SIZE_PX * column, BLOCK_SIZE_PX * ROWS);
        }

        for (int row = 1; row <= ROWS; row++) {
            graphics.drawLine(0, BLOCK_SIZE_PX * row, BLOCK_SIZE_PX * COLUMNS, BLOCK_SIZE_PX * row);
        }

        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                int cell = boardArray[row][column];
                if (cell != 0) {
                    BufferedImage block = allBlocks.getSubimage((cell-1) * BLOCK_SIZE_PX, 0, BLOCK_SIZE_PX, BLOCK_SIZE_PX);
                    graphics.drawImage(block, BLOCK_SIZE_PX * column, BLOCK_SIZE_PX * row, null);
                }
            }
        }
    }
}

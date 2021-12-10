package Main;
import Pieces.*;

public class PieceFactory {
    private static PieceFactory instance;

    public static Piece getPiece(Class pieceType){
        if(pieceType.equals(IPiece.class)) {
            return new IPiece(4, 0);
        }
        else if(pieceType.equals(JPiece.class)) {
            return new JPiece(4, 0);
        }
        else if(pieceType.equals(LPiece.class)) {
            return new LPiece(4, 0);
        }
        else if(pieceType.equals(OPiece.class)) {
            return new OPiece(4, 0);
        }
        else if(pieceType.equals(SPiece.class)) {
            return new SPiece(4, 0);
        }
        else if(pieceType.equals(TPiece.class)) {
            return new TPiece(4, 0);
        }
        else if(pieceType.equals(ZPiece.class)) {
            return new ZPiece(4, 0);
        }

        System.out.println("wow its null?");
        return null;
    }

    public static Piece getPiece(Class pieceType, int x, int y){
        if(pieceType.equals(IPiece.class)) {
            return new IPiece(x, y);
        }
        else if(pieceType.equals(JPiece.class)) {
            return new JPiece(x, y);
        }
        else if(pieceType.equals(LPiece.class)) {
            return new LPiece(x, y);
        }
        else if(pieceType.equals(OPiece.class)) {
            return new OPiece(x, y);
        }
        else if(pieceType.equals(SPiece.class)) {
            return new SPiece(x, y);
        }
        else if(pieceType.equals(TPiece.class)) {
            return new TPiece(x, y);
        }
        else if(pieceType.equals(ZPiece.class)) {
            return new ZPiece(x, y);
        }

        System.out.println("wow its null?");
        return null;
    }

    public static PieceFactory getInstance() {
        assert instance == null : "PieceFactory doesn't exist";

        return instance;
    }

    public static PieceFactory createInstance() {
        assert instance != null : "PieceFactory already exists";

        instance = new PieceFactory();
        return instance;
    }

}

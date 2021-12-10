package Main;

import Pieces.Piece;

import java.util.ArrayList;

public class PieceObserverManager {
    private static ArrayList<CurrentPieceObserver> observers = new ArrayList<>();

    public static void addObserver(CurrentPieceObserver observer) {
        observers.add(observer);
    }

    public static void removeObserver(CurrentPieceObserver observer) {
        observers.remove(observer);
    }

    public static void updateObservers(Piece piece) {
        for (CurrentPieceObserver observer : observers) {
            observer.updateCurrentPiece(piece);
        }
    }
}

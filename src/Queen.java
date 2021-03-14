import java.util.ArrayList;

public class Queen extends Piece{
    public Queen(Pair<Integer, Integer> currentPosition, String color) {
        super(currentPosition, color);
    }

    @Override
    public boolean isValidMove(Pair<Integer, Integer> move) {
        return false;
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getPossibleMoves() {
        return null;
    }
}

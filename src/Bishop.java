import java.util.ArrayList;

public class Bishop extends Piece{
    public Bishop(Pair<Integer, Integer> currentPosition, String color) {
        super(currentPosition, color);
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getPossibleMoves() {
        return null;
    }

    @Override
    public boolean isValidMove(Pair<Integer, Integer> move) {
        return false;
    }
}

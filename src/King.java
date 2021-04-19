import java.util.LinkedList;

public class King extends Piece{
    public King(Pair<Integer, Integer> currentPosition, String color) {
        super(currentPosition, color);
    }

    @Override
    public LinkedList<Pair<Integer, Integer>> getPossibleMoves() {
        return null;
    }

    @Override
    public boolean isValidMove(Pair<Integer, Integer> move) {
        return false;
    }
}

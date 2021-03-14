import java.util.ArrayList;

public class Knight extends Piece{
    public Knight(Pair<Integer, Integer> currentPosition, String color) {
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

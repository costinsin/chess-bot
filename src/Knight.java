import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(Pair<Integer, Integer> currentPosition, String color) {
        super(currentPosition, color);
    }


    /**
     * Function that creates and returns an array of possible and valid moves.
     *
     * @return - array of moves represented by a pair of Integer coordinates.
     */
    @Override
    public ArrayList<Pair<Integer, Integer>> getPossibleMoves() {
        ArrayList<Pair<Integer, Integer>> result = new ArrayList<>();
        Pair<Integer, Integer> move1R = generateMove(-2, 1);
        Pair<Integer, Integer> move2R = generateMove(2, 1);
        Pair<Integer, Integer> move3R = generateMove(-1, 2);
        Pair<Integer, Integer> move4R = generateMove(1, 2);

        Pair<Integer, Integer> move1L = generateMove(-2, -1);
        Pair<Integer, Integer> move2L = generateMove(2, -1);
        Pair<Integer, Integer> move3L = generateMove(-1, -2);
        Pair<Integer, Integer> move4L = generateMove(+1, -2);


        if (isValidMove(move2R))
            result.add(move2R);
        if (isValidMove(move4R))
            result.add(move4R);
        if (isValidMove(move2L))
            result.add(move2L);
        if (isValidMove(move4L))
            result.add(move4L);


        if (isValidMove(move1R))
            result.add(move1R);
        if (isValidMove(move3R))
            result.add(move3R);
        if (isValidMove(move1L))
            result.add(move1L);
        if (isValidMove(move3L))
            result.add(move3L);

        return result;
    }

    /**
     * Function checks if a move is valid or not.
     *
     * @param move - represents where we want to move the knight.
     * @return - true if the given move is a valid one, false otherwise.
     */
    @Override
    public boolean isValidMove(Pair<Integer, Integer> move) {
        ChessBoard chessBoard = ChessBoard.getInstance();

        if (move.getFirst() >= 0 && move.getFirst() <= 7
                && move.getSecond() >= 0 && move.getSecond() <= 7) {

            if(chessBoard.getPiece(move) == null || !chessBoard.getPiece(move).
                    getColor().equalsIgnoreCase(getColor()))
                return true;
        }
        return false;
    }
}
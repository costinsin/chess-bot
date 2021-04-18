import java.util.ArrayList;

public class Bishop extends Piece{
    public Bishop(Pair<Integer, Integer> currentPosition, String color) {
        super(currentPosition, color);
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getPossibleMoves() {
        ArrayList<Pair<Integer, Integer>> moves = new ArrayList<>();

        // Upper Right Diagonal
        addSideMoves(moves, -1, 1);

        // Upper Left Diagonal
        addSideMoves(moves, -1, -1);

        // Lower Right Diagonal
        addSideMoves(moves, 1, 1);

        // Lower Left Diagonal
        addSideMoves(moves, 1, -1);

        return moves;
    }

    /**
     * Function checks if the move coordinates are inside the board size.
     *
     * @param move - pair of x and y coordinates
     * @return - true or false
     */
    @Override
    public boolean isValidMove(Pair<Integer, Integer> move) {
        return move.getFirst() >= 0 && move.getFirst() <= 7
                && move.getSecond() >= 0 && move.getSecond() <= 7;
    }

    /**
     * Function that adds to an array possible and valid moves for a given side: LEFT,
     * RIGHT, UP, DOWN, etc.
     *
     * @param moves - array of possible and valid moves
     * @param signX - int value which can be 1, 0, -1
     * @param signY - int value which can be 1, 0, -1
     */
    public void addSideMoves(ArrayList<Pair<Integer, Integer>> moves, Integer signX, Integer signY) {
        ChessBoard chessBoard = ChessBoard.getInstance();

        for (int i = 1; i <= 8; i++) {
            if (isValidMove(generateMove(signX * i, signY * i))) {
                if (chessBoard.getPiece(generateMove(signX * i, signY * i)) != null) {
                    if (!chessBoard.getPiece(generateMove(signX * i, signY * i)).getColor()
                            .equalsIgnoreCase(getColor())) {
                        moves.add(generateMove(signX * i, signY * i));
                    }
                    break;

                } else {
                    moves.add(generateMove(signX * i, signY * i));
                }
            } else {
                break;
            }
        }
    }
}

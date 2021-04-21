import java.util.LinkedList;

public class King extends Piece {
    public King(Pair<Integer, Integer> currentPosition, String color) {
        super(currentPosition, color);
    }

    /**
     * Function verifies if the King is not checked from the sides(UP, DOWN, LEFT, RIGHT and DIAGONALS)
     *
     * @param signX - value which can be 1, 0, -1 representing a direction
     * @param signY - value which can be 1, 0, -1 representing a direction
     * @param xPos  - x position on table
     * @param yPos  - y position on table
     * @return - true if the sides are free, false if the King is checked
     */
    public boolean freeSide(Integer signX, Integer signY, Integer xPos, Integer yPos, String[] pieces) {
        ChessBoard chessBoard = ChessBoard.getInstance();
        int distance = 1;

        while (isValidMove(new Pair<>(xPos, yPos))) {
            if (chessBoard.getPiece(new Pair<>(xPos, yPos)) != null) {
                if (chessBoard.getPiece(new Pair<>(xPos, yPos)).getColor().equalsIgnoreCase(getColor())) {
                    return true;
                } else {
                    for (String piece : pieces) {
                        if (chessBoard.getPiece(new Pair<>(xPos, yPos)).getClass().getName().equalsIgnoreCase(piece)) {
                            if (!piece.equalsIgnoreCase("KING")
                                    && !piece.equalsIgnoreCase("PAWN")) {
                                return false;
                            }
                            if (piece.equalsIgnoreCase("KING") && distance == 1) {
                                return false;
                            }
                            if (piece.equalsIgnoreCase("PAWN") && distance == 1 &&
                                    signX == (this.getColor().equalsIgnoreCase("BLACK") ? 1 : -1)) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
            }
            xPos += signX;
            yPos += signY;
            distance++;
        }

        return true;
    }

    /**
     * Function verifies if the King is not checked by a Knight.
     *
     * @param signX - value which can be 1, 0, -1 representing a direction
     * @param signY - value which can be 1, 0, -1 representing a direction
     * @param xPos  - x position on table
     * @param yPos  - y position on table
     * @return - true if there is no Knight to check the king, false otherwise
     */
    public boolean freeFromKnights(Integer signX, Integer signY, Integer xPos, Integer yPos) {
        ChessBoard chessBoard = ChessBoard.getInstance();

        if (isValidMove(new Pair<>(xPos + signX, yPos + signY))
                && chessBoard.getPiece(new Pair<>(xPos + signX, yPos + signY)) != null
                && chessBoard.getPiece(new Pair<>(xPos + signX, yPos + signY)).getClass().getName().
                equalsIgnoreCase("KNIGHT")
                && !chessBoard.getPiece(new Pair<>(xPos + signX, yPos + signY)).getColor().equalsIgnoreCase(getColor())) {
            return false;
        }

        return true;
    }

    /**
     * Function checks if the move coordinates creates a "checked" situation for the king.
     *
     * @param position - pair of x and y coordinates
     * @return - true or false
     */
    private boolean isChecked(Pair<Integer, Integer> position) {
        ChessBoard chessBoard = ChessBoard.getInstance();

        //LEFT
        if (!freeSide(0, -1, position.getFirst(), position.getSecond() - 1,
                new String[]{"QUEEN", "ROOK", "KING"})) {
            return true;
        }

        //RIGHT
        if (!freeSide(0, 1, position.getFirst(), position.getSecond() + 1,
                new String[]{"QUEEN", "ROOK", "KING"})) {
            return true;
        }

        //UP
        if (!freeSide(-1, 0, position.getFirst() - 1, position.getSecond(),
                new String[]{"QUEEN", "ROOK", "KING"})) {
            return true;
        }

        //DOWN
        if (!freeSide(1, 0, position.getFirst() + 1, position.getSecond(),
                new String[]{"QUEEN", "ROOK", "KING"})) {
            return true;
        }

        //LEFT UP DIAG
        if (!freeSide(-1, -1, position.getFirst() - 1, position.getSecond() - 1,
                new String[]{"QUEEN", "BISHOP", "KING", "PAWN"})) {
            return true;
        }

        //RIGHT UP DIAG
        if (!freeSide(-1, 1, position.getFirst() - 1, position.getSecond() + 1,
                new String[]{"QUEEN", "BISHOP", "KING", "PAWN"})) {
            return true;
        }

        //LEFT DOWN DIAG
        if (!freeSide(1, -1, position.getFirst() + 1, position.getSecond() - 1,
                new String[]{"QUEEN", "BISHOP", "KING", "PAWN"})) {
            return true;
        }

        //RIGHT DOWN DIAG
        if (!freeSide(1, 1, position.getFirst() + 1, position.getSecond() + 1,
                new String[]{"QUEEN", "BISHOP", "KING", "PAWN"})) {
            return true;
        }

        //KNIGHT
        if (!freeFromKnights(2, 1, position.getFirst(), position.getSecond())) {
            return true;
        }

        if (!freeFromKnights(2, -1, position.getFirst(), position.getSecond())) {
            return true;
        }

        if (!freeFromKnights(-2, 1, position.getFirst(), position.getSecond())) {
            return true;
        }

        if (!freeFromKnights(-2, -1, position.getFirst(), position.getSecond())) {
            return true;
        }

        if (!freeFromKnights(1, 2, position.getFirst(), position.getSecond())) {
            return true;
        }

        if (!freeFromKnights(1, -2, position.getFirst(), position.getSecond())) {
            return true;
        }

        if (!freeFromKnights(-1, 2, position.getFirst(), position.getSecond())) {
            return true;
        }

        if (!freeFromKnights(1, -2, position.getFirst(), position.getSecond())) {
            return true;
        }

        return false;
    }

    /**
     * Overloaded public boolean isChecked(Pair<Integer, Integer> move).
     */
    public boolean isChecked() {
        return isChecked(this.getCurrentPosition());
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
    public void addSideMoves(LinkedList<Pair<Integer, Integer>> moves, Integer signX, Integer signY) {
        ChessBoard chessBoard = ChessBoard.getInstance();

        if (isValidMove(generateMove(signX, signY)) && !isKingCheckedAfterTempMove(generateMove(signX, signY))) {
            if (chessBoard.getPiece(generateMove(signX, signY)) != null) {
                if (!chessBoard.getPiece(generateMove(signX, signY)).getColor().equalsIgnoreCase(getColor())) {
                    moves.addFirst(generateMove(signX, signY));
                }
            } else {
                moves.addLast(generateMove(signX, signY));
            }
        }
    }

    /**
     * Function that creates and returns an array of possible and valid moves.
     *
     * @return - array of moves represented by a pair of Integer coordinates.
     */
    public LinkedList<Pair<Integer, Integer>> getPossibleMoves() {
        LinkedList<Pair<Integer, Integer>> moves = new LinkedList<>();

        // LEFT
        addSideMoves(moves, 0, -1);

        // RIGHT
        addSideMoves(moves, 0, 1);

        // UP
        addSideMoves(moves, -1, 0);

        // DOWN
        addSideMoves(moves, 1, 0);

        // UPPER LEFT DIAG
        addSideMoves(moves, -1, -1);

        // UPPER RIGHT DIAG
        addSideMoves(moves, -1, 1);

        // LOWER LEFT DIAG
        addSideMoves(moves, 1, -1);

        // LOWER RIGHT DIAG
        addSideMoves(moves, 1, 1);

        return moves;
    }
}

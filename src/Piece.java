import java.util.ArrayList;
import java.util.Objects;

public abstract class Piece {
    private Pair<Integer, Integer> currentPosition;
    private String color;

    public Piece(Pair<Integer, Integer> currentPosition, String color) {
        this.currentPosition = currentPosition;
        this.color = color;
    }

    public abstract ArrayList<Pair<Integer, Integer>> getPossibleMoves();
    public abstract boolean isValidMove(Pair<Integer, Integer> move);

    public void moveTo(Pair<Integer, Integer> destination) {
        ChessBoard chessboard = ChessBoard.getInstance();
        chessboard.removePiece(this.currentPosition);
        chessboard.addPiece(destination, this);
        this.currentPosition = destination;
    }

    public Pair<Integer, Integer> getCurrentPosition() {
        return currentPosition;
    }

    public String getColor() {
        return color;
    }

    public void setCurrentPosition(Pair<Integer, Integer> currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(currentPosition, piece.currentPosition) && Objects.equals(color, piece.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentPosition, color);
    }
}

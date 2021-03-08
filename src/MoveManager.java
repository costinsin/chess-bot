public class MoveManager {
    /**
     * Function that converts a String move into cartesian coordinates
     *
     * @param move - move in String format (eg. "a1a2", "h3g7")
     * @return A source-destination pair of cartesian coordinates pairs (eg. "b8c6" -> Pair<Pair<0, 1>, Pair<2, 2>>)
     */
    public Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> getCoordinates(String move) {
        if (move.matches("^[a-h][1-8][a-h][1-8]$")) {
            return new Pair<>(StringToIntCoordinate(move.substring(0, 2)), StringToIntCoordinate(move.substring(2)));
        }

        System.out.println("Invalid move!");
        return null;
    }

    /**
     * Function that converts a coordinate into a pair of cartesian coordinates
     *
     * @param coordinate - coordinate in String format (eg. "g2", "c3")
     * @return A pair of cartesian coordinates (eg. "f5" -> Pair<3, 5>)
     */
    public Pair<Integer, Integer> StringToIntCoordinate(String coordinate) {
        int first = 7 - ((int) (coordinate.getBytes()[1] - '1'));
        int second = ((int) (coordinate.getBytes()[0] - 'a'));
        return new Pair<>(first, second);
    }

    /**
     * Function that converts a pair of cartesian coordinates into a coordinate
     *
     * @param coordinate - coordinate in Pair format (eg. "Pair<0, 7>", "Pair<4, 2>")
     * @return A pair of cartesian coordinates (eg. "Pair<3, 5>" -> f5)
     */
    public String IntToStringCoordinate(Pair<Integer, Integer> coordinate) {
        int first = coordinate.getFirst();
        int second = coordinate.getSecond();
        StringBuilder move = new StringBuilder();

        move.append((char) ('a' + second));
        move.append((char) ('1' + (7 - first)));

        return move.toString();
    }

    /**
     * Function that computes the mirror of a move
     *
     * @param move - move in String format (eg. "e1e3", "a5e1")
     * @return Mirror of the provided move (eg. "a7a5" -> "h2h4")
     */
    public String mirrorMove(String move) {
        if (move.matches("^[a-h][1-8][a-h][1-8]$")) {
            Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> coordinates = getCoordinates(move);
            Pair<Integer, Integer> start = coordinates.getFirst();
            Pair<Integer, Integer> end = coordinates.getSecond();

            start.setFirst(7 - start.getFirst());
            start.setSecond(7 - start.getSecond());
            end.setFirst(7 - end.getFirst());
            end.setSecond(7 - end.getSecond());

            return IntToStringCoordinate(start) + IntToStringCoordinate(end);
        }

        System.out.println("Invalid move!");
        return null;
    }
}

class Pair<T, K> {
    private T first;
    private K second;

    Pair() {
        this.first = null;
        this.second = null;
    }

    Pair(T first, K second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(K second) {
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public K getSecond() {
        return second;
    }
}

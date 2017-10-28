public class Solver {

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("Board cannot be null");
        }
    }

    public boolean isSolvable() {
        return false;
    }

    public int moves() {
        return 0;
    }

    public Iterable<Board> solution() {
        return null;
    }

    public static void main(String[] args) {

    }
}

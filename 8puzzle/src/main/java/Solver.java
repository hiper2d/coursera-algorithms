import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.Comparator;

public class Solver {
    private final boolean solvable;
    private final int moves;
    private final Iterable<Board> solution;

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("Board cannot be null");
        }
        MinPQ<BoardWithMove> manhattanMinPq = new MinPQ<>(new ManhattanComparator());
        MinPQ<BoardWithMove> manhattanTwinMinPq = new MinPQ<>(new ManhattanComparator());

        manhattanMinPq.insert(new BoardWithMove(initial, null, 0));
        manhattanTwinMinPq.insert(new BoardWithMove(initial.twin(), null, 0));
        while (!manhattanMinPq.min().board.isGoal() && !manhattanTwinMinPq.min().board.isGoal()) {
            makeMove(manhattanMinPq);
            makeMove(manhattanTwinMinPq);
        }

        solvable = manhattanMinPq.min().board.isGoal() && !manhattanTwinMinPq.min().board.isGoal();

        moves = manhattanMinPq.min().move;

        solution = createSolution(manhattanMinPq);
    }

    private void makeMove(MinPQ<BoardWithMove> queue) {
        BoardWithMove current = queue.delMin();
        int move = current.move + 1;
        Iterable<Board> neighbors = current.board.neighbors();
        for (Board neighbor: neighbors) {
            if (current.predecessor == null || !current.predecessor.board.equals(neighbor)) {
                queue.insert(new BoardWithMove(neighbor, current, move));
            }
        }
    }

    public boolean isSolvable() {
        return solvable;
    }

    public int moves() {
        return isSolvable() ? moves : -1;
    }

    public Iterable<Board> solution() {
        return isSolvable() ? solution : null;
    }

    private Iterable<Board> createSolution(MinPQ<BoardWithMove> manhattanMinPq) {
        BoardWithMove root = manhattanMinPq.min();
        Stack<Board> returnSequence = new Stack<>();
        while (root != null) {
            returnSequence.push(root.board);
            root = root.predecessor;
        }
        return returnSequence;
    }

    private static class ManhattanComparator implements Comparator<BoardWithMove> {
        @Override
        public int compare(BoardWithMove o1, BoardWithMove o2) {
            return o1.getManhattanWithMove() - o2.getManhattanWithMove();
        }
    }

    private static class BoardWithMove {
        BoardWithMove predecessor;
        Board board;
        int manhattan;
        int move;

        public BoardWithMove(Board board, BoardWithMove predecessor, int move) {
            this.board = board;
            this.move = move;
            this.predecessor = predecessor;
            this.manhattan = board.manhattan();
        }

        public int getManhattanWithMove() {
            return manhattan + move;
        }

        public int getHammingWithMove() {
            return board.hamming() + move;
        }
    }
}

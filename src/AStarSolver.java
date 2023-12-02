import java.util.*;

public class AStarSolver implements Solver<Board> {

    private static HeuristicFunction<Board> heuristicFunction = null;

    public AStarSolver(HeuristicFunction<Board> heuristicFunction) {
        this.heuristicFunction = heuristicFunction;
    }

    private static class Node implements Comparable<Node> {
        Board board;
        int moves;
        Node previous;
        int priority; // This is 'f' in A*

        public Node(Board board, int moves, Node previous) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
            this.priority = moves + heuristicFunction.exec(board);
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.priority, other.priority);
        }
    }

    public List<Board> solve(Board initial) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<Board> closedSet = new HashSet<>();
        openSet.add(new Node(initial, 0, null));
        int algorithmSteps = 0, solutionSteps = 0;

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            algorithmSteps++;

            if (current.board.isSolved()) {
                List<Board> path = constructPath(current);

                return path;
            }

            closedSet.add(current.board);

            for (Board neighbor : current.board.getNeighboringBoards()) {
                if (closedSet.contains(neighbor))
                    continue;
                openSet.add(new Node(neighbor, current.moves + 1, current));
            }
        }

        return Collections.emptyList(); // Return an empty list if no solution is found
    }

    private static List<Board> constructPath(Node node) {
        LinkedList<Board> path = new LinkedList<>();
        while (node != null) {
            path.addFirst(node.board);
            node = node.previous;
        }
        return path;
    }
}

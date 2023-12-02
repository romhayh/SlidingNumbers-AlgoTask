import java.util.*;

public class AStarSolver extends Solver<Board> {

    private HeuristicFunction<Board> heuristicFunction = null;

    public AStarSolver(String name, HeuristicFunction<Board> heuristicFunction) {
        super(name);
        this.heuristicFunction = heuristicFunction;
    }

    public Solution<Board> solve(Board initial) {
        PriorityQueue<AStarNode> openSet = new PriorityQueue<>();
        Set<Board> closedSet = new HashSet<Board>();
        openSet.add(new AStarNode(initial, 0, null, heuristicFunction));
        int algorithmSteps = 0;

        while (!openSet.isEmpty()) {
            AStarNode current = openSet.poll();
            algorithmSteps++;

            if (current.board.isSolved()) {
                List<Board> path = constructPath(current);

                return new Solution<Board>(path, algorithmSteps, path.size());
            }

            closedSet.add(current.board);

            for (Board neighbor : current.board.getNeighboringBoards()) {
                if (closedSet.contains(neighbor))
                    continue;
                openSet.add(new AStarNode(neighbor, current.moves + 1, current, heuristicFunction));
            }
        }

        return null; // Return an empty list if no solution is found
    }

    private List<Board> constructPath(AStarNode AStarNode) {
        LinkedList<Board> path = new LinkedList<>();
        while (AStarNode != null) {
            path.addFirst(AStarNode.board);
            AStarNode = AStarNode.previous;
        }
        return path;
    }
}

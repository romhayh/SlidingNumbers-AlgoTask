
import java.util.*;

public class BFSSolver extends Solver<Board> {

    public BFSSolver(String name) {
        super(name);
    }

    public Solution<Board> solve(Board startBoard) {
        List<Board> path = new ArrayList<>();
        int algorithmSteps = 0, solutionSteps = 0;

        if (startBoard.isSolved()) {
            path = Collections.singletonList(startBoard);
        }

        Queue<Board> queue = new LinkedList<>();
        Map<Board, Board> predecessors = new HashMap<>();
        queue.add(startBoard);
        predecessors.put(startBoard, null);

        while (!queue.isEmpty()) {
            Board current = queue.poll();
            algorithmSteps++;

            if (current.isSolved()) {
                path = buildPath(predecessors, current);
                break;
            }

            for (Board neighbor : current.getNeighboringBoards()) {
                if (!predecessors.containsKey(neighbor)) {
                    queue.add(neighbor);
                    predecessors.put(neighbor, current);
                }
            }
        }
        solutionSteps = path.size();

        return new Solution<Board>(path, algorithmSteps, solutionSteps); // No solution found
    }

    private List<Board> buildPath(Map<Board, Board> predecessors, Board end) {
        LinkedList<Board> path = new LinkedList<>();

        for (Board at = end; at != null; at = predecessors.get(at)) {
            path.addFirst(at);
        }

        return path;
    }

}

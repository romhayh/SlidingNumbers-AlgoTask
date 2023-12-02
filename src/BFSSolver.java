
import java.util.*;

public class BFSSolver implements Solver<Board>{
    public BFSSolver() {

    }
    public List<Board> solve(Board startBoard) {
        if (startBoard.isSolved()) {
            return Collections.singletonList(startBoard);
        }

        Queue<Board> queue = new LinkedList<>();
        Map<Board, Board> predecessors = new HashMap<>();
        queue.add(startBoard);
        predecessors.put(startBoard, null);

        while (!queue.isEmpty()) {
            Board current = queue.poll();

            if (current.isSolved()) {
                return buildPath(predecessors, current);
            }

            for (Board neighbor : current.getNeighboringBoards()) {
                if (!predecessors.containsKey(neighbor)) {
                    queue.add(neighbor);
                    predecessors.put(neighbor, current);
                }
            }
        }

        return new ArrayList<>(); // No solution found
    }

    private List<Board> buildPath(Map<Board, Board> predecessors, Board end) {
        LinkedList<Board> path = new LinkedList<>();

        for (Board at = end; at != null; at = predecessors.get(at)) {
            path.addFirst(at);
        }

        return path;
    }

}

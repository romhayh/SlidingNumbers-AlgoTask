import java.util.List;

public class Main {

    public static void printPath(List<Board> path) {
        System.out.println("Initial Board:");
        path.forEach(board -> {
            System.out.print(board);
            System.out.println("\n---------------------------------");
        });
    }

    public static void main(String[] args) {
        // Create a 4x4 board and shuffle it with 10 random moves
        Board board = new Board(5, 20);

        // Display the initial board

        // BFSSolver bfsGraph = new BFSSolver();
        // List<Board> bfsPath = bfsGraph.solve(board);
        // printPath(bfsPath);

        ManhattanHeuristic manhattanHeuristic = new ManhattanHeuristic();
        DijkastraHeuristic dijkastraHeuristic = new DijkastraHeuristic();
        IncompatableHeuristic incompatableHeuristic = new IncompatableHeuristic();

        AStarSolver astarGraph = new AStarSolver(incompatableHeuristic);
        List<Board> astarPath = astarGraph.solve(board);
        printPath(astarPath);
        System.out.println(astarPath);

    }
}
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void printPath(List<Board> path) {
        System.out.println("Initial Board:");
        path.forEach(board -> {
            System.out.print(board);
            System.out.println("\n---------------------------------");
        });
    }

    public static void printComparisonIndividuals(int boardSize, List<Solver<Board>> solvers) {
        Board board = new Board(boardSize, 10);
        DecimalFormat formatter = new DecimalFormat("#.##");

        System.out.println(board);

        solvers.forEach(solver -> {
            long startTime = System.nanoTime();
            Solution<Board> solution = solver.solve(board);
            long endTime = System.nanoTime();
            String totalTime = formatter.format((double) (endTime - startTime) / 1_000_000);

            System.out.println(solver.name() + ": " + solution + ", total time: " + totalTime + "ms");
            System.out.println();
        });

    }

    public static void printComparisonAverages(int boardSize, List<Solver<Board>> solvers) {
        System.out.println("printing avgs for board size " + boardSize + " * " + boardSize);
        DecimalFormat formatter = new DecimalFormat("#.##");
        final int ITERATIONS = 50;

        int[] summedAlgorithmSteps = new int[solvers.size()];
        int[] summedSolutionSteps = new int[solvers.size()];
        long[] summedTimeTotals = new long[solvers.size()];

        for (int i = 0; i < ITERATIONS; i++) {
            Board board = new Board(boardSize, 10);

            for (int solverIndex = 0; solverIndex < solvers.size(); solverIndex++) {
                Solver<Board> solver = solvers.get(solverIndex);

                long startTime = System.nanoTime();
                Solution<Board> solution = solver.solve(board);
                long endTime = System.nanoTime();

                summedAlgorithmSteps[solverIndex] += solution.algorithmSteps();
                summedSolutionSteps[solverIndex] += solution.solutionSteps();
                summedTimeTotals[solverIndex] += endTime - startTime;
            }

        }

        for (int solverIndex = 0; solverIndex < solvers.size(); solverIndex++) {
            Solver<Board> solver = solvers.get(solverIndex);

            System.out.println(solver.name() + ": avg algorithm steps "
                    + formatter.format(((double) summedAlgorithmSteps[solverIndex]) / ITERATIONS)
                    + ", avg solution steps "
                    + formatter.format(((double) summedSolutionSteps[solverIndex]) / ITERATIONS)
                    + ", avg total time " +
                    formatter.format((double) (summedTimeTotals[solverIndex]) / (1_000_000 * ITERATIONS)) + "ms")

            ;
        }
    }

    public static void main(String[] args) {

        List<Solver<Board>> solvers = new ArrayList<Solver<Board>>();
        solvers.add(new BFSSolver("bfs"));
        solvers.add(new AStarSolver("dijakstra", new DijkastraHeuristic()));
        solvers.add(new AStarSolver("manhattan", new ManhattanHeuristic()));
        solvers.add(new AStarSolver("incompatable", new IncompatableHeuristic()));

        printComparisonIndividuals(4, solvers);
        printComparisonIndividuals(4, solvers);
        printComparisonIndividuals(4, solvers);
        printComparisonIndividuals(4, solvers);
        printComparisonIndividuals(4, solvers);

        printComparisonIndividuals(5, solvers);
        printComparisonIndividuals(5, solvers);
        printComparisonIndividuals(5, solvers);
        printComparisonIndividuals(5, solvers);
        printComparisonIndividuals(5, solvers);

        printComparisonAverages(4, solvers);
        printComparisonAverages(5, solvers);

    }
}
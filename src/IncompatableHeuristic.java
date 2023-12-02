import java.util.Random;

public class IncompatableHeuristic implements HeuristicFunction<Board> {

    @Override
    public int exec(Board t) {
        return new Random().nextInt(10) + 1;
    }

}

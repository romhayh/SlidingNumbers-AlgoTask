public class AStarNode implements Comparable<AStarNode> {
    Board board;
    int moves;
    AStarNode previous;
    int priority; // This is 'f' in A*
    HeuristicFunction<Board> heuristicFunction;

    public AStarNode(Board board, int moves, AStarNode previous, HeuristicFunction<Board> heuristicFunction) {
        this.board = board;
        this.moves = moves;
        this.previous = previous;
        this.priority = moves + heuristicFunction.exec(board);
    }

    @Override
    public int compareTo(AStarNode other) {
        return Integer.compare(this.priority, other.priority);
    }
}

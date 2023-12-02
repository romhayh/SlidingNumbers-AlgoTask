public class ManhattanHeuristic implements HeuristicFunction<Board> {

    @Override
    public int exec(Board board) {
        // TODO Auto-generated method stub
        int distance = 0;
        int size = board.getSize();
        int[][] gameState = board.getGameState();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int value = gameState[i][j];
                if (value != 0) {
                    int targetX = (value - 1) / size; // Expected x-coordinate (row)
                    int targetY = (value - 1) % size; // Expected y-coordinate (column)
                    distance += Math.abs(i - targetX) + Math.abs(j - targetY);
                }
            }
        }
        return distance;
    }

}

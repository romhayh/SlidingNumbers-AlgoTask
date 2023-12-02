import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.*;

public class Board {
    private int[][] gameState;
    private Point emptySlot;
    private int size;

    // Constructor with given gameState
    public Board(int[][] gameState) {

        this.size = gameState.length;
        this.gameState = new int[size][size];
        for (int boardCol = 0; boardCol < size; boardCol++) {
            System.arraycopy(gameState[boardCol], 0, this.gameState[boardCol], 0, gameState[boardCol].length);
        }
        this.emptySlot = findEmptySlot();
    }

    // Constructor with size and number of moves
    public Board(int size, int moves) {
        this.size = size;
        this.gameState = new int[size][size];
        initializeBoard();
        shuffleBoard(moves);
    }

    private void initializeBoard() {
        int num = 1;
        for (int boardCol = 0; boardCol < size; boardCol++) {
            for (int bobardRow = 0; bobardRow < size; bobardRow++) {
                gameState[boardCol][bobardRow] = num++;
            }
        }
        gameState[size - 1][size - 1] = 0;
        emptySlot = new Point(size - 1, size - 1);
    }

    private Point findEmptySlot() {
        for (int boardCol = 0; boardCol < size; boardCol++) {
            for (int boardRow = 0; boardRow < size; boardRow++) {
                if (gameState[boardCol][boardRow] == 0) {
                    return new Point(boardCol, boardRow);
                }
            }
        }
        // Problems
        return new Point(-1, -1);
    }

    private void shuffleBoard(int moves) {
        Random rand = new Random();
        for (int movenum = 0; movenum < moves; movenum++) {
            ArrayList<Point> neighbors = getNeighbors(emptySlot);
            Point move = neighbors.get(rand.nextInt(neighbors.size()));
            swap(emptySlot, move);
            emptySlot = move;
        }
    }

    private void swap(Point p1, Point p2) {
        int temp = gameState[p1.x][p1.y];
        gameState[p1.x][p1.y] = gameState[p2.x][p2.y];
        gameState[p2.x][p2.y] = temp;
    }

    private ArrayList<Point> getNeighbors(Point p) {
        ArrayList<Point> neighbors = new ArrayList<>();
        int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        for (int[] dir : directions) {
            int newX = p.x + dir[0];
            int newY = p.y + dir[1];
            if (newX >= 0 && newX < size && newY >= 0 && newY < size) {
                neighbors.add(new Point(newX, newY));
            }
        }
        return neighbors;
    }

    public boolean isSolved() {
        int num = 1;
        for (int boardCol = 0; boardCol < size; boardCol++) {
            for (int boardRow = 0; boardRow < size; boardRow++) {
                if (boardCol == size - 1 && boardRow == size - 1) {
                    return gameState[boardCol][boardRow] == 0;
                }
                if (gameState[boardCol][boardRow] != num++) {
                    return false;
                }
            }
        }
        return true;
    }

    public ArrayList<Board> getNeighboringBoards() {
        ArrayList<Point> neighbors = getNeighbors(emptySlot);
        ArrayList<Board> boards = new ArrayList<>();

        for (Point neighbor : neighbors) {
            Board newBoard = new Board(this.gameState);
            newBoard.swap(emptySlot, neighbor);
            newBoard.emptySlot = new Point(neighbor);
            boards.add(newBoard);
        }
        return boards;
    }

    @Override
    public String toString() {
        String boardString = "";

        for (int boardCol = 0; boardCol < size; boardCol++) {
            for (int boardRow = 0; boardRow < size; boardRow++) {
                if (gameState[boardCol][boardRow] == 0) {
                    boardString += ("   "); // Display empty slot
                } else {
                    boardString += String.format("%3d", gameState[boardCol][boardRow]);
                }
            }

            if (boardCol < size - 1) {
                boardString += """

                        """;
            }
        }

        return boardString;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Board other = (Board) obj;

        if (this.size != other.size)
            return false;

        for (int boardCol = 0; boardCol < size; boardCol++) {
            for (int boardRow = 0; boardRow < size; boardRow++) {
                if (this.gameState[boardCol][boardRow] != other.gameState[boardCol][boardRow])
                    return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(gameState);
    }

    public int getSize() {
        return size;
    }

    public int[][] getGameState() {
        return gameState;
    }
}

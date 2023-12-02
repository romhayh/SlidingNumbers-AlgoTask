
import java.util.List;

public record Solution<T>(List<T> solutionPath, int algorithmSteps, int solutionSteps) {
    @Override
    public String toString() {
        return "algorithmSteps: " + algorithmSteps + ", solutionSteps: " + solutionSteps;
    }
}

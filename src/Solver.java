
public abstract class Solver<T> {
    private String name;

    public Solver(String name) {
        this.name = name;
    }

    public Solution<T> solve(T t) {
        return null;
    }

    public String name() {
        return this.name;
    }
}

package expression.operations;

import java.util.function.UnaryOperator;

public class Negate extends UnaryOperations {

    private static final Priority priority = new Priority(0, true, 1);
    public Negate(Evaluateble expr) {
        super(expr, (a) -> -a, priority, "-");
    }

    protected Negate(Evaluateble expr, UnaryOperator<Integer> opInteger) {
        super(expr, opInteger, priority, "-");
    }
}

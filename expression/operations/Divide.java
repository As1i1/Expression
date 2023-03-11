package expression.operations;

import java.util.function.BinaryOperator;

public class Divide extends BinaryOperation {

    private static final Priority priority = new Priority(5, false, 0);
    public Divide(Evaluateble left, Evaluateble right) {
        super(left, right, (a, b) -> a / b,
                priority, "/");
    }

    protected Divide(Evaluateble left, Evaluateble right, BinaryOperator<Integer> opInteger) {
        super(left, right, opInteger, priority, "/");
    }
}

package expression.operations;


import java.util.function.BinaryOperator;

public class Multiply extends BinaryOperation {

    private static final Priority priority = new Priority(5, true,1);

    public Multiply(Evaluateble left, Evaluateble right) {
        super(left, right, (a, b) -> a * b,
                priority, "*");
    }

    protected Multiply(Evaluateble left, Evaluateble right, BinaryOperator<Integer> opInteger) {
        super(left, right, opInteger, priority, "*");
    }
}

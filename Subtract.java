package expression;


import java.util.function.BinaryOperator;

public class Subtract extends BinaryOperation {

    private static final Priority priority = new Priority(10, false, 1);

    public Subtract(Evaluateble left, Evaluateble right) {
        super(left, right, (a, b) -> a - b,
                priority,  "-");
    }

    protected Subtract(Evaluateble left, Evaluateble right, BinaryOperator<Integer> operatorInt) {
        super(left, right, operatorInt, priority, "-");
    }
}

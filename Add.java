package expression;

import java.util.function.BinaryOperator;

public class Add extends BinaryOperation {
    private static final Priority priority = new Priority(10, true,1);
    public Add(Evaluateble left, Evaluateble right) {
        super(left, right, Integer::sum,
                priority,"+");
    }

    protected Add(Evaluateble left, Evaluateble right, BinaryOperator<Integer> opInteger){
        super(left, right, opInteger, priority, "+");
    }
}

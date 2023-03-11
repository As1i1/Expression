package expression.exceptions;

import expression.operations.BinaryOperation;
import expression.operations.Evaluateble;
import expression.operations.Priority;

import java.util.function.BinaryOperator;

public class CheckedAbs extends BinaryOperation {
    protected CheckedAbs(Evaluateble left, Evaluateble right, BinaryOperator<Integer> operatorInt, Priority priority, String opString) {
        super(left, right, operatorInt, priority, opString);
    }

    public static int abs (int a) {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowExpressionException("Overflow: abs(" + a + ") not in an integer range");
        }
        return a >= 0 ? a: -a;
    }
}

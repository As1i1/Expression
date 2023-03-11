package expression.exceptions;

import expression.operations.BinaryOperation;
import expression.operations.Evaluateble;
import expression.operations.Priority;

import java.util.function.BinaryOperator;

public class CheckedMod extends BinaryOperation {
    protected CheckedMod(Evaluateble left, Evaluateble right, BinaryOperator<Integer> operatorInt, Priority priority, String opString) {
        super(left, right, operatorInt, priority, opString);
    }

    public static int mod(int a, int b) {
        if (b == 0) {
            throw new DivisionByZeroException("Division by zero: " + a + " % 0");
        }
        return a % b;
    }
}

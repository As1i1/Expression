package expression.exceptions;

import expression.operations.Divide;
import expression.operations.Evaluateble;

public class CheckedDivide extends Divide {
    public CheckedDivide(Evaluateble left, Evaluateble right) {
        super(left, right, CheckedDivide::divide);
    }

    public static int divide(int a, int b){
        if (a == Integer.MIN_VALUE && b == -1){
            throw new OverflowExpressionException("Overflow: " + a + " / " + b + " not in an integer range");
        } else if (b == 0) {
            throw new DivisionByZeroException("Division by zero: " + a + " / 0");
        }
        return a / b;
    }
}

package expression.exceptions;

import expression.Evaluateble;
import expression.Priority;
import expression.UnaryOperations;


public class CheckedPow10 extends UnaryOperations {

    private static final Priority priority = new Priority(0 ,true, 1);
    public CheckedPow10(Evaluateble expr) {
        super(expr, CheckedPow10::pow, priority, "pow10");
    }

    public static int pow(int a) {
        if (a < 0) {
            throw new NegativeArgumentOfFunctionException("Argument pow10 must be positive find: " + a);
        }
        int res = 1;
        for (int i = 0; i < a; i++) {
            try {
            res = CheckedMultiply.multiply(res, 10);
            } catch (EvaluateExpressionException e) {
                throw new OverflowExpressionException("Overflow: pow10(" + a + ") not in an integer range");
            }
        }
        return res;
    }
}

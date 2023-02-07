package expression.exceptions;

import expression.Evaluateble;
import expression.Negate;

public class CheckedNegate extends Negate {
    public CheckedNegate(Evaluateble expr) {
        super(expr, CheckedNegate::negate);
    }

    public static int negate(int a){
        if (a == Integer.MIN_VALUE) {
            throw new OverflowExpressionException("Overflow: - " + a + " not in an integer range");
        }
        return -a;
    }
}

package expression.exceptions;

import expression.Evaluateble;
import expression.Subtract;
public class CheckedSubtract extends Subtract {

    public CheckedSubtract(Evaluateble left, Evaluateble right) {
        super(left, right, CheckedSubtract::subtract);
    }

    public static int subtract(int a, int b){
        int c = a - b;
        if ((a <= 0 && b > 0 && c >= 0) ||
                (a >= 0 && b < 0 && c <= 0)) {
            throw new OverflowExpressionException("Overflow: " + a + " - " + b + " not in an integer range");
        }
        return c;
    }
}

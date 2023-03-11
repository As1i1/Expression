package expression.exceptions;

import expression.operations.Add;
import expression.operations.Evaluateble;

public class CheckedAdd extends Add {

    public CheckedAdd(Evaluateble left, Evaluateble right) {
        super(left, right, CheckedAdd::add);
    }

    public static int add(int a, int b){
        int c = a + b;
        if (a > 0 && b > 0 && c <= 0) {
            throw new OverflowExpressionException("Overflow: " + a + " + " + b + " not in an integer range");
        } else if (a < 0 && b < 0 && c >= 0) {
            throw new OverflowExpressionException("Overflow: " + a + " + " + b + " not in an integer range");
        }
        return c;
    }
}

package expression.exceptions;

import expression.Evaluateble;
import expression.Multiply;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(Evaluateble left, Evaluateble right) {
        super(left, right, CheckedMultiply::multiply);
    }

    public static int multiply(int a, int b){
        if (a == 0 || b == 0) {
            return 0;
        }
        if (a < 0 && b > 0) {
            return check(a, b);
        } else if (b < 0 && a > 0) {
            return check(b, a);
        } else {
            int c = Integer.MAX_VALUE / a;
            if ((a < b ? a : b) == Integer.MIN_VALUE) {
                throw new OverflowExpressionException("Overflow: " + a + " * " + b + " not in an integer range");
            } else if (a < 0 && b < c || a > 0 && c < b) {
                throw new OverflowExpressionException("Overflow: " + a + " * " + b + " not in an integer range");
            } else {
                return a * b;
            }
        }
    }

    private static int check(int a, int b){
        int c = Integer.MIN_VALUE / b;
        if (a < c) {
            throw new OverflowExpressionException("Overflow: " + a + " * " + " b not in an integer range");
        } else {
            return a * b;
        }
    }
}

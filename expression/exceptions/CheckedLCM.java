package expression.exceptions;

import expression.operations.Evaluateble;
import expression.operations.LCM;

public class CheckedLCM extends LCM {
    public CheckedLCM(Evaluateble left, Evaluateble right) {
        super(left, right, CheckedLCM::lcm);
    }

    public static int lcm(int a, int b) {
        if (CheckedGCD.gcd(a, b) == 0) {
            return 0;
        }
        try {
            return CheckedMultiply.multiply(a / CheckedGCD.gcd(a, b), b);
        } catch (EvaluateExpressionException e) {
            throw new OverflowExpressionException("Overflow: " + a + " lcm " + b + " not in an integer range");
        }

    }
}

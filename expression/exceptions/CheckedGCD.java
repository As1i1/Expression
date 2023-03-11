package expression.exceptions;

import expression.operations.Evaluateble;
import expression.operations.GCD;

public class CheckedGCD extends GCD {
    public CheckedGCD(Evaluateble left, Evaluateble right) {
        super(left, right, CheckedGCD::gcd);
    }

    public static int gcd(int a, int b) {
        return GCD.gcd(a, b);
    }
}

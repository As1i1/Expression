package expression.exceptions;

import expression.Evaluateble;
import expression.GCD;

public class CheckedGCD extends GCD {
    public CheckedGCD(Evaluateble left, Evaluateble right) {
        super(left, right, CheckedGCD::gcd);
    }

    public static int gcd(int a, int b) {
        return GCD.gcd(a, b);
    }
}

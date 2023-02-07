package expression;

import java.util.function.BinaryOperator;

public class GCD extends BinaryOperation {

    private static final Priority priority = new Priority(20, true, 1);

    public GCD(Evaluateble left, Evaluateble right) {
        super(left, right, GCD::gcd, priority, "gcd");
    }

    protected GCD(Evaluateble left, Evaluateble right, BinaryOperator<Integer> opInteger) {
        super(left, right, opInteger, priority, "gcd");
    }

    public static int gcd(int a ,int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        return b == 0 ? a: gcd(b, a % b);
    }
}

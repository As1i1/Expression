package expression.operations;

import java.util.function.BinaryOperator;

public class LCM extends BinaryOperation {

    private static final Priority prioity = new Priority(20, true, 0);

    public LCM(Evaluateble left, Evaluateble right) {
        super(left, right, LCM::lcm, prioity, "lcm");
    }

    protected LCM(Evaluateble left, Evaluateble right, BinaryOperator<Integer> opInteger) {
        super(left, right, opInteger, prioity, "lcm");
    }

    public static int lcm(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        return a / GCD.gcd(a, b) * b;
    }
}

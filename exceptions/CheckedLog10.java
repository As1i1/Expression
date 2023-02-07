package expression.exceptions;

import expression.Evaluateble;
import expression.Priority;
import expression.UnaryOperations;

public class CheckedLog10 extends UnaryOperations {

    private static final Priority priority = new Priority(0 ,true, 1);
    protected CheckedLog10(Evaluateble expr) {
        super(expr, CheckedLog10::log, priority, "log10");
    }
    public static int log(int a){
        if (a <= 0) {
                throw new NegativeArgumentOfFunctionException("Argument of log10 must be positive, find: " + a);
        }
        int res = 0;
        while (a >= 10) {
            res++;
            a /= 10;
        }
        return res;
    }
}

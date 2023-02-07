package expression;

import java.util.function.UnaryOperator;

public class Reverse extends UnaryOperations{
    private static final Priority priority = new Priority(0 ,true, 1);

    public Reverse(Evaluateble expr) {
        super(expr, Reverse::reverse, priority, "reverse");
    }

    protected Reverse(Evaluateble expr, UnaryOperator<Integer> opInteger) {
        super(expr, opInteger, priority, "reverse");
    }

    public static int reverse(int a){
        String number = String.valueOf(a);
        Boolean isNegate = a < 0;
        if (isNegate) {
            number = number.substring(1);
        }
        int res = 0;
        int step = 1;
        for (int i = 0; i < number.length(); i++) {
            res += Character.getNumericValue(number.charAt(i)) * step;
            step *= 10;
        }
        return isNegate ? -res: res;
    }
}

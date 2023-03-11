package expression.exceptions;

import expression.operations.Evaluateble;
import expression.operations.Reverse;


public class CheckedReverse extends Reverse {
    public CheckedReverse(Evaluateble expr) {
        super(expr, CheckedReverse::reverse);
    }

    public static int reverse(int a) {
        String number = String.valueOf(a);
        boolean isNegate = a < 0;
        if (isNegate) {
            number = number.substring(1);
        }
        int res = 0;
        int step = 1;
        for (int i = 0; i < number.length(); i++) {
            try {
                res = CheckedAdd.add(res, CheckedMultiply.multiply(Character.getNumericValue(number.charAt(i)), step));
            } catch (EvaluateExpressionException e){
                throw new OverflowExpressionException("Overflow: reverse(" + a + ") not in an integer range");
            }
            step *= 10;
        }
        return isNegate ? -res: res;
    }
}

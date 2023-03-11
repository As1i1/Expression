package expression.generic;

import expression.exceptions.ParserException;
import expression.generic.operations.GenericEvaluable;
import expression.generic.parser.ExpressionParser;
import expression.generic.wrapper.AbstractWrapper;

import java.util.function.Function;

public class TypedEvaluator<T extends Number> {

    public Object[][][] calc(
            ExpressionParser<T> parser,
            Function<Integer, AbstractWrapper<T>> intToWrapper,
            String expression,
            int x1, int x2, int y1, int y2, int z1, int z2
    ) throws ParserException {
        GenericEvaluable<T> expressionParsed = parser.parse(expression);
        Object[][][] result = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                for (int z = z1; z <= z2; z++) {
                    try {
                        result[x - x1][y - y1][z - z1] = expressionParsed.evaluate(
                                intToWrapper.apply(x),
                                intToWrapper.apply(y),
                                intToWrapper.apply(z)
                        ).getValue();
                    } catch (ArithmeticException e) {
                        result[x - x1][y - y1][z - z1] = null;
                    }
                }
            }
        }
        return result;
    }
}

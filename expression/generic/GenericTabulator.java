package expression.generic;

import expression.generic.parser.ExpressionParser;
import expression.generic.wrapper.*;

import java.math.BigInteger;

public class GenericTabulator implements Tabulator{

    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2)
            throws Exception {
        switch (mode) {
            case "i" -> {
                return new TypedEvaluator<Integer>().calc(
                        new ExpressionParser<>(CheckedIntegerWrapper::parseConstant),
                        CheckedIntegerWrapper::intToWrapper,
                        expression, x1, x2, y1, y2, z1, z2
                );
            }
            case "bi" -> {
                return new TypedEvaluator<BigInteger>().calc(
                        new ExpressionParser<>(BigIntegerWrapper::parseConstant),
                        BigIntegerWrapper::intToWrapper,
                        expression, x1, x2, y1, y2, z1, z2
                );
            }
            case "d" -> {
                return new TypedEvaluator<Double>().calc(
                        new ExpressionParser<>(DoubleWrapper::parseConstant),
                        DoubleWrapper::intToWrapper,
                        expression, x1, x2, y1, y2, z1, z2
                );
            }
            case "s" -> {
                return new TypedEvaluator<Short>().calc(
                        new ExpressionParser<>(ShortWrapper::parseConstant),
                        ShortWrapper::intToWrapper,
                        expression, x1, x2, y1, y2, z1, z2
                );
            }
            case "l" -> {
                return new TypedEvaluator<Long>().calc(
                        new ExpressionParser<>(LongWrapper::parseConstant),
                        LongWrapper::intToWrapper,
                        expression, x1, x2, y1, y2, z1, z2
                );
            }
            case "u" -> {
                return new TypedEvaluator<Integer>().calc(
                        new ExpressionParser<>(IntegerWrapper::parseConstant),
                        IntegerWrapper::intToWrapper,
                        expression, x1, x2, y1, y2, z1, z2
                );
            }
            case "p" -> {
                return new TypedEvaluator<Integer>().calc(
                        new ExpressionParser<>(ModularWrapper::parseConstant),
                        ModularWrapper::intToWrapper,
                        expression, x1, x2, y1, y2, z1, z2
                );
            }
            default -> {
                throw new IllegalArgumentException("Unsupported mode. Mode = " + mode);
            }
        }
    }
}

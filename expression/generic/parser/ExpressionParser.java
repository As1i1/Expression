package expression.generic.parser;

import expression.exceptions.*;
import expression.generic.operations.*;
import expression.generic.wrapper.AbstractWrapper;

import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class ExpressionParser<T extends Number> {

    private final Function<String, AbstractWrapper<T>> constantParser;

    public ExpressionParser(Function<String, AbstractWrapper<T>> constantParser) {
        this.constantParser = constantParser;
    }

    public GenericEvaluable<T> parse(String expression) throws ParserException {
        return new Parser<T>(expression, constantParser).parseStart();
    }

    private static class Parser<T extends Number> extends BaseParser {

        private static final String INTEGER_MIN_VALUE = "2147483648";
        private static final String INTEGER_MAX_VALUE = "2147483647";

        private final Function<String, AbstractWrapper<T>> constantParser;
        private final Map<String, BinaryOperator<GenericEvaluable<T>>> ADD_SUBTRACT_CONSTRUCTORS = Map.ofEntries(
                Map.entry("+", Add<T>::new),
                Map.entry("-", Subtract<T>::new)
        );

        private final Map<String, BinaryOperator<GenericEvaluable<T>>> MULTIPLY_DIVIDE_CONSTRUCTORS = Map.ofEntries(
                Map.entry("*", Multiply<T>::new),
                Map.entry("/", Divide<T>::new)
        );

        private final Map<String, BinaryOperator<GenericEvaluable<T>>> MOD_CONSTRUCTORS = Map.ofEntries(
                Map.entry("mod", Mod<T>::new)
        );

        private final Set<String> LONG_OPERATORS = Set.of("mod");

        Parser(String source, Function<String, AbstractWrapper<T>> constantParser) {
            super(source);
            this.constantParser = constantParser;
        }

        private GenericEvaluable<T> parseStart() throws ParserException {
            return parseMod();
        }

        private GenericEvaluable<T> parseOperation(
                final Map<String, BinaryOperator<GenericEvaluable<T>>> OPERATORS_CONSTRUCTORS,
                SupplierException<GenericEvaluable<T>, ParserException> next)
                throws ParserException {
            GenericEvaluable<T> left;
            try {
                left = next.get();
            } catch (InvalidUnaryOperatorException e) {
                if (checkOperator(OPERATORS_CONSTRUCTORS, getBinaryOperator())) {
                    throw new InvalidExpressionException("Expected first argument. " + e.getMessage());
                }
                throw e;
            }
            skipWhitespace();
            do {
                String operator = getBinaryOperator();
                if (!checkOperator(OPERATORS_CONSTRUCTORS, operator)) {
                    return left;
                }
                GenericEvaluable<T> right;
                try {
                    right = next.get();

                } catch (InvalidUnaryOperatorException e) {
                    throw new InvalidExpressionException("Expected second argument. " + e.getMessage());
                }
                left = OPERATORS_CONSTRUCTORS.get(operator).apply(left, right);
                skipWhitespace();
            } while (true);
        }

        private GenericEvaluable<T> parseMod() throws ParserException {
            return parseOperation(MOD_CONSTRUCTORS, this::parseAddSubtract);
        }

        private GenericEvaluable<T> parseAddSubtract() throws ParserException {
            return parseOperation(ADD_SUBTRACT_CONSTRUCTORS, this::parseDivideMultiply);
        }

        private GenericEvaluable<T> parseDivideMultiply() throws ParserException {
           return parseOperation(MULTIPLY_DIVIDE_CONSTRUCTORS, this::parseExpression);
        }


        private GenericEvaluable<T> parseExpression() throws ParserException {
            skipWhitespace();
            if (take('(')) {
                skipWhitespace();
                GenericEvaluable<T> expression = parseStart();
                take(')');
                return expression;
            } else if (take('-')) {
                if (test(Character::isDigit)) {
                    return new Const<>(parseNumber(true));
                } else {
                    return new Negate<>(parseExpression());
                }
            } else if (test(Character::isDigit)) {
                return new Const<>(parseNumber(false));
            } else if (test('x') || test('y') || test('z')) {
                return new Variable<>(Character.toString(take()));
            } else {
                String operator = getOperator();
                if (operator.isEmpty()) {
                    throw new EmptyExpressionException("expected expression pos: " + (source.getPos() + 1));
                } else if (operator.equals("abs")) {
                    takeOperator(operator);
                    return new Abs<>(parseExpression());
                } else if (operator.equals("square")) {
                    takeOperator(operator);
                    return new Square<>(parseExpression());
                } else {
                    throw new InvalidUnaryOperatorException("Invalid unary operator: expected expression find \"" +
                            operator + "\" pos: " + (source.getPos() + 1));
                }
            }
        }

        private boolean checkOperator(Map<String, ?> operators, String operator) {
            if (operators.containsKey(operator)) {
                takeOperator(operator);
                return true;
            }
            return false;
        }

        private String getBinaryOperator() throws ParserException {
            skipWhitespace();
            String operator;
            if (test('+')) {
                operator = "+";
            } else if (test('-')) {
                operator = "-";
            } else if (test('*')) {
                operator = "*";
            } else if (test('/')) {
                operator = "/";
            } else if (test(')') || test(END)) {
                operator = "";
            } else {
                operator = getOperator();
                if (LONG_OPERATORS.contains(operator)) {
                    return operator;
                }
                if (operator.isEmpty()) {
                    operator = getNextToken();
                }
                throw new InvalidBinaryOperatorException("Expect binary operator: find \"" + operator +
                        "\"" + " pos: " + (source.getPos() + 1));
            }
            return operator;
        }

        private AbstractWrapper<T> parseNumber(boolean isNegate) throws ParserException {
            StringBuilder sb = new StringBuilder();
            while (test(Character::isDigit)){
                sb.append(take());
            }
            String number = sb.toString();
            if (number.length() > INTEGER_MAX_VALUE.length()) {
                throw new ConstantOverflowException("find " + number + " pos: " + (source.getPos() + 1));
            } else if (number.length() == INTEGER_MAX_VALUE.length()) {
                if ((isNegate && number.compareTo(INTEGER_MIN_VALUE) > 0) ||
                        (!isNegate && number.compareTo(INTEGER_MAX_VALUE) > 0)) {
                    throw new ConstantOverflowException("find " + number + " positive Number: " + (source.getPos() + 1));
                }
            }
            if (isNegate) {
                number = "-" + sb.toString();
            }
            return constantParser.apply(number);
        }

        private String getOperator() {
            int position = getPos();
            StringBuilder sb = new StringBuilder();
            while (test(Character::isDigit) || test(Character::isLetter)) {
                sb.append(take());
            }
            setPos(position);
            return sb.toString();
        }

        private void takeOperator(String operation){
            expect(operation);
            skipWhitespace();
        }

        private String getNextToken() {
            int position = getPos();
            StringBuilder sb = new StringBuilder();
            while (!test(Character::isWhitespace) && !test(')') && !test(END)) {
                sb.append(take());
            }
            setPos(position);
            return sb.toString();
        }

    }
}

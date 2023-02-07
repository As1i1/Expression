package expression.exceptions;

import expression.*;

import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;

public class ExpressionParser implements TripleParser{

    @Override
    public TripleExpression parse(String expression) throws ParserException {
        return new Parser(expression).parseStart(BaseParser.END);
    }

    private static class Parser extends BaseParser {

        private static final String STRING_MIN_VALUE = "2147483648";
        private static final String STRING_MAX_VALUE = "2147483647";

        private static final Set<String> LONG_OPERATORS = Set.of("gcd", "lcm");

        private static final Map<String, BinaryOperator<Evaluateble>> OPERATORS_CONSTRUCTORS = Map.ofEntries(
                Map.entry("+", CheckedAdd::new),
                Map.entry("-", CheckedSubtract::new),
                Map.entry("*", CheckedMultiply::new),
                Map.entry("/", CheckedDivide::new),
                Map.entry("gcd", CheckedGCD::new),
                Map.entry("lcm", CheckedLCM::new)
        );

        Parser(String source) {
            super(source);
        }

        private Evaluateble parseStart(char endSymbol) throws ParserException {
            return parseGcdLcm(endSymbol);
        }

        private Evaluateble parseGcdLcm(char endSymbol) throws ParserException {
            final Set<String> OPERATORS = Set.of("gcd", "lcm");
            Evaluateble left;
            try {
                left = parseAddSubtract(endSymbol);
            } catch (InvalidUnaryOperatorException e) {
                if (checkOperator(OPERATORS, getBinaryOperator())) {
                    throw new InvalidExpressionException("Expected first argument. " + e.getMessage());
                }
                throw e;
            }
            skipWhitespace();
            while (!test(endSymbol)) {
                String operator = getBinaryOperator();
                if (!checkOperator(OPERATORS, operator)) {
                    return left;
                }
                Evaluateble right;
                try {
                    right = parseAddSubtract(endSymbol);

                } catch (InvalidUnaryOperatorException e) {
                    throw new InvalidExpressionException("Expected second argument. " + e.getMessage());
                }
                left = union(left, right, operator);
                skipWhitespace();
            }
            return left;
        }


        private Evaluateble parseAddSubtract(char endSymbol) throws ParserException {
            final Set<String> OPERATORS = Set.of("+", "-");
            Evaluateble left;
            try {
                left = parseDivideMultiply(endSymbol);
            } catch (InvalidUnaryOperatorException e) {
                if (checkOperator(OPERATORS, getBinaryOperator())) {
                    throw new InvalidExpressionException("Expected first argument. " + e.getMessage());
                }
                throw e;
            }skipWhitespace();
            while (!test(endSymbol)) {
                String operator = getBinaryOperator();
                if (!checkOperator(OPERATORS, operator)) {
                    return left;
                }
                Evaluateble right;
                try {
                  right = parseDivideMultiply(endSymbol);
                } catch (InvalidUnaryOperatorException e) {
                    throw new InvalidExpressionException("Expected second argument. " + e.getMessage());
                }
                left = union(left, right, operator);
                skipWhitespace();
            }
            return left;
        }

        private Evaluateble parseDivideMultiply(char endSymbol) throws ParserException {
            final Set<String> OPERATORS = Set.of("/", "*");
            Evaluateble left;
            try {
                left = parseExpression();
            } catch (InvalidUnaryOperatorException e) {
                if (checkOperator(OPERATORS, getBinaryOperator())) {
                    throw new InvalidExpressionException("Expected first argument. " + e.getMessage());
                }
                throw e;
            }
            skipWhitespace();
            while (!test(endSymbol)) {
                String operator = getBinaryOperator();
                if (!checkOperator(OPERATORS, operator)) {
                    return left;
                }
                Evaluateble right;
                try {
                    right = parseExpression();
                } catch (InvalidUnaryOperatorException e) {
                    throw new InvalidExpressionException("Expected second argument. " + e.getMessage());
                }
                left = union(left, right, operator);
                skipWhitespace();
            }
            return left;
        }


        private Evaluateble union(Evaluateble left, Evaluateble right, String operator) {
            return OPERATORS_CONSTRUCTORS.get(operator).apply(left, right);
        }

        private Evaluateble parseExpression() throws ParserException {
            skipWhitespace();
            if (take('(')) {
                skipWhitespace();
                Evaluateble expression = parseStart(')');
                take(')');
                return expression;
            } else if (take('-')) {
                if (test(Character::isDigit)) {
                    return new Const(-parseNumber(true));
                } else {
                    return new CheckedNegate(parseExpression());
                }
            } else if (test(Character::isDigit)) {
                return new Const(parseNumber(false));
            } else if (test('x') || test('y') || test('z')) {
                return new Variable(Character.toString(take()));
            } else {
                String operator = getOperator();
                if (operator.equals("reverse")) {
                    takeOperator(operator);
                    return new CheckedReverse(parseExpression());
                } else if (operator.equals("log10")) {
                    takeOperator(operator);
                    return new CheckedLog10(parseExpression());
                } else if (operator.equals("pow10")) {
                    takeOperator(operator);
                    return new CheckedPow10(parseExpression());
                } else {
                    if (operator.isEmpty()) {
                        operator = getNextToken();
                    }

                    if (operator.isEmpty()) {
                        throw new EmptyExpressionException("expected expression pos: " + (source.getPos() + 1));
                    } else {
                        throw new InvalidUnaryOperatorException("Invalid unary operator: expected expression find \"" +
                                operator + "\" pos: " + (source.getPos() + 1));
                    }
                }
            }
        }

        private boolean checkOperator(Set<String> operators, String operator) {
            if (operators.contains(operator)) {
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

        private int parseNumber(boolean isNegate) throws ParserException {
            StringBuilder sb = new StringBuilder();
            while (test(Character::isDigit)){
                sb.append(take());
            }
            String number = sb.toString();
            if (number.length() > STRING_MAX_VALUE.length()) {
                throw new ConstantOverflowException("find " + number + " pos: " + (source.getPos() + 1));
            } else if (number.length() == STRING_MAX_VALUE.length()) {
                if ((isNegate && number.compareTo(STRING_MIN_VALUE) > 0) ||
                        (!isNegate && number.compareTo(STRING_MAX_VALUE) > 0)) {
                    throw new ConstantOverflowException("find " + number + " pos: " + (source.getPos() + 1));
                }
            }
            return Integer.parseUnsignedInt(number);
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

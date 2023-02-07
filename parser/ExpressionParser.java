package expression.parser;

import expression.*;

import java.util.Map;

public class ExpressionParser implements TripleParser{
    public ExpressionParser(){
    }

    @Override
    public TripleExpression parse(String expression) {
        return new ExprParser(expression).parse();
    }

    private static class ExprParser extends BaseParser{

        private final Map<String, Integer> PRIORITY = Map.of(
                "gcd", 1,
                "lcm", 1,
                "+", 5,
                "-", 5,
                "*", 10,
                "/", 10
        );

        ExprParser(String source) {
            super(source);
        }

        private TripleExpression parse() {
            return parseStart((char) 0);
        }

        private Evaluateble union(Evaluateble left, Evaluateble right, String operation) {
            Evaluateble res;
            switch (operation) {
                case "+" -> res = new Add(left, right);
                case "-" -> res = new Subtract(left, right);
                case "*" -> res = new Multiply(left, right);
                case "/" -> res = new Divide(left, right);
                case "gcd" -> res = new GCD(left, right);
                case "lcm" -> res = new LCM(left, right);
                default -> throw error("Unsupported operator");
            }
            return res;
        }

        private String binaryOperation(){
            String operation;
            if (test('+')) {
                operation = "+";
            } else if (test('-')) {
                operation = "-";
            } else if (test('*')) {
                operation = "*";
            } else if (test('/')) {
                operation = "/";
            } else {
                String token = getNextToken();
                if (PRIORITY.get(token) != null) {
                    operation = token;
                } else {
                    throw error("Unsupported operator");
                }
            }
            return operation;
        }

        private String getNextToken() {
            int position = getPos();
            StringBuilder sb = new StringBuilder();
            while (test(Character::isDigit) || test(Character::isLetter)) {
                sb.append(take());
            }
            setPos(position);
            return sb.toString();
        }

        private void takeTokne(String operation){
            expect(operation);
            skipWhitespace();
        }

        private Evaluateble parseOperation(Evaluateble left, Evaluateble right, String operation, int priorityBound, char bracket) {
            skipWhitespace();
            if (eof() || test(bracket)) {
                return union(left, right, operation);
            }

            String nextOperation = binaryOperation();
            if (PRIORITY.get(nextOperation) <= priorityBound) {
                return union(left, right, operation);
            }
            takeTokne(nextOperation);
            Evaluateble next = parseExpression();
            if (PRIORITY.get(operation) >= PRIORITY.get(nextOperation)) {
                return parseOperation(
                        union(left, right, operation),
                        next,
                        nextOperation,
                        priorityBound,
                        bracket
                );
            } else {
                return parseOperation(
                        left,
                        parseOperation(right, next, nextOperation, PRIORITY.get(operation), bracket),
                        operation,
                        priorityBound,
                        bracket
                );
            }
        }

        private Evaluateble parseStart(char bracket) {
            skipWhitespace();
            Evaluateble left = parseExpression();
            skipWhitespace();
            if (eof() || test(bracket)) {
                return left;
            }
            String operation = binaryOperation();
            takeTokne(operation);
            Evaluateble right = parseExpression();
            return parseOperation(left, right, operation, -1, bracket);
        }

        private Evaluateble parseExpression() {
            skipWhitespace();
            if (take('(')) {
                skipWhitespace();
                Evaluateble expression = parseStart(')');
                take(')');
                return expression;
            } else if (take('-')) {
                if (test(Character::isDigit)) {
                    return new Const(-parseNumber());
                } else {
                    return new Negate(parseExpression());
                }
            } else if (test(Character::isDigit)) {
                return new Const(parseNumber());
            } else {
                String token = getNextToken();
                takeTokne(token);
                if (token.equals("x") || token.equals("y") || token.equals("z")){
                    return new Variable(token);
                } else if (token.equals("reverse")) {
                    return new Reverse(parseExpression());
                } else {
                    throw new IllegalArgumentException("Unsupported variable name");
                }
            }
        }

        private int parseNumber() {
            StringBuilder sb = new StringBuilder();
            while (test(Character::isDigit) && !eof()) {
                sb.append(take());
            }
            return Integer.parseUnsignedInt(sb.toString());
        }
    }
}

package expression.exceptions;

import expression.operations.TripleExpression;

@FunctionalInterface
public interface TripleParser {
    TripleExpression parse(String expression) throws Exception;
}

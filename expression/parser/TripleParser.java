package expression.parser;

import expression.operations.TripleExpression;

@FunctionalInterface
public interface TripleParser {
    TripleExpression parse(String expression);
}

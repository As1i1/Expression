package expression.parser;

import expression.operations.TripleExpression;

public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser();
        TripleExpression res = parser.parse("x * (x - 2)*x + 1");
        System.out.println(res.toMiniString());
        System.out.println(res);
        if (args.length != 0) {
            res = parser.parse(args[0]);
            System.out.println(res.toMiniString());
            System.out.println(res);
        }
    }
}

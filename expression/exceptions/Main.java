package expression.exceptions;


import expression.operations.TripleExpression;

public class Main {
    public static void main(String[] args) throws ParserException {
        ExpressionParser parser = new ExpressionParser();
        System.out.println(parser.parse("1 + * 3"));
        calc("1000000*x*x*x*x*x/(x-1) ", parser);

       if (args.length != 0) {
            calc(args[0], parser);
       }
    }

    private static void calc(String expression, ExpressionParser parser) {
        TripleExpression expr = null;
        try {
            expr = parser.parse(expression);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println(expr.toMiniString());
        System.out.println("x: result");
        for (int i = 0; i <= 10; i++) {
            try{
                System.out.print(i + ": ");
                System.out.println(expr.evaluate(i, 0 ,0));
            } catch (EvaluateExpressionException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

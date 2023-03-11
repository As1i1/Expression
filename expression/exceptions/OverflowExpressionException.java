package expression.exceptions;

public class OverflowExpressionException extends EvaluateExpressionException{
    OverflowExpressionException(String message) {
        super(message);
    }
}

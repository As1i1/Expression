package expression.exceptions;

public class NegativeArgumentOfFunctionException extends EvaluateExpressionException{
    NegativeArgumentOfFunctionException(String message) {
        super(message);
    }
}

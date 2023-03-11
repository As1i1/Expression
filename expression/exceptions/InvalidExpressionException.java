package expression.exceptions;

public class InvalidExpressionException extends ParserException{
    public InvalidExpressionException(String message) {
        super(message);
    }
}

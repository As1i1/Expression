package expression.exceptions;

public class ConstantOverflowException extends ParserException{
    public ConstantOverflowException(String message) {
        super("Constant Overflow: " + message);
    }
}

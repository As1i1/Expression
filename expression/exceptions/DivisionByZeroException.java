package expression.exceptions;

public class DivisionByZeroException extends EvaluateExpressionException{
    public DivisionByZeroException(String message){
        super(message);
    }
}

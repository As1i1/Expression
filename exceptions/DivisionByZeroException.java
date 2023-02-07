package expression.exceptions;

public class DivisionByZeroException extends EvaluateExpressionException{
    DivisionByZeroException(String message){
        super(message);
    }
}

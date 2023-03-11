package expression.generic.operations;

import expression.generic.wrapper.AbstractWrapper;

public class Square<T extends Number> extends GenericUnaryOperator<T>{

    public Square(GenericEvaluable<T> argument) {
        super(argument);
    }

    @Override
    public AbstractWrapper<T> apply(AbstractWrapper<T> argument) {
        return argument.square();
    }
}

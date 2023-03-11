package expression.generic.operations;

import expression.generic.wrapper.AbstractWrapper;

public class Abs<T extends Number> extends GenericUnaryOperator<T>{

    public Abs(GenericEvaluable<T> argument) {
        super(argument);
    }

    @Override
    public AbstractWrapper<T> apply(AbstractWrapper<T> argument) {
        return argument.abs();
    }
}

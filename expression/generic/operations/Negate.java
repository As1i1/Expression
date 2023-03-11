package expression.generic.operations;

import expression.generic.wrapper.AbstractWrapper;

public class Negate<T extends Number> extends GenericUnaryOperator<T> {

    public Negate(GenericEvaluable<T> argument) {
        super(argument);
    }

    @Override
    public AbstractWrapper<T> apply(AbstractWrapper<T> argument) {
        return argument.negate();
    }
}

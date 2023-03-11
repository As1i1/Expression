package expression.generic.operations;

import expression.generic.wrapper.AbstractWrapper;

public abstract class GenericUnaryOperator<T extends Number> implements GenericEvaluable<T> {

    private final GenericEvaluable<T> argument;

    public GenericUnaryOperator(final GenericEvaluable<T> argument) {
        this.argument = argument;
    }

    public abstract AbstractWrapper<T> apply(AbstractWrapper<T> argument);

    @Override
    public AbstractWrapper<T> evaluate(AbstractWrapper<T> x, AbstractWrapper<T> y, AbstractWrapper<T> z) {
        return apply(argument.evaluate(x, y, z));
    }
}

package expression.generic.operations;

import expression.generic.wrapper.AbstractWrapper;

public class Divide<T extends Number> extends GenericBinaryOperation<T> {

    public Divide(GenericEvaluable<T> left, GenericEvaluable<T> right) {
        super(left, right);
    }

    @Override
    public AbstractWrapper<T> apply(AbstractWrapper<T> left, AbstractWrapper<T> right) {
        return left.divide(right);
    }
}
package expression.generic.operations;

import expression.generic.wrapper.AbstractWrapper;

public class Subtract<T extends Number> extends GenericBinaryOperation<T> {

    public Subtract(GenericEvaluable<T> left, GenericEvaluable<T> right) {
        super(left, right);
    }

    @Override
    public AbstractWrapper<T> apply(AbstractWrapper<T> left, AbstractWrapper<T> right) {
        return left.subtract(right);
    }
}

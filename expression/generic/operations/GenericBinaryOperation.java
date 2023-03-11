package expression.generic.operations;

import expression.generic.wrapper.AbstractWrapper;

public abstract class GenericBinaryOperation<T extends Number> implements GenericEvaluable<T> {

    private final GenericEvaluable<T> left;
    private final GenericEvaluable<T> right;

    public GenericBinaryOperation(GenericEvaluable<T> left, GenericEvaluable<T> right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public AbstractWrapper<T> evaluate(AbstractWrapper<T> x, AbstractWrapper<T> y, AbstractWrapper<T> z) {
        return apply(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    public abstract AbstractWrapper<T> apply(AbstractWrapper<T> left, AbstractWrapper<T> right);
}

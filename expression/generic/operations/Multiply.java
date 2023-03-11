package expression.generic.operations;

import expression.generic.wrapper.AbstractWrapper;

public class Multiply<T extends Number> extends GenericBinaryOperation<T>{


    public Multiply(GenericEvaluable<T> left, GenericEvaluable<T> right) {
        super(left, right);
    }

    @Override
    public AbstractWrapper<T> apply(AbstractWrapper<T> left, AbstractWrapper<T> right) {
        return left.multiply(right);
    }
}

package expression.generic.operations;

import expression.generic.wrapper.AbstractWrapper;

public class Const<T extends Number> implements GenericEvaluable<T> {

    private final AbstractWrapper<T> value;

    public Const(AbstractWrapper<T> value) {
        this.value = value;
    }

    @Override
    public AbstractWrapper<T> evaluate(AbstractWrapper<T> x, AbstractWrapper<T> y, AbstractWrapper<T> z) {
        return value;
    }
}

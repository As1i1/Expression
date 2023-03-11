package expression.generic.operations;

import expression.generic.wrapper.AbstractWrapper;

public interface GenericEvaluable<T extends Number>{

    AbstractWrapper<T> evaluate(AbstractWrapper<T> x, AbstractWrapper<T> y, AbstractWrapper<T> z);
}

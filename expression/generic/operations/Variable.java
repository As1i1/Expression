package expression.generic.operations;

import expression.generic.wrapper.AbstractWrapper;

public class Variable<T extends Number> implements GenericEvaluable<T> {

    private final String variable;

    public Variable(String variable){
        this.variable = variable;
    }

    @Override
    public AbstractWrapper<T> evaluate(AbstractWrapper<T> x, AbstractWrapper<T> y, AbstractWrapper<T> z) {
        if (variable.equals("x")) {
            return x;
        } else if (variable.equals("y")) {
            return y;
        } else {
            return z;
        }
    }
}

package expression.generic.wrapper;

import java.math.BigInteger;


public class IntegerWrapper extends AbstractWrapper<Integer>{

    public IntegerWrapper(Integer value) {
        super(value);
    }
    public static IntegerWrapper intToWrapper(Integer value) {
        return new IntegerWrapper(value);
    }

    public static IntegerWrapper parseConstant(String number) {
        return new IntegerWrapper(Integer.parseInt(number));
    }

    @Override
    public AbstractWrapper<Integer> negate() {
        return new IntegerWrapper(-getValue());
    }

    @Override
    public AbstractWrapper<Integer> divide(AbstractWrapper<Integer> other) {
        return new IntegerWrapper(getValue() / other.getValue());
    }

    @Override
    public AbstractWrapper<Integer> multiply(AbstractWrapper<Integer> other) {
        return new IntegerWrapper(getValue() * other.getValue());
    }

    @Override
    public AbstractWrapper<Integer> add(AbstractWrapper<Integer> other) {
        return new IntegerWrapper(getValue() + other.getValue());
    }

    @Override
    public AbstractWrapper<Integer> subtract(AbstractWrapper<Integer> other) {
        return new IntegerWrapper(getValue() - other.getValue());
    }

    @Override
    public AbstractWrapper<Integer> abs() {
        return new IntegerWrapper(Math.abs(getValue()));
    }

    @Override
    public AbstractWrapper<Integer> square() {
        return multiply(this);
    }

    @Override
    public AbstractWrapper<Integer> mod(AbstractWrapper<Integer> other) {
        return new IntegerWrapper(getValue() % other.getValue());
    }
}

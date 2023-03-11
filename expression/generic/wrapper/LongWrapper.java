package expression.generic.wrapper;

import java.math.BigInteger;

public class LongWrapper extends AbstractWrapper<Long>{
    public LongWrapper(Long value) {
        super(value);
    }

    public static LongWrapper intToWrapper(Integer value) {
        return new LongWrapper(value.longValue());
    }

    public static LongWrapper parseConstant(String number) {
        return new LongWrapper(Long.parseLong(number));
    }

    @Override
    public AbstractWrapper<Long> negate() {
        return new LongWrapper(-getValue());
    }

    @Override
    public AbstractWrapper<Long> divide(AbstractWrapper<Long> other) {
        return new LongWrapper(getValue() / other.getValue());
    }

    @Override
    public AbstractWrapper<Long> multiply(AbstractWrapper<Long> other) {
        return new LongWrapper(getValue() * other.getValue());
    }

    @Override
    public AbstractWrapper<Long> add(AbstractWrapper<Long> other) {
        return new LongWrapper(getValue() + other.getValue());
    }

    @Override
    public AbstractWrapper<Long> subtract(AbstractWrapper<Long> other) {
        return new LongWrapper(getValue() - other.getValue());
    }

    @Override
    public AbstractWrapper<Long> abs() {
        return new LongWrapper(Math.abs(getValue()));
    }

    @Override
    public AbstractWrapper<Long> square() {
        return multiply(this);
    }

    @Override
    public AbstractWrapper<Long> mod(AbstractWrapper<Long> other) {
        return new LongWrapper(getValue() % other.getValue());
    }
}

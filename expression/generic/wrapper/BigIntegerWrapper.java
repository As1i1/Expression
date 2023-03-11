package expression.generic.wrapper;

import java.math.BigInteger;

public class BigIntegerWrapper extends AbstractWrapper<BigInteger> {

    public BigIntegerWrapper(BigInteger value) {
        super(value);
    }

    public static BigIntegerWrapper intToWrapper(Integer value) {
        return new BigIntegerWrapper(new BigInteger(value.toString()));
    }

    public static BigIntegerWrapper parseConstant(String number) {
        return new BigIntegerWrapper(new BigInteger(number));
    }

    @Override
    public BigIntegerWrapper negate() {
        return new BigIntegerWrapper(getValue().multiply(BigInteger.valueOf(-1)));
    }

    @Override
    public AbstractWrapper<BigInteger> divide(AbstractWrapper<BigInteger> other) {
        return new BigIntegerWrapper(getValue().divide(other.getValue()));
    }

    @Override
    public AbstractWrapper<BigInteger> multiply(AbstractWrapper<BigInteger> other) {
        return new BigIntegerWrapper(getValue().multiply(other.getValue()));
    }

    @Override
    public AbstractWrapper<BigInteger> add(AbstractWrapper<BigInteger> other) {
        return new BigIntegerWrapper(getValue().add(other.getValue()));
    }

    @Override
    public AbstractWrapper<BigInteger> subtract(AbstractWrapper<BigInteger> other) {
        return new BigIntegerWrapper(getValue().subtract(other.getValue()));
    }

    @Override
    public AbstractWrapper<BigInteger> abs() {
        return new BigIntegerWrapper(getValue().abs());
    }

    @Override
    public AbstractWrapper<BigInteger> square() {
        return multiply(this);
    }

    @Override
    public AbstractWrapper<BigInteger> mod(AbstractWrapper<BigInteger> other) {
        return new BigIntegerWrapper(getValue().mod(other.getValue()));
    }

}

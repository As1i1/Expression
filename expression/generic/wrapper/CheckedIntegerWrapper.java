package expression.generic.wrapper;

import expression.exceptions.*;

public class CheckedIntegerWrapper extends AbstractWrapper<Integer>{

    public CheckedIntegerWrapper(Integer value) {
        super(value);
    }

    public static CheckedIntegerWrapper intToWrapper(Integer value) {
        return new CheckedIntegerWrapper(value);
    }

    public static CheckedIntegerWrapper parseConstant(String number) {
        return new CheckedIntegerWrapper(Integer.parseInt(number));
    }

    @Override
    public AbstractWrapper<Integer> negate() {
        return new CheckedIntegerWrapper(CheckedNegate.negate(getValue()));
    }

    @Override
    public AbstractWrapper<Integer> divide(AbstractWrapper<Integer> other) {
        return new CheckedIntegerWrapper(CheckedDivide.divide(getValue(), other.getValue()));
    }

    @Override
    public AbstractWrapper<Integer> multiply(AbstractWrapper<Integer> other) {
        return new CheckedIntegerWrapper(CheckedMultiply.multiply(getValue(), other.getValue()));
    }

    @Override
    public AbstractWrapper<Integer> add(AbstractWrapper<Integer> other) {
        return new CheckedIntegerWrapper(CheckedAdd.add(getValue(), other.getValue()));
    }

    @Override
    public AbstractWrapper<Integer> subtract(AbstractWrapper<Integer> other) {
        return new CheckedIntegerWrapper(CheckedSubtract.subtract(getValue(), other.getValue()));
    }

    @Override
    public AbstractWrapper<Integer> abs() {
        return new CheckedIntegerWrapper(CheckedAbs.abs(getValue()));
    }

    @Override
    public AbstractWrapper<Integer> square() {
        return multiply(this);
    }

    @Override
    public AbstractWrapper<Integer> mod(AbstractWrapper<Integer> other) {
        return new CheckedIntegerWrapper(CheckedMod.mod(getValue(), other.getValue()));
    }
}

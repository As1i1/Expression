package expression.generic.wrapper;

public class ShortWrapper extends AbstractWrapper<Short>{

    public ShortWrapper(Short value) {
        super(value);
    }

    public static ShortWrapper intToWrapper(Integer value) {
        return new ShortWrapper(value.shortValue());
    }

    public static ShortWrapper parseConstant(String number) {
        return new ShortWrapper((short) Integer.parseInt(number));
    }

    @Override
    public AbstractWrapper<Short> negate() {
        return new ShortWrapper((short) -getValue());
    }

    @Override
    public AbstractWrapper<Short> divide(AbstractWrapper<Short> other) {
        return new ShortWrapper((short) (getValue() / other.getValue()));
    }

    @Override
    public AbstractWrapper<Short> multiply(AbstractWrapper<Short> other) {
        return new ShortWrapper((short) (getValue() * other.getValue()));
    }

    @Override
    public AbstractWrapper<Short> add(AbstractWrapper<Short> other) {
        return new ShortWrapper((short) (getValue() + other.getValue()));
    }

    @Override
    public AbstractWrapper<Short> subtract(AbstractWrapper<Short> other) {
        return new ShortWrapper((short) (getValue() - other.getValue()));
    }

    @Override
    public AbstractWrapper<Short> abs() {
        return new ShortWrapper((short) Math.abs(getValue()));
    }

    @Override
    public AbstractWrapper<Short> square() {
        return multiply(this);
    }

    @Override
    public AbstractWrapper<Short> mod(AbstractWrapper<Short> other) {
        return new ShortWrapper((short) (getValue() % other.getValue()));
    }
}

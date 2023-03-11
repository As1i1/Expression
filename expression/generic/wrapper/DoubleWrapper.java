package expression.generic.wrapper;

public class DoubleWrapper extends AbstractWrapper<Double>{

    public DoubleWrapper(Double value) {
        super(value);
    }

    public static DoubleWrapper intToWrapper(Integer value) {
        return new DoubleWrapper(value.doubleValue());
    }

    public static DoubleWrapper parseConstant(String number) {
        return new DoubleWrapper(Double.parseDouble(number));
    }

    @Override
    public AbstractWrapper<Double> negate() {
        return new DoubleWrapper(-getValue());
    }

    @Override
    public AbstractWrapper<Double> divide(AbstractWrapper<Double> other) {
        return new DoubleWrapper(getValue() / other.getValue());
    }

    @Override
    public AbstractWrapper<Double> multiply(AbstractWrapper<Double> other) {
        return new DoubleWrapper(getValue() * other.getValue());
    }

    @Override
    public AbstractWrapper<Double> add(AbstractWrapper<Double> other) {
        return new DoubleWrapper(getValue()  + other.getValue());
    }

    @Override
    public AbstractWrapper<Double> subtract(AbstractWrapper<Double> other) {
        return new DoubleWrapper(getValue() - other.getValue());
    }

    @Override
    public AbstractWrapper<Double> abs() {
        return new DoubleWrapper(Math.abs(getValue()));
    }

    @Override
    public AbstractWrapper<Double> square() {
        return multiply(this);
    }

    @Override
    public AbstractWrapper<Double> mod(AbstractWrapper<Double> other) {
        return new DoubleWrapper(getValue() % other.getValue());
    }
}

package expression.generic.wrapper;

public abstract class AbstractWrapper<T extends Number> {

    private final T value;

    public AbstractWrapper(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public abstract AbstractWrapper<T> negate();

    public abstract AbstractWrapper<T> divide(AbstractWrapper<T> other);

    public abstract AbstractWrapper<T> multiply(AbstractWrapper<T> other);

    public abstract AbstractWrapper<T> add(AbstractWrapper<T> other);
    public abstract AbstractWrapper<T> subtract(AbstractWrapper<T> other);

    public abstract AbstractWrapper<T> abs();

    public abstract AbstractWrapper<T> square();

    public abstract AbstractWrapper<T> mod(AbstractWrapper<T> other);

}

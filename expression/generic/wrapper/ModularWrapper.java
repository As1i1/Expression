package expression.generic.wrapper;

import expression.exceptions.DivisionByZeroException;

import java.math.BigInteger;

public class ModularWrapper extends AbstractWrapper<Integer>{

    private static final int MOD = 10079;
    public ModularWrapper(Integer value) {
        super((value % MOD + MOD) % MOD);
    }

    private static Integer modValue(Integer value) {
        return (value % MOD + MOD) % MOD;
    }

    private Integer modInverse(Integer value) {
        if (value == 0) {
            throw new DivisionByZeroException("Division by zero");
        }
        return binPow(value, MOD - 2);
    }

    private int binPow(int a, int b) {
        if (b == 0) return 1;
        if (b % 2 == 1) return binPow(a, b - 1) * a % MOD;
        else {
            int t = binPow(a, b / 2);
            return t * t % MOD;
        }
    }

    public static ModularWrapper intToWrapper(Integer value) {
        return new ModularWrapper(modValue(value));
    }

    public static ModularWrapper parseConstant(String number) {
        return new ModularWrapper(modValue(Integer.parseInt(number)));
    }

    @Override
    public AbstractWrapper<Integer> negate() {
        return new ModularWrapper(modValue(-getValue()));
    }

    @Override
    public AbstractWrapper<Integer> divide(AbstractWrapper<Integer> other) {
        return new ModularWrapper(getValue() * modInverse(other.getValue()));
    }

    @Override
    public AbstractWrapper<Integer> multiply(AbstractWrapper<Integer> other) {
        return new ModularWrapper(modValue(getValue() * other.getValue()));
    }

    @Override
    public AbstractWrapper<Integer> add(AbstractWrapper<Integer> other) {
        return new ModularWrapper(modValue(getValue() + other.getValue()));
    }

    @Override
    public AbstractWrapper<Integer> subtract(AbstractWrapper<Integer> other) {
        return new ModularWrapper(modValue(getValue() - other.getValue()));
    }

    @Override
    public AbstractWrapper<Integer> abs() {
        return new ModularWrapper(modValue(Math.abs(getValue())));
    }

    @Override
    public AbstractWrapper<Integer> square() {
        return multiply(this);
    }

    @Override
    public AbstractWrapper<Integer> mod(AbstractWrapper<Integer> other) {
        return new ModularWrapper(modValue(getValue() % other.getValue()));
    }
}

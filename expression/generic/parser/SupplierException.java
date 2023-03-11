package expression.generic.parser;

public interface SupplierException<T ,E extends Exception> {

    T get() throws E;

}

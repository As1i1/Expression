package expression.operations;


@FunctionalInterface
@SuppressWarnings("ClassReferencesSubclass")
public interface TripleExpression extends ToMiniString {

    int evaluate(int x, int y, int z);

}

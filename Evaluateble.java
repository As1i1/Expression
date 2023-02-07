package expression;

public interface Evaluateble extends TripleExpression{

    boolean getCommutativity();

    int getReflexivity();

    int getPriority();
}

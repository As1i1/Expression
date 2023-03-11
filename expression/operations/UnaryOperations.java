package expression.operations;

import java.util.function.UnaryOperator;

public abstract class UnaryOperations implements Evaluateble {
    UnaryOperator<Integer> operator;
    String stringOperator;
    Evaluateble expr;
    Priority priority;

    protected UnaryOperations(Evaluateble expr, UnaryOperator<Integer> operator, Priority priority, String stringOp){
        this.expr = expr;
        this.operator = operator;
        this.stringOperator = stringOp;
        this.priority = priority;
    }

    @Override
    public boolean getCommutativity() {
        return priority.commutativity;
    }

    @Override
    public int getReflexivity() {
        return priority.reflexivity;
    }

    @Override
    public int getPriority() {
        return priority.priority;
    }

    @Override
    public String toMiniString() {
        if (priority.priority == expr.getPriority()) {
            return stringOperator + " " + expr.toMiniString();
        } else {
            return stringOperator + "(" + expr.toMiniString() + ")";
        }
    }

    @Override
    public String toString() {
        return stringOperator + "(" + expr.toString() + ")";
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return operator.apply(expr.evaluate(x, y, z));
    }
}

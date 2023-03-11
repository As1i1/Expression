package expression.operations;

import java.util.Objects;
import java.util.function.BinaryOperator;

public abstract class BinaryOperation implements Evaluateble {
    BinaryOperator<Integer> operatorInt;
    String operatorString;
    Evaluateble left;
    Evaluateble right;
    final Priority priority;

    protected BinaryOperation(Evaluateble left, Evaluateble right, BinaryOperator<Integer> operatorInt
            , Priority priority, String opString) {
        this.left = left;
        this.right = right;
        this.operatorInt = operatorInt;
        operatorString = opString;
        this.priority = priority;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return operatorInt.apply(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " " + operatorString + " " + right.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BinaryOperation obj1){
            return obj1.operatorString.equals(operatorString) &&
                    obj1.right.equals(right) && obj1.left.equals(left);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(operatorInt, operatorString, left, right, priority);
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
        StringBuilder sb = new StringBuilder();
        if (left.getPriority() > getPriority()) {
            sb.append("(").append(left.toMiniString()).append(")");
        } else {
            sb.append(left.toMiniString());
        }

        sb.append(" ").append(operatorString).append(" ");


        if (right.getPriority() > getPriority()) {
            sb.append("(").append(right.toMiniString()).append(")");
        } else if (right.getPriority() == getPriority() &&
                (!getCommutativity() || getReflexivity() != right.getReflexivity())) {

            sb.append("(").append(right.toMiniString()).append(")");
        } else {
            sb.append(right.toMiniString());
        }

        return sb.toString();
    }
}

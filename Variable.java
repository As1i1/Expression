package expression;

import java.util.Objects;

public class Variable implements Evaluateble {

    String variable;
    final Priority priority = new Priority(0, true, 1);

    public Variable(String x) {
        variable = x;
    }

    @Override
    public String toMiniString() {
        return variable;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if (Objects.equals(variable, "x")) {
            return x;
        } else if (Objects.equals(variable, "y")) {
            return y;
        } else {
            return z;
        }
    }

    @Override
    public String toString() {
        return variable;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Variable obj1) {
            return Objects.equals(obj1.variable, variable);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(variable);
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
}

package expression;

import java.util.Objects;

public class Const implements Evaluateble {

    Number value;
    final Priority priority = new Priority(0, true, 1);
    public Const (int val){
        value = val;
    }

    public Const (double val) {
        value = val;
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value.intValue();
    }


    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Const obj1) {
            return Objects.equals(obj1.value, value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
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

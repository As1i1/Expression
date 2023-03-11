package expression.operations;

import java.util.Objects;

public class Priority {
    final boolean commutativity;
    final int reflexivity;
    final int priority;

    public Priority(int priority, boolean commutativity, int reflexivity) {
        this.commutativity = commutativity;
        this.reflexivity = reflexivity;
        this.priority = priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(commutativity, reflexivity, priority);
    }
}

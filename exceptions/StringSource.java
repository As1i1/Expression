package expression.exceptions;

public class StringSource implements CharSource{

    private final String string;
    private int pos = 0;

    public StringSource(String string){
        this.string = string;
    }

    @Override
    public boolean hasNext() {
        return pos < string.length();
    }

    @Override
    public void setPos(int pos) {
        if (pos >= string.length() || pos < 0) {
            throw new IllegalArgumentException("Cannot set pos: " + pos);
        }
        this.pos = pos;
    }

    @Override
    public int getPos() {
        return pos == 0 ? 0 : pos - 1;
    }

    @Override
    public char next() {
        return string.charAt(pos++);
    }

    @Override
    public IllegalArgumentException error(String message) {
        return new IllegalArgumentException(message);
    }
}

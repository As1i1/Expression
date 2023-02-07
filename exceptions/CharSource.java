package expression.exceptions;

public interface CharSource {
    boolean hasNext();
    char next();

    int getPos();

    void setPos(int position);

    IllegalArgumentException error(String message);

}

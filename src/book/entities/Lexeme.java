package book.entities;

/**
 * Created by tonchief on 01/14/2017.
 *
 * Lexeme = Wrapper for words & separators / punctuation marks
 *
 */

public interface Lexeme {
    String string = "";
    @Override
    String toString();
    Integer length();
    boolean isWord();
    void update(String word);
}

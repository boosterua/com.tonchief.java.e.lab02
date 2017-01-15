package book.entities;

/**
 * Created by tonchief on 01/14/2017.
 */
public class PunctuationMark implements Lexeme {
    private String string;

    public PunctuationMark(String string) {
        this.string = string;
    }

    public String toString(){
        return string;
    }

    @Override
    public Integer length() {
        return string.length();
    }

    @Override
    public boolean isWord() {
        return false;
    }

    @Override
    public void update(String word) {
        this.string = word;
    }


    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

}

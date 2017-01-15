package book.entities;

/**
 * Created by tonchief on 01/14/2017.
 */
public class Word implements Lexeme{
    private String string;

    public Word(String wordText) {
        this.string = wordText;
    }

    @Override
    public String toString(){
        return string;
    }

    @Override
    public boolean isWord() {
        return true;
    }

    @Override
    public void update(String word) {
        this.string = word;
    }

    @Override
    public Integer length (){
        return string.length();
    }

    public String getText() {
        return string;
    }


}

package book.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonchief on 01/14/2017.
 */
public class Sentence {
    private String sentenceText;
    private List<Lexeme> lexemes = new ArrayList<>();
    private Integer numWords;

    public Sentence(String sentence){
        this.sentenceText = sentence;
        this.numWords = 0;
    }

    public String getSentenceText() {
        return sentenceText;
    }

    public void setSentenceText(String sentenceText) {
        this.sentenceText = sentenceText;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        String lastLexeme="";
        for(Lexeme l:lexemes){
            str.append(lastLexeme + (l.isWord()?" ":"") );
            lastLexeme = l.toString();
        }
        return  str.delete(0,1).append(lastLexeme).toString();
    }

    public void addLexeme(Lexeme lexeme){
        lexemes.add(lexeme);
        if(lexeme instanceof Word) this.numWords++;
    }

    public List<Lexeme> getLexemes() {
        return lexemes;
    }

    public Integer getNumWords() {
        return numWords;
    }

    public boolean equals (Sentence target){
        return this.sentenceText.equals(target.toString());
    }

}

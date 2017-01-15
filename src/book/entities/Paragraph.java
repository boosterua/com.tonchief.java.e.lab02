package book.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonchief on 01/14/2017.
 */
public class Paragraph {
    private String paragraphText;
    private List<Sentence> sentences = new ArrayList<>();

    public String getParagraphText() {
        return paragraphText;
    }

    public void setParagraphText(String paragraphText) {
        this.paragraphText = paragraphText;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    public void addSentence(Sentence sentence) {
        this.sentences.add(sentence);
    }
}

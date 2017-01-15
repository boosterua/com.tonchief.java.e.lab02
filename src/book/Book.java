package book;
import book.entities.Paragraph;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by tonchief on 01/14/2017.
 */

public class Book {
    private Reader reader;
    private String bookRawText;
    private List<Paragraph> paragraphs = new ArrayList<>();

    private String name;
    private String fileNameFull;

    Book(String name, String filename){
        this.fileNameFull = filename;
        this.name = name;
    }

    @Override
    public String toString(){
        // Returns the name of book, file path, general statistics of
        // how many paragraphs/sentences/words this book contains
        return name + "\n" + fileNameFull + "\nNr.of Paragraphs:" + paragraphs.size();
    }

    public String getFileName(){
        return ""+ this.fileNameFull;
    }

    public void read()  {
        this.reader = new Reader(this);
        try {
            reader.read();
            bookRawText = reader.getText();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void parse() {
        Parser parser = new Parser(bookRawText);
        // split into paragraphs
        this.paragraphs = parser.extractParagraphs();
    }

    public List<Paragraph> getParagraphs(){
        return paragraphs;
    }
}

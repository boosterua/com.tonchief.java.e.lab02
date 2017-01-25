package book;
import book.entities.Lexeme;
import book.entities.Paragraph;
import book.entities.Sentence;
import book.utils.Parser;
import book.utils.Reader;
import book.utils.ValueComparator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

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

    Book(String rawText){
        this.bookRawText = rawText;
    }

    @Override
    public String toString(){

        StringBuilder bookText = new StringBuilder();
        for (Paragraph p: this.getParagraphs()) {
            List<Sentence> sentences = p.getSentences();
            for (Sentence s : sentences) {
                String lastLexeme="";
                for (Lexeme l : s.getLexemes()) {
                    bookText.append(lastLexeme + (l.isWord() ? " " : ""));
                    lastLexeme = l.toString();
                }
                bookText.delete(0,1).append(lastLexeme);
            }
            bookText.append("\n\n");
        }
        return bookText.toString();
    }

    public String getBookDetails(){
        // Returns the name of book, file path, general statistics of
        // how many paragraphs/sentences/words this book contains
        return name + "\n" + fileNameFull + "\n"+
                "Nr.of Paragraphs:" + paragraphs.size();
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
        this.paragraphs = parser.extractParagraphs();        //System.out.println(paragraphs.size());
    }

    public List<Paragraph> getParagraphs(){
        return paragraphs;
    }

    public void setBookRawText(String bookRawText) {
        this.bookRawText = bookRawText;
    }


    // 2.*	Вывести все предложения заданного текста в порядке возрастания количества слов в каждом из них.

    public static String Task02(Book book){
        HashMap<String, Integer> map = new HashMap<>();
        ValueComparator bvc = new ValueComparator(map);
        TreeMap<String, Integer> sentencesSortedByNrWords = new TreeMap<>(bvc);
        StringBuilder resultString = new StringBuilder(); // for testing only

        for (Paragraph p: book.getParagraphs()){
            List <Sentence> sentences = p.getSentences();
            for (Sentence s: sentences)
                map.put(s.toString(), s.getNumWords());
        }
        sentencesSortedByNrWords.putAll(map);

        PrintWriter out = null;
        try {
            String outputFileName = book.getFileName().replaceAll(".*/","").replaceAll("[^a-zA-Z0-9\\-_]","");
            out = new PrintWriter("~OUT02~" + outputFileName + ".txt");
            for(Map.Entry<String,Integer> entry: sentencesSortedByNrWords.entrySet()){
                String sentence = entry.getKey();
                Integer length = entry.getValue();
                out.print(sentence+"\n");
                resultString.append(sentence+"\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }   finally {
            try {
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ""+resultString;
    }



    // 16.* В некотором предложении текста слова заданной длины заменить указанной подстрокой, длина которой может не совпадать с длиной слова.

    public static String Task16(Book book, int length, String sentence, String substring){

        Sentence targetSentence = new Parser("").pushLexemesToSentence(sentence);
        int sentenceLength = targetSentence.getNumWords();
        String resultString = null;

        for (Paragraph p: book.getParagraphs()){
            List <Sentence> sentences = p.getSentences();
            for (Sentence s: sentences) {
                if (sentenceLength == s.getNumWords() && s.equals(targetSentence)) {
                    System.out.println("TARGET found!" + "\nReplacing words of length "+length + " with "+substring);
                    System.out.println("SRC: "+s);
                    for (Lexeme w : s.getLexemes()) {
                        if (w.isWord() && w.length() == length)
                            w.update(substring);
                    }
                    System.out.println("RES: "+s);
                    resultString = s.toString();
                }
            }
        }

        PrintWriter out = null;
        try {
            out = new PrintWriter("output16.txt");
            for (Paragraph p: book.getParagraphs()){
                for (Sentence s: p.getSentences())
                    out.print(s+"\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }   finally {
            try {
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }
}

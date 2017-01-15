package book;

import book.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tonchief on 01/14/2017.
 */
public class Parser {
    private String text;
    private static Pattern punct = Pattern.compile("\\p{Punct}");


    public Parser(String text){
        // read file
        this.text = text;
    }

    public String getText(String inputText){
        return inputText;
    }


    public List<Paragraph> extractParagraphs() {
        Paragraph p;
        List <Paragraph> pp = new ArrayList<>();


        // Test Text: 84Kb File with 5 paragraphs
        // 52..80s
        //      String [] s = text.split("(\n\r?){2,}|(?<=[!?.]+)(\n\r?)");
        // 70s
        //      String newLine = "\n";
        //      // Check if input stream file is in WIN or UNIX  (\n vs \r\n)
        //      // Speed up the split method at least twice
        //      Pattern pt = Pattern.compile("\r\n");
        //      Matcher m = pt.matcher(text);
        //      if (m.find()) newLine = "\r\n";
        //      String [] s = text.split("("+ newLine +"){2,}|(?<=[!?.]+)"+newLine);
        // 0.7 s
        //      String [] s = text.split("(\n\r){2,}|(?<=[!?.])\n\r");
        // 0.03s for given example;
        // 0.21s for 2.7 Mb file with 30640 paragraphs
        //      String [] paragraphs = text.split("(\n\r?){2,}|(?<=[!?.])(\n\r?)");

        String [] paragraphs = text.split("(\n\r?){2,}|(?<=[!?.])(\n\r?)");
        Matcher match;

        for(String paragraph: paragraphs){     //"(?<=;)"
                                                // ((?<=;)|(?=;)) equals to select an empty character before ; or after ;.
            p = new Paragraph();
            pp.add(p);

            String[] sentences = paragraph.split("(?<=[!?.])[.!?]*\\s*");
            for ( String str : sentences) {
                p.addSentence(pushLexemesToSentence( str.replaceAll("[\\n\\r]","-")) );
            }
        }

        return pp;
    }

    public Sentence pushLexemesToSentence(String input){
        Sentence sentence = new Sentence(input);
        for (String lexeme : input.split("\\s+|((?<=[\\p{Punct}&&[^']])|(?=[\\p{Punct}&&[^']]))")) {
            Lexeme lex = (punct.matcher(lexeme).matches())?
                    new PunctuationMark(lexeme) :
                    new Word(lexeme);
            sentence.addLexeme(lex);
        }
        return sentence;
    }





/*
     public List<Paragraph> extractParagraphs() {
        Paragraph p;
        List <Paragraph> pp = new ArrayList<>();

        long startTime = System.currentTimeMillis();

        if(false) {
            String newLine = "\n";
            // Check if input stream file is in WIN or UNIX  (\n vs \r\n)
            // Speed up the split method at least twice
            Pattern pt = Pattern.compile("\r\n");
            Matcher m = pt.matcher(text);
            if (m.find()) newLine = "\r\n";
        }

        // Test Text: 84Kb File with 5 paragraphs

        // 52..80s
        //      String [] s = text.split("(\n\r?){2,}|(?<=[!?.]+)(\n\r?)");           //System.out.println("Paragraphs total: " + s.length + "\nPARagraphS text:"); for (String t:s) System.out.println(++i + ") " + t);

        // 70s
        //      String [] s = text.split("("+ newLine +"){2,}|(?<=[!?.]+)"+newLine);

        // 0.7 s
        //      String [] s = text.split("(\n\r){2,}|(?<=[!?.])\n\r");

        // 0.03s for given example;
        // 0.21s for 2.7 Mb file with 30640 paragraphs
        String [] s = text.split("(\n\r?){2,}|(?<=[!?.])(\n\r?)");

        long endTime   = System.currentTimeMillis();

        for( String paragraph: s){    //"(?<=;)"
            p = new Paragraph();
            p.setText(paragraph);     //System.out.println("par " + ++i + ")\n" + paragraph + "\n[EOP]");
            pp.add(p);
        }

        //System.out.printf("Time spent splitting the book (s):%.2f%n" , (endTime - startTime)/1000.0);

        return pp;
    }
* */














//
//    public String extractParagraph() {
//        if (!file.exists()) {
//            throw new IllegalArgumentException(
//                    String.format("Specified source file '%s' does not exist!", file.getAbsolutePath())
//            );
//        }
//        try {
//
//            return parser.getText(readFileToString(file, FILE_ENCODING));
//
//        } catch (IOException e) {
//            throw new RuntimeException("Error reading file" + file.getAbsolutePath());
//        }
//    }


}

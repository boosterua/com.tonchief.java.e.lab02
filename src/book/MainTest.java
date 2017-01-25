package book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by tonchief on 01/16/2017.
 */
class MainTest {


    @Test
    void bookNull() {
        System.out.println("Book class tests*");
        Book book = new Book(null);
        book.parse();
        assertEquals("", book.toString());
    }

    @Test
    void bookNonExisting() {
        System.out.println("Book class tests /Doesn't exist!/*");
        Book book = new Book("", "notExisiting.file");
        book.parse();
        assertEquals("", book.toString());
    }


    String text = "What Are Regular Expressions?\n" +
            "\n" +
            "Regular expressions are a way to describe a set of strings based on common characteristics shared by each string in the set. They can be used to search, edit, or manipulate text and data. You must learn a specific syntax to create regular expressions — one that goes beyond the normal syntax of the Java programming language. Regular expressions vary in complexity, but once you understand the basics of how they're constructed, you'll be able to decipher (or create) any regular expression.\n" +
            "\n" +
            "This trail teaches the regular expression syntax supported by the java.util.regex API and presents several working examples to illustrate how the various objects interact. In the world of regular expressions, there are many different flavors to choose from, such as grep, Perl, Tcl, Python, PHP, and awk. The regular expression syntax in the java.util.regex API is most similar to that found in Perl.\n" +
            "\n" +
            "How Are Regular Expressions Represented in This Package?\n" +
            "\n" +
            "The java.util.regex package primarily consists of three classes: Pattern, Matcher, and PatternSyntaxException.\n" +
            "\n" +
            "A Pattern object is a compiled representation of a regular expression. The Pattern class provides no public constructors." +
            "To create a pattern, you must first invoke one of its public static compile methods, which will then return a Pattern object. " +
            "These methods accept a regular expression as the first argument; the first few lessons of this trail will teach you the required syntax.\n" +
            "A Matcher object is the engine that interprets the pattern and performs match operations against an input string. " +
            "Like the Pattern class, Matcher defines no public constructors. " +
            "You obtain a Matcher object by invoking the matcher method on a Pattern object.\n" +
            "A PatternSyntaxException object is an unchecked exception that indicates a syntax error in a regular expression pattern.\n" +
            "The last few lessons of this trail explore each class in detail. " +
            "But first, you must understand how regular expressions are actually constructed. " +
            "Therefore, the next section introduces a simple test harness that will be used repeatedly to explore their syntax.";

    String textSortedByNrWords = "What Are Regular Expressions?\n" +
            "How Are Regular Expressions Represented in This Package?\n" +
            "Like the Pattern class, Matcher defines no public constructors.\n" +
            "But first, you must understand how regular expressions are actually constructed.\n" +
            "A Pattern object is a compiled representation of a regular expression.\n" +
            "The last few lessons of this trail explore each class in detail.\n" +
            "They can be used to search, edit, or manipulate text and data.\n" +
            "The java. util. regex package primarily consists of three classes: Pattern, Matcher, and PatternSyntaxException.\n" +
            "You obtain a Matcher object by invoking the matcher method on a Pattern object.\n" +
            "A PatternSyntaxException object is an unchecked exception that indicates a syntax error in a regular expression pattern.\n" +
            "Therefore, the next section introduces a simple test harness that will be used repeatedly to explore their syntax.\n" +
            "The regular expression syntax in the java. util. regex API is most similar to that found in Perl.\n" +
            "A Matcher object is the engine that interprets the pattern and performs match operations against an input string.\n" +
            "Regular expressions are a way to describe a set of strings based on common characteristics shared by each string in the set.\n" +
            "You must learn a specific syntax to create regular expressions — one that goes beyond the normal syntax of the Java programming language.\n" +
            "In the world of regular expressions, there are many different flavors to choose from, such as grep, Perl, Tcl, Python, PHP, and awk.\n" +
            "These methods accept a regular expression as the first argument; the first few lessons of this trail will teach you the required syntax.\n" +
            "Regular expressions vary in complexity, but once you understand the basics of how they're constructed, you'll be able to decipher ( or create) any regular expression.\n" +
            "This trail teaches the regular expression syntax supported by the java. util. regex API and presents several working examples to illustrate how the various objects interact.\n" +
            "The Pattern class provides no public constructors. To create a pattern, you must first invoke one of its public static compile methods, which will then return a Pattern object.\n";

    @Test
    void task02() {
        //2.*	Вывести все предложения заданного текста в порядке возрастания количества слов в каждом из них.

        System.out.println("Task02*");
        Book book = new Book(text);
        book.parse();
        String result = Book.Task02(book);
        assertEquals(textSortedByNrWords, result);
        int nrWordsLast = 0;
        for (String line: textSortedByNrWords.split("\n")){
            int nrWords  = (line.split("[^\\w\\S']+")).length;
            assertTrue(nrWords>=nrWordsLast);
            System.out.println("nrWords >= nrWordsLast : " + nrWords+" >="+nrWordsLast);
            nrWordsLast = nrWords;
        }
        System.out.println("02.Finished");

    }

    @Test
    void task16() {
        System.out.println("Task16*");
        Book book = new Book(text);
        book.parse();

        int length = 6;
        String sentence = "You obtain a Matcher object by invoking the matcher method on a Pattern object.";
        String substring = "[##_6_chars_Replaced_##]";

        String result = Book.Task16(book, length, sentence, substring);
        String sentenceExpected = "You [##_6_chars_Replaced_##] a Matcher [##_6_chars_Replaced_##] " +
                "by invoking the matcher [##_6_chars_Replaced_##] on a Pattern [##_6_chars_Replaced_##].";
        System.out.println(book.toString());
        assertEquals(sentenceExpected, result);
    }


}
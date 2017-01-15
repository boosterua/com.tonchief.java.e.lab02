package book;/*
* LAB 2
* Создать программу обработки текста учебника по программированию с использованием классов:
* Символ, Слово, Предложение, Знак препинания и др.
* Во всех задачах с формированием текста
* заменять табуляции и последовательности пробелов одним пробелом.
*
*/
/*
1.  	Найти наибольшее количество предложений текста, в которых есть одинаковые слова.
2.*	Вывести все предложения заданного текста в порядке возрастания количества слов в каждом из них.
3.  	Найти такое слово в первом предложении, которого нет ни в одном из остальных предложений.
4.      *	Во всех вопросительных предложениях текста найти и напечатать без повторений слова заданной длины.
5.  	В каждом предложении текста поменять местами первое слово с последним, не изменяя длины предложения.
6.      *	Напечатать слова текста в алфавитном порядке по первой букве. Слова, начинающиеся с новой буквы, печатать с красной строки.
7.  	Рассортировать слова текста по возрастанию доли гласных букв (отношение количества гласных к общему количеству букв в слове).
8.  	Слова текста, начинающиеся с гласных букв, рассортировать в алфавитном порядке по первой согласной букве слова.
9.  	Все слова текста рассортировать по возрастанию количества заданной буквы в слове. Слова с одинаковым коли¬чеством расположить в алфавитном порядке.
10. 	Существует текст и список слов. Для каждого слова из заданного списка найти, сколько раз оно встречается в каждом предложении, и рассортировать слова по убыванию общего количества вхождений.
11. 	В каждом предложении текста исключить подстроку максимальной длины, начинающуюся и заканчивающуюся заданными символами.
12.     * Из текста удалить все слова заданной длины, начинающиеся на согласную букву.
13. 	Отсортировать слова в тексте по убыванию количества вхождений заданного символа, а в случае равенства – по алфавиту.
14.     * В заданном тексте найти подстроку максимальной длины, являющуюся палиндромом, т.е. читающуюся слева направо и справа налево одинаково.
15.	    Преобразовать каждое слово в тексте, удалив из него все последующие (предыдущие) вхождения первой (последней) буквы этого слова.
16.* В некотором предложении текста слова заданной длины заменить указанной подстрокой, длина которой может не совпадать с длиной слова.
*/


import book.entities.Lexeme;
import book.entities.Paragraph;
import book.entities.Sentence;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Book book;
        book = new Book("Bruce Eckel: Thinking in Java (4th ed)", "books/BRUCE_ECKEL_ThinkingInJava.txt");
        //   book = new book("debug test bookRaw", "books/test.txt");

        book.read();
        book.parse();
        System.out.println(book);

        Task02(book);

        Task16(book,7, "I won't explain the details of the rest of the code " +
                "other than to say that you can probably figure it out by walking through it.",
                "/***999***/");

        for (Paragraph p: book.getParagraphs()) {
            List<Sentence> sentences = p.getSentences();
            for (Sentence s : sentences)
                if (s.toString().contains("other than to say that you can probably")) {
                    System.out.println(s.toString());
                }
        }

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
                //System.out.println(sentence);
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


class ValueComparator implements Comparator<String>{ // took it from stackoverflow.
    Map<String, Integer> base;
    public ValueComparator(Map<String,Integer> base){
        this.base = base;
    }

    @Override
    public int compare(String a, String b) {
        if(base.get(b) >= base.get(a))
            return -1;
        return 1;
    }
}






/*
public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) { // I took it from: http://stackoverflow.com/questions/109383/sort-a-mapkey-value-by-values-java
    return map.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
            .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (e1, e2) -> e1,
                    LinkedHashMap::new
            ));
}*/

/*
class Test{
    public void main(){
//        String text="123" +
//                "\n\n" +
//                "321." +
//                "\n";
//        //List<String> s = new ArrayList<>();
//        String [] s = text.split("\n\\n|(?<=[!?.]+)\n");
//        System.out.println(s.length);
//        for (String t:s) System.out.println(t);
//        System.exit(0);

        book book;
        book = new book("Bruce Eckel: Thinking in Java (4th ed)", "books/BRUCE_ECKEL_ThinkingInJava.txt");
        //     book = new book("debug test bookRaw", "books/test.txt");

        book.read();
        book.parse();
        System.out.println(book);

// 2.*	Вывести все предложения заданного текста в порядке возрастания количества слов в каждом из них.


        int i=0;
        for (Paragraph p: book.getParagraphs()){
            List <Sentence> sentences = p.getSentences();
            for (Sentence s: sentences){
                List<Lexeme> lexemes = s.getLexemes();
                for (Lexeme c:lexemes){
                    if(c instanceof Word)
                        System.out.print(""+c+" ");
                    else
                        System.out.print(""+c+" ");
                }
                System.out.println("");
            }
            if(i++>20) System.exit(0);
            System.out.println("\n");
            //System.out.println(++i+") " + p.getParagraphText() + "\n{EOP]");
        }
    }
}
*/
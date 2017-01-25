package book.utils;
import book.Book;

import java.io.*;
import java.util.Objects;


/**
 * Created by tonchief on 01/14/2017.
 *
 */
public class Reader {

        private static final String FILE_ENCODING = "UTF-8";
        private File file;
        public Book book;
        private String bookText;
        private boolean atTheEOF = true;

        public Reader (Book book){
            this.book = book;
            String fileName = book.getFileName();
            Objects.requireNonNull(fileName);
            this.file = new File(fileName);
        }


        public void read() throws IOException {
            if (!file.exists()) {
                throw new IllegalArgumentException(String.format("Specified source file '%s' does not exist!", file.getAbsolutePath()));
            }
            try {
                this.bookText = readFileToString(file, FILE_ENCODING);
            } catch (IOException e) {
                throw new RuntimeException("Error reading file" + file.getAbsolutePath());
            }
        }

        public String getText(){
            return this.bookText;
        }

        private String readFileToString(File file, String encoding) throws IOException {

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                StringBuilder sb = new StringBuilder();
                atTheEOF = false;
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                return sb.toString();
            }
        }

        public boolean hasNext(){
            return !atTheEOF;
        }

}

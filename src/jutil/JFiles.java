package jutil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class JFiles {
    private static Scanner fileReader;
    private static PrintWriter fileWriter;

    private static final boolean DEFAULT_APPEND = false;

    private JFiles() {
    }

    /**
     * Returns the number of tokens in {@code file} seperated by whitespace or
     * newlines.
     * 
     * @param file the file
     * @return number of words in the file
     * @throws FileNotFoundException if the file isn't found
     */
    public static int countWords(File file) throws FileNotFoundException {
        getScanner(file);

        int count = 0;
        while (fileReader.hasNext()) {
            fileReader.next();
            count++;
        }
        return count;
    }

    /**
     * Returns the number of lines in {@code file}.
     * <p>
     * A {@code \n} counts as a new line.
     * 
     * @param file the file
     * @return number of lines in the file
     * @throws FileNotFoundException if the file isn't found
     */
    public static int countLines(File file) throws FileNotFoundException {
        getScanner(file);
        int count = 0;
        while (fileReader.hasNextLine()) {
            fileReader.nextLine();
            count++;
        }
        return count;
    }

    /**
     * Reads the lines of {@code file} into a list of strings.
     * <p>
     * Each line is read into a String and stored in an ArrayList
     * 
     * @param file the file
     * @return list of the lines of the file
     * @throws FileNotFoundException if the file isn't found
     */
    public static List<String> readLines(File file) throws FileNotFoundException {
        getScanner(file);

        List<String> tokens = new ArrayList<>();

        while (fileReader.hasNextLine()) {
            tokens.add(fileReader.nextLine());
        }
        return tokens;
    }

    /**
     * Reads the words of {@code file} into a list of strings.
     * <p>
     * Token seperated by a whitespace or a newline are read as String and stored in
     * an ArrayList.
     * 
     * @param file the file
     * @return list of the words of the file
     * @throws FileNotFoundException if the file isn't found
     */
    public static List<String> readWords(File file) throws FileNotFoundException {
        getScanner(file);

        List<String> tokens = new ArrayList<>();

        while (fileReader.hasNext()) {
            tokens.add(fileReader.next());
        }
        return tokens;
    }

    /**
     * Reads the entirety of {@code file} into a single continous String.
     * <p>
     * New lines are removed and whitespaces are conserved.
     * <p>
     * The file is traversed and each token is appended into a StringBuilder.
     * 
     * @param file the file to read
     * @return the file as a String
     * @throws FileNotFoundException if the file isn't found
     */
    public static String read(File file) throws FileNotFoundException {
        getScanner(file);

        StringBuilder sb = new StringBuilder();

        while (fileReader.hasNext()) {
            sb.append(fileReader.next());
            sb.append(" ");
        }
        return sb.toString();
    }

    /**
     * Writes the given {@code text} into {@code file}.
     * <p>
     * If {@code true} is passed to append the file contents are preserved and they
     * are overwritten otherwise.
     * 
     * @param file   the file to write to
     * @param text   the text to write to the file
     * @param append {@code true} preserves the contents and {@code false}
     *               overwrites them
     * @throws IOException if the write operation to the file was unsuccessful
     */
    public static void write(File file, String text, boolean append) throws IOException {
        getWriter(file, append);
        fileWriter.print(text);
        fileWriter.close();
    }

    /**
     * Writes the given {@code text} into {@code file}.
     * <p>
     * This overwrites any previous data in the file.
     * To write to the file without overwriting use
     * {@link #write(File, String, boolean)}.
     * 
     * @param file the file to write to
     * @param text the text to write to the file
     * @throws IOException if the write operation to the file was unsuccessful
     */
    public static void write(File file, String text) throws IOException {
        write(file, text, DEFAULT_APPEND);
    }

    /**
     * Writes the given {@code text} into {@code file} and moves the cursor down one
     * line.
     * <p>
     * If {@code true} is passed to append the file contents are preserved and they
     * are overwritten otherwise.
     * 
     * @param file   the file to write to
     * @param text   the text to write to the file
     * @param append {@code true} preserves the contents and {@code false}
     *               overwrites them
     * @throws IOException if the write operation to the file was unsuccessful
     */
    public static void writeln(File file, String text, boolean append) throws IOException {
        getWriter(file, append);
        fileWriter.println(text);
        fileWriter.close();
    }

    /**
     * Writes the given {@code text} into {@code file} and moves the cursor down one
     * line.
     * <p>
     * This overwrites any previous data in the file.
     * To write to the file without overwriting use
     * {@link #writeln(File, String, boolean)}.
     * 
     * @param file the file to write to
     * @param text the text to write to the file
     * @throws IOException if the write operation to the file was unsuccessful
     */
    public static void writeln(File file, String text) throws IOException {
        writeln(file, text, DEFAULT_APPEND);
    }

    /**
     * Deletes all the content in {@code file}.
     * 
     * @param file the file to truncate
     * @throws FileNotFoundException if the file isn't found
     */
    public static void truncate(File file) throws FileNotFoundException {
        new PrintWriter(file).close();
    }

    private static void getScanner(File f) throws FileNotFoundException {
        fileReader = new Scanner(f);
    }

    private static void getWriter(File f, boolean append) throws IOException {
        fileWriter = new PrintWriter(new FileWriter(f, append));
    }
}

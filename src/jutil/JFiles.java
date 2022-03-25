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
    private static Scanner     fileReader;
    private static PrintWriter fileWriter;

    private static final boolean DEFAULT_APPEND = false;

    private JFiles() {}

    public static int countWords(File f) throws FileNotFoundException {
        getScanner(f);

        int count = 0;
        while (fileReader.hasNext()) {
            fileReader.next();
            count++;
        }
        return count;
    }

    public static List<String> readLines(File f) throws FileNotFoundException {
        getScanner(f);

        List<String> tokens = new ArrayList<>();

        while (fileReader.hasNextLine()) {
            tokens.add(fileReader.nextLine());
        }
        return tokens;
    }

    public static List<String> readWords(File f) throws FileNotFoundException {
        getScanner(f);

        List<String> tokens = new ArrayList<>();

        while (fileReader.hasNext()) {
            tokens.add(fileReader.next());
        }
        return tokens;
    }

    public static String read(File f) throws FileNotFoundException {
        getScanner(f);

        StringBuilder sb = new StringBuilder();

        while (fileReader.hasNext()) {
            sb.append(fileReader.next());
            sb.append(" ");
        }
        return sb.toString();
    }

    public static void write(File f, String text, boolean append) throws IOException {
        getWriter(f, append);
        fileWriter.print(text);
        fileWriter.close();
    }

    public static void write(File f, String text) throws IOException {
        write(f, text, DEFAULT_APPEND);
    }
    
    public static void writeln(File f, String text, boolean append) throws IOException {
        getWriter(f, append);
        fileWriter.println(text);
        fileWriter.close();
    }

    public static void writeln(File f, String text) throws IOException {
        writeln(f, text, DEFAULT_APPEND);
    }

    public static void truncate(File f) throws FileNotFoundException {
        new PrintWriter(f).close();
    }

    private static void getScanner(File f) throws FileNotFoundException {
        fileReader = new Scanner(f);
    }

    private static void getWriter(File f, boolean append) throws IOException {
        fileWriter = new PrintWriter(new FileWriter(f, append));
    }
}

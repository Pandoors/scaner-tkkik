package pl.agh.edu.tk.html.generator;

import pl.agh.edu.tk.scanner.implementation.Scanner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HtmlFactory {

    /**
     * Generates HTML file with colored tokens
     */
    public static void generateHtml(String body) throws IOException {
        File f = new File("code.html");

        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write("<html><body><pre>");

        bw.write(body);
        bw.write("</pre></body></html>");
        bw.close();

    }

}

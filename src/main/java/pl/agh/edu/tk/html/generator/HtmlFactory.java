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
    public static void generateHtml(Scanner scanner) throws IOException {
        File f = new File("code.htm");

        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write("<html><body>");
//        for (int ii = 0; ii < 20; ii++) {
//            bw.write("<p style=\"color:red\">Blah blah..</p>");
//        }

        StringBuilder content = new StringBuilder();

        String code = scanner.getExpression();



        bw.write(content.toString());
        bw.write("</body></html>");
        bw.close();

    }

}

package pl.agh.edu.tk;

import pl.agh.edu.tk.html.generator.HtmlFactory;
import pl.agh.edu.tk.scanner.implementation.Scanner;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        //Scanning tokens
        Scanner scanner = new Scanner();
        scanner.Scan("122 +4-(-12/    5)*9 x = 5  *  (-99    +1)");
        System.out.println("\nPrinting list of all tokens:");
        for (String s : scanner.getTokenDescriptionsList()){
            System.out.println(s);
        }

        //generating HTML
        HtmlFactory.generateHtml(scanner);


    }
}

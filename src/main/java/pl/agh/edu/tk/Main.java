package pl.agh.edu.tk;

import pl.agh.edu.tk.html.generator.HtmlFactory;
import pl.agh.edu.tk.scanner.implementation.Scanner;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        //Scanning tokens
        Scanner scanner = new Scanner();
        scanner.Scan("122 +13-(-12-f01101100 + z -    5)*9  = #  (-99    +1) # 25*x ");
        System.out.println("\nPrinting list of all tokens:");
        for (String s : scanner.getTokenDescriptionsList()){
            System.out.println(s);
        }

        //generating HTML
        HtmlFactory.generateHtml(scanner.getHtmlBody());


    }
}

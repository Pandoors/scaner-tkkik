package pl.agh.edu.tk;

import pl.agh.edu.tk.scanner.implementation.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner();
        scanner.Scan("12+2    13-(-12/    5)*9  *   *(-99    +1)");
        System.out.println("\nPrinting list of all tokens:");
        for (String s : scanner.getTokenDescriptionsList()){
            System.out.println(s);
        }

    }
}

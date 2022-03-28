package pl.agh.edu.tk;

import pl.agh.edu.tk.scanner.implementation.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner();
        scanner.Scan("   12   *  ( 2343 + 56)( ");

        for (String s : scanner.getTokenList()){
            System.out.println(s);
        }

    }
}

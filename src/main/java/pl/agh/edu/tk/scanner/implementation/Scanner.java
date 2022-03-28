package pl.agh.edu.tk.scanner.implementation;

import lombok.NoArgsConstructor;
import pl.agh.edu.tk.scanner.MathSymbol;

import java.util.regex.Pattern;

@NoArgsConstructor
public class Scanner {

    /*
     *  0 - 48
     *  9 - 57
     * */
    public void Scan(String mathExpr) {
        // Usuwanie znakow bialych
        mathExpr = removeWhiteSpaces(mathExpr);
        System.out.println("after removed whitespaces: " + mathExpr);

        int openParenthesis = 0;
        int index = -1;
        for (char c : mathExpr.toCharArray()) {
            index++;
            if (!validateToken(c)) {
                System.out.println("Ten znak nie jest tokenem wyrazenia matematycznego");
                break;
            }

            if (c == MathSymbol.LEFT_PARENTHESIS) {
                openParenthesis++;
            } else if (c == MathSymbol.RIGHT_PARENTHESIS) {
                openParenthesis--;
            } else if (c == MathSymbol.DIVIDE) {

            } else if (c == MathSymbol.MINUS) {
                char before = mathExpr.charAt(index - 1);

                if (!MathSymbol.isNumber(before) && before != MathSymbol.RIGHT_PARENTHESIS) {
                    System.out.println(c + " w zlym miejscu: " + index);
                }
            } else if (c == MathSymbol.PLUS || c == MathSymbol.MULTIPLY) {
                if (index == 0) {
                    System.out.println(c + " nie moze znajdowac sie w indexie: " + index);
                    break;
                }
                char before = mathExpr.charAt(index - 1);

                if (!MathSymbol.isNumber(before) && before != MathSymbol.RIGHT_PARENTHESIS) {
                    System.out.println(c + " w zlym miejscu: " + index);
                }

            } else {



            }

            if (openParenthesis < 0) {
                System.out.println("Blad w nawiasowaniu w: index " + index);
            }
        }

    }

    //na samym koncu sparwdzic liczbe otwartych nawiasow
    private boolean validateToken(char token) {

        switch (token) {
            case MathSymbol.DIVIDE:
            case MathSymbol.MINUS:
            case MathSymbol.LEFT_PARENTHESIS:
            case MathSymbol.MULTIPLY:
            case MathSymbol.PLUS:
            case MathSymbol.RIGHT_PARENTHESIS:
                return true;
            default:
                return ((int) token >= 48 && (int) token <= 57);
        }
    }

    private String removeWhiteSpaces(String expression) {

        StringBuilder sb = new StringBuilder();
        for (char c : expression.toCharArray()) {
            if (c == ' ' || c == '\t' || c == '\r' || c == '\n') {
                continue;
            }
            sb.append(c);
        }

        return sb.toString();
    }

}

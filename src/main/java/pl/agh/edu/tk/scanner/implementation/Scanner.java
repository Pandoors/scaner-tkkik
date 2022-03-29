package pl.agh.edu.tk.scanner.implementation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.agh.edu.tk.scanner.MathSymbol;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Scanner {
    private List<String> tokenList;


    public void Scan(String mathExpr) {
        tokenList = new ArrayList<>();

        // Usuwanie znakow bialych
        mathExpr = removeWhiteSpaces(mathExpr);
        System.out.println("after removed whitespaces: " + mathExpr);

        int openParenthesis = 0;
        int index;
        for (index = 0; index < mathExpr.length(); index++) {
            char c = mathExpr.charAt(index);

            if (!validateToken(c)) {
                System.out.println("Ten znak nie jest tokenem wyrazenia matematycznego");
                break;
            }

            if (c == MathSymbol.LEFT_PARENTHESIS) {
                openParenthesis++;
                tokenList.add("LEFT_PARENTHESIS{ " + c + " }");

            } else if (c == MathSymbol.RIGHT_PARENTHESIS) {
                openParenthesis--;
                tokenList.add("RIGHT_PARENTHESIS{ " + c + " }");

            } else if (c == MathSymbol.MINUS) {
                if (index == mathExpr.length() - 1 || !(MathSymbol.isNumber(mathExpr.charAt(index + 1)) || mathExpr.charAt(index + 1) == '(')) {
                    System.out.println(c + " nie moze znajdowac sie w indexie: " + index);
                    break;
                }
                if (index == 0) {

                    StringBuilder sb = new StringBuilder();
                    sb.append(c);
                    int tempIndex;
                    for (tempIndex = index + 1; tempIndex < mathExpr.length(); tempIndex++) {
                        char subToken = mathExpr.charAt(tempIndex);
                        if (MathSymbol.isNumber(subToken)) {
                            sb.append(subToken);
                        } else {
                            break;
                        }
                    }
                    index = tempIndex - 1;
                    if (sb.toString().length() == 1) tokenList.add("MINUS{ " + sb.toString() + " }");
                    else tokenList.add("NEGATIVE_NUMBER{ " + sb.toString() + " }");

                } else {

                    char before = mathExpr.charAt(index - 1);

                    if (!MathSymbol.isNumber(before) && before != MathSymbol.RIGHT_PARENTHESIS && before != MathSymbol.LEFT_PARENTHESIS) {
                        System.out.println(c + " w zlym miejscu: " + index);
                        break;
                    }

                    if (before == MathSymbol.LEFT_PARENTHESIS) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(c);
                        int tempIndex;
                        for (tempIndex = index + 1; tempIndex < mathExpr.length(); tempIndex++) {
                            char subToken = mathExpr.charAt(tempIndex);
                            if (MathSymbol.isNumber(subToken)) {
                                sb.append(subToken);
                            } else {
                                break;
                            }
                        }
                        index = tempIndex - 1;
                        if (sb.toString().length() == 1) tokenList.add("MINUS{ " + sb.toString() + " }");
                        else tokenList.add("NEGATIVE_NUMBER{ " + sb.toString() + " }");
                    } else {
                        tokenList.add("MINUS{ " + c + " }");
                    }
                }

            } else if (c == MathSymbol.PLUS || c == MathSymbol.MULTIPLY || c == MathSymbol.DIVIDE) {
                if (index == 0 || index == mathExpr.length() - 1) {
                    System.out.println(c + " nie moze znajdowac sie w indexie: " + index);
                    break;
                }
                char before = mathExpr.charAt(index - 1);

                if (!MathSymbol.isNumber(before) && before != MathSymbol.RIGHT_PARENTHESIS) {
                    System.out.println(c + " w zlym miejscu: " + index);
                    break;
                }
                if (c == MathSymbol.PLUS) {
                    tokenList.add("PLUS{ " + c + " }");
                } else if (c == MathSymbol.MULTIPLY) {
                    tokenList.add("MULTIPLY{ " + c + " }");
                } else {
                    tokenList.add("DIVIDE{ " + c + " }");
                }

            } else {

                StringBuilder sb = new StringBuilder();
                int tempIndex;
                for (tempIndex = index; tempIndex < mathExpr.length(); tempIndex++) {
                    char subToken = mathExpr.charAt(tempIndex);
                    if (MathSymbol.isNumber(subToken)) {
                        sb.append(subToken);
                    } else {
                        break;
                    }
                }
                index = tempIndex - 1;
                tokenList.add("NUBMER{ " + sb.toString() + " }");

            }


        }
        if (openParenthesis != 0) {
            System.out.println("Blad w nawiasowaniu w: index " + index);

        }
    }

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

    public List<String> getTokenList() {
        return tokenList;
    }
}

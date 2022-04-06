package pl.agh.edu.tk.scanner.implementation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.agh.edu.tk.scanner.MathSymbol;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Scanner {

    public List<String> getTokenDescriptionsList() {
        return tokenDescriptionsList;
    }

    @Getter
    private List<String> tokenDescriptionsList;

    private List<String> tokenList;

    public void Scan(String mathExpr) {
        tokenDescriptionsList = new ArrayList<>();
        tokenList = new ArrayList<>();
        // Usuwanie znakow bialych
        System.out.println("after removed whitespaces: " + mathExpr);

        int openParenthesis = 0;
        int index;
        for (index = 0; index < mathExpr.length(); index++) {
            char c = mathExpr.charAt(index);

            if (c == ' ' || c == '\t' || c == '\r' || c == '\n') {
                continue;
            }

            if (!validateToken(c)) {
                System.out.println("Ten znak nie jest tokenem wyrazenia matematycznego");
                break;
            }

            if (c == MathSymbol.LEFT_PARENTHESIS) {
                openParenthesis++;
                tokenDescriptionsList.add("LEFT_PARENTHESIS{ " + c + " }");
                tokenList.add(String.valueOf(c));

            } else if (c == MathSymbol.RIGHT_PARENTHESIS) {
                if (getLastCharacter() == '-'){
                    System.out.println(c + " nie moze znajdowac sie w indexie: " + index);
                    break;
                }
                openParenthesis--;
                tokenDescriptionsList.add("RIGHT_PARENTHESIS{ " + c + " }");
                tokenList.add(String.valueOf(c));

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
                        if (subToken == ' ' || subToken == '\t' || subToken == '\r' || subToken == '\n') {
                            continue;
                        }
                        if (MathSymbol.isNumber(subToken)) {
                            sb.append(subToken);
                        } else {
                            break;
                        }
                    }
                    index = tempIndex - 1;
                    if (sb.toString().length() == 1){
                        tokenDescriptionsList.add("MINUS{ " + sb.toString() + " }");
                        tokenList.add(sb.toString());
                    }
                    else {
                        tokenDescriptionsList.add("NEGATIVE_NUMBER{ " + sb.toString() + " }");
                        tokenList.add(sb.toString());
                    }

                } else {

                    char before = getLastCharacter();

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
                            if (subToken == ' ' || subToken == '\t' || subToken == '\r' || subToken == '\n') {
                                continue;
                            }
                            if (MathSymbol.isNumber(subToken)) {
                                sb.append(subToken);
                            } else {
                                break;
                            }
                        }
                        index = tempIndex - 1;
                        if (sb.toString().length() == 1) tokenDescriptionsList.add("MINUS{ " + sb.toString() + " }");
                        else tokenDescriptionsList.add("NEGATIVE_NUMBER{ " + sb.toString() + " }");
                        tokenList.add(sb.toString());
                    } else {
                        tokenDescriptionsList.add("MINUS{ " + c + " }");
                        tokenList.add(String.valueOf(c));

                    }
                }

            } else if (c == MathSymbol.PLUS || c == MathSymbol.MULTIPLY || c == MathSymbol.DIVIDE) {
                if (getLastCharacter() == '-'){
                    System.out.println(c + " nie moze znajdowac sie w indexie: " + index);
                    break;
                }
                if (index == 0 || index == mathExpr.length() - 1) {
                    System.out.println(c + " nie moze znajdowac sie w indexie: " + index);
                    break;
                }
                char before = getLastCharacter();

                if (!MathSymbol.isNumber(before) && before != MathSymbol.RIGHT_PARENTHESIS) {
                    System.out.println(c + " w zlym miejscu: " + index);
                    break;
                }
                if (c == MathSymbol.PLUS) {
                    tokenDescriptionsList.add("PLUS{ " + c + " }");
                    tokenList.add(String.valueOf(c));
                } else if (c == MathSymbol.MULTIPLY) {
                    tokenDescriptionsList.add("MULTIPLY{ " + c + " }");
                    tokenList.add(String.valueOf(c));
                } else {
                    tokenDescriptionsList.add("DIVIDE{ " + c + " }");
                    tokenList.add(String.valueOf(c));
                }

            } else {

                StringBuilder sb = new StringBuilder();
                int tempIndex;
                for (tempIndex = index; tempIndex < mathExpr.length(); tempIndex++) {
                    char subToken = mathExpr.charAt(tempIndex);
                    if (subToken == ' ' || subToken == '\t' || subToken == '\r' || subToken == '\n') {
                        continue;
                    }
                    if (MathSymbol.isNumber(subToken)) {
                        sb.append(subToken);
                    } else {
                        break;
                    }
                }
                index = tempIndex - 1;
                tokenDescriptionsList.add("NUBMER{ " + sb.toString() + " }");
                tokenList.add(sb.toString());


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

    private char getLastCharacter(){
        if (tokenList.isEmpty()){
            return ' ';
        } else{
            String obj = tokenList.get(tokenList.size()-1);
            if (obj.length()==1){
                return obj.charAt(0);
            } else{
                return obj.charAt(obj.length()-1);
            }
        }
    }


}

package pl.agh.edu.tk.scanner;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MathSymbol {

    public static final char PLUS = '+';
    public static final char MINUS = '-';
    public static final char MULTIPLY = '*';
    public static final char DIVIDE = '/';
    public static final char LEFT_PARENTHESIS = '(';
    public static final char RIGHT_PARENTHESIS = ')';
    public static final char HASHTAG = '#';
    public static final char NUMBER = 'n';
    public static final char VARIABLE = 'v';
    public static final char EQUALS = '=';



    public static boolean isNumber(char c){
        return  ((int) c >= 48 && (int) c <= 57);
    }
    public static boolean isLetter(char c) {return (((int) c >=97 && (int) c <= 122) || ((int) c >= 65 && (int) c <=90));}

}

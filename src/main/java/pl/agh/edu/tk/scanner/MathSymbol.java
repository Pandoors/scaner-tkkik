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

    public static boolean isNumber(char c){
        return  ((int) c >= 48 && (int) c <= 57);
    }

}

package function.util;

import function_test.Function;

/**
 * @author shane on 8/21/15.
 */
public class Util {
    /**
     * Explicitly prevents instantiation and inheritance
     */
    public Util(){
        throw new AssertionError();
    }

    public static boolean isEmpty(Object object){
        return null == object;
    }

    public static boolean isNumeric(String str) {
        try {
            double num = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public static boolean isOperator(String token) {
        return (Function.ADDITION.equals(token) || Function.SUBTRACTION.equals(token) || Function.DIVISION.equals(token) || Function.MULTIPLICATION.equals(token));
    }
}

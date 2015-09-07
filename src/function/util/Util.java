package function.util;

/**
 * Utility helper class.
 *
 * @author  Shane Richards
 * @since   2015-21-8
 */
public class Util {

    /**
     * Explicitly prevents instantiation and inheritance
     */
    private Util(){
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
        return (Constants.ADDITION.equals(token) || Constants.SUBTRACTION.equals(token) || Constants.DIVISION.equals(token) || Constants.MULTIPLICATION.equals(token));
    }
}

package function.util;

/**
 *
 *
 * @author shane on 8/21/15.
 */
public final class FunctionParser {

    private FunctionParser() { throw new AssertionError(); }

    public static String[] tokenize(String equation){
        return equation.split("\\s+");
    }

    public static String insertSpaces(String equation){
        return equation.replaceAll(".(?=.)", "$0 ");
    }

    /**
     * Compress the outer most brackets
     *
     * @param expression a typical mathematical equation
     * @return          the same equation, with the inner brackets containing no spaces
     */
    public static String compressBrackets(String expression) {

        int openBracketIndex = expression.indexOf(Constants.BRACKET_OPEN);
        int closeBracketIndex = expression.indexOf(Constants.BRACKET_CLOSED);

        // no brackets found
        if (openBracketIndex == closeBracketIndex)
            return expression;

        // strips outermost brackets if the initial expression was enclosed by brackets
        if ((openBracketIndex == 0) && (closeBracketIndex == expression.length() - 1))
            compressBrackets(expression.substring(1, expression.length() - 2));

        if (openBracketIndex >= closeBracketIndex)
            throw new UnsupportedOperationException();

        return expression.substring(0, openBracketIndex)
                + expression.substring(openBracketIndex, closeBracketIndex + 1).replaceAll("\\s+", "")
                + compressBrackets(expression.substring(closeBracketIndex + 1, expression.length()));

    }
}

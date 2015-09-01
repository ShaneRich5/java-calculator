package function.util;

import function.util.Constants;

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

//    public String[] tokenize(){
//        // http://stackoverflow.com/questions/9856916/java-string-split-regex
//        String[] ops = equation.split("\\s*[a-zA-Z0-9]+\\s*");
//        String[] notops = equation.split("\\s*[^a-zA-Z0-9]+\\s*");
//        String[] res = new String[ops.length+notops.length-1];
//        for(int i=0; i<res.length; i++)
//            res[i] = i%2==0 ? notops[i/2] : ops[i/2+1];
//
//        return res;
//    }

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

package function;

/**
 *
 *
 * @author shane on 8/21/15.
 */
public final class FunctionParser {

    private FunctionParser() { throw new AssertionError(); }

    private String equation;

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
     * @param expression
     * @return
     */
    public static String compressOuterBrackets(String expression) {

        int openBracketIndex = expression.indexOf(Constants.BRACKET_OPEN);
        int closeBracketIndex = expression.lastIndexOf(Constants.BRACKET_CLOSED);

        // no brackets found
        if (openBracketIndex == closeBracketIndex)
            return expression;

        if (openBracketIndex >= closeBracketIndex)
            throw new UnsupportedOperationException();

        String beforeBracket = expression.substring(0, openBracketIndex);
        String afterBracket = expression.substring(closeBracketIndex + 1, expression.length() - 1);

        String bracketedString = expression
                .substring(openBracketIndex, closeBracketIndex + 1)
                .replaceAll("\\s+", "");

        return beforeBracket + bracketedString + afterBracket;
    }
}

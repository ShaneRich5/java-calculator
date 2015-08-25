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

    public String getEquation(){
        return equation;
    }

    public String[] splitMultiplication(){
        return equation.split(Constants.MULTIPLICATION);
    }

    public String[] splitAddition(){
        return equation.split(Constants.ADDITION);
    }

    public String[] splitSubtraction(){
        return equation.split(Constants.SUBTRACTION);
    }

    public String[] splitDivision(){
        return equation.split(Constants.DIVISION);
    }
}

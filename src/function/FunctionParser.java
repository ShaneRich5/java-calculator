package function;

/**
 * Created by shane on 8/21/15.
 */
public final class FunctionParser {

    private String equation;

    public FunctionParser(String equation){
        this.equation = equation;
    }

    public static FunctionParser newInstance(String equation){
        return new FunctionParser(equation);
    }

    public String[] tokenify(){
        // http://stackoverflow.com/questions/9856916/java-string-split-regex
        String[] ops = equation.split("\\s*[a-zA-Z0-9]+\\s*");
        String[] notops = equation.split("\\s*[^a-zA-Z0-9]+\\s*");
        String[] res = new String[ops.length+notops.length-1];
        for(int i=0; i<res.length; i++)
            res[i] = i%2==0 ? notops[i/2] : ops[i/2+1];

        return res;
    }

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

package function;

/**
 * Created by shane on 8/21/15.
 */
public class FunctionParser {
    public static final String ADDITION = "[+]";
    public static final String SUBTRACTION = "[-]";
    public static final String DIVISION = "[/]";
    public static final String MUTILIPLICATION = "[*]";

    private String equation;

    public FunctionParser(String equation){
        this.equation = equation;
    }

    public static FunctionParser newInstance(String equation){
        return new FunctionParser(equation);
    }

    public String getEquation(){
        return equation;
    }

    public String[] splitMultiplication(){
        return equation.split(MUTILIPLICATION);
    }

    public String[] splitAddition(){
        return equation.split(ADDITION);
    }

    public String[] splitSubtraction(){
        return equation.split(SUBTRACTION);
    }

    public String[] splitDivision(){
        return equation.split(DIVISION);
    }
}

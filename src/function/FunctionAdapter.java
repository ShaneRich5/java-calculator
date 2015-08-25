package function;

import function_test.Function;

import java.util.*;

/**
 * Created by shane on 8/21/15.
 */
public final class FunctionAdapter {
    private final List<String> expressions;

    public FunctionAdapter(List<String> expressions) {
        this.expressions = expressions;
    }

    public static FunctionAdapter newInstance(String[] expressions) {
        return new FunctionAdapter(new ArrayList<>(Arrays.asList(expressions)));
    }

    public List<String> getExpressions(){
        return expressions;
    }

    /**
     * Consider fly weight approach for constructing trees
     *
     * @return
     */
    public Tree buildTree() {
        // invalid list
        // should probably use a factory here
        if (expressions.size() <= 0)
            return NullTree.getInstance();

        return FunctionTree.newInstance(findOperands(expressions));
    }

    private Node findOperands(List<String> expressions){
//        System.out.println("Current expressions:" + expressions.size() + " " + expressions.toString());
//        System.out.println(" index: " + lastIndexOfOperator(expressions));

        Node node = null;

        // it is an operand
//        if (1 == expressions.size())
            // check if value is brackets (variable with no spaces)
//            return Node.newInstance(expressions.remove(0)); // pop it and return as new node


        // needs testing, not sure how correct this is
        if (1 == expressions.size())
            return expandBrackets(expressions.remove(0));

        // indexes of the various operations
        int index = lastIndexOfOperator(expressions);

        if (-1 != index){
            node = Node.newInstance(expressions.remove(index));
            node.setRight(findOperands(expressions.subList(index, expressions.size())));
            node.setLeft(findOperands(expressions.subList(0, index)));
        }

        return node;
    }

    /**
     * Bracketed functions should be added without spaces,
     * for example (a - b) * c should be (a-b) * c so the above
     * method prioritizes the bracketed part
     *
     * Two methods in the FunctionParser should be created to serve this purpose,
     * one to compress the brackets for the inputs and another to expand the brackets
     * and remove them from the equation.
     *
     * @param   expression
     */
    private Node expandBrackets(String expression){
        int indexOpen = expression.indexOf(Constants.BRACKET_OPEN);
        int indexClose = expression.lastIndexOf(Constants.BRACKET_CLOSED);

        // this is only true if they are -1
        // which means no brackets were present
        if (indexOpen == indexClose)
            return Node.newInstance(expression);

        // if the brackets do not match up
        if ((indexOpen > indexClose) || (indexOpen != 0 ) || (indexClose != expression.length() - 1))
            throw new UnsupportedOperationException();

        StringBuilder bracketedString = new StringBuilder(expression);

        bracketedString.deleteCharAt(0);
        bracketedString.deleteCharAt(bracketedString.length() - 1);

        String outerBracketsRemoved = bracketedString.toString();

        // check if contains inner brackets
        indexOpen = expression.indexOf(Constants.BRACKET_OPEN);
        indexClose = expression.lastIndexOf(Constants.BRACKET_CLOSED);

        if (indexOpen > indexClose)
            throw new UnsupportedOperationException();

        String temp = (indexOpen != indexClose) ? outerBracketsRemoved.substring(indexOpen, indexClose) : "";
        String beforeSplit = FunctionParser.insertSpaces(temp.substring(0, indexOpen - 1));
        String afterSplit = FunctionParser.insertSpaces(temp.substring(indexClose + 1, outerBracketsRemoved.length() - 1));

        return findOperands(Collections.singletonList(beforeSplit + temp + afterSplit));
    }

    private int lastIndexOfOperator(List<String> expressions) {
        int indexOfMinusSign = lastIndex(expressions, Function.SUBTRACTION);
        int indexOfAddSign = lastIndex(expressions, Function.ADDITION);
        int indexOfDivisionSign = lastIndex(expressions, Function.DIVISION);
        int indexOfMultiplicationSign = lastIndex(expressions, Function.MULTIPLICATION);

        if (indexOfMinusSign > -1)
            return indexOfMinusSign;
        if (indexOfAddSign > -1)
            return indexOfAddSign;
        if (indexOfDivisionSign > -1)
            return indexOfDivisionSign;
        if (indexOfMultiplicationSign > -1)
            return indexOfMultiplicationSign;

        return -1;
    }

    private int lastIndex(List<String> elements, String expression){
        for (int i = (elements.size() - 1); i >= 0; i--) {
            if (elements.get(i).equals(expression))
                return i;
        }
        return -1;
    }

}

package function;

import function_test.Function;

import java.util.*;

/**
 * Created by shane on 8/21/15.
 */
public final class FunctionAdapter {
    private final List<String> expressions;

    public FunctionAdapter(String[] expressions) {
        this.expressions = new ArrayList<>(Arrays.asList(expressions));
    }

    public List<String> getExpressions(){
        return expressions;
    }

    public Tree buildTree() {
        // invalid list
        if (expressions.size() <= 0)
            return NullTree.getInstance();

        return FunctionTree.newInstance(findOperands(expressions));
    }

    private Node findOperands(List<String> expressions){
//        System.out.println("Current expressions:" + expressions.size() + " " + expressions.toString());
//        System.out.println(" index: " + lastIndexOfOperator(expressions));

        Node node = null;

        // it is an operand
        if (1 == expressions.size()) {
            return Node.newInstance(expressions.remove(0)); // pop it and return as new node
        }

        // indexes of the various operations
        int index = lastIndexOfOperator(expressions);

        if (-1 != index){
            node = Node.newInstance(expressions.remove(index));
            node.setRight(findOperands(expressions.subList(index, expressions.size())));
            node.setLeft(findOperands(expressions.subList(0, index)));
        }

        return node;
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

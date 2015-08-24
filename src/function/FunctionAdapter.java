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
        System.out.print(expressions.toString());
        System.out.println(" index: " + lastIndexOfOperator(expressions));

        Node node = null;

        // it is an operand
        if (1 == expressions.size()) {
            return Node.newInstance(expressions.remove(0)); // pop it and return as new node
        }

        // indexes of the various operations
        int index = lastIndexOfOperator(expressions);

        if (-1 != index){
            node = Node.newInstance(expressions.remove(index));
            node.setLeft(findOperands(expressions.subList(0, index)));
            node.setRight(findOperands(expressions.subList(index, expressions.size())));
        }

        return node;
    }

    private int lastIndexOfOperator(List<String> expressions) {
        int indexOfMinusSign = expressions.lastIndexOf(Function.SUBTRACTION);
        int indexOfAddSign = expressions.lastIndexOf(Function.ADDITION);
        int indexOfDivisionSign = expressions.lastIndexOf(Function.DIVISION);
        int indexOfMultiplicationSign = expressions.indexOf(Function.MULTIPLICATION);

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

}

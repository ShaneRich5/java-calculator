package function;

import function_test.Function;
import util.Util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by shane on 8/21/15.
 */
public final class FunctionAdapter {
    private Tree tree;
    private final List<String> expressions;
    private String[][] holders;
    private Node root = null;

    public FunctionAdapter(String[] expressions) {
        this.expressions = Arrays.asList(expressions);
        tree = NullTree.getInstance(); // represents empty tree
    }

    public Tree buildTree() {
        if (!Util.isEmpty(tree)) // if the tree is not empty
            return tree;

        // invalid list
        if (expressions.size() <= 1)
            return NullTree.getInstance();

        return FunctionTree.newInstance(findOperands(expressions));
    }

    private Node findOperands(List<String> expressions){
        Node node = null;

        // it is an operand
        if (1 == expressions.size()) {
            return Node.newInstance(expressions.remove(0)); // pop it and return as new node
        }

        // indexes of the various operations
        int indexOfMinusSign = expressions.lastIndexOf(Function.SUBTRACTION);
        int indexOfAddSign = expressions.lastIndexOf(Function.ADDITION);
        int indexOfDivisionSign = expressions.lastIndexOf(Function.DIVISION);
        int indexOfMultiplicationSign = expressions.indexOf(Function.MULTIPLICATION);

        // check if minus sign is present
        if (-1 != indexOfMinusSign){

            node = Node.newInstance(Function.SUBTRACTION);
            expressions.remove(indexOfMinusSign);

            node.setLeft(findOperands(expressions.subList(0, indexOfMinusSign)));
            node.setRight(findOperands(expressions.subList((indexOfMinusSign + 1), expressions.size())));

        } else if (-1 != indexOfAddSign){

            node = Node.newInstance(Function.ADDITION);
            expressions.remove(indexOfAddSign);

            node.setLeft(findOperands(expressions.subList(0, indexOfAddSign)));
            node.setRight(findOperands(expressions.subList((indexOfAddSign + 1), expressions.size())));

        } else if (-1 != indexOfDivisionSign){

            node = Node.newInstance(Function.DIVISION);
            expressions.remove(indexOfDivisionSign);

            node.setLeft(findOperands(expressions.subList(0, indexOfDivisionSign)));
            node.setRight(findOperands(expressions.subList((indexOfDivisionSign + 1), expressions.size())));

        } else if (-1 != indexOfMultiplicationSign) {

            node = Node.newInstance(Function.MULTIPLICATION);
            expressions.remove(indexOfMultiplicationSign);

            node.setLeft(findOperands(expressions.subList(0, indexOfMultiplicationSign)));
            node.setRight(findOperands(expressions.subList((indexOfMultiplicationSign + 1), expressions.size())));

        }

        return node;
    }
}

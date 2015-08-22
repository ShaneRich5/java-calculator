package function;

import function_test.Function;
import util.Util;

import java.util.Collections;
import java.util.List;

/**
 * Created by shane on 8/21/15.
 */
public class FunctionAdapter {
    private FunctionTree tree;
    private String[] expressions;
    private String[][] holders;
    private Node root = null;

    public FunctionAdapter(String[] expressions) {
        this.expressions = expressions;
    }

    public List<String> toList() {
        // TODO
        return null;
    }

    public Tree buildTree() {
        if (!Util.isEmpty(tree))
            return tree;

        // invalid list
        if (expressions.length <= 1)
            return NullTree.getInstance();


    }

    private Node findSubtraction(Node node, String[] expressions) {
        if (expressions.length == 0)
            return null;

        expressions = reverseArray(expressions);


    }


    private String[] reverseArray(String[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            String temp = arr[i];
            arr[i] = arr[arr.length - i - 1]; // remain in bound
            arr[arr.length - i - 1] = temp;
        }

        return arr;
    }
}

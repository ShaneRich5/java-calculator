package function;

import util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.lang.System.out;

/**
 * Created by shane on 8/21/15.
 */
public final class FunctionTree extends Tree {
    List<String> validOperations = new ArrayList<>();

    private Node root;

    private FunctionTree(Node root) {
        validOperations.add(Constants.ADDITION);
        validOperations.add(Constants.SUBTRACTION);
        validOperations.add(Constants.MULTIPLICATION);
        validOperations.add(Constants.DIVISION);

        this.root = root;
    }

    //========================================================
    //              Factories
    //========================================================
    public static FunctionTree newInstance(Node root) {
        return new FunctionTree(root);
    }

    public Node getRoot() {
        return root;
    }

    //========================================================
    //              Traversal
    //========================================================
    public void inOrder(Node node){
        if (null != node) {
            postOrder(node.getLeft());
            out.println(node.getData());
            postOrder(node.getRight());
        }
    }

    public void postOrder(Node node){
        if (null != node) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            out.println(node.getData());
        }
    }

    public boolean isEmpty(){
        return null == root;
    }

    public void preOrder(Node node){
        if (null != node) {
            out.println(node.getData());
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    public void inOrder(){
        if (null != root)
            inOrder(root);
    }

    public void postOrder() {
        if (null != root)
            postOrder(root);
        System.out.println("Empty tree");
    }

    public void preOrder(){
        if (null != root)
            preOrder(root);
    }

    public String execute(){
        if (validOperations.contains("+"))
            return "Yes";



        FunctionTree tree = FunctionTree.newInstance(this.root); // use a new instance

        Node root = tree.getRoot();

        if (!root.isLeaf())
            root = calcNode(root.getLeft(), root.getRight(), root.getData());
//            root = Node.newInstance(
//                    String.valueOf(
//                            calculate(root.getLeft(), root.getRight(), root.getData())
//                    )
//            );

        return root.getData();
    }

    private Double calculate(Node left, Node right, String operation) {
        if (left.isLeaf() && right.isLeaf()){
            try {
                // if leaf
                return performOperation(
                        Double.parseDouble(left.getData()),
                        Double.parseDouble(right.getData()),
                        operation);
            } catch (UnsupportedOperationException e) {
                return Double.NaN;
            } catch (Exception e) {
                return Double.NaN;
            }
        } else {
            return Double.NaN;
        }
    }

    private Node calcNode(Node left, Node right, String operation){
        System.out.println(left.getData() + operation + right.getData());
        // if they aren't leaves
        // then make them leaves....
//        if (!left.isLeaf())
//            left = calcNode(left.getLeft(), left.getRight(), left.getData());
//        if (!right.isLeaf())
//            right = calcNode(right.getLeft(), right.getRight(), right.getData());
//
//        return Node.newInstance(
//                String.valueOf(
//                        performOperation(Double.parseDouble(left.getData()),
//                                Double.parseDouble(right.getData()),
//                                operation)));
        // if they are both leave, execut the function
        if (left.isLeaf() && right.isLeaf())
            return Node.newInstance(performOperation(Double.parseDouble(left.getData()), Double.parseDouble(right.getData()), operation).toString());
        // else, if the right is a leaf, clearly the left is not
//        else if (right.isLeaf())
//            return Node.newInstance(performOperation(Double.parseDouble(calcNode(left.getLeft(), left.getRight(), left.getData()).getData()), Double.parseDouble(right.getData()), operation).toString());
//        // and vice versa
//        else if (left.isLeaf())
//            return Node.newInstance(performOperation(Double.parseDouble(left.getData()), Double.parseDouble(calcNode(right.getLeft(), right.getRight(), right.getData()).getData()), operation).toString());
        // neither are leaves
        else
            return Node.newInstance(performOperation(
                    Double.parseDouble((left.isLeaf()) ? (calcNode(left.getLeft(), left.getRight(), left.getData()).getData()) : left.getData()),
                    Double.parseDouble((right.isLeaf()) ? (calcNode(right.getLeft(), right.getRight(), right.getData()).getData()) : right.getData()),
                    operation).toString());

    }

    private Double performOperation(double operandA, double operandB, String operation) {
        switch (operation){
            case "+":
                return operandA + operandB;
            case "-":
                return operandA - operandB;
            case "/":
                return operandA / operandB;
            case "*":
                return operandA * operandB;
            default:
                throw new UnsupportedOperationException();
        }
    }

    private boolean operatorIsValid(String operator){
        for (int i = 0; i < validOperations.size(); i++)
            if (validOperations.get(i).equals(operator))
                return true;
        return false;
    }
}

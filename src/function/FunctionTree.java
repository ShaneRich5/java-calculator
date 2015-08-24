package function;

import util.Util;

import java.util.function.Function;

import static java.lang.System.out;

/**
 * Created by shane on 8/21/15.
 */
public final class FunctionTree extends Tree {
    private Node root;

    private FunctionTree(Node root) {
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
        FunctionTree tree = FunctionTree.newInstance(this.root); // use a new instance

        Node root = tree.getRoot();

        if (root.hasChildren())
            root = Node.newInstance(
                    String.valueOf(
                            calculate(root.getLeft(), root.getRight(), root.getData())
                    )
            );

        return root.getData();
    }

    private Double calculate(Node left, Node right, String operation) {
        try {
            return performOperation(
                    Double.parseDouble(left.getData()),
                    Double.parseDouble(right.getData()),
                    operation);
        } catch (UnsupportedOperationException e) {
            return Double.NaN;
        } catch (Exception e) {
            return Double.NaN;
        }
    }

    private double performOperation(double operandA, double operandB, String operation) {
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
}

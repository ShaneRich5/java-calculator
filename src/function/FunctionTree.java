package function;

import function.adapters.FunctionAdapter;
import function.util.Constants;
import function.util.FunctionParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    /**
     * Use this factory
     *
     * @return
     */
    public static Tree buildTree(String equation) {
        return FunctionAdapter
                .newInstance(
                        FunctionParser.tokenize(
                                FunctionParser.compressBrackets(equation)
                        )
                )
                .buildTree();
    }

    public Node getRoot() {
        return root;
    }

    //========================================================
    //              Traversal
    //========================================================
    private List<String> inOrder(Node node, List<String> values){
        if (null != node) {
            inOrder(node.getLeft(), values);
            values.add( node.getData() );
            inOrder(node.getRight(), values);
        }
        return values;
    }

    private void postOrder(Node node){
        if (null != node) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            out.print(node.getData() + " ");
        }
    }

    private void preOrder(Node node){
        if (null != node) {
            out.print(node.getData() + " ");
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    public boolean isEmpty(){
        return null == root;
    }


    public List<String> inOrder(){
        List<String> nodeList = new ArrayList<>();
        if (null != root)
            return inOrder(root, nodeList);
        return Collections.emptyList();
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

    private void equationFormat(Node node) {
        if (null != node) {
            equationFormat(node.getLeft());
        }
    }

    public String execute(){

        FunctionTree tree = FunctionTree.newInstance(this.root); // use a new instance

        Node root = tree.getRoot();

        if (!root.isLeaf())
            root = calcNode(root.getLeft(), root.getRight(), root.getData());

        return root.getData();
    }

    private Node calcNode(Node leftNode, Node rightNode, String operation){
//        System.out.println(leftNode.getData() + operation + rightNode.getData());

        return Node.newInstance(String.valueOf(performOperation(
                Double.parseDouble(!(leftNode.isLeaf()) ? (calcNode(leftNode.getLeft(), leftNode.getRight(), leftNode.getData())).getData() : leftNode.getData()),
                Double.parseDouble(!(rightNode.isLeaf()) ? (calcNode(rightNode.getLeft(), rightNode.getRight(), rightNode.getData())).getData() : rightNode.getData()),
                operation
        )));
    }

    private double performOperation(double operandA, double operandB, String operation) {

        FunctionFactory factory = new FunctionFactory();

        Function function = Function.newInstance(operandA, operandB);

        function.setOperation(factory.getOperator(operation));

        return function.execute(operandA, operandB);
    }
}

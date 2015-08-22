package function;

import util.Util;

import static java.lang.System.out;

/**
 * Created by shane on 8/21/15.
 */
public class FunctionTree extends Tree {

    public static final String EQUALS = "==";
    public static final String GREATER = ">";
    public static final String LESSER = "<";
    public static final String GREATER_EQUAL =">=";
    public static final String LESSER_EQUAL ="<=";

    private Node root;

    public FunctionTree(Node root) {
        this.root = root;
    }

    private FunctionTree() {}

    //========================================================
    //              Factories
    //========================================================
    public static FunctionTree newInstance(Node root) {
        return new FunctionTree(root);
    }

    public static FunctionTree newInstance() {
        FunctionTree tree = new FunctionTree();
        tree.setRoot(Node.newIntance(""));
        return tree;
    }

    public Node getRoot() {
        return root;
    }

    public boolean setRoot(Node newRoot){
        if (!Util.isEmpty(root))
            return false;
        root = newRoot;
        return true;
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
    }

    public void preOrder(){
        if (null != root)
            preOrder(root);
    }

    /**
     * Creates an instance of the FunctionTree object by iterating
     * through an array several times, creating several
     * @param expressions
     * @return
     */
    public static Tree buildTree(String[] expressions) {
        if (1 <= expressions.length)
            return NullTree.getInstance();


        return new FunctionTree(new Node());
    }
}

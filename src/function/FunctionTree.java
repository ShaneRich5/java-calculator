package function;

import static java.lang.System.out;

/**
 * Created by shane on 8/21/15.
 */
public class FunctionTree {

    private Node root;

    public FunctionTree(Node root) {
        this.root = root;
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
}

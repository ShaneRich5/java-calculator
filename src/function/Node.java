package function;

/**
 * Created by shane on 8/21/15.
 *
 * If
 */
public class Node {
    public static final String OPERAND = "operand";
    public static final String OPERATOR = "operator";

    private String data;
    private String type;

    private Node left;
    private Node right;

    public Node(String data){
        this.data = data;
    }


    //========================================================
    //              Factories
    //========================================================
    public static Node newIntance(String data){
        return new Node(data);
    }

    public void setData(String newData) {
        data = newData;
    }

    public String getData() {
        return data;
    }

    /**
     * Sets a new node as the left child of the current node
     *
     * @param newLeft is the new not to add as the left child
     * @return false if branch is already occupied, otherwise,
     * if the node is added successfully, true is returned.
     */
    public boolean setLeft(Node newLeft) {
        if (null != left)
            return false;
        else
            left = newLeft;
        return true;
    }

    /**
     * Sets a new node as the left child of the current node
     *
     * @param newRight is the new not to add as the left child
     * @return false if branch is already occupied, otherwise,
     * if the node is added successfully, true is returned.
     */
    public boolean setRight(Node newRight) {
        if (null != right)
            return false;
        else
            right = newRight;
        return true;
    }


    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

}

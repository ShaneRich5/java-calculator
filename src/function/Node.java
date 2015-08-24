package function;

import util.Util;

/**
 * Created by shane on 8/21/15.
 *
 * If
 */
public class Node {
    private String data;

    private Node left;
    private Node right;

    public Node(String data){
        this.data = data;
    }

    //========================================================
    //              Factories
    //========================================================
    public static Node newInstance(String data){
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

    public boolean hasChild(){
        return !Util.isEmpty(left) || !Util.isEmpty(right);
    }

    public boolean hasChildren() {  return !Util.isEmpty(left) && !Util.isEmpty(right); }

    /**
     * Determins if the node is a grandparent
     *
     * @return bool
     */
    public boolean hasGrandchildren() {
        return left.hasChildren() && right.hasGrandchildren();
    }

    public boolean hasRight() {
        return !Util.isEmpty(getRight());
    }

    public boolean hasLeft() {
        return !Util.isEmpty(getLeft());
    }
}

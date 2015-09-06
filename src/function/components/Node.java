package function.components;

import function.util.Util;

/**
 * This class represents a <code>Node</code>
 *
 * @author shane
 */
public class Node {
    private String data;

    private Node left;
    private Node right;

    public Node(String data){
        this.data = data;
    }

    /**
     * Facotry for creating a new instance of <code>Node</code>
     *
     * @param data  payload of node
     * @return      new instance of a node
     */
    public static Node newInstance(String data){
        return new Node(data);
    }

    /**
     * Getter method for a node's payload
     *
     * @return      a string representing a node's data
     */
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

    public boolean isLeaf() {
        return Util.isEmpty(left) && Util.isEmpty(right);
    }
}

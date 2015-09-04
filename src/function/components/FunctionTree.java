package function.components;

import function.exceptions.MalformedNumberException;
import function.factories.OperatorFactory;
import function.util.Constants;
import function.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <h1>FunctionTree</h1>
 *
 * <p>
 *     A specialized binary tree with each node representing an equation.
 *     Each node represents either an operand or operator. The tree has no
 *     other constraints in its construction
 * </p>
 *
 * @author  Shane Richards
 * @version 1.0
 * @since   2015-21-8
 */
public final class FunctionTree extends Tree {

    /**
     * Maintains a pointer to the root tre node
     */
    private Node root;

    /**
     * Private constructor that is used internal within the class.
     * This grantees immutability and from external instantiation.
     *
     * @param root  the root node of the tree
     */
    private FunctionTree(Node root) {

        this.root = root;

    }

    /**
     * Factory method that return a new instance of a function tree.
     *
     * @param root  first node of the tree
     * @return      new instance of the FunctionTree
     */
    public static FunctionTree newInstance(Node root) {
        return new FunctionTree(root);
    }

    /**
     * Factory method that creates an instance of a Tree based on the
     * equation provided. If the equation is malformed or contains any
     * errors, a NullTree is returned. Otherwise, a FunctionTree is
     * created using the FunctionAdapter helper class.
     *
     * @param equation  a equation in the form of a string
     * @return          a tree
     */
    public static Tree buildTree(String equation) {
        return FunctionAdapter
                .newInstance(FunctionParser.tokenize(FunctionParser.compressBrackets(equation)))
                .buildTree();
    }

    /**
     * Accessor method of the root state.
     *
     * @return      the node at the root of the tree
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Used internally to walk the tree recursively using an in-order traversal technique.
     *
     * @param node      node presently being accessed
     * @param values    current list of values appended from prior nodes
     * @return          list of node values collected from previously traversed
     *                  nodes
     */
    private List<String> inOrder(Node node, List<String> values){
        if (null != node) {
            inOrder(node.getLeft(), values);
            values.add(node.getData());
            inOrder(node.getRight(), values);
        }
        return values;
    }

    /**
     * Used internally to walk the tree recursively using an post-order traversal technique.
     *
     * @param node      node presently being accessed
     * @param values    current list of values appended from prior nodes
     * @return          list of node values collected from previously traversed
     *                  nodes
     */
    private List<String> postOrder(Node node, List<String> values){
        if (null != node) {
            postOrder(node.getLeft(), values);
            postOrder(node.getRight(), values);
            values.add(node.getData());
        }
        return values;
    }

    /**
     * Used internally to walk the tree recursively using an pre-order traversal technique.
     *
     * @param node      node presently being accessed
     * @param values    current list of values appended from prior nodes
     * @return          list of node values collected from previously traversed
     *                  nodes
     */
    private List<String> preOrder(Node node, List<String> values){
        if (null != node) {
            values.add(node.getData());
            preOrder(node.getLeft(), values);
            preOrder(node.getRight(), values);
        }
        return values;
    }

    /**
     * List the element node of the Tree using an in-order traversal
     *
     * @return      a list of strings representing the node in the tree if the root is not null,
     *              otherwise, an empty list is given
     */
    public List<String> inOrder(){
        List<String> nodeList = new ArrayList<>();
        if (null != root)
            return inOrder(root, nodeList);
        return Collections.emptyList();
    }

    /**
     * List the element node of the Tree using an post-order traversal
     *
     * @return      a list of strings representing the node in the tree if the root is not null,
     *              otherwise, an empty list is given
     */
    public List<String> postOrder() {
        List<String> values = new ArrayList<>();
        if (null != root)
            return postOrder(root, values);
        return Collections.emptyList();
    }

    /**
     * List the element node of the Tree using an pre-order traversal
     *
     * @return      a list of strings representing the node in the tree if the root is not null,
     *              otherwise, an empty list is given
     */
    public List<String> preOrder(){
        List<String> values = new ArrayList<>();
        if (null != root)
            return preOrder(root, values);
        return Collections.emptyList();
    }

    /**
     * Executes the equations based on the operands and operators stored in the nodes of the tree.
     * If any error occurs, error is displayed
     *
     * @return      the result of the equation
     */
    public String execute(){

        // a new instance of the tree is used, so that the state of the original tree is unchanged
        FunctionTree tree = FunctionTree.newInstance(this.root);

        Node root = tree.getRoot();

        if (!root.isLeaf())
            root = calculateTree(root.getLeft(), root.getRight(), root.getData());

        return root.getData();
    }

    /**
     * Calculates the current equation by using three nodes. The non-leaf parent node is used as
     * the operator and the child nodes are treated as the operands. If the child nodes are not leaves,
     * then sub operations are executed using that node and it subsequent children recursively, storing
     * the result in the parent node.
     *
     * @param leftNode  left child current node
     * @param rightNode right child of current node
     * @param operation string operator stored in the current node
     * @return          new node that replaces the parent node with the resulting data
     */
    private Node calculateTree(Node leftNode, Node rightNode, String operation){

        return Node.newInstance(String.valueOf(performOperation(
                Double.parseDouble(!(leftNode.isLeaf()) ? (calculateTree(leftNode.getLeft(), leftNode.getRight(), leftNode.getData())).getData() : leftNode.getData()),
                Double.parseDouble(!(rightNode.isLeaf()) ? (calculateTree(rightNode.getLeft(), rightNode.getRight(), rightNode.getData())).getData() : rightNode.getData()),
                operation
        )));
    }

    /**
     * Executes operations by creating a function with the correct behaviour by using a factory.
     *
     * @param operandA  first operand
     * @param operandB  second operand
     * @param operation operator to execute
     * @return          resulting value of equation
     */
    private double performOperation(double operandA, double operandB, String operation) {

        OperatorFactory factory = new OperatorFactory();

        Function function = Function.newInstance(operandA, operandB);

        function.setOperation(factory.getOperator(operation));

        return function.execute();
    }

    /**
     * <h1>FunctionParser</h1>
     *
     * A static helper class that manipulates the state of the equation
     * string provided while the FunctionTree is being created.
     *
     * @author Shane Richards
     * @version 1.0
     * @since 2015-08-21
     */
    public static final class FunctionParser {

        /**
         * This constructor exists to prevent the creation of a default.
         * Instantiation is barred externally by being made private and
         * internally by throwing an AssertionError exception
         */
        private FunctionParser() { throw new AssertionError(); }

        /**
         * This method delimits strings by matching the regex for spaces,
         * which is '\\s+'. These tokens are added to an array of strings
         *
         * @param equation  a string representing a complex mathematically
         *                  equations
         * @return          an array of strings representing the delimited
         *                  equation
         */
        public static String[] tokenize(String equation){
            return equation.split("\\s+");
        }

        /**
         * This method is inserts spaces into a string equation
         *
         * @param equation  an equation in the form of a string,
         *                  containing no spaces
         * @return          a string, in which, each operand and
         *                  operator is separated by a space
         */
        public static String insertSpaces(String equation){
            return equation.replaceAll(".(?=.)", "$0 ");
        }

        /**
         * Compress the outer most brackets
         *
         * @param expression    a typical mathematical equation
         * @return              the same equation, with the inner brackets
         *                      containing no spaces
         */
        public static String compressBrackets(String expression) {

            int openBracketIndex = expression.indexOf(Constants.BRACKET_OPEN);
            int closeBracketIndex = expression.indexOf(Constants.BRACKET_CLOSED);

            // no brackets found
            if (openBracketIndex == closeBracketIndex)
                return expression;

            // strips outermost brackets if the initial expression was enclosed by brackets
            if ((openBracketIndex == 0) && (closeBracketIndex == expression.length() - 1))
                compressBrackets(expression.substring(1, expression.length() - 2));

            if (openBracketIndex >= closeBracketIndex)
                throw new UnsupportedOperationException();

            return expression.substring(0, openBracketIndex)
                    + expression.substring(openBracketIndex, closeBracketIndex + 1).replaceAll("\\s+", "")
                    + compressBrackets(expression.substring(closeBracketIndex + 1, expression.length()));
        }
    }

    /**
     * <h1>FunctionAdapter</h1>
     *
     * <p>Converts strings of equations into binary
     * tress and vice versa.</p>
     *
     * @author  Shane Richards
     * @version 1.0
     * @since   2015-08-21
     */
    public static final class FunctionAdapter {

        /**
         * Maintains the state of the tokens
         */
        private final List<String> expressions;

        /**
         * Private constructor used to prevent external instantiation.
         *
         * @param expressions list of string tokens
         */
        private FunctionAdapter(List<String> expressions) {

            this.expressions = expressions;

        }

        /**
         * Factory method for creating a new instance of the FunctionAdapter
         *
         * @param expressions   array of tokenized string
         * @return              instance of the FunctionAdapter
         */
        public static FunctionAdapter newInstance(String[] expressions) {

            return new FunctionAdapter(new ArrayList<>(Arrays.asList(expressions)));

        }

        /**
         * Consider fly weight approach for constructing trees
         *
         * @return      instance of the subclass of Tree being a FunctionTree if the equation contains
         *              no errors
         */
        public Tree buildTree() {
            if (expressions.size() <= 0)
                return NullTree.getInstance();
            try {
                return FunctionTree.newInstance(findOperands(expressions));
            } catch(MalformedNumberException e) {
                return NullTree.getInstance();
            }

        }

        /**
         *
         * @param expressions   list of string tokens representing the equation
         * @return              node
         */
        private Node findOperands(List<String> expressions){

            Node node = null;

            // needs testing, not sure how correct this is
            if (1 == expressions.size())
                return expandBrackets(expressions.remove(0));

            // indexes of the various operations
            int index = lastIndexOfOperator(expressions);

            if (-1 != index){
                node = Node.newInstance(expressions.remove(index));
                node.setRight(findOperands(expressions.subList(index, expressions.size())));
                node.setLeft(findOperands(expressions.subList(0, index)));
            }
            return node;
        }

        /**
         * Bracketed functions should be added without spaces,
         * for example (a - b) * c should be (a-b) * c so the above
         * method prioritizes the bracketed part
         *
         * Two methods in the FunctionParser should be created to serve this purpose,
         * one to compress the brackets for the inputs and another to expand the brackets
         * and remove them from the equation.
         *
         * @param   expression
         */
        private Node expandBrackets(String expression){

            int indexOpen = expression.indexOf(Constants.BRACKET_OPEN);
            int indexClose = expression.lastIndexOf(Constants.BRACKET_CLOSED);

            // this is only true if they are -1
            // which means no brackets were present
            if (indexOpen == indexClose){
                // too many decimal points in number
                if (Util.isNumeric(expression) && expression.length() - expression.replace(".", "").length() > 1)
                    throw new MalformedNumberException();
                else
                    return Node.newInstance(expression);
            }

            // if the brackets do not match up
            if ((indexOpen > indexClose) || (indexOpen != 0 ) || (indexClose != expression.length() - 1))
                throw new UnsupportedOperationException();

            expression = expression.substring(1, expression.length() - 1);

            // check if contains inner brackets
            indexOpen = expression.indexOf(Constants.BRACKET_OPEN);
            indexClose = expression.lastIndexOf(Constants.BRACKET_CLOSED);

            System.out.println("In adapter: " + FunctionParser.insertSpaces(expression));

            if (indexOpen > indexClose)
                throw new UnsupportedOperationException();

            if (indexOpen != indexClose) {
                String temp = expression.substring(indexOpen, indexClose + 1);
                String beforeSplit = FunctionParser.insertSpaces(expression.substring(0, indexOpen));
                String afterSplit = FunctionParser.insertSpaces(expression.substring(indexClose + 1, expression.length()));

                expression = beforeSplit + " " + temp + " " + afterSplit;
            } else {
                expression = FunctionParser.insertSpaces(expression);
            }
            return findOperands(new ArrayList<>(Arrays.asList(FunctionParser.tokenize(expression))));
        }

        /**
         * Finds the right most operator in the order of precedence
         *
         * @param expressions   list of tokens
         * @return              index of desired operator
         */
        private int lastIndexOfOperator(List<String> expressions) {
            int values[] = {
                    lastIndex(expressions, Constants.SUBTRACTION),
                    lastIndex(expressions, Constants.ADDITION),
                    lastIndex(expressions, Constants.DIVISION),
                    lastIndex(expressions, Constants.MULTIPLICATION)
            };

            int max = -1;

            for (int value : values) max = (max < value) ? value : max;

            return max;
        }

        /**
         * Scanning from right to left, the index of the operator the
         * least precedence is determined
         *
         * @param elements      list of strings
         * @param expression    expression to match
         * @return
         */
        private int lastIndex(List<String> elements, String expression){
            for (int i = (elements.size() - 1); i >= 0; i--) {
                if (elements.get(i).equals(expression))
                    return i;
            }
            return -1;
        }
    }
}

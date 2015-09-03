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

    /**
     * Use this factory
     *
     * @return
     */
    public static Tree buildTree(String equation) {
        return FunctionAdapter
                .newInstance(FunctionParser.tokenize(FunctionParser.compressBrackets(equation)))
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
            values.add(node.getData());
            inOrder(node.getRight(), values);
        }
        return values;
    }

    private List<String> postOrder(Node node, List<String> values){
        if (null != node) {
            postOrder(node.getLeft(), values);
            postOrder(node.getRight(), values);
            values.add(node.getData());
        }
        return values;
    }

    private List<String> preOrder(Node node, List<String> values){
        if (null != node) {
            values.add(node.getData());
            preOrder(node.getLeft(), values);
            preOrder(node.getRight(), values);
        }
        return values;
    }

    public List<String> inOrder(){
        List<String> nodeList = new ArrayList<>();
        if (null != root)
            return inOrder(root, nodeList);
        return Collections.emptyList();
    }

    public List<String> postOrder() {
        List<String> values = new ArrayList<>();
        if (null != root)
            return postOrder(root, values);
        return Collections.emptyList();
    }

    public List<String> preOrder(){
        List<String> values = new ArrayList<>();
        if (null != root)
            return preOrder(root, values);
        return Collections.emptyList();
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

        private final List<String> expressions;

        private FunctionAdapter(List<String> expressions) {

            this.expressions = expressions;

        }

        public static FunctionAdapter newInstance(String[] expressions) {

            return new FunctionAdapter(new ArrayList<>(Arrays.asList(expressions)));

        }

        /**
         * Consider fly weight approach for constructing trees
         *
         * @return
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
         * @return
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
         * @param elements
         * @param expression
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

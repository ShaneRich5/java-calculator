import function.adapters.FunctionAdapter;
import function.components.FunctionTree;
import function.components.Node;
import function.components.NullTree;
import function.components.Tree;
import function.util.FunctionParser;

import java.util.*;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {
//        testInsertSpaces();
//        testTree3();

        String expression = "5 + 2 - 3 / 4";

        Tree tree = FunctionTree.buildTree(expression);

        if (tree instanceof NullTree)
            out.println("Unable to create tree");
        else {
            System.out.print("\nIn-order: ");
            System.out.println(((FunctionTree) tree).inOrder());
            System.out.print(((FunctionTree) tree).execute());
        }
    }

    public static void testInsertSpaces() {
        String expression = "(5+3)";

        out.println("Expression :" + expression);

        out.println("With spaces :" + FunctionParser.insertSpaces(expression));
    }

    public static void testTree3(){
        String expression = "5 + 4 / ( 1 + 1 )";

        out.println("Expression: " + expression);

        expression = FunctionParser.compressBrackets(expression);

        String[] results = expression.split("\\s+");

        out.println("Arrayee: " + Arrays.toString(results));

        Tree tree = FunctionAdapter.newInstance(results).buildTree();

        if (tree instanceof NullTree)
            out.println("Unable to create tree");
        else {
            ((FunctionTree) tree).postOrder();
            out.print("Results: " + ((FunctionTree) tree).execute());
        }
    }

    public static void testTree2(){

        String expression = null;

        expression = "5 + (4+4) ";
//        expression  = "a * b * c";
//        expression = "a + b - c * d / e < f > g >= h <= i == j";

        String[] tokens = FunctionParser.tokenize(expression);

        out.println(Arrays.toString(tokens));

        Tree tree = FunctionAdapter.newInstance(tokens).buildTree();

        if (tree instanceof NullTree) {
            out.print("Nothing entered");
            return;
        }

        ((FunctionTree) tree).postOrder();
    }

    public static void testTree(){
        String expression = null;

        out.println("Enter an expression: ");
//        Scanner scan = new Scanner(System.in);
//        expression = scan.nextLine().trim();

//        String[] t = "2-2+-2".split("(?=[-+*/()])|(?<=[^-+*/][-+*/])|(?<=[()])");

//        out.print(Arrays.toString(t));

        expression = "5 + 4 +  -2";
//        expression  = "a * b * c";
//        expression = "a + b - c * d / e < f > g >= h <= i == j";

        String[] tokens = FunctionParser.tokenize(expression);

        out.println(Arrays.toString(tokens));

        Tree tree = FunctionAdapter.newInstance(tokens).buildTree();

        if (tree instanceof NullTree) {
            out.print("Nothing entered");
            return;
        }

        ((FunctionTree) tree).postOrder();

        String result = ((FunctionTree) tree).execute();

        out.print("Result is " + result);

    }

    public static void testFunctionTree() {
        String equation = "6*9*5";

        List<String> subEquations = Arrays.asList(equation.split("\\*"));

        Node node = Node.newInstance("*");

        node.setLeft(Node.newInstance(subEquations.get(0)));
        node.setRight(Node.newInstance(subEquations.get(1)));

        Node root = Node.newInstance("*");

        root.setLeft(node);
        root.setRight(Node.newInstance(subEquations.get(2)));

        FunctionTree tree = FunctionTree.newInstance(root);

        out.println("PostOrder");
        tree.postOrder();
    }
}

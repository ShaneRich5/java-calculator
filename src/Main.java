import function.components.FunctionTree;
import function.components.Tree;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {

        String expression = "5 + 2 - 3 / 4";

        Tree tree = FunctionTree.buildTree(expression);

        if (tree instanceof Tree.NullTree)
            out.println("Unable to create tree");
        else {
            System.out.print("\nIn-order: ");
            System.out.println(((FunctionTree) tree).inOrder());
            System.out.print(((FunctionTree) tree).execute());
        }
    }
}

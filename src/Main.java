import component_pattern_test.DiscJockey;
import component_pattern_test.Song;
import component_pattern_test.SongComponent;
import component_pattern_test.SongGroup;
import function.*;
import function_test.Function;
import util.Util;

import java.util.*;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {
        String expression = null;

        out.println("Enter an expression: ");
//        Scanner scan = new Scanner(System.in);
//        expression = scan.nextLine().trim();

        expression = "1 / 2 - 1";
//        expression  = "a * b * c";
//        expression = "a + b - c * d / e < f > g >= h <= i == j";

        String[] tokens = FunctionParser.newInstance(expression).tokenify();

        FunctionAdapter adapter = new FunctionAdapter(tokens);

        Tree tree = adapter.buildTree();

        if (tree instanceof NullTree) {
            out.print("Nothing entered");
            return;
        }

        String result = ((FunctionTree) tree).execute();

        out.print("Result is " + result);
//        List<String> testList = new ArrayList<>(Arrays.asList(tokens));

//        out.println("Test list: " + testList.toString());
//        out.println("Index of last * " + testList.lastIndexOf("*"));

//        out.print(Arrays.toString(res));

//        ((FunctionTree) tree).postOrder();

//        out.print("Result = " + ((FunctionTree) tree).execute());
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

    public static void songTest() {
        SongComponent industrialMusic =
                new SongGroup("Industrial",
                        "is a style of experimental music that draws on transgressive and provocative themes");

        SongComponent heavyMetalMusic =
                new SongGroup("\nHeavy Metal",
                        "is a genre of rock that developed in the late 1960s, largely in the UK and in the US");

        SongComponent dubstepMusic =
                new SongGroup("\nDubstep",
                        "is a genre of electronic dance music that originated in South London, England");

        // Top level component that holds everything

        SongComponent everySong = new SongGroup("component_pattern_test.Song List", "Every component_pattern_test.Song Available");

        // Composite that holds individual groups of songs
        // This holds Songs plus a component_pattern_test.SongGroup with Songs

        everySong.add(industrialMusic);

        industrialMusic.add(new Song("Head Like a Hole", "NIN", 1990));
        industrialMusic.add(new Song("Headhunter", "Front 242", 1988));

        industrialMusic.add(dubstepMusic);

        dubstepMusic.add(new Song("Centipede", "Knife Party", 2012));
        dubstepMusic.add(new Song("Tetris", "Doctor P", 2011));

        // This is a component_pattern_test.SongGroup that just holds Songs

        everySong.add(heavyMetalMusic);

        heavyMetalMusic.add(new Song("War Pigs", "Black Sabath", 1970));
        heavyMetalMusic.add(new Song("Ace of Spades", "Motorhead", 1980));

        DiscJockey crazyLarry = new DiscJockey(everySong);

        crazyLarry.getSongList();

    }
}

import component_pattern_test.DiscJockey;
import component_pattern_test.Song;
import component_pattern_test.SongComponent;
import component_pattern_test.SongGroup;
import function.FunctionParser;
import function.FunctionTree;
import function.Node;
import function_test.Function;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.lang.System.out;

public class Main {



    public static void main(String[] args) {
        String equation = "6*9*5";

//        List<String> subEquations = Arrays.asList(equation.split("[*]", 2));
//
//        for (String str: subEquations)
//            out.println("[" + str + "]");

        String[] expressions = FunctionParser.newInstance(equation).splitMultiplication();

//        for (String str: expressions)
//            out.println("[" + str + "]");

        String str = "a + b - c * d / e < f > g >= h <= i == j";

        String[] ops = str.split("\\s*[a-zA-Z]+\\s*");
        String[] notops = str.split("\\s*[^a-zA-Z]+\\s*");
        String[] res = new String[ops.length+notops.length-1];
        for(int i=0; i<res.length; i++) res[i] = i%2==0 ? notops[i/2] : ops[i/2+1];

        for(int i = 0; i < res.length; i++)
            out.println(res[i]);
    }

    private static void buildTree(String[] expressions) {

    }

    public static void testFunctionTree() {
        String equation = "6*9*5";

        List<String> subEquations = Arrays.asList(equation.split("\\*"));

        Node node = Node.newIntance("*");

        node.setLeft(Node.newIntance(subEquations.get(0)));
        node.setRight(Node.newIntance(subEquations.get(1)));

        Node root = Node.newIntance("*");

        root.setLeft(node);
        root.setRight(Node.newIntance(subEquations.get(2)));

        FunctionTree tree = FunctionTree.newInstance(root);

//        out.println("InOrder");
//        tree.inOrder();
//
//        out.println("PreOrder");
//        tree.preOrder();

        out.println("PostOrder");
        tree.postOrder();
    }

    public static Integer[] naturalNumbers() {
        return new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    }

    public static int randInt(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
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

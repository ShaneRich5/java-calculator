import component_pattern_test.DiscJockey;
import component_pattern_test.Song;
import component_pattern_test.SongComponent;
import component_pattern_test.SongGroup;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {
        // generate data to test
//        Integer data[] = naturalNumbers();

        // apply function to it
//        for (int x : data) System.out.print(x + " ");

        // new line
//        System.out.println();

//        function_test.Function function = new function_test.Function(data);

        //
//        System.out.print(function.transform("*", 6).transform("*", 9));

        String equation = "3+5";

        List<String> subequations = Arrays.asList(equation.split("\\+"));

        out.print("Equation: ");

        int result = 0;

        for (int i=0; i<subequations.size(); i++){
            out.print(subequations.get(i));

            out.print(i == subequations.size() - 1 ? "=" : "+");

            result += Integer.parseInt(subequations.get(i));
        }

        out.print(result);
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

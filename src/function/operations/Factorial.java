package function.operations;

import function.interfaces.Operable;

/**
 * Created by shane on 9/2/15.
 */
public class Factorial implements Operable {
    @Override
    public double equate(double num, double result) {
        if (0 == num)
            return 1;
        if (1 == num)
            return result;
        return equate(num - 1, result * num);
    }
}

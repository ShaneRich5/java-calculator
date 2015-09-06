package function.operations;

import function.interfaces.Operable;

/**
 * Implements facotrial functionality
 * through the strategy interface.
 *
 * @author Shane Richards
 * @since 2015-2-9
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

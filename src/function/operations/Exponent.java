package function.operations;

import function.interfaces.Operable;

/**
 * Implements exponent functionality
 * through the strategy interface.
 *
 * @author Shane Richrds
 * @since 2015-2-9
 */
public class Exponent implements Operable {
    @Override
    public double equate(double num, double exponent) {

        return exponent(num, exponent, num);

    }

    private double exponent(double num, double exponent, double result) {

        if (exponent == 0)
            return 1;
        else if (exponent == 1)
            return result;
        else
            return exponent(num, exponent - 1, num * result);
    }
}

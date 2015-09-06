package function.operations;

import function.interfaces.Operable;

/**
 * Implements modulus functionality
 * through the strategy interface.
 *
 * @author Shane Richards
 * @since 2015-1-9
 */
public class Modulus implements Operable {
    @Override
    public double equate(double num1, double num2) {
        return num1 % num2;
    }
}

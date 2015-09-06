package function.operations;

import function.interfaces.Operable;

/**
 * Implements multiplication functionality
 * through the strategy interface.
 *
 * @author Shane Richards
 * @since 2015-1-9
 */
public class Multiply implements Operable {
    @Override
    public double equate(double num1, double num2) {
        return num1 * num2;
    }
}

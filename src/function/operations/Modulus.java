package function.operations;

import function.interfaces.Operable;

/**
 * Created by shane on 9/2/15.
 */
public class Modulus implements Operable {
    @Override
    public double equate(double num1, double num2) {
        return num1 % num2;
    }
}

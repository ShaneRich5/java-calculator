package function.operations;

import function.interfaces.Operable;

/**
 * Created by shane on 9/1/15.
 */
public class Multiply implements Operable {
    @Override
    public double equate(double num1, double num2) {
        return num1 * num2;
    }
}

package function.operations;

import function.interfaces.Operable;

/**
 * Created by shane on 9/2/15.
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

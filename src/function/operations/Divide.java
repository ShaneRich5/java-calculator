package function.operations;

import function.exceptions.ZeroDivisionException;
import function.interfaces.Operable;

/**
 * Created by shane on 9/1/15.
 */
public class Divide implements Operable {

    @Override
    public double equate(double num1, double num2) {

        if (0 == num2)
            throw new ZeroDivisionException();
        return num1 / num2;

    }
}

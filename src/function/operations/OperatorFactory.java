package function.operations;

import function.interfaces.Operable;
import function.util.Constants;

/**
 * Created by shane on 9/1/15.
 */
public class OperatorFactory {

    public Operable getOperator(String newOperator) {

        switch (newOperator) {
            case Constants.ADDITION:

                return new Add();

            case Constants.SUBTRACTION:

                return new Subtract();

            case Constants.MULTIPLICATION:

                return  new Multiply();

            case Constants.DIVISION:

                return new Divide();

            default:

                throw new UnsupportedOperationException();
        }

    }
}

package function;

import function.interfaces.Operable;
import function.operations.Add;
import function.operations.Divide;
import function.operations.Multiply;
import function.operations.Subtract;
import function.util.Constants;

/**
 * Created by shane on 9/1/15.
 */
public class FunctionFactory {

    public Operable getOperator(String newOperator) {

        Operable opertor = null;

        switch (newOperator) {
            case Constants.ADDITION:
                opertor = new Add();
                break;
            case Constants.SUBTRACTION:
                opertor = new Subtract();
                break;
            case Constants.MULTIPLICATION:
                opertor = new Multiply();
                break;
            case Constants.DIVISION:
                opertor = new Divide();
                break;
            default:
                throw new UnsupportedOperationException();
        }

        return opertor;
    }
}

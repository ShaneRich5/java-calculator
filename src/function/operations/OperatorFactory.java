package function.operations;

import function.interfaces.Operable;
import function.operations.Add;
import function.operations.Divide;
import function.operations.Multiply;
import function.operations.Subtract;
import function.util.Constants;

/**
 * Created by shane on 9/1/15.
 */
public class OperatorFactory {

    public Operable getOperator(String newOperator) {

        Operable operator;

        switch (newOperator) {
            case Constants.ADDITION:
                operator = new Add();
                break;
            case Constants.SUBTRACTION:
                operator = new Subtract();
                break;
            case Constants.MULTIPLICATION:
                operator = new Multiply();
                break;
            case Constants.DIVISION:
                operator = new Divide();
                break;
            default:
                throw new UnsupportedOperationException();
        }

        return operator;
    }
}

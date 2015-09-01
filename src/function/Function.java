package function;

import function.interfaces.Operable;

/**
 * Created by shane on 9/1/15.
 */
public final class Function {

    private double num1;
    private double num2;

    private Operable operation;

    public Function(double num1, double num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public double execute(double num1, double num2) {

        return operation.equate(num1, num2);

    }

    public static Function newInstance(double num1, double num2) {

        return new Function(num1, num2);

    }

    public void setOperation(Operable operation) {

        this.operation = operation;

    }
}

package function.components;

import function.interfaces.Operable;

/**
 * A class that executes operations of the subtree nodes
 * by taking the value of the leaves as operands and executing
 * an operation on it based on the parent.
 *
 * @author  Shane Richards
 * @version 1.0
 * @since   2015-9-1/15.
 */
public final class Function {

    /**
     * Operands of the equation
     */
    private double num1;
    private double num2;

    /**
     * Strategy interface used to determine the
     * operation to be executed
     */
    private Operable operation;

    public Function(double num1, double num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    /**
     * The method acts as the generic command to execute
     * any type of function, based on the operation provided.
     * @return
     */
    public double execute() {

        return operation.equate(num1, num2);

    }

    /**
     * Factory method that returns a new instance of the Function
     * class
     *
     * @param num1  the first operand
     * @param num2  the second operand
     * @return      new instance of the function
     */
    public static Function newInstance(double num1, double num2) {

        return new Function(num1, num2);

    }

    /**
     * Changes the function behaviour at runtime based on the
     * operator provided
     *
     * @param operation sets the function behaviour, each operable
     *                  class handles a different operator
     */
    public void setOperation(Operable operation) {

        this.operation = operation;

    }
}

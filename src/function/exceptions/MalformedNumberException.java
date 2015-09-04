package function.exceptions;

/**
 * Thrown to indicate the a token in the FunctionTree equations
 * was incorrect.
 *
 * @author  Shane Richards
 * @version 1.0
 * @since   2015-31-8.
 */
public class MalformedNumberException extends RuntimeException {

    /**
     * Constructs a <code>MalformedNumberException</code> with no detailed message.
     */
    public MalformedNumberException() {
        super();
    }
}

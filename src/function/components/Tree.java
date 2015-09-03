package function.components;

/**
 * <h1>Tree</h1>
 *
 * An abstract class representing tree, it manages state by
 * maintaining a reference to a root note.
 *
 * Preserve immutability as best as possible by avoiding setters
 * or any other kinds of mutator methods.
 *
 * @author  Shane Richards
 * @version 1.0
 * @since   2015-8-21
 */
public abstract class Tree {

    public abstract Node getRoot();
}

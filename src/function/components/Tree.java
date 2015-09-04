package function.components;

import function.util.Util;

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

    public static final Tree EMPTY_TREE = NullTree.getInstance();

    public abstract Node getRoot();

    public static Tree emptyTree() {
        return (NullTree) EMPTY_TREE;
    }

    /**
     * Represents a null tree instance
     *
     * @author  Shane Richards
     * @since   8/21/15.
     */
    private static class NullTree extends Tree {
        private static NullTree sInstance = null;

        private NullTree(){}

        public Node getRoot(){
            return null;
        }

        public static NullTree getInstance(){
            if (Util.isEmpty(sInstance))
                sInstance = new NullTree();
            return sInstance;
        }
    }
}

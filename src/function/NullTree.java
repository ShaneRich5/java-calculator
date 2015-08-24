package function;

import util.Util;

/**
 * Created by shane on 8/21/15.
 */
public class NullTree extends Tree {
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

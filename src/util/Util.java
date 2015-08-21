package util;

/**
 * Created by shane on 8/21/15.
 */
public class Util {

    /**
     * Explicitly prevents instantiation and inheritance
     */
    public Util(){
        throw new AssertionError();
    }

    public static boolean isEmpty(Object object){
        return null == object;
    }
}

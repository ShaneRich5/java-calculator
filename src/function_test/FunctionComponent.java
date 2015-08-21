package function_test;

/**
 * Created by shane on 8/12/15.
 */
public abstract class FunctionComponent {

    public void add(FunctionComponent newComponent) {
        throw new UnsupportedOperationException();
    }

    public void remove(FunctionComponent component) {
        throw new UnsupportedOperationException();
    }

    public FunctionComponent getComponent(int index) {
        throw new UnsupportedOperationException();
    }

}

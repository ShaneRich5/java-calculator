package function_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shane on 8/12/15.
 */
public class Function {
    public List<Integer> data;
    public List<String> errors;
    public int size;

    public Function(Integer[] data) {
        this.data = Arrays.asList(data);
        size = data.length;
    }

    public Function(ArrayList<Integer> data) {
        this.data = data;
        size = data.size();
    }

    public Function transform(String operator, final int operand) {


        switch (operator) {
            case ">":
                for (int i=0; i<size; i++) if (data.get(i) <= operand) data.remove(i);
                break;
            case "<":
                for (int i=0; i<size; i++) if (data.get(i) >= operand) data.remove(i);
                break;
            case "==":
                for(int i=0; i<size; i++) if (data.get(i) != operand) data.remove(i);
                break;
            case "+":
                for (int i=0; i<size; i++) data.set(i, data.get(i) + operand);
                break;
            case "-":
                for (int i=0; i<size; i++) data.set(i, data.get(i) - operand);
                break;
            case "/":
                if (0 == operand)
                    errors.add("Cannot divide by zero");
                else
                    for (int i = 0; i < size; i++) data.set(i, data.get(i) / operand);
                break;
            case "*":
                for (int i=0; i<size; i++)  data.set(i, data.get(i) * operand);
                break;
            default:
                break;
        }
        updateSize();
        return this;
    }

    private void updateSize() {
        size = data.size();
    }

    @Override public String toString() {

//        for (Integer x: data) System.out.print(x + " ");

        return data + "";
    }
}

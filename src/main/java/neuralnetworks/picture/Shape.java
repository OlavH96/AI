package neuralnetworks.picture;

import java.util.Arrays;

/**
 * Created by faiter on 10/11/17.
 */
public enum Shape {

    O(0),
    X(1);

    private int value;

    Shape(int i) {
        this.value = i;
    }
    public int getValue() {
        return value;
    }

    public static Shape fromValue(int val){

        return Arrays.stream(values()).filter(shape -> shape.getValue() == val).findFirst().get();

    }

}

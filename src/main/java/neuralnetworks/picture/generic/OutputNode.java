package neuralnetworks.picture.generic;

import java.util.Arrays;

/**
 * Created by faiter on 10/16/17.
 */
public interface OutputNode {

    default double[] getResultArray() {
        return null;
    }

    static <T> T of(double[] array, T[] values){

        double asDouble = Arrays.stream(array).max().orElse(1);

        int index = 0;
        for(double d : array){
            if (d == asDouble) break;
            index++;
        }

        return values[index];    }

}

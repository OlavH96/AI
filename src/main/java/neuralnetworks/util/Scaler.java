package neuralnetworks.util;

import java.util.Arrays;
import java.util.stream.DoubleStream;

/**
 * Created by faiter on 10/11/17.
 */
public class Scaler {

    /**
     * Scale number to 0-1
     */
    public static double scale(double d, double min, double max){

        double factor = max-min;

        return (d-min)/factor;

    }
    public static double[] scaleArray(double[] array, double min, double max){
        double[] out = new double[array.length];

        for (int i = 0; i < array.length; i++) {
            out[i] = scale(array[i], min, max);
        }
        return out;
    }

    /**
     * Autodetects min and max
     * Fine if you know the array contains the min and max values
     */
    public static double[] autoScaleArray(double[] array){

        double min = DoubleStream.of(array).min().orElse(0);
        double max = DoubleStream.of(array).max().orElse(100);

        return scaleArray(array, min, max);
    }



    public static void main(String[] args) {

        System.out.println(scale(11, 0,100));

        double[] doubles = {1, 10, 100, 50, 55, 0};

        System.out.println(Arrays.toString(autoScaleArray(doubles)));


    }
}

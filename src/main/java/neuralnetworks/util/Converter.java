package neuralnetworks.util;

/**
 * Created by faiter on 10/17/17.
 */
public class Converter {

    public static double[] convert(byte[] bytes){

        double[] out = new double[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            out[i] = (double) bytes[i];
        }
        return out;

    }
}

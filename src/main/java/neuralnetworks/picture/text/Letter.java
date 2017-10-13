package neuralnetworks.picture.text;

import java.util.Arrays;

/**
 * Created by faiter on 10/13/17.
 */
public enum Letter {

    A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z;

    public static double[] getResultArrayFor(Letter letter, int size){

        double[] out = new double[size];

        out[letter.ordinal()] = 1;

        return out;
    }
    public static Letter of(double[] array){

        double asDouble = Arrays.stream(array).max().orElse(1);

        int index = 0;
        for(double d : array){
            if (d == asDouble) break;
            index++;
        }
        System.out.println(index);

        return values()[index];

    }

}

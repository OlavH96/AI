package neuralnetworks.picture.text.data;

import neuralnetworks.picture.generic.OutputNode;

import java.util.Arrays;

/**
 * Created by faiter on 10/13/17.
 */
public enum Letter implements OutputNode {

    A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine;

    public static Letter[] onlyLetters(){

        return Arrays.copyOf(values(), 26);
    }

    @Override
    public double[] getResultArray() {

        double[] out = new double[Letter.values().length];

        out[ordinal()] = 1;

        return out;
    }

    public double[] getResultArray(int size) {

        double[] out = new double[size];

        out[ordinal()] = 1;

        return out;
    }

    public static void main(String[] args) {

        Letter of = OutputNode.of(new double[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, Letter.values());

        System.out.println(of);
    }
}

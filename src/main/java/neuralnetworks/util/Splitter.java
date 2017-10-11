package neuralnetworks.util;

import java.util.Arrays;

/**
 * Created by faiter on 10/11/17.
 */
public class Splitter {

    public static double[][] split(double[] array, int rowCount){

        int perRow = array.length/rowCount;

        double[][] out = new double[rowCount][perRow];


        int counter = 0;
        for (int i = 0; i < rowCount; i++) {

            for (int j = 0; j < perRow; j++) {

                if (counter < array.length){
                    out[i][j] = array[counter++];
                }
                else {
                    out[i][j] = 0;
                }

            }

        }
        return out;
    }

    public static void main(String[] args) {

        double[] array = {12,31,32,32,1,45,45,4,65,76,87,8,6,5,4,53,4,3,43};

        System.out.println(Arrays.deepToString(split(array, 2)));


    }
}

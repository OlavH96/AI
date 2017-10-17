package neuralnetworks.picture.text.util;

import neuralnetworks.picture.text.data.Letter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.DoubleStream;

/**
 * Created by faiter on 10/16/17.
 */
public class Percentage {

    public static Map<Letter, Double> percentagesFor(double[] data){

        Map<Letter, Double> map = new HashMap<>();

        DoubleStream.of(data).filter(v -> v > 0.1).sorted().forEach(v -> {

            int index = search(data, v);
            System.out.println(Letter.values()[index]+" - "+v);
            map.put(Letter.values()[index], v);
        });

        return map;


    }
    private static int search(double[] data, double d){
        int index = 0;
        for(double dd : data){
            if (dd == d) break;
            index++;
        }
        return index;
    }

}

package neuralnetworks.picture.text.app.util;

import neuralnetworks.picture.text.data.Letter;

import java.util.Map;

/**
 * Created by faiter on 10/16/17.
 */
public class ToStringer {

    public static String toStr(Map<Letter, Double> map){

        final String[] out = {""};

        map.entrySet().stream().sorted((letterDoubleEntry, t1) -> Double.compare(t1.getValue(), letterDoubleEntry.getValue()))
                .forEach(letterDoubleEntry -> {

            out[0] +=letterDoubleEntry.getKey();
            out[0] += " : ";
            out[0] += ((int)(letterDoubleEntry.getValue()*100)) +" %";
            out[0] += "\n";

        });

        return out[0];
    }

}

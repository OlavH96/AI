package neuralnetworks.util;

/**
 * Created by faiter on 10/17/17.
 */
public class Parser {

    public static byte[] parse(String str){

        str = str.trim();
        str = str.substring(1);

        String[] split = str.split(",");

        byte[] out = new byte[split.length];

        for (int i = 0; i < split.length; i++) {
            out[i] = Byte.parseByte(String.valueOf(split[i]));

        }
        return out;

    }
}

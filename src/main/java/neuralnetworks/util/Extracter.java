package neuralnetworks.util;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

/**
 * Created by faiter on 10/17/17.
 */
public class Extracter {

    public static int sizeOf(BufferedImage image){

        int size = ((DataBufferByte)image.getRaster().getDataBuffer()).getData().length;

        return size;

    }
}

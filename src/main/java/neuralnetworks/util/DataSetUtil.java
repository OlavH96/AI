package neuralnetworks.util;

import neuralnetworks.picture.Shape;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.List;

/**
 * Created by faiter on 10/11/17.
 */
public class DataSetUtil {

    private final static int NUMBER_OF_BYTES_IN_PIXEL = 4;

    public static DataSet createDataSet(List<BufferedImage> images, List<Shape> output){

        if (images.size() != output.size()) throw new IllegalArgumentException("images length != output length");

        int size = ((DataBufferByte) images.get(0).getRaster().getDataBuffer()).getData().length;

        DataSet trainingSet = new DataSet(size/NUMBER_OF_BYTES_IN_PIXEL, 1);

        for (int i = 0; i < images.size(); i++) {

            BufferedImage bufferedImage = images.get(i);

            double[] doubles = Loader.loadPixelData(bufferedImage);

            doubles = Scaler.scaleArray(doubles, 0, 255);

            trainingSet.addRow(new DataSetRow(doubles, new double[]{output.get(i).getValue()}));
        }

        return trainingSet;
    }
}

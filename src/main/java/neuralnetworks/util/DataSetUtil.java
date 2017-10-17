package neuralnetworks.util;

import neuralnetworks.picture.X_or_O.data.Shape;
import neuralnetworks.picture.generic.OutputNode;
import neuralnetworks.picture.text.util.Constants;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.Arrays;
import java.util.List;

/**
 * Created by faiter on 10/11/17.
 */
public class DataSetUtil {


    public static DataSet createDataSet(List<BufferedImage> images, List<Shape> output){

        if (images.size() != output.size()) throw new IllegalArgumentException("images length != output length");

        int size = ((DataBufferByte) images.get(0).getRaster().getDataBuffer()).getData().length;

        DataSet trainingSet = new DataSet(size/ Constants.NUMBER_OF_BYTES_IN_PIXEL.getNumber(), 1);

        for (int i = 0; i < images.size(); i++) {

            BufferedImage bufferedImage = images.get(i);

            double[] doubles = Loader.loadPixelData(bufferedImage);

            doubles = Scaler.scaleArray(doubles, 0, 255);

            trainingSet.addRow(new DataSetRow(doubles, new double[]{output.get(i).getValue()}));
        }

        return trainingSet;
    }

    public static DataSetRow learn(BufferedImage newImage, OutputNode letter){

        double[] doubles = Loader.loadPixelData(newImage);
        doubles = Scaler.scaleArray(doubles, 0, 255);

        double[] array = letter.getResultArray();
        System.out.println(Arrays.toString(doubles) +" - "+ Arrays.toString(array));

        return new DataSetRow(doubles, array);

    }
}

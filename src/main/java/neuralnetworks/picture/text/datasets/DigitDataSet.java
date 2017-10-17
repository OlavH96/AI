package neuralnetworks.picture.text.datasets;

import neuralnetworks.picture.text.data.Letter;
import neuralnetworks.picture.text.util.Constants;
import neuralnetworks.util.Loader;
import neuralnetworks.util.Scaler;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.List;

/**
 * Created by faiter on 10/17/17.
 */
public class DigitDataSet {

    public static DataSet createInitialDataSet(int size){
        DataSet dataSet = new DataSet(size, Letter.values().length);

        dataSet.setLabel("Letter Dataset");

        return dataSet;
    }

    public static void appendDataSet(DataSet toAppend, List<BufferedImage> images, Letter expectedDigit){

        int size = ((DataBufferByte)images.get(0).getRaster().getDataBuffer()).getData().length;
        // number of pixels
        int numberOfPixels = size/ Constants.NUMBER_OF_BYTES_IN_PIXEL.getNumber();


        for (int i = 0; i < images.size()/100; i++) {

            BufferedImage image = images.get(i);

            double[] doubles = Loader.loadPixelData(image);
            doubles = Scaler.scaleArray(doubles, 0, 255);


            toAppend.addRow(new DataSetRow(doubles, expectedDigit.getResultArray()));

        }
    }

}

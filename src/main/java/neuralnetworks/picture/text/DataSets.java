package neuralnetworks.picture.text;

import neuralnetworks.util.Loader;
import neuralnetworks.util.Scaler;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.List;


/**
 * Created by faiter on 10/13/17.
 */
public class DataSets {


    public static DataSet createDataSet(List<BufferedImage> images, List<Letter> expected){

        int size = ((DataBufferByte)images.get(0).getRaster().getDataBuffer()).getData().length;
        // number of pixels
        int numberOfPixels = size/ Constants.NUMBER_OF_BYTES_IN_PIXEL.getNumber();

        DataSet dataSetRows = new DataSet(numberOfPixels, expected.size());
        dataSetRows.setLabel("Letter Dataset");


        for (int i = 0; i < images.size(); i++) {

            BufferedImage image = images.get(i);

            double[] doubles = Loader.loadPixelData(image);
            doubles = Scaler.scaleArray(doubles, 0, 255);

            dataSetRows.addRow(new DataSetRow(doubles, Letter.getResultArrayFor(expected.get(i), expected.size())));

        }



        return dataSetRows;
    }
}

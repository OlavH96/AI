package neuralnetworks.picture.X_or_O.learning;

import neuralnetworks.picture.X_or_O.SimpleNeuralNetwork;
import neuralnetworks.util.DataSetUtil;
import neuralnetworks.util.Loader;
import org.neuroph.core.data.DataSet;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static neuralnetworks.picture.X_or_O.data.Shape.O;
import static neuralnetworks.picture.X_or_O.data.Shape.X;

/**
 * Created by faiter on 10/13/17.
 */
public class Learner {

    final static int NUMBER_OF_BYTES_IN_PIXEL = 4;

    public static SimpleNeuralNetwork learn() throws IOException {

        List<BufferedImage> bufferedImages = Loader.loadImages(
                Arrays.asList(
                        "/pictures/train/x_5_1.png",
                        "/pictures/train/x_5_2.png",
                        "/pictures/train/o_5_1.png",
                        "/pictures/train/o_5_2.png",
                        "/pictures/train/o_5_3.png",
                        "/pictures/train/o_5_4.png",
                        "/pictures/train/o_5_5.png"
                ));

        DataSet trainingSet = DataSetUtil.createDataSet(
                bufferedImages, // images
                Arrays.asList(X,X,O,O,O,O,O) // expected values
        );

        System.out.println(trainingSet);

        // number of bytes
        int size = ((DataBufferByte)bufferedImages.get(0).getRaster().getDataBuffer()).getData().length;
        // number of pixels
        int numberOfPixels = size/NUMBER_OF_BYTES_IN_PIXEL;

        System.out.println(numberOfPixels);

        SimpleNeuralNetwork neuralNetwork = new SimpleNeuralNetwork(numberOfPixels, 1);

        System.out.println("Training..");
        neuralNetwork.train(trainingSet);

        System.out.println("Saving..");
        neuralNetwork.getNeuralNetwork().save("/home/faiter/IdeaProjects/AI/src/main/resources/perceptron.nnet");

        return neuralNetwork;
    }

    public static void main(String[] args) throws IOException {

        learn();
    }
}
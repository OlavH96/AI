package neuralnetworks.picture;

import neuralnetworks.util.Loader;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Perceptron;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static neuralnetworks.picture.Shape.O;
import static neuralnetworks.picture.Shape.X;

/**
 * Created by faiter on 10/11/17.
 */
public class O_or_x {

    public static DataSet createDataSet(List<BufferedImage> images, List<Shape> output){

        int size = ((DataBufferByte) images.get(0).getRaster().getDataBuffer()).getData().length;

        DataSet trainingSet = new DataSet(size, 1);

        for (int i = 0; i < images.size(); i++) {

            BufferedImage bufferedImage = images.get(i);
            double[] doubles = Loader.loadPixelData(bufferedImage);
            trainingSet.addRow(new DataSetRow(doubles, new double[]{output.get(i).getValue()}));
        }

        return trainingSet;
    }

    public static void main(String[] args) throws IOException {

        List<BufferedImage> bufferedImages = Loader.loadImages(
                Arrays.asList(
                        "/pictures/train/x.png",
                "/pictures/train/o1.png",
                "/pictures/train/o2.png"
                ));

        DataSet trainingSet = createDataSet(
                bufferedImages, // images
                Arrays.asList(X, O, O) // expected
        );

        int size = ((DataBufferByte)bufferedImages.get(0).getRaster().getDataBuffer()).getData().length;

        NeuralNetwork neuralNetwork = new Perceptron(size, 1);

        System.out.println("Learning dataset");
        neuralNetwork.learn(trainingSet);

        //neuralNetwork.save("/home/faiter/IdeaProjects/AI/src/main/resources/perceptron.nnet");
        //NeuralNetwork fromFile = NeuralNetwork.createFromFile("/home/faiter/IdeaProjects/AI/src/main/resources/perceptron.nnet");

        BufferedImage o_test = Loader.loadImage("/pictures/test/o2_test.png");
        double[] doubles = Loader.loadPixelData(o_test);

        neuralNetwork.setInput(doubles);

        System.out.println("Calculating");
        neuralNetwork.calculate();

        List<BufferedImage> images = Loader.loadImagesFromFolder("/home/faiter/IdeaProjects/AI/src/main/resources/pictures/test");

        images.forEach(image -> {

            double[] data = Loader.loadPixelData(image);

            neuralNetwork.setInput(data);
            neuralNetwork.calculate();

            double[] output = neuralNetwork.getOutput();

            System.out.println(Shape.fromValue((int) output[0]));
        });

    }
}
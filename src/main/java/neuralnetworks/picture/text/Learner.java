package neuralnetworks.picture.text;

import neuralnetworks.util.Loader;
import neuralnetworks.util.Scaler;
import org.neuroph.core.data.DataSet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by faiter on 10/13/17.
 */
public class Learner {
    final static String TEST_FOLDER = "/home/faiter/IdeaProjects/AI/src/main/resources/text/test";
    final static String TRAIN_FOLDER ="/home/faiter/IdeaProjects/AI/src/main/resources/text/train";

    public static TextNeuralNetwork learn() throws IOException {

        List<BufferedImage> bufferedImages = Loader.loadImagesFromFolder(TRAIN_FOLDER);

        List<Letter> letters = Arrays.asList(Letter.values());

        DataSet dataSetRows = DataSets.createDataSet(bufferedImages, letters);

        System.out.println(dataSetRows.getInputSize());
        TextNeuralNetwork neuralNetwork = new TextNeuralNetwork(dataSetRows.getInputSize(), dataSetRows.getOutputSize());

        System.out.println("Valid: "+((letters.size() == bufferedImages.size()) && (letters.size() == dataSetRows.getOutputSize())));

        System.out.println(dataSetRows);

        System.out.println("Training..");
        neuralNetwork.train(dataSetRows);


        List<File> filesInFolder = Loader.getFilesInFolder(TRAIN_FOLDER); // Just so you have the names
        List<BufferedImage> images = Loader.loadImagesFromFolder(TRAIN_FOLDER);

        System.out.println(filesInFolder);
        System.out.println(images);

        for (int i = 0; i < images.size(); i++) {

            String name = filesInFolder.get(i).getName();

            BufferedImage image = images.get(i);
            //image = PictureUtil.scale(image, (int) Math.sqrt(dataSetRows.getInputSize())); // Scales image to test images size

            double[] data = Loader.loadPixelData(image);
            data = Scaler.scaleArray(data, 0, 255);

            double[] output = neuralNetwork.test(data);
            System.out.println(Arrays.toString(output));

            System.out.println(name +" - "+ Letter.of(output));

        }

        System.out.println("Saving..");
        neuralNetwork.getNeuralNetwork().save("/home/faiter/IdeaProjects/AI/src/main/resources/textnetwork.nnet");

        return neuralNetwork;
    }

    public static void main(String[] args) throws IOException {

        learn();
    }
}
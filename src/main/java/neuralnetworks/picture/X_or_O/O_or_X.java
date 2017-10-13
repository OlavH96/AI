package neuralnetworks.picture.X_or_O;

import neuralnetworks.picture.Shape;
import neuralnetworks.picture.SimpleNeuralNetwork;
import neuralnetworks.picture.picUtil.PictureUtil;
import neuralnetworks.util.Loader;
import org.neuroph.core.NeuralNetwork;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by faiter on 10/11/17.
 */
public class O_or_X {

    final static String TEST_FOLDER = "/home/faiter/IdeaProjects/AI/src/main/resources/pictures/test";
    final static String OUTPUT_FOLDER = "/home/faiter/IdeaProjects/AI/src/main/resources/pictures/output/";

    public static void main(String[] args) throws IOException {

        NeuralNetwork fromFile = NeuralNetwork.createFromFile("/home/faiter/IdeaProjects/AI/src/main/resources/perceptron.nnet");
        SimpleNeuralNetwork neuralNetwork = new SimpleNeuralNetwork(fromFile);

        List<File> filesInFolder = Loader.getFilesInFolder(TEST_FOLDER); // Just so you have the names
        List<BufferedImage> images = Loader.loadImagesFromFolder(TEST_FOLDER);

        for (int i = 0; i < images.size(); i++) {

            String name = filesInFolder.get(i).getName();

            BufferedImage image = images.get(i);
            image = PictureUtil.scale(image, (int) Math.sqrt(fromFile.getInputsCount())); // Scales image to test images size

            double[] data = Loader.loadPixelData(image);

            //System.out.println(Arrays.toString(data));

            ImageIO.write(image, "png", new File(OUTPUT_FOLDER+"scaled_"+name));

            double[] output = neuralNetwork.test(data);

            System.out.println(name +" - "+ Shape.fromValue((int) output[0]));

        }
    }
}
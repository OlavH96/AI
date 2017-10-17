package neuralnetworks.picture.text.learners;

import neuralnetworks.picture.text.datasets.DigitDataSet;
import neuralnetworks.picture.text.networks.TextNeuralNetwork;
import neuralnetworks.picture.text.data.Letter;
import neuralnetworks.util.DigitToString;
import neuralnetworks.util.Extracter;
import neuralnetworks.util.Loader;
import org.neuroph.core.data.DataSet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Created by faiter on 10/17/17.
 */
public class DigitLearner {

    final static String TRAIN_FOLDER ="/home/faiter/IdeaProjects/AI/src/main/resources/digits/training";
    final static String OUTPUT_FOLDER ="/home/faiter/IdeaProjects/AI/src/main/resources/digits/output";


    public static TextNeuralNetwork learn() throws IOException, URISyntaxException {

        File folder = Paths.get(TRAIN_FOLDER).toFile();

        List<File> subdirs = Arrays.asList(folder.listFiles());

        BufferedImage read = ImageIO.read(Paths.get(DigitLearner.class.getResource("/digits/training/0/1.png").toURI()).toFile());
        DataSet dataSetRows = DigitDataSet.createInitialDataSet(Extracter.sizeOf(read));

        for (int i = 0; i < subdirs.size(); i++) {

            File subdir = subdirs.get(i);

            int i1 = Integer.parseInt(subdir.getName());
            Letter digit = DigitToString.fromDigit(i1);
            System.out.println(i1+" - "+digit);

            List<BufferedImage> bufferedImages = Loader.loadImagesFromFolder(subdir.getAbsolutePath());

            DigitDataSet.appendDataSet(dataSetRows, bufferedImages, digit);
        }

        System.out.println(dataSetRows.getInputSize());
        TextNeuralNetwork neuralNetwork = new TextNeuralNetwork(dataSetRows.getInputSize(), dataSetRows.getOutputSize());

        //System.out.println("Valid: "+((digits.size() == bufferedImages.size()) && (digits.size() == dataSetRows.getOutputSize())));

        System.out.println(dataSetRows.size());

        System.out.println("Training..");
        neuralNetwork.train(dataSetRows);

        System.out.println("Saving..");
        neuralNetwork.getNeuralNetwork().save("/home/faiter/IdeaProjects/AI/src/main/resources/digitNetwork.nnet");

        return neuralNetwork;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {

        learn();
    }
}

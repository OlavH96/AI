package neuralnetworks.picture;

import neuralnetworks.picture.picUtil.PictureUtil;
import neuralnetworks.util.Loader;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Perceptron;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static neuralnetworks.picture.Shape.O;
import static neuralnetworks.picture.Shape.X;

/**
 * Created by faiter on 10/11/17.
 */
public class O_or_X {

    final static String TEST_FOLDER = "/home/faiter/IdeaProjects/AI/src/main/resources/pictures/test";
    final static String OUTPUT_FOLDER = "/home/faiter/IdeaProjects/AI/src/main/resources/pictures/output/";

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

        //System.out.println(size/4); // /4 fordi r g b alpha bytes pr pixel
        int width = (int) Math.sqrt(size/4);
        //System.out.println(width);

        NeuralNetwork neuralNetwork = new Perceptron(size, 1);

        neuralNetwork.learn(trainingSet);

        //neuralNetwork.save("/home/faiter/IdeaProjects/AI/src/main/resources/perceptron.nnet");
        //NeuralNetwork fromFile = NeuralNetwork.createFromFile("/home/faiter/IdeaProjects/AI/src/main/resources/perceptron.nnet");

        List<File> filesInFolder = Loader.getFilesInFolder(TEST_FOLDER);
        List<BufferedImage> images = Loader.loadImagesFromFolder(TEST_FOLDER);

        for (int i = 0; i < images.size(); i++) {

            BufferedImage image = images.get(i);
            image = PictureUtil.scale(image, width); // Scales image to test images size

            String name = filesInFolder.get(i).getName();

            double[] data = Loader.loadPixelData(image);

            ImageIO.write(image, "png", new File(OUTPUT_FOLDER+name+"_compressed.png"));

            neuralNetwork.setInput(data);
            neuralNetwork.calculate();

            double[] output = neuralNetwork.getOutput();

            System.out.println(name +" - "+Shape.fromValue((int) output[0]));
        }

    }
}
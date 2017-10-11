package neuralnetworks.picture;

import neuralnetworks.picture.picUtil.PictureUtil;
import neuralnetworks.util.DataSetUtil;
import neuralnetworks.util.Loader;
import org.neuroph.core.data.DataSet;

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

    final static int NUMBER_OF_BYTES_IN_PIXEL = 4;

    public static void main(String[] args) throws IOException {

        List<BufferedImage> bufferedImages = Loader.loadImages(
                Arrays.asList(
                        "/pictures/train/x.png",
                        "/pictures/train/x1.png",
                "/pictures/train/o1.png",
                "/pictures/train/o2.png"
                ));

        DataSet trainingSet = DataSetUtil.createDataSet(
                bufferedImages, // images
                Arrays.asList(X, X, O, O) // expected values
        );

        // number of bytes
        int size = ((DataBufferByte)bufferedImages.get(0).getRaster().getDataBuffer()).getData().length;
        // number of pixels
        int numberOfPixels = size/NUMBER_OF_BYTES_IN_PIXEL;

        SimpleNeuralNetwork neuralNetwork = new SimpleNeuralNetwork(numberOfPixels, 1);
        neuralNetwork.train(trainingSet);

        List<File> filesInFolder = Loader.getFilesInFolder(TEST_FOLDER); // Just so you have the names
        List<BufferedImage> images = Loader.loadImagesFromFolder(TEST_FOLDER);

        for (int i = 0; i < images.size(); i++) {

            BufferedImage image = images.get(i);
            image = PictureUtil.scale(image, (int) Math.sqrt(numberOfPixels)); // Scales image to test images size

            double[] data = Loader.loadPixelGreyscaleData(image);

            String name = filesInFolder.get(i).getName();

            ImageIO.write(image, "png", new File(OUTPUT_FOLDER+name+"_compressed.png"));

            double[] output = neuralNetwork.test(data);

            System.out.println(name +" - "+Shape.fromValue((int) output[0]));
        }

    }
}
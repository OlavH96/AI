package neuralnetworks.picture.text.learners;

import neuralnetworks.picture.text.data.Letter;
import neuralnetworks.picture.text.networks.TextNeuralNetwork;
import neuralnetworks.util.Converter;
import neuralnetworks.util.Parser;
import neuralnetworks.util.Scaler;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by faiter on 10/17/17.
 */
public class TextLearner {

    public static String OUTPUT_NETWORK = "/home/faiter/IdeaProjects/AI/src/main/resources/networks/textnetwork.nnet";

    public static void main(String[] args) throws URISyntaxException, IOException {

        Path path = Paths.get(TextLearner.class.getResource("/training/letter-recognition.data.txt").toURI());
        BufferedReader reader = Files.newBufferedReader(path);

        System.out.println(Arrays.toString(Letter.onlyLetters()));
        int length = Letter.onlyLetters().length;

        TextNeuralNetwork neuralNetwork = new TextNeuralNetwork(16, length, 0,0);

        int inputsCount = neuralNetwork.getNeuralNetwork().getInputsCount();
        int outputsCount = neuralNetwork.getNeuralNetwork().getOutputsCount();

        DataSet dataSet = new DataSet(inputsCount, outputsCount);

        reader.lines().limit(50).forEach(s -> {

            Letter letter = Letter.valueOf(s.split(",")[0]);

            byte[] bytes = Parser.parse(s.substring(1));
            double[] convert = Converter.convert(bytes);
            double[] scaled = Scaler.scaleArray(convert, 0, 15);

            System.out.println(letter+" - "+ Arrays.toString(scaled) + Arrays.toString(letter.getResultArray(length)));

            dataSet.add(new DataSetRow(scaled, letter.getResultArray(length)));
        });

        System.out.println("Inputs: "+inputsCount);
        System.out.println("Outputs: "+outputsCount);
        System.out.println("Size: "+dataSet.size());

        System.out.println("Training..");

        neuralNetwork.train(dataSet);
        neuralNetwork.getNeuralNetwork().save(OUTPUT_NETWORK);
        System.out.println("Finished");
    }
}

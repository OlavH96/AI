package neuralnetworks.picture;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Perceptron;

import java.util.Arrays;

/**
 * Created by faiter on 10/11/17.
 */
public class Test {

    public static void main(String[] args) {

        //org.neuroph.util.benchmark.BenchmarkSample.main(new String[]{"test"});

        NeuralNetwork neuralNetwork = new Perceptron(2, 1);

        DataSet trainingSet = new DataSet(2, 1);

        trainingSet.addRow(new DataSetRow(new double[]{0, 0}, new double[]{0})); // 0,0 -> 0
        trainingSet.addRow(new DataSetRow(new double[]{0, 1}, new double[]{1}));
        trainingSet.addRow(new DataSetRow(new double[]{1, 0}, new double[]{1}));
        trainingSet.addRow(new DataSetRow(new double[]{1, 1}, new double[]{1}));

        neuralNetwork.learn(trainingSet);

        neuralNetwork.save("/home/faiter/IdeaProjects/AI/src/main/resources/perceptron.nnet");

        //NeuralNetwork fromFile = NeuralNetwork.createFromFile("/home/faiter/IdeaProjects/AI/src/main/resources/perceptron.nnet");

        neuralNetwork.setInput(0.1, 0.2);

        neuralNetwork.calculate();
        System.out.println(Arrays.toString(neuralNetwork.getOutput()));
    }
}

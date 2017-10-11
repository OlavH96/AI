package neuralnetworks.picture;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.Perceptron;

/**
 * Created by faiter on 10/11/17.
 */
public class SimpleNeuralNetwork {

    private int numberOfInputs;
    private int numberOfOutputs;

    private NeuralNetwork neuralNetwork;

    public SimpleNeuralNetwork(int numberOfInputs, int numberOfOutputs) {

        this.numberOfInputs = numberOfInputs;
        this.numberOfOutputs = numberOfOutputs;

        neuralNetwork = new Perceptron(numberOfInputs, numberOfOutputs);
    }

    public void train(DataSet trainingSet){
        neuralNetwork.learn(trainingSet);
    }

    public double[] test(double[] data){

        neuralNetwork.setInput(data);
        neuralNetwork.calculate();

        double[] output = neuralNetwork.getOutput();

        return output;
    }
}

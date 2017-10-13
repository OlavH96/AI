package neuralnetworks.picture;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Perceptron;
import org.neuroph.util.TransferFunctionType;

/**
 * Created by faiter on 10/11/17.
 */
public class SimpleNeuralNetwork {

    private NeuralNetwork neuralNetwork;

    public SimpleNeuralNetwork(int numberOfInputs, int numberOfOutputs) {

        neuralNetwork = new Perceptron(numberOfInputs, numberOfOutputs, TransferFunctionType.SIGMOID);
    }

    public SimpleNeuralNetwork(NeuralNetwork neuralNetwork) {

        this.neuralNetwork = neuralNetwork;
    }
    private DataSet trainingSet;
    public void train(DataSet trainingSet){

        if (this.trainingSet == null) this.trainingSet = trainingSet;

        neuralNetwork.learn(trainingSet);
    }

    public void retrain(DataSetRow newData){

        trainingSet.add(newData);
        System.out.println(trainingSet);
        train(trainingSet);
    }

    public double[] test(double[] data){

        neuralNetwork.setInput(data);
        neuralNetwork.calculate();

        double[] output = neuralNetwork.getOutput();

        return output;
    }

    public NeuralNetwork getNeuralNetwork() {

        return neuralNetwork;
    }

    public DataSet getTrainingSet() {

        return trainingSet;
    }
}

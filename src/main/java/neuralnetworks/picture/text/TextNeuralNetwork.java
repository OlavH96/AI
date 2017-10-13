package neuralnetworks.picture.text;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;

/**
 * Created by faiter on 10/11/17.
 */
public class TextNeuralNetwork {

    private NeuralNetwork neuralNetwork;
    private DataSet trainingSet;

    public TextNeuralNetwork(int numberOfInputs, int numberOfOutputs) {

        System.out.println("Making network");
        neuralNetwork  = new MultiLayerPerceptron(numberOfInputs, numberOfOutputs);

    }

    public TextNeuralNetwork(NeuralNetwork neuralNetwork) {

        this.neuralNetwork = neuralNetwork;
    }
    public void train(DataSet trainingSet){

        if (this.trainingSet == null) this.trainingSet = trainingSet;

        neuralNetwork.learn(trainingSet);
    }

    public void retrain(DataSetRow newData){

        // Turns out you dont need the original dataset to retrain the network with new data
        if (this.trainingSet == null) this.trainingSet = new DataSet(newData.getInput().length);

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

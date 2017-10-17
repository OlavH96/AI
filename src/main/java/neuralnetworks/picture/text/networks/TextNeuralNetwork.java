package neuralnetworks.picture.text.networks;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;

import java.util.Arrays;

/**
 * Created by faiter on 10/11/17.
 */
public class TextNeuralNetwork {

    private NeuralNetwork neuralNetwork;
    private DataSet trainingSet;

    public TextNeuralNetwork(int numberOfInputs, int numberOfOutputs) {

        System.out.println("Making network");
        int hiddenLayers = 2;
        int neuronsInHiddenLayer = 100;//(numberOfInputs+numberOfOutputs) / 2;

        int[] hiddenLayerArray = new int[hiddenLayers+2];
        hiddenLayerArray[0] = numberOfInputs;
        hiddenLayerArray[hiddenLayerArray.length-1]=numberOfOutputs;
        for (int i = 1; i < hiddenLayers+1; i++) {
            hiddenLayerArray[i] = neuronsInHiddenLayer;
        }
        System.out.println(Arrays.toString(hiddenLayerArray));

        neuralNetwork = new MultiLayerPerceptron(hiddenLayerArray);

    }
    public TextNeuralNetwork(int numberOfInputs, int numberOfOutputs, int layers, int neuronsPerLayer) {

        System.out.println("Making network");
        int hiddenLayers = layers;
        int neuronsInHiddenLayer = neuronsPerLayer;//(numberOfInputs+numberOfOutputs) / 2;

        int[] hiddenLayerArray = new int[hiddenLayers+2];
        hiddenLayerArray[0] = numberOfInputs;
        hiddenLayerArray[hiddenLayerArray.length-1]=numberOfOutputs;
        for (int i = 1; i < hiddenLayers+1; i++) {
            hiddenLayerArray[i] = neuronsInHiddenLayer;
        }
        System.out.println(Arrays.toString(hiddenLayerArray));

        neuralNetwork = new MultiLayerPerceptron(hiddenLayerArray);

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

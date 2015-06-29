package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.TrainingSetImport;
import org.neuroph.util.TransferFunctionType;


public class NeuralTraining extends MainFrame {

//    ConfigurationProperties conf = new ConfigurationProperties();
    static int INPUT_NEURON = 3;
    static int OUTPUT_NEURON = 1;
    StringBuilder messageTrainInput = new StringBuilder("");
    StringBuilder messageTrainOutput = new StringBuilder("");
    double[] networkOutput;
//    String messageTrainInputs = "";

    public StringBuilder getMessageTrainInput() {
        return messageTrainInput;
    }

    public StringBuilder getMessageTrainOutput() {
        return messageTrainOutput;
    }

    public void train(String trainingSetFileName, String JST_FILE_NAME) {
        conf.loadProperties();
        String sHiddenLayer = conf.getNeuronHiddenLayer();
        String maxIteration = conf.getMaxIteration();
        String momentum = conf.getMomentum();
        String learningRate = conf.getLearningRate();
        String maxError = conf.getMaxError();

        int neuronHiddenLayer = Integer.parseInt(sHiddenLayer);

        String message = "Menjalankan Training..";
        printTrain(message);
        System.out.println("Menjalankan Training: " + trainingSetFileName);

        DataSet dataSet = null;
        try {
            dataSet = TrainingSetImport.importFromFile(trainingSetFileName, INPUT_NEURON, OUTPUT_NEURON, ",");
        } catch (FileNotFoundException ex) {
            System.out.println("File " + trainingSetFileName + "tidak ditemukan!");
        } catch (IOException | NumberFormatException ex) {
            System.out.println("Kesalahan membaca file atau format list yang tidak benar!");
        }

//         Buat multilayer perceptron
        System.out.println("Membuat Neural network");
        MultiLayerPerceptron neuralNet = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, INPUT_NEURON, neuronHiddenLayer, OUTPUT_NEURON);

//         set learning parameter
        MomentumBackpropagation learningRule = (MomentumBackpropagation) neuralNet.getLearningRule();
//        learningRule.setLearningRate(0.5);
//        learningRule.setMomentum(0.8);
        learningRule.setLearningRate(Double.valueOf(learningRate));
        learningRule.setMomentum(Double.valueOf(momentum));
        learningRule.setMaxIterations(Integer.valueOf(maxIteration));
        learningRule.setMaxError(Double.valueOf(maxError));

//        TrainingNetwork(neuralNet, trainingSet);        
        System.out.println("Training neural network...");
        neuralNet.learn(dataSet);
        System.out.println("Training neural network Selesai");

        neuralNet.save(JST_FILE_NAME);
    }

    public void test(String trainingSetFileName, String jstFileName) {
        System.out.println("Menjalankan Training: " + trainingSetFileName);
        conf.loadProperties();

        DataSet trainingSet = null;
        try {
            trainingSet = TrainingSetImport.importFromFile(trainingSetFileName, INPUT_NEURON, OUTPUT_NEURON, ",");
        } catch (FileNotFoundException ex) {
            System.out.println("File tidak ditemukan!");
        } catch (IOException | NumberFormatException ex) {
            System.out.println("Kesalahan membaca file atau format list yang tidak benar!");
        }

        NeuralNetwork neuralNetwork = NeuralNetwork.createFromFile(jstFileName);

        for (DataSetRow trainingElement : trainingSet.getRows()) {
//            System.out.println("row"+trainingSet.getRowAt(0));
            neuralNetwork.setInput(trainingElement.getInput());
            neuralNetwork.calculate();
            networkOutput = neuralNetwork.getOutput();
            
            System.out.print("Input: " + Arrays.toString(trainingElement.getInput()));
            System.out.println(" Output: " + Arrays.toString(networkOutput));
        }
    }

    public String prediction(String testSetFileName, String jstName) {

        conf.loadProperties();

        DataSet trainingSet = null;
        try {
            trainingSet = TrainingSetImport.importFromFile(testSetFileName, INPUT_NEURON, OUTPUT_NEURON, ",");
        } catch (FileNotFoundException ex) {
            System.out.println("File tidak ditemukan!");
        } catch (IOException | NumberFormatException ex) {
            System.out.println("Kesalahan membaca file atau format list yang tidak benar!");
        }

        NeuralNetwork neuralNetwork = NeuralNetwork.createFromFile(jstName);

        for (DataSetRow trainingElement : trainingSet.getRows()) {
            System.out.println("row" + trainingSet.getRowAt(0));
            neuralNetwork.setInput(trainingElement.getInput());
            neuralNetwork.calculate();

            networkOutput = neuralNetwork.getOutput();

            System.out.print("Input: " + Arrays.toString(trainingElement.getInput()));
            System.out.println(" Output: " + Arrays.toString(networkOutput));
        }
        return Arrays.toString(networkOutput);
    }

}

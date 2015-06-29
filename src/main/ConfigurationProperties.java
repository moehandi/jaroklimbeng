package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationProperties {
    
    Properties prop = new Properties();
    String learningRate ;
    String momentum;
    String neuronHiddenLayer;  
    String maxIteration;
    String maxError;

    public Properties getProp() {
        return prop;
    }

    public void setProp(Properties prop) {
        this.prop = prop;
    }

    public String getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(String learningRate) {
        this.learningRate = learningRate;
    }

    public String getMomentum() {
        return momentum;
    }

    public void setMomentum(String momentum) {
        this.momentum = momentum;
    }

    public String getNeuronHiddenLayer() {
        return neuronHiddenLayer;
    }

    public void setNeuronHiddenLayer(String neuronHiddenLayer) {
        this.neuronHiddenLayer = neuronHiddenLayer;
    }

    public String getMaxIteration() {
        return maxIteration;
    }

    public void setMaxIteration(String maxIteration) {
        this.maxIteration = maxIteration;
    }

    public String getMaxError() {
        return maxError;
    }

    public void setMaxError(String maxError) {
        this.maxError = maxError;
    }
       
    public void writeProperties(String valueLearningRate, String valueMomentum,String valueNeuronHiddenLayer, 
                                 String valueMaxIteration, String valueMaxError){
        try{
            prop.setProperty("learningRate", valueLearningRate);
            prop.setProperty("momentum", valueMomentum);
            prop.setProperty("neuronHiddenLayer", valueNeuronHiddenLayer);
            prop.setProperty("maxIteration", valueMaxIteration);
            prop.setProperty("maxError", valueMaxError);                        
            //menyimpan property ke root folder
            prop.store(new FileOutputStream("config.properties"), null);
        }catch (IOException ex){
        }
    }
    
    public void loadProperties(){       
        try{
            prop.load(new FileInputStream("config.properties"));
            learningRate = prop.getProperty("learningRate");
            momentum = prop.getProperty("momentum");
            neuronHiddenLayer = prop.getProperty("neuronHiddenLayer");
            maxIteration = prop.getProperty("maxIteration");
            maxError = prop.getProperty("maxError");                       
        }catch(IOException ioe){}
    }
}

package NeuralNet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Data {

    public ArrayList<DataSample> data = new ArrayList<>();

    public ArrayList<DataSample> train;

    public ArrayList<DataSample> test;
    
    public Data(String data, String fileName) {
        getDataFromFile(fileName);
        storeData(data);
        initData();
    }

    public Data(String fileName) {
        getDataFromFile(fileName);
        initData();
    }

    public Data() {}

    public void addData(double[] x, int y) {

        data.add(new DataSample(x, y));

    }

    public void randomizeData() {
        Collections.shuffle(data);
    }

    public void partitionData() {
        
        int size = data.size();

        int[] trainingPartition = new int[] {0, size*4/5};
        int[] testingPartition = new int[] {trainingPartition[1], size};

        train = new ArrayList<DataSample>(data.subList(trainingPartition[0], trainingPartition[1]));

        test = new ArrayList<DataSample>(data.subList(testingPartition[0], testingPartition[1]));

    }

    private void initData() {
        partitionData();
        //System.out.println(train_X.size());
        //System.out.println(test_X.size());
    }

    public static Data createBlank(String data) {
        Data temp = new Data();
        temp.storeData(data);
        temp.initData();
        return temp;
    }

    private void storeData(String data) {

        String[] values;
        String outcome;

        String[] dataS = data.split(";");

        for (String d:dataS) {

            values = d.split(":")[0].split(",");
            outcome = d.split(":")[1];

            double[] x = new double[] {
                
                Integer.parseInt(values[0]),
                Integer.parseInt(values[1]),
                Integer.parseInt(values[2])

            };
            int y = Integer.parseInt(outcome);

            this.data.add(new DataSample(x, y));

        }

    }

    private void getDataFromFile(String fileName) {

        File trainingSet;
        BufferedReader reader;
        
        String data;
        
        trainingSet = new File(Paths.get("").toAbsolutePath().toString() + fileName);

        try {
            reader = new BufferedReader(new FileReader(trainingSet));

            data = reader.readLine();

            storeData(data);
            

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

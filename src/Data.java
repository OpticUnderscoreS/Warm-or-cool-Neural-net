import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Data {

    public ArrayList<double[]> data_X = new ArrayList<>();
    public ArrayList<Integer> data_Y = new ArrayList<>();

    public ArrayList<double[]> train_X;
    public ArrayList<Integer> train_Y;

    public ArrayList<double[]> test_X;
    public ArrayList<Integer> test_Y;
    
    public Data(String data, String fileName) {
        getDataFromFile(fileName);
        storeData(data);
        initData();
    }

    public Data(String fileName) {
        getDataFromFile(fileName);
        initData();
    }

    private Data() {}

    private void initData() {
        int size = data_X.size();

        int[] trainingPartition = new int[] {0, size*4/5};
        int[] testingPartition = new int[] {trainingPartition[1], size};

        train_X = new ArrayList<double[]>(data_X.subList(trainingPartition[0], trainingPartition[1]));
        train_Y = new ArrayList<Integer>(data_Y.subList(trainingPartition[0], trainingPartition[1]));

        test_X = new ArrayList<double[]>(data_X.subList(testingPartition[0], testingPartition[1]));
        test_Y = new ArrayList<Integer>(data_Y.subList(testingPartition[0], testingPartition[1]));
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

            data_X.add(new double[] {
                
                Integer.parseInt(values[0]),
                Integer.parseInt(values[1]),
                Integer.parseInt(values[2])

            });

            data_Y.add(Integer.parseInt(outcome));

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

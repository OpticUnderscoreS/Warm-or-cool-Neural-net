import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Data {

    public ArrayList<double[]> rgb = new ArrayList<>();
    public ArrayList<Integer> outcomes = new ArrayList<>();
    
    public Data(String data, String fileName) {
        initTrainingData(fileName);
        storeTrainingData(data);
    }

    public Data(String fileName) {
        initTrainingData(fileName);
    }

    private Data() {}

    public static Data createBlank(String data) {
        Data temp = new Data();
        temp.storeTrainingData(data);
        return temp;
    }

    private void storeTrainingData(String data) {

        String[] values;
        String outcome;

        String[] dataS = data.split(";");

        for (String d:dataS) {

            values = d.split(":")[0].split(",");
            outcome = d.split(":")[1];

            rgb.add(new double[] {
                
                Integer.parseInt(values[0]),
                Integer.parseInt(values[1]),
                Integer.parseInt(values[2])

            });

            outcomes.add(Integer.parseInt(outcome));

        }

    }

    private void initTrainingData(String fileName) {

        File trainingSet;
        BufferedReader reader;
        
        String data;
        
        trainingSet = new File(Paths.get("").toAbsolutePath().toString() + fileName);

        try {
            reader = new BufferedReader(new FileReader(trainingSet));

            data = reader.readLine();

            storeTrainingData(data);
            

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

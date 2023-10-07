

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class NeuralNet {

    private int inputs;

    private ArrayList<Node> nodes;
    
    public NeuralNet(int inputs) {
        this.inputs = inputs;
        nodes = new ArrayList<>();
    }

    public void createNode(int... inputNums) {
        nodes.add(new Node(inputNums));
    }

    public String predict(int... inputs) {

        double output = nodes.get(0).run(inputs);
        //System.out.println(output);

        return (output > 0.5) ? "Warm" : "Cool";

    }

    public void train() {

        // Get data
        File trainingSet;
        BufferedReader reader;
        ArrayList<int[]> rgb = new ArrayList<>();
        ArrayList<Integer> outcomes = new ArrayList<>();

        String[] trainingBits;
        
        String[] values;
        String outcome;

        System.out.println(Paths.get("").toAbsolutePath().toString());

        trainingSet = new File(Paths.get("").toAbsolutePath().toString() + "\\src\\data\\TrainingSet.txt");

        try {
            reader = new BufferedReader(new FileReader(trainingSet));

            trainingBits = reader.readLine().split(";");

            for (String d:trainingBits) {

                values = d.split(":")[0].split(",");
                outcome = d.split(":")[1];

                rgb.add(new int[] {
                    
                    Integer.parseInt(values[0]),
                    Integer.parseInt(values[1]),
                    Integer.parseInt(values[2])
 
                });

                outcomes.add(Integer.parseInt(outcome));

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Use data

        for (int i = 0; i < outcomes.size(); i++) {
            nodes.get(0).train(outcomes.get(i), rgb.get(i));
        }

    }

}

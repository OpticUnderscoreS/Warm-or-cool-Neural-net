package NeuralNet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.OptionalDouble;

public class NeuralNet {

    private static int inputs;
    public static double alpha = 0.05;

    public static ArrayList<Node> nodes;
    public static Data data;

    public static void initRawNeuralNet() {
        inputs = 3;
        nodes = new ArrayList<>();
        data = new Data();
    }

    public static void initNeuralNet() {
        inputs = 3;
        nodes = new ArrayList<>();
        data = new Data("\\src\\data\\DataSet.txt");
    }

    public static void addData(double[] x, int y) {
        data.addData(x, y);
    }

    public static void createNode(int... inputNums) {
        nodes.add(new Node(inputNums));
    }

    public static int predict(double... inputs) {

        double[] outputs = new double[nodes.size()];

        for (int i = 0; i < nodes.size(); i++) {
            outputs[i] = nodes.get(i).run(inputs);
            //System.out.println(outputs[i]);
        }
        
        return (outputs[0] > outputs[1]) ? 0 : 1;

    }

    public static double[] oneHot(int index, int size) {

        double[] temp = new double[size];

        for (int i = 0; i < size; i++) {
            temp[i] = (i == index) ? 1 : 0;
        }

        return temp;
    }

    public static void reset() {
        nodes.stream().forEach(Node::resetWeights);
        data.randomizeData();
        data.partitionData();
    }
 
    public static void train() {

        double[] A1;
        double[] Z1;
        double[] dZ1;
        double[][] dW1;
        double db1;
        double[] X;

        double m = nodes.size();
        int size = nodes.size();

        // Get data
        
        double[] oneHot;

        // Use data
        
        for (int i = 0; i < data.train.size(); i++) {

            X = data.train.get(i).getX();

            oneHot = oneHot(data.train.get(i).getY(), size);

            Z1 = new double[size];
            
            for (int j = 0; j < size; j++) {
                
                Z1[j] = nodes.get(j).run(X);
                //System.out.println(String.format("Z1: %s", Z1[j]));
            }
            
            A1 = softmax(Z1);
            dZ1 = new double[A1.length];
            //System.out.println(size + " " + A1.length);
            dW1 = new double[size][X.length];

            for (int j = 0; j < A1.length; j++) {
                   
                dZ1[j] = A1[j] - oneHot[j];
                
            }

            //System.out.println(A1[j] - oneHot[j]);
            for (int j = 0; j < size; j++) {
                for (int ii = 0; ii < X.length; ii++) {
                    dW1[j][ii] = (1/m) * dZ1[j] * X[ii]; 
                }
            }

            db1 = (1/m) * sum(dZ1);

            for (int j = 0; j < size; j++) {
                nodes.get(j).adjustWeights(dW1[j], db1);
            }

        }

    }
    
    public static void printNodeWeights() {
        for (int i = 0; i < nodes.size(); i++) {
            System.out.print(i + " - ");
            nodes.get(i).printWeights();
        }
    }

    public static double[] softmax(double[] Z) {

        //fix

        double maxZ = Arrays.stream(Z).max().getAsDouble();

        for (int i = 0; i < Z.length; i++) {
            Z[i] -= maxZ;
        }

        double[] sm = new double[Z.length];
        double normalizationTerm = 0;

        normalizationTerm = Arrays.stream(Z).map(Math::exp).sum();
        

        for (int j = 0; j < nodes.size(); j++) {
           
            sm[j] = Math.exp(Z[j]) / normalizationTerm;
            
        }

        return sm;
    }

    private static double test(int amtLeft, double percent) {
        
        if (amtLeft == 0) {
            return percent;
        }

        reset();
        train();

        amtLeft--;

        double tested = 0;
        double correct = 0;

        int predict;
        int outcome;

        for (int i = 0; i < data.test.size(); i++) {
            tested++;
            predict = predict(data.test.get(i).getX());
            outcome = data.test.get(i).getY();

            if (predict == outcome) {
                //System.out.println(predict);
                //System.out.print(outcome);
                correct++;
            }
            
        }

        return test(amtLeft, (correct/tested) + percent);
        
    }

    public static double test(int amt) {

        return 100*test(amt, 0)/amt;
        
    }

    private static double sum(double[] arr) {
        double temp = 0;
        for (double num:arr) {
            temp += num;
        }

        return temp;
    }

}

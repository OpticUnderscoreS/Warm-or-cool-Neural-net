

public class Node {
    
    private int[] usableInputs;
    private double[] weights;

    public Node(int[] usableInputs) {

        this.usableInputs = usableInputs;
        weights = new double[usableInputs.length];

        for (int i = 0; i < usableInputs.length; i++) {
            weights[i] = Math.random();
        }

    }//end constructor

    public void train(int z, int... inputs) {
        
        double output;
        double error;

        output = run(inputs);

        error = (z-output) * 0.1;

        for (int i = 0; i < usableInputs.length; i++) {
            weights[usableInputs[i]] += error * inputs[usableInputs[i]];
        }   

    }

    public double run(int... inputs) {

        double output = 0;

        for (int i = 0; i < usableInputs.length; i++) {
            output += inputs[usableInputs[i]] * weights[i];
        } 

        output = sigmoid(output);

        return output;

    }


    private static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }
}

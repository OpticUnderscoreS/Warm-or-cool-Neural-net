package NeuralNet;

public class DataSample {
    private double[] x;
    private int y;
    
    public double[] getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }

    public DataSample(double[] x, int y) {
        this.x = x;
        this.y = y;
    }
}

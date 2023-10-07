import java.util.Random;

public class Main {

    static NeuralNet nn;
    static Random randomGen = new Random();

    public static void main(String[] args) throws Exception {

        nn  = new NeuralNet(3);
        nn.createNode(0, 1, 2);

        nn.train();

        for (int i = 0; i < 10; i++) {
            test();
        }
 
    }

    public static void test() {
        int[] rgb = new int[] {
            randomGen.nextInt(255),
            randomGen.nextInt(255),
            randomGen.nextInt(255)
        };
        System.out.printf("\033[48;2;%s;%s;%sm  \033[0m", rgb[0], rgb[1], rgb[2]);
        System.out.print(" is " + nn.predict(rgb[0], rgb[1], rgb[2]) + "!");
        System.out.println();
    }

}

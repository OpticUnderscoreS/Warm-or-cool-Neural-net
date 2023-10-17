import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static NeuralNet nn;
    static Random randomGen = new Random();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        nn  = new NeuralNet(3, new Data("\\src\\data\\DataSet.txt"));
        nn.createNode(0, 1, 2);
        nn.createNode(0, 1, 2);

        System.out.println("[0] Use\n[1] Train");
        if (input.nextLine().startsWith("0")) {
            useAI();
        } else {
            trainAI();
        }
 
    }

    public static void useAI() {

        System.out.println(TextTools.BFTT + "Weights BEFORE Training" + TextTools.BFTD);
        nn.printNodeWeights();

        for (int i = 0; i < 100; i++) {
            nn.train();
        }

        System.out.println();
        System.out.println(TextTools.AFTT + "Weights AFTER Training" + TextTools.AFTD);

        nn.printNodeWeights();

        System.out.println(TextTools.BLUE + "Accuracy:");

        System.out.println(nn.test());
        System.out.print(TextTools.RESET);

        for (int i = 0; i < 0; i++) {
            test1();
        }
    }

    public static void trainAI() {

        String data = "";
        String userInput = "";
        String rgbText;

        System.out.println("Type 1 for warm, 0 for cool, t for test and x for exit");

        while (!userInput.equals("x")) {

            int[] rgb = new int[] {
                randomGen.nextInt(255),
                randomGen.nextInt(255),
                randomGen.nextInt(255)
            };

            rgbText = String.format("%s,%s,%s:", null);

            System.out.printf("\033[48;2;%s;%s;%sm  \033[0m", rgb[0], rgb[1], rgb[2]);
            System.out.print(": ");

            userInput = input.nextLine();
            
            switch (userInput) {
                case "0":
                    data += String.format("%s,%s,%s:0;", rgb[0], rgb[1], rgb[2]);
                    break;
                case "1":
                    data += String.format("%s,%s,%s:1;", rgb[0], rgb[1], rgb[2]);
                    break;
                case "t":
                    test(data);
                    break;
            }
 
        }

        // Save data

        File trainingData = new File (Paths.get("").toAbsolutePath().toString() + "\\src\\data\\DataSet.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(trainingData, true));
            writer.append(data);
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void test(String data) {
        int amt = 50;
        double outputs = 0;

        for (int i = 0; i < amt; i++) {
            NeuralNet t_nn = new NeuralNet(3, Data.createBlank(data));
            t_nn.createNode(0, 1, 2);
            t_nn.createNode(0, 1, 2);
            for (int j = 0; j < 50; j++) {
                t_nn.train();
            }

            outputs += t_nn.test();
        }

        System.out.println(outputs/amt);

    }

    public static void test1() {
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

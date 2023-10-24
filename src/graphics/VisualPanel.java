package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Arrays;

import NeuralNet.NeuralNet;
import NeuralNet.Node;

public class VisualPanel extends Panel {

    ArrayList<Ellipse2D> input;
    ArrayList<Ellipse2D> output;

    public VisualPanel(Dimension size) {
        super(size);
        setBackground(Color.black);

        input = new ArrayList<>();
        output = new ArrayList<>();

        input.add(new Ellipse2D.Double(100, 50, 100, 100));
        input.add(new Ellipse2D.Double(100, 187, 100, 100));
        input.add(new Ellipse2D.Double(100, 325, 100, 100));

        output.add(new Ellipse2D.Double(350, 115, 100, 100));
        output.add(new Ellipse2D.Double(350, 265, 100, 100));
    }

    public void update() {

    }
    
    public void paintComponent(Graphics g) {
        ArrayList<Node> nodes = NeuralNet.nodes;
        ArrayList<double[]> weights = new ArrayList<>();

        double min = nodes.get(0).getWeights()[0];
        double max = nodes.get(0).getWeights()[0];

        for (int i = 0; i < nodes.size(); i++) {
            weights.add(nodes.get(i).getWeights());

            double curMin = Arrays.stream(nodes.get(i).getWeights()).min().orElse(-1); 
            double curMax = Arrays.stream(nodes.get(i).getWeights()).max().orElse(-1); 

            if (curMin < min) {
                min = curMin;
            }

            if (curMax > max) {
                max = curMax;
            }

        }

        min = Math.abs(min);

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) (g);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        
        

        for (int i = 0; i < 2; i++) {

            for (int j = 0; j < 3; j++) {

                double x = (weights.get(i)[j] + min) / (max + min);
                System.out.println(x);

                g2d.setColor(new Color((int) (255 * (1-x)), (int) (255 * (x)), 0));

                System.out.println(weights.get(i)[j]);

                g2d.drawLine((int) (input.get(j).getCenterX()), 
                (int) (input.get(j).getCenterY()), 
                (int) (output.get(i).getCenterX()), 
                (int) (output.get(i).getCenterY()));
            }
            
        }

        g2d.setColor(Color.red);

        input.forEach(e -> g2d.fill(e));

        output.forEach(e -> g2d.fill(e));

        g2d.dispose();
    }

    public void drawNode() {

    }

}

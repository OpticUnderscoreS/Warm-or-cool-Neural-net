package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;

import NeuralNet.NeuralNet;

public class InputPanel extends Panel {

    public InputPanel() {
        super(new Dimension(700, 500));
    
        initPanel();
    }
    
    private void initPanel() {

        JButton warm;
        JButton cool;
        JButton unknown;

        warm = new JButton("Warm");
        warm.setBackground(Color.red);
        warm.setVisible(true);
        warm.setFocusable(false);
        warm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                InputPanel.this.buttonPress("warm");
            }
            
        });

        unknown = new JButton("Don't know");
        unknown.setBackground(Color.gray);
        unknown.setVisible(true);
        unknown.setFocusable(false);
        unknown.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                InputPanel.this.buttonPress("unknown");
            }
            
        });

        cool = new JButton("Cool");
        cool.setBackground(Color.CYAN);
        cool.setVisible(true);
        cool.setFocusable(false);
        cool.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                InputPanel.this.buttonPress("cool");
            }
            
        });

        add(warm);
        add(unknown);
        add(cool);
        
    }
    protected Color colour;
    protected Random randomGen = new Random();
    
    public void randomizeColour() {
        colour = new Color(
            randomGen.nextInt(0, 255), 
            randomGen.nextInt(0, 255), 
            randomGen.nextInt(0, 255)
        );

        try {
            GraphicsManager.stats.updatePrediction();
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
        setBackground(colour);
    }

    public double[] getColour() {
        return new double[] {colour.getRed(), colour.getGreen(), colour.getBlue()};
    }

    public void buttonPress(String id) {
        int y = -1;

        switch (id) {
            case "warm":
                y = 1;
                break;
            case "cool":
                y = 0;
                break;
            case "unknown":
                randomizeColour();
                GraphicsManager.stats.update();
                return;
        }

        NeuralNet.addData(new double[] {colour.getRed(), colour.getGreen(), colour.getBlue()}, y);

        NeuralNet.reset();

        GraphicsManager.visuals.repaint();
        
        randomizeColour();
        GraphicsManager.stats.update();

    }

}

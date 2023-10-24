package graphics;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

import NeuralNet.NeuralNet;

public class StatsPanel extends Panel {

    private JLabel accuracy;
    private JLabel numTotalSet;
    private JLabel numTestSet;
    private JLabel numTrainSet;

    public JToggleButton showPrediction;

    public JLabel prediction;

    public StatsPanel(Dimension size) {

        super(size);

        initPanel();


    }

    public void update() {

        
        accuracy.setText(String.format("Accuracy: %.2f %%", NeuralNet.test(500)));
        numTotalSet.setText(String.format("Number of samples provided: %s", NeuralNet.data.data.size()));
        numTrainSet.setText(String.format("Number of samples in training set: %s", NeuralNet.data.train.size()));
        numTestSet.setText(String.format("Number of samples in test set: %s", NeuralNet.data.test.size()));
        

        
    }

    public void updatePrediction() {
        String predictionText;
        if (NeuralNet.predict(GraphicsManager.input.getColour()) == 0) {
            predictionText = "Cool";
        } else {
            predictionText = "Warm";
        }

        prediction.setText(String.format("My Prediction For This Colour: %s", predictionText));
    }

    private void initPanel() {

        setLayout(new FlowLayout(FlowLayout.LEFT, 40, 10));

        accuracy = new JLabel("Accuracy: ");
        numTotalSet = new JLabel("Number of samples provided: ");
        numTestSet = new JLabel("Number of samples in test set: ");
        numTrainSet = new JLabel("Number of samples in training set: ");

        prediction = new JLabel("My Prediction For This Colour:");
        prediction.setVisible(false);

        showPrediction = new JToggleButton("Show Prediction");
        showPrediction.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                StatsPanel.this.prediction.setVisible(e.getStateChange() == ItemEvent.SELECTED);
            }
            
        });

        add(prediction, FlowLayout.LEFT);
        add(showPrediction, FlowLayout.LEFT);
        add(numTestSet, FlowLayout.LEFT);
        add(numTrainSet, FlowLayout.LEFT);
        add(numTotalSet, FlowLayout.LEFT);
        add(accuracy, FlowLayout.LEFT);

    }
    
}

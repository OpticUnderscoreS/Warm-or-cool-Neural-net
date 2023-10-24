package graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicsManager {
    
    protected static JFrame window;
    protected static InputPanel input;
    protected static VisualPanel visuals;
    protected static StatsPanel stats;

    public static void initGraphicsManager() {
        initStats();
        initInput();
        initVisuals();
        initWindow();
    }

    public static void initWindow() {

        window = new JFrame("Neural Net", null);
        window.setLayout(new BorderLayout(5, 5));

        window.add(stats, BorderLayout.LINE_START);
        window.add(input, BorderLayout.CENTER);
        window.add(visuals, BorderLayout.LINE_END);
        
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }

    public static void initInput() {

        input = new InputPanel();
        input.randomizeColour();
        
        input.setVisible(true);

    } 

    public static void initVisuals() {
        
        visuals = new VisualPanel(new Dimension(500, 400));

        visuals.setVisible(true);

        


    }

    public static void initStats() {

        stats = new StatsPanel(new Dimension(300, 500));

        stats.setVisible(true);

    }

}

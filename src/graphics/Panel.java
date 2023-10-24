package graphics;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.LayoutStyle;

public class Panel extends JPanel {

    public String data = "";
    
    public Panel(Dimension size) {
        super(new FlowLayout(FlowLayout.CENTER, 5, (int) (size.getHeight()/2)), true);
        setPreferredSize(size);
    }


    public void addData(String newData) {
        data += newData;
    }

}

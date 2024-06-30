package FrameDesign;

import javax.swing.*;

public class SetAttributes {
    public void set(JFrame frame){
        frame.setLayout(null);
        //setTitle("Registration Page");
        frame.setSize(350,350);
        frame.setLocation(300,150);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

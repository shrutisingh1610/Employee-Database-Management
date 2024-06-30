package FrameDesign;

import Database.EmployeeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Search extends JFrame implements ActionListener {
    JTextField searchBar;
    JPanel dataPanel;
    JButton back;
    ArrayList<JButton> resultEmployee;
    String table;
    Search(String t) {
        //Facilities.statusString = "";
        table = t;
        searchBar = new JTextField();
        dataPanel = new JPanel();
        back = new JButton("Prev");

        searchBar.setBounds(5, 5, 300, 30);
        dataPanel.setBounds(5, 40, 300, 200);
        back.setBounds(10, 250, 90, 30);
        resultEmployee = new ArrayList<>();

        dataPanel.setBackground(Color.WHITE);
        back.setBackground(Color.PINK);
        back.setOpaque(true);

        searchBar.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                resultEmployee.clear();
                dataPanel.removeAll();

                EmployeeData.searchAndSetData(table,resultEmployee, searchBar.getText());

                displaySearchResult();
                repaint();
            }
        });

        back.addActionListener(e -> {
            //Facilities.statusString = "";
            setVisible(false);
            new Facilities(table);
        });

        add(back);
        add(searchBar);
        add(dataPanel);

        new SetAttributes().set(this);
    }
    private void displaySearchResult(){
        int y=0;
        for(JButton label:resultEmployee){
            label.setBounds(0,y,300,20);
            dataPanel.add(label);
            y+=20;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

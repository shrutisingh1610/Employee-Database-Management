package FrameDesign;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Update extends JFrame implements ActionListener {

    JLabel message,email;
    JTextField email_field;
    JButton prev,update;
    String table;
    Update(String t){
        table = t;

        SetAttributes s1 = new SetAttributes();
        s1.set(this);
        setTitle("Update");
        getContentPane().setBackground(Color.white);

        message = new JLabel("This email id does not exists.");

        email = new JLabel("Emial");
        email.setBounds(20,30,50,20);

        email_field = new JTextField();
        email_field.setBounds(70,30,200,20);

        update = new JButton("Update");
        update.setBounds(220,260,80,30);
        update.setBackground(new Color(255,182,193));
        update.addActionListener(this);

        prev = new JButton("Prev");
        prev.setBounds(20,260,80,30);
        prev.setBackground(new Color(255,182,193));
        prev.addActionListener(this);


        add(message);
        add(email);
        add(email_field);
        add(update);
        add(prev);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == prev){
            new Facilities(table);
        }
        else{
            String s = email_field.getText();
            if(s.isEmpty()) {
                remove(message);
                message.setBounds(10, 50, 200, 20);
                add(message);
                repaint();
            }
            else {
                new UpdateInfo(email_field.getText(),table);
            }
        }
        setVisible(false);
    }
}

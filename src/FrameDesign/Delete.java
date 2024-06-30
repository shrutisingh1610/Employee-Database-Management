package FrameDesign;

import Database.AdminData;
import Database.EmployeeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Delete extends JFrame implements ActionListener {
    String table;
    JLabel email,message;
    JTextField email_field;
    JButton delete,prev,delete_all;
    Delete(String t){
        SetAttributes s1 = new SetAttributes();
        s1.set(this);
        setTitle("Delete Page");
        getContentPane().setBackground(Color.white);
        table = t;

        message = new JLabel("This email id does not exists.");

        delete_all = new JButton("Delete all employees");
        delete_all.setBounds(75,150,170,30);
        delete_all.addActionListener(this);
        delete_all.setBackground(new Color(255,182,193));

        email = new JLabel("Emial");
        email.setBounds(20,30,50,20);

        email_field = new JTextField();
        email_field.setBounds(70,30,200,20);

        delete = new JButton("Delete");
        delete.setBounds(220,260,80,30);
        delete.setBackground(new Color(255,182,193));
        delete.addActionListener(this);

        prev = new JButton("Prev");
        prev.setBounds(20,260,80,30);
        prev.setBackground(new Color(255,182,193));
        prev.addActionListener(this);

        add(message);
        add(email);
        add(email_field);
        add(delete);
        add(prev);
        add(delete_all);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EmployeeData.init();
        AdminData.init();
        if(e.getSource() == delete_all){
            EmployeeData.drop_table(table);
            AdminData.create_table(table);
            new Facilities(table);
        }
        else if(e.getSource() == delete){
            String s = email_field.getText();
            if(s.isEmpty()) {
                remove(message);
                message.setBounds(20, 90, 200, 30);
                add(message);
                repaint();
            }
            else {
                boolean isPresent = EmployeeData.delete(email_field.getText(), table);
                if (!isPresent) {
                    repaint();
                    message.setBounds(20, 90, 200, 30);
                    repaint();
                } else {
                    new Facilities(table);
                    setVisible(false);
                }
            }
        }
        else{
            new Facilities(table);
            setVisible(false);
        }

    }
}

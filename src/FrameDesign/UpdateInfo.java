package FrameDesign;

import Database.EmployeeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateInfo extends JFrame implements ActionListener {
    JLabel name,age,salary,email,id,phoneNo,message;
    JTextField name_field,age_field,salary_field,email_field,id_field,phoneNo_field;
    JButton add_emp,prev;
    String table,em;
    UpdateInfo(String emailIDtoBeUpdated,String t){
        table = t;
        em = emailIDtoBeUpdated;

        SetAttributes s1 = new SetAttributes();
        s1.set(this);
        setTitle("Update");
        getContentPane().setBackground(Color.white);

        prev = new JButton("Prev");
        prev.addActionListener(this);
        prev.setBounds(10,270,100,30);
        prev.setBackground(new Color(255,182,193));

        name = new JLabel("Name: ");
        name.setBounds(10,15,50,20);

        name_field = new JTextField();
        name_field.setBounds(80,15,200,20);

        age = new JLabel("Age: ");
        age.setBounds(10,45,50,20);

        age_field = new JTextField();
        age_field.setBounds(80,45,200,20);

        salary = new JLabel("Salary: ");
        salary.setBounds(10,70,50,20);

        salary_field = new JTextField();
        salary_field.setBounds(80,70,200,20);

        id = new JLabel("ID: ");
        id.setBounds(10,100,50,20);

        id_field = new JTextField();
        id_field.setBounds(80,100,200,20);

        email = new JLabel("Email: ");
        email.setBounds(10,130,50,20);

        email_field = new JTextField();
        email_field.setBounds(80,130,200,20);

        phoneNo = new JLabel("PhoneNo. :");
        phoneNo.setBounds(10,160,70,20);

        phoneNo_field = new JTextField();
        phoneNo_field.setBounds(80,160,200,20);

        add_emp = new JButton("Update");
        add_emp.setBounds(220,270,100,30);
        add_emp.setBackground(new Color(255,182,193));
        add_emp.addActionListener(this);

        message = new JLabel("No record found");

        add(name);
        add(name_field);

        add(age);
        add(age_field);

        add(salary);
        add(salary_field);

        add(id);
        add(id_field);

        add(email);
        add(email_field);

        add(phoneNo);
        add(phoneNo_field);

        add(add_emp);
        add(message);
        add(prev);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == add_emp){
            EmployeeData.init();
            boolean email_exists = EmployeeData.update_record(em,table,email_field.getText(),name_field.getText(),Integer.parseInt(age_field.getText()),Long.parseLong(salary_field.getText()),Integer.parseInt(id_field.getText()),Long.parseLong(phoneNo_field.getText()));
            if(!email_exists){
                message.setBounds(10,190,200,20);
                add(message);
                repaint();
            }
            else{
                new Update(table);
                setVisible(false);
            }

        }
    }
}

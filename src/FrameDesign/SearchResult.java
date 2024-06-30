package FrameDesign;

import Database.EmployeeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchResult extends JFrame implements ActionListener {
    JLabel name,age,imageView,salary,email,id,phoneNo,message;
    JTextField name_field,age_field,salary_field,email_field,id_field,phoneNo_field;
    JButton add_emp,selectImage,prev;
    //JPanel imagePanel;
    String table;
    JPanel imagePanel;
    public SearchResult(String t,String resultEmail){
        table = t;
        //System.out.println(resultEmail);
        String[] data = EmployeeData.searchByEmail(resultEmail,table);
        SetAttributes s1 = new SetAttributes();
        s1.set(this);
        setTitle("Insert");
        getContentPane().setBackground(Color.white);

        prev = new JButton("Prev");
        prev.setBackground(new Color(255,182,193));
        prev.addActionListener(this);
        prev.setBounds(120,270,80,30);

        name = new JLabel("Name: ");
        name.setBounds(10,15,50,20);

        name_field = new JTextField(data[2]);
        name_field.setBounds(80,15,200,20);
        name_field.setEditable(false);

        age = new JLabel("Age: ");
        age.setBounds(10,45,50,20);

        age_field = new JTextField(data[5]);
        age_field.setBounds(80,45,200,20);
        age_field.setEditable(false);

        salary = new JLabel("Salary: ");
        salary.setBounds(10,70,50,20);

        salary_field = new JTextField(data[4]);
        salary_field.setBounds(80,70,200,20);
        salary_field.setEditable(false);

        id = new JLabel("ID: ");
        id.setBounds(10,100,50,20);

        id_field = new JTextField(data[0]);
        id_field.setBounds(80,100,200,20);
        id_field.setEditable(false);

        email = new JLabel("Email: ");
        email.setBounds(10,130,50,20);

        email_field = new JTextField(data[1]);
        email_field.setBounds(80,130,200,20);
        email_field.setEditable(false);

        phoneNo = new JLabel("PhoneNo. :");
        phoneNo.setBounds(10,160,70,20);

        phoneNo_field = new JTextField(data[3]);
        phoneNo_field.setBounds(80,160,200,20);
        phoneNo_field.setEditable(false);

        add(email_field);
        add(phoneNo_field);
        add(id_field);
        add(salary_field);
        add(age_field);
        add(name_field);
        add(prev);

        add(email);
        add(phoneNo);
        add(id);
        add(salary);
        add(age);
        add(name);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == prev){
            new Search(table);
        }
    }
}

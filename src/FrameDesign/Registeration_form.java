package FrameDesign;

import Database.AdminData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registeration_form extends JFrame implements ActionListener {

    JLabel EmailID,Password,duplicateEmail;
    JButton prev,registerDone;
    JTextField Email;

    JPasswordField password;
    JCheckBox showPassword;
    Registeration_form(){
        SetAttributes s1 = new SetAttributes();
        s1.set(this);
        setTitle("Registration Page");

        getContentPane().setBackground(Color.white);

        duplicateEmail = new JLabel("This username already exists! Try Again.");
        EmailID = new JLabel("Email : ");
        Password = new JLabel("Password : ");
        EmailID.setBounds(20,40,50,15);
        Password.setBounds(20,70,70,15);
        Email = new JTextField();
        password = new JPasswordField();
        Email.setBounds(100,40,200,20);
        password.setBounds(100,70,200,20);

        showPassword = new JCheckBox("Show Password");
        showPassword.setBounds(100,100,120,20);
        showPassword.addActionListener(e -> {
            JCheckBox c = (JCheckBox) e.getSource();
            password.setEchoChar(c.isSelected() ? '\u0000' : (Character) UIManager.get("PasswordField.echoChar"));
        });

        prev = new JButton("PREV");
        prev.setBounds(30,260,90,30);
        prev.setBackground(new Color(255,182,193)); // To change background of button
        prev.addActionListener(this);

        registerDone = new JButton("REGISTER");
        registerDone.setBounds(220,260,100,30);
        registerDone.setBackground(new Color(255,182,193)); // To change background of button
        registerDone.addActionListener(this);

        add(duplicateEmail);
        add(EmailID);
        add(Password);
        add(Email);
        add(password);
        add(showPassword);

        add(prev);
        add(registerDone);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == prev){
            new Login_or_Register(" ");
            setVisible(false);
        }
        else if(e.getSource() == registerDone){
            AdminData.init();
            String s = String.valueOf(password.getPassword());
            //String e = Email.getText();
            if(s.equals("")) {
                remove(duplicateEmail);
                duplicateEmail.setText("Enter valid Password!");
                duplicateEmail.setBounds(10,12,300,15);
                add(duplicateEmail);
                repaint();
            }
            else {
                boolean isInserted = AdminData.addAdmin(Email.getText(), new String(password.getPassword()));
                if (!isInserted) {
                    remove(duplicateEmail);
                    duplicateEmail.setText("This username already exists! Try Again.");
                    duplicateEmail.setBounds(10, 12, 300, 15);
                    add(duplicateEmail);
                    repaint();
                } else {
                    String em = Email.getText();
                    char em1[] = em.toCharArray();
                    StringBuilder str1 = new StringBuilder();
                    for(int i=0;i<em1.length;i++){
                        if(em1[i] == '@')
                            break;
                        str1.append(em1[i]);
                    }
                    String table_name = AdminData.getAsciiValue(String.valueOf(str1));
                    //System.out.println(table_name);
                    AdminData.create_table(table_name);
                    remove(duplicateEmail);
                    new Login_or_Register("Registration Successful!");
                    setVisible(false);
                    repaint();
                }
            }
            AdminData.close();
        }
    }
}

package FrameDesign;

import Database.AdminData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login_form extends JFrame implements ActionListener {
    JLabel EmailID,Password,duplicateEmail;
    JButton prev,LoginDone;
    JTextField Email;

    JPasswordField password;
    JCheckBox showPassword;
    Login_form(){
        SetAttributes s1 = new SetAttributes();
        s1.set(this);
        setTitle("Login Page");

        getContentPane().setBackground(Color.white);

        duplicateEmail = new JLabel();
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

        LoginDone = new JButton("LOGIN");
        LoginDone.setBounds(220,260,100,30);
        LoginDone.setBackground(new Color(255,182,193)); // To change background of button
        LoginDone.addActionListener(this);

        add(duplicateEmail);
        add(EmailID);
        add(Password);
        add(Email);
        add(password);
        add(showPassword);

        add(prev);
        add(LoginDone);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == LoginDone){
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
                boolean correct = AdminData.checkID(Email.getText(), new String(password.getPassword()));
                if(correct){
                    String em = Email.getText();
                    char em1[] = em.toCharArray();
                    StringBuilder str1 = new StringBuilder();
                    for(int i=0;i<em1.length;i++){
                        if(em1[i] == '@')
                            break;
                        str1.append(em1[i]);
                    }
                    String table_name = AdminData.getAsciiValue(String.valueOf(str1));
                    new Facilities(table_name);
                    setVisible(false);
                }
                else{
                    remove(duplicateEmail);
                    duplicateEmail.setText("Incorrect Information!");
                    duplicateEmail.setBounds(10,12,300,15);
                    add(duplicateEmail);
                    repaint();
                }
            }

        }
        else if(e.getSource() == prev){
            new Login_or_Register("");
        }
        setVisible(false);
    }
}

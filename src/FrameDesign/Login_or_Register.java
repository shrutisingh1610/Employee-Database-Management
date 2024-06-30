package FrameDesign;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Login_or_Register extends JFrame implements ActionListener{
    JButton Login, Register;
    JLabel str;
    Login_or_Register(String s) {
        str =  new JLabel("Registeration Successful!!");
        if(s.equals("Registration Successful!")){
            str.setBounds(100,250,200,30);
        }
        Login = new JButton("LOGIN");
        Register = new JButton("REGISTER");
        Login.setBounds(123,120,100,30);
        Login.addActionListener(this);
        Register.setBounds(123,160,100,30);
        Register.addActionListener(this);

        getContentPane().setBackground(Color.white); // To change background of JFrame


        Login.setBackground(new Color(255,182,193)); // To change background of button
        Login.setForeground(Color.black); // To change text color on button
        Login.setOpaque(true);

        Register.setBackground(new Color(255,182,193)); // To change background of button
        Register.setForeground(Color.black); // To change text color on button
        Register.setOpaque(true);


        add(Login);
        add(Register);

        add(str);

        setLayout(null);
        setTitle("Login/Register");
        setSize(350,350);
        setLocation(300,150);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Login){
            new Login_form();
        }
        else{
            new Registeration_form();
        }
        setVisible(false);
    }
}

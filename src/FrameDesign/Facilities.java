package FrameDesign;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Facilities extends JFrame implements ActionListener {
    JButton insert,delete,update,edit_admin_profile,logout,search;
    String table;
    Facilities(String t){
        table = t;
        SetAttributes s1 = new SetAttributes();
        s1.set(this);
        setTitle("Facilities");
        getContentPane().setBackground(Color.white);

        insert = new JButton("Add Employee");
        insert.setBounds(110,75,120,30);
        insert.setBackground(new Color(255,182,193)); // To change background of button
        insert.addActionListener(this);

        delete = new JButton("Delete Record");
        delete.setBounds(110,120,120,30);
        delete.setBackground(new Color(255,182,193)); // To change background of button
        delete.addActionListener(this);

        update = new JButton("Update");
        update.setBounds(110,167,120,30);
        update.setBackground(new Color(255,182,193)); // To change background of button
        update.addActionListener(this);

        edit_admin_profile = new JButton("Edit Profile");
        edit_admin_profile.setBounds(110,30,120,30);
        edit_admin_profile.setBackground(new Color(255,182,193)); // To change background of button
        edit_admin_profile.addActionListener(this);

        search = new JButton("Search");
        search.setBounds(110,213,120,30);
        search.setBackground(new Color(255,182,193)); // To change background of button
        search.addActionListener(this);

        logout = new JButton("LOGOUT");
        logout.setBounds(110,257,120,30);
        logout.setBackground(new Color(255,182,193));
        logout.addActionListener(this);

        add(insert);
        add(delete);
        add(update);
        add(edit_admin_profile);
        add(search);
        add(logout);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == insert){
            new Insert(table);
        }
        else if(e.getSource() == delete){
            new Delete(table);
        }
        else if(e.getSource() == update){
            new Update(table);
        }
        else if(e.getSource() == search){
            new Search(table);
        }
        else{
            new Login_or_Register("");
        }
        setVisible(false);
    }
}

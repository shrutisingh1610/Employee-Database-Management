package FrameDesign;

import Database.EmployeeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import javax.swing.filechooser.FileFilter;
import java.io.File;


public class Insert extends JFrame implements ActionListener {

    JLabel name,age,imageView,salary,email,id,phoneNo,message;
    JTextField name_field,age_field,salary_field,email_field,id_field,phoneNo_field;
    JButton add_emp,selectImage,prev;
    JPanel imagePanel;
    String table;
    Insert(String t){
        table = t;
        SetAttributes s1 = new SetAttributes();
        s1.set(this);
        setTitle("Insert");
        getContentPane().setBackground(Color.white);

        prev = new JButton("Prev");
        prev.setBackground(new Color(255,182,193));
        prev.addActionListener(this);
        prev.setBounds(120,270,80,30);

        selectImage = new JButton("Select");
        imagePanel = new JPanel();

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

        imageView = new JLabel();
        imagePanel.setBounds(10,200,100,100);
        selectImage.setBounds(120,200,100,20);

        message = new JLabel("This employee record already exists...");


        selectImage.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.addChoosableFileFilter(new ImageFilter());
            fileChooser.setAcceptAllFileFilterUsed(false);

            int option = fileChooser.showOpenDialog(this);
            if(option == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                // imageView=new JLabel(new ImageIcon(file.getPath()));
                imageView.setIcon(new ImageIcon(file.getPath()));
                repaint();
            }
        });
        imagePanel.add(imageView);

        add(message);
        add(selectImage);
        add(imagePanel);
        add(selectImage);
        add(prev);

        add_emp = new JButton("Submit");
        add_emp.setBounds(220,270,100,30);
        add_emp.setBackground(new Color(255,182,193));
        add_emp.addActionListener(this);

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

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EmployeeData.init();
        if(e.getSource() == add_emp){
            boolean inserted = EmployeeData.insert_employee(table,email_field.getText(),name_field.getText(),Integer.parseInt(age_field.getText()),Long.parseLong(salary_field.getText()),Integer.parseInt(id_field.getText()),Long.parseLong(phoneNo_field.getText()));
            if(!inserted)
                message.setBounds(10,10,200,10);
            else{
                new Facilities(table);
                setVisible(false);
            }
        }
        else{
            new Facilities(table);
        }
    }
}
class ImageFilter extends FileFilter {
    public final static String JPEG = "jpeg";
    public final static String JPG = "jpg";
    public final static String GIF = "gif";
    public final static String TIFF = "tiff";
    public final static String TIF = "tif";
    public final static String PNG = "png";

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = getExtension(f);
        if (extension != null) {
            if (extension.equals(TIFF) ||
                    extension.equals(TIF) ||
                    extension.equals(GIF) ||
                    extension.equals(JPEG) ||
                    extension.equals(JPG) ||
                    extension.equals(PNG)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Image Only";
    }

    String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}

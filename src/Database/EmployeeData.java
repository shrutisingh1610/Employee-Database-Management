package Database;

import FrameDesign.SearchResult;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class EmployeeData {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    private static String email_id = "email_id";
    private static String id = "id";

    public static void init(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/employee_record", "root", "0710");
            statement = connection.createStatement();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static boolean insert_employee(String table, String email, String name, int age, long salary, int id, long phoneNo) {
        String query = "INSERT INTO "+ table + " VALUES('" + name + "' , " + id +",'"+email+"',"+age+","+salary+","+phoneNo+");";
        try {
            resultSet = statement.executeQuery("SELECT * FROM " + table +" WHERE  id = "+id+";");
            if(resultSet.next()){
                // This condition is to check duplicate email in database
                return false;
            }
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return true;
    }

    public static boolean delete(String email,String table) {
        try {
            resultSet = statement.executeQuery("SELECT * FROM " + table + " WHERE email_id  = '" + email + "';");
            if (resultSet.next()) {
                statement.executeUpdate("DELETE FROM " + table + " WHERE email_id = '" + resultSet.getString(email_id) + "';");
                return true;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;

    }

    public static boolean update_record(String em,String table, String email, String name, int age, long salary, int id, long phoneNo) {
        try {
            resultSet = statement.executeQuery("SELECT * FROM " + table +" WHERE email_id = '"+em+"';");
            if(resultSet.next()){
                // This condition is to check duplicate email in database
                statement.executeUpdate("UPDATE "+ table + " SET name = '"+name+"', id = '"+id+"',email_id = '"+email+"',age = "+age+",salary = "+salary+",phoneNo = "+phoneNo+" WHERE id = "+resultSet.getString("id")+";");
                return true;
            }
            else return false;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static void searchAndSetData(String table, ArrayList<JButton> resultEmployee, String toSearch) {
        String query = "SELECT * FROM " + table + " WHERE email_id LIKE '" + toSearch + "%'";
        init();
        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                JButton button = new JButton(resultSet.getString("email_id"));

                button.addActionListener(e -> {
                    new SearchResult(table,button.getText());
                });

                resultEmployee.add(button);
            }
        } catch (Exception e){
            e.fillInStackTrace();
        }
    }

    public static String[] searchByEmail(String email,String table){
        String query = "SELECT * FROM "+table+" WHERE email_id = '"+email+"';";
        init();
        String[] res=new String[6];
        try {
            resultSet = statement.executeQuery(query);
            resultSet.next();
            res[0] = resultSet.getString("id");
            res[1] = resultSet.getString("email_id");
            res[2] = resultSet.getString("name");
            res[3] = resultSet.getString("phoneNo");
            res[4] = resultSet.getString("salary");
            res[5] = resultSet.getString("age");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    public static void drop_table(String t) {
        try{
            statement.executeUpdate("DROP TABLE "+ t +";");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

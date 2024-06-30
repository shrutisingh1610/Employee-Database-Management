package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminData {
    private final static String TABLE_NAME = "admin_record";
    private final static String EMAIL_COLUMN = "admin_email";
    private final static String PASSWORD_COLUMN = "admin_password";

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void init() {
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost/employee_record", "root", "0710");
            statement = connection.createStatement();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static boolean addAdmin(String email,String Password){
        Password = HashPassword.getSHA256(Password);
        String query = "INSERT INTO "+ TABLE_NAME + " VALUES( '" + email + "' , '" + Password +"' );";
        try {
            resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+EMAIL_COLUMN +" = '"+email+"';");
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

    public static boolean checkID(String email,String password){
        password = HashPassword.getSHA256(password);
        try {
            resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + EMAIL_COLUMN + " = '" + email + "';");
            if(resultSet.next()){
                return resultSet.getString(PASSWORD_COLUMN).equals(password);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static void create_table(String email){
        try {
            statement.executeUpdate("CREATE TABLE "+email+"(name varchar(100),id INT PRIMARY KEY,email_id varchar(50),age INT,salary LONG,phoneNo LONG);");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void close(){
        try{
            connection.close();
            statement.close();
            resultSet.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static String getAsciiValue(String email) {
        char str[] = email.toCharArray();
        int n = str.length;
        StringBuilder res = new StringBuilder();
        for(int i=0;i<n;i++){
            if(str[i]>='A' && str[i]<='Z')
                res.append(str[i]);
            else if(str[i]>='a' && str[i]<='z'){
                res.append(str[i]);
            }
            else if(str[i]>='0' && str[i]<='9'){
                res.append(str[i]);
            }
            else{
                res.append((int)str[i]);
            }
        }
        return String.valueOf(res);
    }
}

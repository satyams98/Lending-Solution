import java.io.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        String url = "jdbc:oracle:thin:@//localhost:1521/XE";
        String user = "c##_saty";
        String pass = "password";

        Class.forName("oracle.jdbc.driver.OracleDriver");

        Connection dbConn = DriverManager.getConnection(url, user, pass);
        Employee employee = new Employee(42, "AA", 9);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(employee);
        byte[] employeeAsBytes = baos.toByteArray();
        PreparedStatement pstmt = dbConn
                .prepareStatement("INSERT INTO EMPLOYEE (emp) VALUES(?)");
        ByteArrayInputStream bais = new ByteArrayInputStream(employeeAsBytes);
        pstmt.setBinaryStream(1, bais, employeeAsBytes.length);
        pstmt.executeUpdate();
        pstmt.close();
        Statement stmt = dbConn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT emp FROM Employee");
        rs.next();
            InputStream st = rs.getBlob(1).getBinaryStream();
            ObjectInputStream ois = new ObjectInputStream(st);
            Employee emp11 = (Employee) ois.readObject();
        System.out.println(emp11.name);
        stmt.close();
        rs.close();
        dbConn.close();
    }
}

class Employee implements Serializable {
    int ID;
    String name;
    double salary;

    public Employee(int ID, String name, double salary) {
        this.ID = ID;
        this.name = name;
        this.salary = salary;
    }
}

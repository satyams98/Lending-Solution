package JDBC;

import Bank.LoanProduct;
import Customer.*;
import oracle.jdbc.proxy.annotation.Pre;
import oracle.sql.BLOB;

import java.io.*;
import java.net.ConnectException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DAO {
    public String getLoanStatus(Connection connection, String loanAgreementId) throws SQLException, IOException, ClassNotFoundException {
        LoanAgreement l = (LoanAgreement) this.getLoanApplicationObject(connection, loanAgreementId);
        return l.loanStatus;
    }
    public List<String> getAllActiveLoan(Connection connection) throws SQLException, IOException, ClassNotFoundException {
        String selectQuery = "Select object from loanAgreementDtl";
        PreparedStatement stmt = connection.prepareStatement(selectQuery);
        ResultSet rs = stmt.executeQuery();
        List<String> dtl = new ArrayList<String>();

        while(rs.next()){
            InputStream st = rs.getBlob(1).getBinaryStream();
            ObjectInputStream ois = new ObjectInputStream(st);
            LoanAgreement lObj = (LoanAgreement)ois.readObject();

            if ((lObj.loanStatus).equalsIgnoreCase("Active")){
                String temp = lObj.loanAgreementId+" "+ lObj.loanStatus+" "
                        + lObj.loanAmount+" "+ lObj.tenure+" "+ lObj.roi;

                dtl.add(temp);
            }
        }
        rs.close();
        stmt.close();
        return dtl;
    }

    public String getLoanDetail(Connection connection, String loanAgreementId) throws SQLException, IOException, ClassNotFoundException {
        LoanAgreement lObj = (LoanAgreement) getLoanApplicationObject(connection, loanAgreementId);

        if (lObj!=null){
            return lObj.loanAgreementId+" "+ lObj.loanStatus+" "
                    + lObj.loanAmount+" "+ lObj.tenure+" "+ lObj.roi;
        }
        return "";
    }

    public List<String> getAllLoanByACustomer(Connection connection, String customerId) throws SQLException, IOException, ClassNotFoundException {
        Customer customer = (Customer) getCustomerObject(connection, customerId);
        List<String> dtl = new ArrayList<String>();
        if (customer!=null){
            for (LoanAgreement l:
                 customer.loanAgreements) {
                String temp = l.loanAgreementId+" "+ l.loanStatus+" "
                        + l.loanAmount+" "+ l.tenure+" "+ l.roi;
                dtl.add(temp);
            }
        }
        return dtl;
    }

    public Object getLoanApplicationObject(Connection connection, String loanAgreementId) throws SQLException, IOException, ClassNotFoundException{
        String selectQuery = "SELECT object from loanAgreementDtl where loanAgreementId = ?";

        PreparedStatement stmt = connection.prepareStatement(selectQuery);
        stmt.setString(1, loanAgreementId);
        ResultSet rs = stmt.executeQuery();
        rs.next();

        InputStream st = rs.getBlob(1).getBinaryStream();
        ObjectInputStream ois = new ObjectInputStream(st);
        Object obj = ois.readObject();
        stmt.close();
        rs.close();
        return obj;

    }

    public Object getCustomerObject(Connection connection, String customerId) throws SQLException, IOException, ClassNotFoundException {
        String selectQuery = "SELECT object from Customer where customerId = ?";

        PreparedStatement stmt = connection.prepareStatement(selectQuery);
        stmt.setString(1, customerId);
        ResultSet rs = stmt.executeQuery();
        rs.next();

        InputStream st = rs.getBlob(1).getBinaryStream();
        ObjectInputStream ois = new ObjectInputStream(st);
        Object obj = ois.readObject();
        stmt.close();
        rs.close();
        return obj;

    }
    public boolean insertCustomerObject( Connection connection, Customer customer) throws SQLException, IOException {
        String insertCustomer = "insert into customer values(?,?,?)";
        CallableStatement stmt = connection.prepareCall(insertCustomer);
        String customerId =String.valueOf(( customer).customerId);
        stmt.setString(1, customerId);
        stmt.setString(2, customer.getClass().getSimpleName());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(customer);

        byte[] customerAsBytes = baos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(customerAsBytes);
        stmt.setBinaryStream(3, bais, customerAsBytes.length);
        stmt.executeUpdate();

        stmt.close();
        return true;
    }
    public boolean insertLoanApplicationObject(Connection connection, LoanAgreement loanAgreement) throws SQLException, IOException {
        String insertLoanAgreement = "insert into loanAgreementDtl values(?, ?, ?)";
        CallableStatement stmt = connection.prepareCall(insertLoanAgreement);
        String loanAgreementId = loanAgreement.loanAgreementId;

        stmt.setString(1, loanAgreementId);
        stmt.setString(2, loanAgreement.getClass().getSimpleName());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(loanAgreement);

        byte[] loanAgreementAsByte = baos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(loanAgreementAsByte);
        stmt.setBinaryStream(3, bais, loanAgreementAsByte.length);
        stmt.executeUpdate();
        stmt.close();
        return true;

    }



    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        String url = "jdbc:oracle:thin:@//localhost:1521/XE";
        String user = "c##_saty";
        String pass = "password";

        Class.forName("oracle.jdbc.driver.OracleDriver");

        Connection connection = DriverManager.getConnection(url, user, pass);

        String createCustomer = "create table Customer(customerId varchar2(12) primary key, " +
                "objectName varchar(20), object blob)";

        String createLoanAgreement = "create table loanAgreementDtl(loanAgreementId varchar2(20) primary key," +
                "ObjectName varchar2(20), object blob )";



        Customer c1 = new Customer();
        Customer c2 = new Customer();

        c1.customerId = 101;
        c1.customerName = "Satyam Singh";
        c1.dateOfBirth = LocalDate.of(2000, 9, 07);
        c1.companyNames = "Nucleus";
        c1.contactNumber = "875167125";
        c1.monthlyIncome = 400000;

        c2.customerId = 102;
        c2.customerName = "Aditya";
        c2.dateOfBirth = LocalDate.of(1999, 9, 1);
        c2.monthlyIncome = 600000;
        PreparedStatement stmt = connection.prepareStatement(createCustomer);
        int i = stmt.executeUpdate();
        System.out.println("created: "+i);


        LoanProduct l1 = new LoanProduct();
        l1.loanProductCode = "s101";
        l1.status = "Pending";
        l1.emiStatus = "Pending";
        l1.maxLoanAmount = 10;


        LoanProduct l2 = new LoanProduct();
        l2.loanProductCode = "s201";
        l2.emiStatus = "Cleared";
        l2.status = "Active";
        l2.maxLoanAmount = 80;

        LoanProduct l3 = new LoanProduct();
        l3.loanProductCode = "s203";
        l3.emiStatus = "Cleared";
        l3.status = "Active";
        l3.maxLoanAmount = 50;

        LoanAgreement la1 = new LoanAgreement();
        la1.loanAgreementId = String.valueOf(la1.hashCode());
        la1.loanProduct = l1;
        la1.loanAmount = l1.maxLoanAmount;
        la1.loanDisbursalDate = LocalDate.of(2015, 9, 15);
        la1.emiDueDate=LocalDate.of(2022,2,15);
        la1.loanStatus ="Active";

        LoanAgreement la2 = new LoanAgreement();
        la2.loanProduct = l2;
        la2.loanAmount = l2.maxLoanAmount;
        la2.loanDisbursalDate = LocalDate.of(2017, 8, 15);
        la2.emiDueDate=LocalDate.of(2022,3,1);
        la2.loanStatus ="Closed";

        LoanAgreement la3 = new LoanAgreement();
        la3.loanProduct = l3;
        la3.loanAmount = l3.maxLoanAmount;
        la3.loanDisbursalDate = LocalDate.of(2012, 1, 15);
        la3.emiDueDate = LocalDate.of(2022,2,15);
        la3.loanStatus ="Closed";

        c1.loanAgreements.add(la1);

        DAO d = new DAO();

        PreparedStatement statement = connection.prepareStatement(createLoanAgreement);
        statement.executeUpdate();

        d.insertCustomerObject(connection, c1);
        Customer c = (Customer) d.getCustomerObject(connection, String.valueOf(c1.customerId));
        System.out.println(la1.loanAgreementId);
        System.out.println(d.insertLoanApplicationObject(connection, la1));
        LoanAgreement la = (LoanAgreement) d.getLoanApplicationObject(connection, la1.loanAgreementId);
        System.out.println(la.loanAgreementId);
        System.out.println(c.customerName);
        System.out.println(c.loanAgreements);
        System.out.println(d.getLoanStatus(connection, la.loanAgreementId));
        System.out.println(d.getAllActiveLoan(connection));
        System.out.println(d.getAllLoanByACustomer(connection, String.valueOf(c1.customerId)));
        System.out.println(d.getLoanDetail(connection, la.loanAgreementId));
        connection.close();
    }
}

package com.contactbook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactsDBService {

    /*This method used to Connect Database and return Connection instance
    * @param takes host name, Database name, Username, and Password
    * @return connection
     */

    private Connection getConnection(String host, String DBname, String userName, String password) throws SQLException, SQLException {
        String jdbcURL = "jdbc:mysql://"+host+":3306/"+DBname+"?use_SSL=false";
        String driver = "com.mysql.jdbc.Driver";
        Connection connection = null;
        try{connection= DriverManager.getConnection(jdbcURL,userName,password);}
        catch(SQLException e){
            System.out.println("Issue with connection");
            connection=null;
        }
        return  connection;
    }

    /*This Method used to read the data from Database using query
    which uses Connection method to create a connection to Database after retrieving the data from
    creates instance of contact for every data retrieved and stores in  contactlist
    @returns list of contacts data
     */
    public List<Contact> readData() throws SQLException {
        String sql="Select firstname,lastname , city, state, zip, PhoneNumber , email from contact";
        List<Contact> contactlist=new ArrayList<>();
        try(Connection connection = this.getConnection("localhost","contact_book",
                "root","1234");) {
            Statement statement = connection.createStatement();
            ResultSet resultSet= statement.executeQuery(sql);
            while (resultSet.next()){
                String firstName=resultSet.getString("firstname");
                String lastName=resultSet.getString("lastname");
                String city=resultSet.getString("city");
                String state=resultSet.getString("state");
                int zip= Integer.parseInt(resultSet.getString("zip"));
                int phoneNumer= Integer.parseInt(resultSet.getString("PhoneNumber"));
                String email=resultSet.getString("email");
                contactlist.add(new Contact(firstName,lastName, city, state,zip, phoneNumer, email));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return contactlist;
    }
}

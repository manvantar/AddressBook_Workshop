package com.contactbook;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContactsDBService {

    /*This method used to Connect Database and return Connection instance
     * @param takes host name, Database name, Username, and Password
     * @return connection
     */

    private Connection getConnection(String host, String DBname, String userName, String password) throws SQLException, SQLException {
        String jdbcURL = "jdbc:mysql://" + host + ":3306/" + DBname + "?use_SSL=false";
        String driver = "com.mysql.jdbc.Driver";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, userName, password);
        } catch (SQLException e) {
            System.out.println("Issue with connection");
            connection = null;
        }
        return connection;
    }

    /*This Method used to read the data from Database using query
    which uses Connection method to create a connection to Database after retrieving the data from
    creates instance of contact for every data retrieved and stores in  contactlist
    * @Param it takes query
    * @returns list of contacts data
     */
    public List<Contact> readData(String query) throws SQLException {
        String sql = null;
        if (query == null) {
            sql = "Select firstname,lastname , city, state, zip, PhoneNumber , email from contact";
        } else {
            sql = query;
        }
        List<Contact> contactlist = new ArrayList<>();
        try (Connection connection = this.getConnection("localhost", "contact_book",
                "root", "1234");) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                int zip = Integer.parseInt(resultSet.getString("zip"));
                long phoneNumer = Integer.parseInt(resultSet.getString("PhoneNumber"));
                String email = resultSet.getString("email");
                contactlist.add(new Contact(firstName, lastName, city, state, zip, phoneNumer, email));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return contactlist;
    }

    /*This method used to update the phoneNumber
    @ param takes parameters firstName and lastName and phoneNumber
    @ return boolean value true if updated successfully else false
    */
    public boolean updateEmployeeDataUsingStatement(String firstName, String lastName, int phoneNumber) {
        String sql1 = "update contact set PhoneNumber=" + phoneNumber + " where firstname='" + firstName + "' and lastname='" + lastName + "'";
        try (Connection connection = this.getConnection("localhost", "contact_book",
                "root", "1234");) {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sql1);
            if (result == 1)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*This method used to read contactsList data From DB for specific time which inturn calls readData()
     * @param startdate and endDate
     * @return contactlist
     */
    public List<Contact> getDataByDates(LocalDate startDate, LocalDate endDate) throws SQLException {
        String query = "Select firstname,lastname , city, state, zip, PhoneNumber , email from contact where date(added_time)" +
                " between '" + startDate + "'" + " and '" + endDate + "'";
        return readData(query);
    }

    /*This method used to read contactsList data From DB for specific city which inturn calls readData()
     * @param cityName
     * @return contactlist
     */
    public List<Contact> getDataByCity(String cityName) throws SQLException {
        String query = "Select firstname,lastname , city, state, zip, PhoneNumber , email from contact where city='" + cityName + "'";
        return readData(query);
    }

    /*This method used to insert data to DataBase
     * @param contact list
     * @return boolean value
     */
    public boolean insertIntoMultipleTable(ArrayList<Contact> contactlist) throws SQLException {
        for (int i = 0; i < contactlist.size(); i++) {
            boolean inserted = insertIntoTabel(contactlist.get(i).firstName, contactlist.get(i).lastName, contactlist.get(i).city,
                    contactlist.get(i).state, contactlist.get(i).zip, contactlist.get(i).phoneNumber, contactlist.get(i).email,
                    contactlist.get(i).type);
            if (inserted == false)
                return false;
        }
        return true;
    }

    /*This method used to insert data to DataBase contact in contact table
     * @param contact parameters firstName, lastName, city,state, zip, phoneNumber, email,type
     * @return boolean value if inserted successfully
     */
    public boolean insertIntoTabel(String firstName, String lastName, String city, String state, int zip, long mob, String email, String type) throws SQLException {
        int contactId = -1;
        String sql2=null;
        Contact contact = null;
        String sql = String.format("insert into contact (firstname,lastname , city, state, zip, PhoneNumber , email)" +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')", firstName, lastName, city, state,String.valueOf(zip), String.valueOf(mob), email);
        Connection connection = this.getConnection("localhost", "contact_book",
                "root", "1234");
        try  {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(sql, statement.RETURN_GENERATED_KEYS);
            if (rowAffected == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) contactId = resultSet.getInt(1);
                sql2 = String.format("insert into relation(contactID,type) values('%s','%s')", contactId, type);
            }
            else
                return false;
            int rowAffected2 = statement.executeUpdate(sql2, statement.RETURN_GENERATED_KEYS);
            if (rowAffected == 1) {
                return true;
            }
            else {
                connection.rollback();
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            connection.rollback();
            return false;
        }
    }

}
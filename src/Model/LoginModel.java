package Model;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class LoginModel {
    Connection connection = null;

    public LoginModel() {
        connection = SqliteConnection.Connector();
        if (connection == null) {
            System.out.println("Connection not successfull");
            System.exit(1);
        }
    }

    public boolean isDbConnected() {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isLogin(String user, String pass) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM Customer WHERE username = ? and password=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
                connection.close();
            } catch (SQLException e) {
                return false;
            }
        }
    }

    public void resetConnection() {
        try {
            if (connection.isClosed()) {
                connection = SqliteConnection.Connector();
                if (connection == null) {
                    System.out.println("Connection not successfull");
                    resetConnection();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String removeCustomer(String user)throws SQLException {
        String query = "DELETE FROM Customer WHERE username = ?";
        try (Connection conn = this.connection;
             Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(query)){

            // loop through the result set
            while (result.next()) {
                user = result.getString("username");
            }
            return user;

        } catch (SQLException e) {
            System.out.println("User not found");
            return null;
        }

    }



    public ArrayList getItemsFromDatabase(){
        String query = "SELECT * FROM Items";
        String name;
        ArrayList<String> array = new ArrayList<String>();

        try (Connection conn = this.connection;
             Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(query)) {
            while (result.next()) {
                name = result.getString("name");
                array.add(name);
            }
            return array;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return array;
    }

    public ArrayList getUsernames(){
        String query = "SELECT * FROM Customer";
        String username;
        ArrayList<String> array = new ArrayList<String>();

        try (Connection conn = this.connection;
             Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(query)) {

            while (result.next()) {
                username = result.getString("username");
                array.add(username);

            }
            return array;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return array;
    }


    public void addCustomer(String username,String password,String firstName, String lastName, String address, String country,
                            String zipCode, String email,ActionEvent event) throws SQLException,IOException {
        PreparedStatement preparedStatement;
        String query = "INSERT INTO Customer (username,password,firstName,lastName,address,country,zipCode,email) VALUES("
                + "'" + username + "', " + "'" + password + "', "+ "'" + firstName + "', " + "'" + lastName + "', "
                + "'" + address + "', "+ "'" + country + "', "+ "'" + zipCode + "', "+ "'" + email + "'); ";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();

        //confirmation for the user that the account has been created
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Congratulations, your account has been created");
        alert.showAndWait();

    }

    public void addItem(String name,int quantity,double price,String category, String imageUrl) throws SQLException,IOException {
        PreparedStatement preparedStatement;
        String query = "INSERT INTO Items (name,quantity,price,category,imageUrl) VALUES("
                + "'" + name + "', " + "'" + quantity + "', " + "'" + price + "', " + "'" + category + "', "
                + "'" + imageUrl + "'); ";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();

        //confirmation for the user that the account has been created
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Item has been stored in database");
        alert.showAndWait();
    }


    public String selectPassword(String password){
        String query = "SELECT * FROM Customer";
        try (Connection conn = this.connection;
             Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(query)){

            // loop through the result set
            while (result.next()) {
                password = result.getString("password");
            }
            return password;

        } catch (SQLException e) {
            return "did not find password";
        }
    }

    public String selectUsername(String username){
        String query = "SELECT * FROM Customer";
        try (Connection conn = this.connection;
             Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(query)){

            // loop through the result set
            while (result.next()) {
                username = result.getString("username");

            }
            return username;

        } catch (SQLException e) {
            return "did not find username";

        }
    }

    public String selectFirstName(String firstName){
        String query = "SELECT * FROM Customer";
        try (Connection conn = this.connection;
             Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(query)){

            // loop through the result set
            while (result.next()) {
                firstName = result.getString("firstName");
            }
            return firstName;

        } catch (SQLException e) {
            return "did not find first name";

        }
    }

    public String selectLastName(String lastName){
        String query = "SELECT * FROM Customer";
        try (Connection conn = this.connection;
             Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(query)){

            // loop through the result set
            while (result.next()) {
                lastName = result.getString("lastName");
            }
            return lastName;

        } catch (SQLException e) {
            return "did not find last name";

        }
    }

    public String selectaddress(String address){
        String query = "SELECT * FROM Customer";
        try (Connection conn = this.connection;
             Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(query)){

            // loop through the result set
            while (result.next()) {
                address = result.getString("address");
            }
            return address;

        } catch (SQLException e) {
            return "did not find address";

        }
    }

    public String selectcountry(String country){
        String query = "SELECT * FROM Customer";
        try (Connection conn = this.connection;
             Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(query)){

            // loop through the result set
            while (result.next()) {
                country = result.getString("country");
            }
            return country;

        } catch (SQLException e) {
            return "did not find country";

        }
    }

    public String selectZipCode(String zipCode){
        String query = "SELECT * FROM Customer";
        try (Connection conn = this.connection;
             Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(query)){

            // loop through the result set
            while (result.next()) {
                zipCode = result.getString("zipCode");
            }
            return zipCode;

        } catch (SQLException e) {
            return "did not find Zip Code";
        }
    }

    public String selectEmail(String email){
        PreparedStatement preparedStatement = null;
        String query = "SELECT * FROM Customer WHERE username = ?";
        try (Connection conn = this.connection;
             Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(query)){

            // loop through the result set
            while (result.next()) {
                preparedStatement.setString(8, email);
//               email = result.setString("email");
            }
            System.out.println("testing");
            System.out.println(email);
            return email;

        } catch (SQLException e) {
            return "did not find email address";
        }
    }

    public String getImageUrl(String name){
        String query = "SELECT * FROM Items";
        try (Connection conn = this.connection;
             Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(query)){

            // loop through the result set
            while (result.next()) {
                name = result.getString("imageUrl");

            }
            System.out.println(name);
            return name;

        } catch (SQLException e) {
            return "did not find username";

        }
    }

    public String getItemPrice(String name){
        String query = "SELECT * FROM Items";
        try (Connection conn = this.connection;
             Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(query)){

            // loop through the result set
            while (result.next()) {
                name = result.getString("price");

            }
            System.out.println(name);
            return name;

        } catch (SQLException e) {
            return "";

        }
    }
}

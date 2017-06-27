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

    public boolean removeCustomer(String user)throws SQLException {
        String query = "DELETE FROM Customer WHERE username = ?";
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        String name = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            System.out.println(user);
            preparedStatement.setString(1, user);
            result = preparedStatement.executeQuery();
            // loop through the result set
            while (result.next()) {
                name = result.getString("username");
                return true;
            }
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public String getItemFromUniqueDatabase(){
        String query = "SELECT * FROM ItemToPurchase";
        String name = null;

        try (Connection conn = this.connection;
             Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(query)) {
            while (result.next()) {
                name = result.getString("name");
            }
            return name;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
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

    public ArrayList<String> getUsernameInformation(String username) {
        String query = "SELECT * FROM Customer WHERE username=?";
        ArrayList<String> array = new ArrayList<String>();
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        String password;
        String firstName;
        String lastName;
        String address;
        String country;
        String zipCode;
        String email;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            result = preparedStatement.executeQuery();

            if (result.next()) {
                array.add(username);
                password = result.getString("password");
                array.add(password);
                firstName = result.getString("firstName");
                array.add(firstName);
                lastName = result.getString("lastName");
                array.add(lastName);
                address = result.getString("address");
                array.add(address);
                country = result.getString("country");
                array.add(country);
                zipCode = result.getString("zipCode");
                array.add(zipCode);
                email = result.getString("email");
                array.add(email);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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

    public void addUniqueItem(String name,double price) throws SQLException,IOException {
        PreparedStatement preparedStatement;
        String query = "INSERT INTO ItemToPurchase (name,price) VALUES("
                + "'" + name + "', " + "'" + price + "'); ";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
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

    public String getImageUrl(String name){
        String query = "SELECT * FROM Items WHERE name = ?";
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        String url = null;

        try {preparedStatement = connection.prepareStatement(query);
            System.out.println(name);
            preparedStatement.setString(1, name);
            result = preparedStatement.executeQuery();

            // loop through the result set
            while (result.next()) {
                url = result.getString("imageUrl");

            }
            return url;
        } catch (SQLException e) {
            return "failure";
        }
    }

    public double getItemPrice(String name) {
        String query = "SELECT * FROM Items WHERE name = ?";
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        double price = 0;

        try {
            preparedStatement = connection.prepareStatement(query);
            System.out.println(name);
            preparedStatement.setString(1, name);
            result = preparedStatement.executeQuery();

            // loop through the result set
            while (result.next()) {
                price = result.getDouble("price");

            }

            return price;

        } catch (SQLException e) {

            return 0;

        }
    }

    public double getUniqueItemPrice(){
        String query = "SELECT * FROM ItemToPurchase";
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        double price = 0;

        try {preparedStatement = connection.prepareStatement(query);
            System.out.println(price);
            result = preparedStatement.executeQuery();

            // loop through the result set
            while (result.next()) {
                price = result.getDouble("price");
                return price;
            }
            return price;
        } catch (SQLException e) {
            return 0;
        }
    }

    public void deleteUniqueTable() throws SQLException {
        Statement stmt = connection.createStatement();
        String sqlCommand = "DELETE FROM 'ItemToPurchase'";
        stmt.executeUpdate(sqlCommand);
    }

}

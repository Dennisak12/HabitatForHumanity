package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import java.io.IOException;
import java.sql.*;

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
        ResultSet resultSet = null;
        String query = "DELETE FROM Customer WHERE username = ?";
        try {
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setString(1, user);
                pstmt.executeUpdate();
                System.out.println("Found");
                return true;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println("not found");
                return false;
            }
        }

        public String getItems(){
            String query = "SELECT * FROM Customer";
            String username;

            try (Connection conn = this.connection;
                 Statement statement = conn.createStatement();
                 ResultSet result = statement.executeQuery(query)) {
                while (result.next()) {
                    username = result.getString("username");
                    return username;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return "nothing to return";
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

    public String getAllUsernames(String username){
        String query = "SELECT * FROM Customer";
        try (Connection conn = this.connection;
             Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(query)){

            // loop through the result set
            while (!result.next()) {
                username = result.getString("username");
            }
            return username;

        } catch (SQLException e) {
            return "did not find username";

        }
    }
}

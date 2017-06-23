package Model;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    Connection connection = null;

    public LoginModel(){
        connection = SqliteConnection.Connector();
        if (connection == null){
            System.out.println("Connection not successfull");
            System.exit(1);
        }
    }
    public boolean isDbConnected(){
        try{
            return !connection.isClosed();
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean isLogin(String user, String pass){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM Customer WHERE username = ? and password=?";
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,user);
            preparedStatement.setString(2,pass);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            return false;
        }finally{
            try{
                preparedStatement.close();
                resultSet.close();
                connection.close();
            }catch(SQLException e){
                return false;
            }
        }
    }

    public void resetConnection(){
        try{
            if(connection.isClosed()){
                connection = SqliteConnection.Connector();
                if(connection == null){
                    System.out.println("Connection not successfull");
                    resetConnection();
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
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

        Node node=(Node) event.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("../View/LoginView2.fxml"));
        Scene scene = new Scene(root, 900, 975);
        Stage stage=(Stage) node.getScene().getWindow();
        stage.setTitle("Habitat For Humanity");
        stage.setScene(scene);
        stage.show();

    }





}

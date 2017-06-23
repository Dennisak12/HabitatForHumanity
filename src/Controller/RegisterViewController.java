package Controller;

import Model.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;


import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Boss on 6/23/2017.
 */
public class RegisterViewController {

    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField countryField;
    @FXML
    private TextField zipCodeField;
    @FXML
    private TextField emailField;
    @FXML
    private Button registerButton;
    @FXML
    private Button exitButton;

    LoginModel loginModel = new LoginModel();

    public void addCustomer(ActionEvent event) throws SQLException,IOException {
        loginModel.addCustomer(userNameField.getText(),passwordField.getText(),firstNameField.getText(),lastNameField.getText(),
                addressField.getText(),countryField.getText(),zipCodeField.getText(),emailField.getText(),event);
        loginModel.resetConnection();
        Node node=(Node) event.getSource();

    }

    //after the user creates account, will jump to the login page
    public void exitScene(ActionEvent event)throws IOException {
        Node node=(Node) event.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("../View/LoginView2.fxml"));
        Scene scene = new Scene(root, 700, 400);
        Stage stage=(Stage) node.getScene().getWindow();
        stage.setTitle("Habitat For Humanity");
        stage.setScene(scene);
        stage.show();
    }
}

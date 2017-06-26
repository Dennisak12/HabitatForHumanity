package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import Model.LoginModel;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginView2Controller implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label connectedLabel;

    @FXML
    private Button registerButton;

    @FXML
    private Button loginButton;

    LoginModel loginModel = new LoginModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(loginModel.isDbConnected()){
            connectedLabel.setText("Connected to database");
        }else{
            connectedLabel.setText("Not connected to database");
        }

    }

    public void jumpToRegister(ActionEvent event) throws IOException{
        Node node=(Node) event.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("../View/RegisterView.fxml"));
        Scene scene = new Scene(root, 600, 400);
        Stage stage=(Stage) node.getScene().getWindow();
        stage.setTitle("Register For An Account");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void login(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        System.out.println(username + " " + password);

        //tells us that there is a correct username and password
        if(loginModel.isLogin(username,password)){
            System.out.println("connection successfull");

            //if username matches the admins, jump to admin pane
            if(username.equals("Administrator") && password.equals("admin")){
                System.out.println("connection successfull admin");
                Node node=(Node) event.getSource();
                Parent root = FXMLLoader.load(getClass().getResource("../View/ManagerWindow.fxml"));
                Scene scene = new Scene(root, 600, 400);
                Stage stage=(Stage) node.getScene().getWindow();
                stage.setTitle("Administrator");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

                //if username and password doesnt match admin, jump to regular user pane
            }else {

                System.out.println("connection successfull regular");
                connectedLabel.setText("Username and password are correct");
                Node node = (Node) event.getSource();
                Parent root = FXMLLoader.load(getClass().getResource("../View/RegularUserLogin.fxml"));
                Scene scene = new Scene(root, 600, 500);
                Stage stage = (Stage) node.getScene().getWindow();
                stage.setTitle("Welcome To Habitat For Humanity");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            }
        }else {
            connectedLabel.setText("Incorrect Username or Password!");
            loginModel.resetConnection();
        }
    }
}

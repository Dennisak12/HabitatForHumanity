package Controller;

import Model.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.control.Button;


import java.io.IOException;


/**
 * Created by Boss on 6/24/2017.
 */

public class AdminController {
    @FXML
    private Button viewReportsButton;
    @FXML
    private Button editAccountButton;
    @FXML
    private Button addAccountButton;
    @FXML
    private Button removeAccountButton;
    @FXML
    private Button addItemButton;
    @FXML
    private Button logoutButton;

    LoginModel loginModel = new LoginModel();

    public void jumpToEditAccount(ActionEvent event) throws IOException {
        Node node=(Node) event.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("../View/EditAccountView.fxml"));
        Scene scene = new Scene(root, 600, 400);
        Stage stage=(Stage) node.getScene().getWindow();
        stage.setTitle("Edit An Account");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void jumpToRemoveAccount(ActionEvent event) throws IOException {
        Node node=(Node) event.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("../View/RemoveAccountView.fxml"));
        Scene scene = new Scene(root, 600, 400);
        Stage stage=(Stage) node.getScene().getWindow();
        stage.setTitle("Remove An Account");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void openCreateAccount(ActionEvent event) throws IOException{
        Node node=(Node) event.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("../View/MockRegisterView.fxml"));
        Scene scene = new Scene(root, 600, 400);
        Stage stage=(Stage) node.getScene().getWindow();
        stage.setTitle("Habitat For Humanity");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void openAddItem(ActionEvent event) throws IOException{
        Node node=(Node) event.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("../View/AddItemView.fxml"));
        Scene scene = new Scene(root, 600, 400);
        Stage stage=(Stage) node.getScene().getWindow();
        stage.setTitle("Add An Item To The Database");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void jumpToMainScreen(ActionEvent event) throws IOException {
        //confirmation for the user that the account has been created
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Logging Out");
        alert.setContentText("Successfully Logged Out");
        alert.showAndWait();

        Node node=(Node) event.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("../View/LoginView2.fxml"));
        Scene scene = new Scene(root, 700, 600);
        Stage stage=(Stage) node.getScene().getWindow();
        stage.setTitle("Welcome To Habitat For Humanity");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}

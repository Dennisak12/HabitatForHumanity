package Controller;

import Model.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/RegisterView.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Create A New Account");
        stage.setScene(new Scene(root1));
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

}

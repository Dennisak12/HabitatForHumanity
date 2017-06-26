package Controller;

import Model.LoginModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * Created by Boss on 6/24/2017.
 */
public class EditAccountController implements Initializable{
    @FXML
    private TextField userNameField;
    @FXML
    private TextField passwordField;
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
    private Button editAccountButton;
    @FXML
    private Button exitFromViewButton;
    @FXML
    private ComboBox<String> usernameComboBox;

    LoginModel loginModel = new LoginModel();
    private Connection connection;


    public void getTextFieldInformation(ActionEvent event) {

        //start fresh on a reset database
        loginModel.resetConnection();

        String username = usernameComboBox.getValue();
        String password= null;
        String firstName= null;
        String lastName= null;
        String address= null;
        String country= null;
        String zipCode= null;
        String email= null;

        //setting all the text fields so the user knows what the previous info was.
        userNameField.setText(loginModel.selectUsername(username));
        loginModel.resetConnection();
        passwordField.setText(loginModel.selectPassword(password));
        loginModel.resetConnection();
        firstNameField.setText(loginModel.selectFirstName(firstName));
        loginModel.resetConnection();
        lastNameField.setText(loginModel.selectLastName(lastName));
        loginModel.resetConnection();
        addressField.setText(loginModel.selectaddress(address));
        loginModel.resetConnection();
        countryField.setText(loginModel.selectcountry(country));
        loginModel.resetConnection();
        zipCodeField.setText(loginModel.selectZipCode(zipCode));
        loginModel.resetConnection();
        emailField.setText(loginModel.selectEmail(email));
        loginModel.resetConnection();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginModel.resetConnection();
        String username = null;
        ArrayList<String> array = new ArrayList<>();
        array = loginModel.getUsernames();

        loginModel.resetConnection();
        ObservableList<String> list = FXCollections.observableArrayList();

        loginModel.resetConnection();
        for(int i = 0; i < array.size(); i++){
            loginModel.resetConnection();
            list.add(array.get(i));
        }
        usernameComboBox.getItems().addAll(list);
        loginModel.resetConnection();
    }


    public void exitToAdminPane(ActionEvent event)throws IOException {
        Node node=(Node) event.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("../View/ManagerWindow.fxml"));
        Scene scene = new Scene(root, 600, 400);
        Stage stage=(Stage) node.getScene().getWindow();
        stage.setTitle("Habitat For Humanity");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}

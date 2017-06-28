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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Boss on 6/27/2017.
 */
public class ReceiptController implements Initializable{
    @FXML
    private Label itemNameLabel;
    @FXML
    private Label totalPriceLabel;
    @FXML
    private ComboBox<String> itemsComboBox;
    @FXML
    private Button exitFromViewButton;

    LoginModel loginModel = new LoginModel();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginModel.resetConnection();

        ArrayList<String> array = new ArrayList<>();
        array = loginModel.getItemsFromInvoiceDatabase();

        loginModel.resetConnection();
        ObservableList<String> list = FXCollections.observableArrayList();

        loginModel.resetConnection();
        for(int i = 0; i < array.size(); i++){
            loginModel.resetConnection();
            list.add(array.get(i));
        }
        itemsComboBox.getItems().addAll(list);
        loginModel.resetConnection();
    }

    public void displayReceipt(ActionEvent event){

        String name = itemsComboBox.getValue();
        double price = loginModel.getInvoiceItemPrice(name);

        loginModel.resetConnection();
        totalPriceLabel.setText("$"+price);
        itemNameLabel.setText(loginModel.getItemFromInvoiceDatabase(name));
        loginModel.resetConnection();

    }

    public void goBackWindow(ActionEvent event) throws IOException {
        Node node=(Node) event.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("../View/ManagerWindow.fxml"));
        Scene scene = new Scene(root, 600, 400);
        Stage stage=(Stage) node.getScene().getWindow();
        stage.setTitle("Administrator");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}

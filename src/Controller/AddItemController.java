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
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Boss on 6/25/2017.
 */
public class AddItemController implements Initializable{
    @FXML
    private TextField itemNameField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField priceField;
    @FXML
    private ComboBox categoryComboBox;
    @FXML
    private Button addImageButton;
    @FXML
    private Button createItemButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button addItemsToBox;
    @FXML
    private Label imageUrlLabel;
    @FXML
    private ImageView imageViewResults;

    LoginModel loginModel = new LoginModel();
    String image = null;


    public void addItems(ActionEvent event) throws SQLException,IOException {
        int quantity = Integer.parseInt(quantityField.getText());
        double price = Double.parseDouble(priceField.getText());


        loginModel.addItem(itemNameField.getText(),quantity,price,(String)categoryComboBox.getValue(),image);
        loginModel.resetConnection();
    }

    public void imageFileChooser(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if (file!=null){
            image = file.getAbsolutePath();
        }
    }

    public void jumpToadminPane(ActionEvent event) throws IOException {
        Node node=(Node) event.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("../View/ManagerWindow.fxml"));
        Scene scene = new Scene(root, 600, 400);
        Stage stage=(Stage) node.getScene().getWindow();
        stage.setTitle("AdminPane");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Doors","Interior Trim","Wood Floor","Paint","Power Tools","Hand Tools","Appliances","Misc"
                );
        categoryComboBox.setItems(options);
    }

}
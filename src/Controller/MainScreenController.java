/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author shane
 */
public class MainScreenController implements Initializable {
    
    Stage stage;
    Parent scene;

    @FXML
    private Label lblMainLabel;
    @FXML
    private Label labelPartsLabel;
    @FXML
    private Button btnSearchPart;
    @FXML
    private TextField txtPartSearch;
    @FXML
    private TableColumn<?, ?> colPartId;
    @FXML
    private TableColumn<?, ?> colPartName;
    @FXML
    private TableColumn<?, ?> colPartCount;
    @FXML
    private TableColumn<?, ?> colPartPrice;
    @FXML
    private Button btnDeletePart;
    @FXML
    private Button btnModifyPart;
    @FXML
    private Button btnAddPart;
    @FXML
    private Label labelProducts;
    @FXML
    private Button btnSearchProduct;
    @FXML
    private TextField txtSearchProduct;
    @FXML
    private TableColumn<?, ?> colProductId;
    @FXML
    private TableColumn<?, ?> colProductName;
    @FXML
    private TableColumn<?, ?> colProductCount;
    @FXML
    private TableColumn<?, ?> colProductPrice;
    @FXML
    private Button btnDeleteProduct;
    @FXML
    private Button btnModifyProduct;
    @FXML
    private Button btnAddProduct;
    @FXML
    private Button btnExit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void searchPartHandler(ActionEvent event) {
    }

    @FXML
    private void deletePartHandler(ActionEvent event) {
    }

    @FXML
    private void modifyPartHandler(ActionEvent event) throws IOException {
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/ModifyPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        
    }

    @FXML
    private void addPartHandler(ActionEvent event) throws IOException {
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        
    }

    @FXML
    private void searchProductHandler(ActionEvent event) {
    }

    @FXML
    private void deleteProductHandler(ActionEvent event) {
    }

    @FXML
    private void modifyProductHandler(ActionEvent event) throws IOException {
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/ModifyProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        
    }

    @FXML
    private void addProductHandler(ActionEvent event) throws IOException {
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private void exitMainHandler(ActionEvent event) {
        
        System.exit(0);
        
    }
    
}

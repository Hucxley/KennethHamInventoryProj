/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
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
    Inventory inventory = new Inventory();

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
        
        String searchText = txtPartSearch.getText();
        
        // Regex pattern for matches from Stack Overflow user tokhi
        // URL to source: https://stackoverflow.com/a/39531087
        Boolean isNumber = searchText.matches("^[0-9]*$");
        // If searchText is a number, search parts by index
        if(isNumber){
            int partId = Integer.parseInt(txtPartSearch.getText());
            
            // Ensure partId is not 0 and is not greater than length of parts list
            if(partId != 0 && partId <= Inventory.getAllParts().size()){
                Part foundPart = Inventory.lookupPart(partId);
                
                // TODO: display search results
                
                System.out.println(foundPart);
            } else {  // notify user to enter valid part ID
                
                // TODO: create notifcation / dialogue for invalid part ID
                
                System.out.println("TODO; Dialog for invalid part ID");
            }
        } else { // if searchText is not a numbger, search parts list with string
            System.out.println(txtPartSearch.getText());
        }
    
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

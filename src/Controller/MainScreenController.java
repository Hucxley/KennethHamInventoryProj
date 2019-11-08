/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView<Part> tablePartsList;
    @FXML
    private TableColumn<Part, Integer> colPartId;
    @FXML
    private TableColumn<Part, String> colPartName;
    @FXML
    private TableColumn<Part, Integer> colPartCount;
    @FXML
    private TableColumn<Part, Double> colPartPrice;
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
    private TableView<Product> tableProductsList;
    @FXML
    private TableColumn<Product, Integer> colProductId;
    @FXML
    private TableColumn<Product, String> colProductName;
    @FXML
    private TableColumn<Product, Integer> colProductCount;
    @FXML
    private TableColumn<Product, Double> colProductPrice;
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
        System.out.println( Inventory.getAllParts().size());
        
        tablePartsList.setItems(Inventory.getAllParts());
        colPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPartCount.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        tableProductsList.setItems(Inventory.getAllProducts());
        colProductId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colProductCount.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
    }    

    @FXML
    private void searchPartHandler(ActionEvent event) {
        
        String searchText;
        searchText = txtPartSearch.getText();
        ObservableList<Part> searchResults = FXCollections.observableArrayList();
        
        // Regex pattern for matches from Stack Overflow user tokhi
        // URL to source: https://stackoverflow.com/a/39531087
        Boolean isNumber = searchText.matches("^[0-9]*$");
        
        if(searchText.length() == 0){
            tablePartsList.setItems(Inventory.getAllParts());
        } else if(isNumber) { // If searchText is a number, search parts by index
            int partId = Integer.parseInt(txtPartSearch.getText());
            
            // Ensure partId is not 0 and is not greater than length of parts list
            if(partId != 0 && partId <= Inventory.getAllParts().size()){

                Part foundPart = Inventory.lookupPart(partId);
                searchResults.add(foundPart);
                
                // TODO: display search results
                
                System.out.println(foundPart);
            } else {  // notify user to enter valid part ID
                
                // TODO: create notifcation / dialogue for invalid part ID
                
                System.out.println("TODO; Dialog for invalid part ID");
            }
        } else { // if searchText is not a numbger, search parts list with string
            ObservableList foundParts = Inventory.lookupPart(searchText);
            searchResults.setAll(foundParts);
        }
        
        tablePartsList.setItems(searchResults);
    
    }

    @FXML
    private void deletePartHandler(ActionEvent event) {
        try{
            Part selectedPart = tablePartsList.getSelectionModel().getSelectedItem();
            // confirmation alert format from https://code.makery.ch/blog/javafx-dialogs-official/
            Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
            confirmAlert.setTitle("Delete Part confirmation");
            confirmAlert.setHeaderText("Deleting a Part will remove it completely!");
            confirmAlert.setContentText("Are you sure you want to delete " + selectedPart.getName() + "?");

            Optional<ButtonType> response = confirmAlert.showAndWait();
            if(response.get() == ButtonType.OK){
                Inventory.deletePart(selectedPart);
                tablePartsList.refresh();
            }
        }catch(NullPointerException e){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error in Part Deletion");
            errorAlert.setHeaderText("No Part Selected to delete");
            errorAlert.setContentText("You must click on and select a part to delete.");

            Optional<ButtonType> response = errorAlert.showAndWait();
                if(response.get() == ButtonType.OK){
                   // do nothing

            }
        }   
    }

    @FXML
    private void modifyPartHandler(ActionEvent event) throws IOException {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/ModifyPart.fxml"));
            loader.load();

            ModifyPartController MPCController = loader.getController();
            MPCController.setPartToModify(tablePartsList.getSelectionModel().getSelectedItem());



            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }catch(NullPointerException e){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error in Part Selection");
            errorAlert.setHeaderText("No Part Selected to modify");
            errorAlert.setContentText("You must click on and select a part to modify.");

            Optional<ButtonType> response = errorAlert.showAndWait();
                if(response.get() == ButtonType.OK){
                   // do nothing

            }
        }
     
        
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
        
        String searchText;
        searchText = txtSearchProduct.getText();
        
        // Regex pattern for matches from Stack Overflow user tokhi
        // URL to source: https://stackoverflow.com/a/39531087
        Boolean isNumber = searchText.matches("^[0-9]*$");
        // If searchText is a number, search parts by index
        if(isNumber){
            int productId = Integer.parseInt(searchText);
            
            // Ensure partId is not 0 and is not greater than length of parts list
            if(productId != 0 && productId <= Inventory.getAllProducts().size()){
                Product foundProduct = Inventory.lookupProduct(productId);
                
                // TODO: display search results
                
                System.out.println(foundProduct);
            } else {  // notify user to enter valid part ID
                
                // TODO: create notifcation / dialogue for invalid part ID
                
                System.out.println("TODO; Dialog for invalid part ID");
            }
        } else { // if searchText is not a numbger, search parts list with string
            System.out.println(txtPartSearch.getText());
        }
        

    }

    @FXML
    private void deleteProductHandler(ActionEvent event) {
        
        try{
            Product selectedProduct = tableProductsList.getSelectionModel().getSelectedItem();
            // confirmation alert format from https://code.makery.ch/blog/javafx-dialogs-official/
            Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
            confirmAlert.setTitle("Delete Product confirmation");
            confirmAlert.setHeaderText("Deleting a Product will remove it completely!");
            confirmAlert.setContentText("Are you sure you want to delete " + selectedProduct.getName() + "?");

            Optional<ButtonType> response = confirmAlert.showAndWait();
            if(response.get() == ButtonType.OK){
                Inventory.deleteProduct(selectedProduct);
                tablePartsList.refresh();
            }
        }catch(NullPointerException e){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error in Product Deletion");
            errorAlert.setHeaderText("No Product Selected to delete");
            errorAlert.setContentText("You must click on and select a product to delete.");

            Optional<ButtonType> response = errorAlert.showAndWait();
                if(response.get() == ButtonType.OK){
                   // do nothing

            }
        }
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

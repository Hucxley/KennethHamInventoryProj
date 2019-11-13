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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
public class AddProductController implements Initializable {

    /**
     * @return the txtMin
     */
    public Integer getTxtMin() {
        return Integer.parseInt(txtMin.getText());
    }

    /**
     * @return the txtMax
     */
    public Integer getTxtMax() {
        return Integer.parseInt(txtMax.getText());
    }

    /**
     * @return the txtPrice
     */
    public Double getTxtPrice() {
        return Double.parseDouble(txtPrice.getText());
    }

    /**
     * @return the txtInv
     */
    public Integer getTxtInv() {
        return Integer.parseInt(txtInv.getText());
    }

    /**
     * @return the txtName
     */
    public String getTxtName() {
        return txtName.getText();
    }

    /**
     * @param txtID the txtID to set
     */
    public void setTxtID(TextField txtID) {
        this.txtID = txtID;
    }

    /**
     * @param tableProductPartList the tableProductPartList to set
     */
    public void setTableProductPartList(TableView<Part> tableProductPartList) {
        this.tableProductPartList = tableProductPartList;
    }

    /**
     * @return the txtSearch
     */
    public String getTxtSearch() {
        return txtSearch.getText().toLowerCase().trim();
    }

    /**
     * @param tablePartsList the tablePartsList to set
     */
    public void setTablePartsList(TableView<Part> tablePartsList) {
        this.tablePartsList = tablePartsList;
    }
    
    Stage stage;
    Parent scene;
    public static ObservableList<Part> productPartList = FXCollections.observableArrayList();

    @FXML
    private TextField txtMin;
    @FXML
    private TextField txtMax;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtInv;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtID;
    @FXML
    private Button btnSearchProduct;
    @FXML
    private Button btnAddProductPart;
    @FXML
    private TableView<Part> tableProductPartList;
    @FXML
    private TableColumn<Part, Integer> colProductPartId;
    @FXML
    private TableColumn<Part, String> colProductPartName;
    @FXML
    private TableColumn<Part, Integer> colProductPartCount;
    @FXML
    private TableColumn<Part, Double> colProductPartPrice;
    @FXML
    private TextField txtSearch;
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
    private Button btnDeleteProduct;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSaveProduct;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
        ObservableList partsList = Inventory.getAllParts();
        productPartList.clear();

        tablePartsList.setItems(partsList);
        colPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPartCount.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        tableProductPartList.setItems(productPartList);
        colProductPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProductPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colProductPartCount.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colProductPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }    

    @FXML
    private void searchProductHandler(ActionEvent event) {
        String searchText;
        searchText = txtSearch.getText().trim();
        ObservableList<Part> searchResults = FXCollections.observableArrayList();
        
        // Regex pattern for matches from Stack Overflow user tokhi
        // URL to source: https://stackoverflow.com/a/39531087
        Boolean isNumber = searchText.matches("^[0-9]*$");
        
        if(searchText.length() == 0){
            txtSearch.clear();
            searchResults = Inventory.getAllParts();
        } else if(isNumber) { // If searchText is a number, search parts by index
            int partId = Integer.parseInt(txtSearch.getText());
            
            // Ensure partId is not 0 and is not greater than length of parts list
            if(partId != 0 && partId <= Inventory.getAllParts().size()){

                Part foundPart = Inventory.lookupPart(partId);
                searchResults.add(foundPart);
                
                
                System.out.println(foundPart);
            } else {                
                searchResults.clear(); // ensure search results are clear for invalid ID

            }
        } else { // if searchText is not a numbger, search parts list with string
            ObservableList foundParts = Inventory.lookupPart(searchText);
            searchResults.setAll(foundParts);
        }
        
        tablePartsList.setItems(searchResults);
    

    }

    @FXML
    private void btnActionAddProductPart(ActionEvent event) {
        Part selectedPart = tablePartsList.getSelectionModel().getSelectedItem();
        productPartList.add(selectedPart);
        tablePartsList.refresh();
        tableProductPartList.refresh();
        
    }

    @FXML
    private void btnActionDeleteProductHandler(ActionEvent event) {
        
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this part?", ButtonType.YES, ButtonType.NO);
        confirmAlert.setTitle("Confirm Part Deletioon");
        confirmAlert.setHeaderText("Deleted items CANNOT be recovered!");

        Optional<ButtonType> response = confirmAlert.showAndWait();
        if(response.isPresent() && response.get() == ButtonType.YES){
            Part selectedPart = tableProductPartList.getSelectionModel().getSelectedItem();
            // TODO: Error handler if nothing selected

            int selectedPartIndex = productPartList.indexOf(selectedPart);
            productPartList.remove(selectedPartIndex);
            tableProductPartList.refresh();
        }else{
            confirmAlert.hide();
        } 
             

    }

    @FXML
    private void btnActionCancelHandler(ActionEvent event) throws IOException {
        
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?", ButtonType.YES, ButtonType.NO);
            confirmAlert.setTitle("Confirm Cancel");
            confirmAlert.setHeaderText("Changes will be lost if cancelled without saving");

            Optional<ButtonType> response = confirmAlert.showAndWait();
            if(response.isPresent() && response.get() == ButtonType.YES){
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }else{
                confirmAlert.hide();
            }       
        
    }

    @FXML
    private void btnActionSaveProduct(ActionEvent event) throws IOException {
        
        Boolean saveComplete = false;
        int lastProductIndex;
        int lastProductId = 0;
        int productId = lastProductId + 1;
        
        try{
        
            Product product;
            lastProductIndex = Inventory.getAllProducts().size();
            if(lastProductIndex > 0){
                lastProductId = Inventory.getAllProducts().get(lastProductIndex - 1).getId();
                productId = lastProductId + 1;
            }

            String productName = this.getTxtName();
            Double productPrice = this.getTxtPrice();
            int productStock = this.getTxtInv();
            int productMin = this.getTxtMin();
            int productMax = this.getTxtMax();
            
            if(productMin > productMax){
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Min/Max Inventory Error");
                errorAlert.setHeaderText("Min Count is greater than Max Count");
                errorAlert.setContentText("Min Part Count MUST be LESS than Max Part Count");

                Optional<ButtonType> response = errorAlert.showAndWait();
                    if(response.get() == ButtonType.OK){
                       // do nothing

                }
            }else if(productMax < productMax){
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Min/Max Inventory Error");
                errorAlert.setHeaderText("Max Count is less than Min Count");
                errorAlert.setContentText("Max Part Count MUST be MORE than Min Part Count");

                Optional<ButtonType> response = errorAlert.showAndWait();
                    if(response.isPresent() && response.get() == ButtonType.OK){
                       // do nothing
                    }else{
                        errorAlert.hide();
                    }
            }else{
                product = new Product(productPartList, productId, productName, productPrice, productStock, productMin, productMax);

                Inventory.addProduct(product);
                saveComplete = true;
            }
        
        }catch(Exception e){
            System.out.println(e);
        }
        
        if(saveComplete){
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }
    
}

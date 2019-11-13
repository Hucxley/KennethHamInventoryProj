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
import java.text.NumberFormat;
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
import javafx.scene.control.TableCell;
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
public class ModifyProductController implements Initializable {
    
    Stage stage;
    Parent scene;
    
    Product productToUpdate = null;
    
    // create holding list for part list updates
    ObservableList<Part> modifiedProductPartsList = FXCollections.observableArrayList();

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
    @FXML
    private Button btnAddProductPart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList partsList = Inventory.getAllParts();
        modifiedProductPartsList.clear();
       
        tablePartsList.setItems(partsList);
        colPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPartCount.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        /** number format cell factory example used from StackOverflow user James_D 
         *  Direct URL https://stackoverflow.com/a/48733179
         */
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        colPartPrice.setCellFactory(tc -> new TableCell<Part, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(currencyFormat.format(price));
                }
            }
        });
        
        tableProductPartList.setItems(modifiedProductPartsList);
        colProductPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProductPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colProductPartCount.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colProductPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
         /** number format cell factory example used from StackOverflow user James_D 
         *  Direct URL https://stackoverflow.com/a/48733179
         */
        colProductPartPrice.setCellFactory(tc -> new TableCell<Part, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(currencyFormat.format(price));
                }
            }
        });
        
        tableProductPartList.refresh();
        
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
        // TODO: Error handler if nothing selected
        
        System.out.println(selectedPart);
        
        modifiedProductPartsList.add(selectedPart);
        tableProductPartList.refresh();
    }

    @FXML
    private void btnActionDeleteProductHandler(ActionEvent event) {
        Part selectedPart = tableProductPartList.getSelectionModel().getSelectedItem();
        
        System.out.println(selectedPart);
        System.out.println(modifiedProductPartsList);
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this part from the product?", ButtonType.YES, ButtonType.NO);
        confirmAlert.setTitle("Confirm Associated Part Deletioon");
        confirmAlert.setHeaderText("Deleted items CANNOT be recovered!");

        Optional<ButtonType> response = confirmAlert.showAndWait();
        if(response.isPresent() && response.get() == ButtonType.YES){
            modifiedProductPartsList.remove(selectedPart);
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
        try{
            System.out.println(productToUpdate);
            Integer productIndex = Inventory.getAllProducts().indexOf(productToUpdate);
            System.out.println(productIndex);
            productToUpdate.setId(Integer.parseInt(txtID.getText()));
            productToUpdate.setMax(Integer.parseInt(txtMax.getText()));
            productToUpdate.setMin(Integer.parseInt(txtMin.getText()));
            productToUpdate.setName(txtName.getText());
            productToUpdate.setStock(Integer.parseInt(txtInv.getText()));
            productToUpdate.setPrice(Double.parseDouble(txtPrice.getText()));
            productToUpdate.getAllAssociatedParts().clear();
            productToUpdate.getAllAssociatedParts().setAll(modifiedProductPartsList);
            if(productToUpdate.getMin() > productToUpdate.getMax()){
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Min/Max Inventory Error");
                errorAlert.setHeaderText("Min Count is greater than Max Count");
                errorAlert.setContentText("Min Part Count MUST be LESS than Max Part Count");

                Optional<ButtonType> response = errorAlert.showAndWait();
                    if(response.get() == ButtonType.OK){
                       // do nothing
                }
            }else if(productToUpdate.getMax() < productToUpdate.getMin()){
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

                Inventory.updateProduct(productIndex, productToUpdate);
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

    /**
     * @return the txtMin
     */
    public TextField getTxtMin() {
        return txtMin;
    }

    /**
     * @param txtMin the txtMin to set
     */
    public void setTxtMin(TextField txtMin) {
        this.txtMin = txtMin;
    }

    /**
     * @return the txtMax
     */
    public TextField getTxtMax() {
        return txtMax;
    }

    /**
     * @param txtMax the txtMax to set
     */
    public void setTxtMax(TextField txtMax) {
        this.txtMax = txtMax;
    }

    /**
     * @return the txtPrice
     */
    public TextField getTxtPrice() {
        return txtPrice;
    }

    /**
     * @param txtPrice the txtPrice to set
     */
    public void setTxtPrice(TextField txtPrice) {
        this.txtPrice = txtPrice;
    }

    /**
     * @return the txtInv
     */
    public TextField getTxtInv() {
        return txtInv;
    }

    /**
     * @param txtInv the txtInv to set
     */
    public void setTxtInv(TextField txtInv) {
        this.txtInv = txtInv;
    }

    /**
     * @return the txtName
     */
    public TextField getTxtName() {
        return txtName;
    }

    /**
     * @param txtName the txtName to set
     */
    public void setTxtName(TextField txtName) {
        this.txtName = txtName;
    }

    /**
     * @param txtID the txtID to set
     */
    public void setTxtID(TextField txtID) {
        this.txtID = txtID;
    }

    /**
     * @return the tableProductPartList
     */
    public TableView<?> getTableProductPartList() {
        return tableProductPartList;
    }

    /**
     * @return the txtSearch
     */
    public TextField getTxtSearch() {
        return txtSearch;
    }

    /**
     * @param txtSearch the txtSearch to set
     */
    public void setTxtSearch(TextField txtSearch) {
        this.txtSearch = txtSearch;
    }

    /**
     * @return the tablePartsList
     */
    public TableView<?> getTablePartsList() {
        return tablePartsList;
    }

    /**
     * @param tablePartsList the tablePartsList to set
     */
    public void setTablePartsList(TableView<Part> tablePartsList) {
        this.tablePartsList = tablePartsList;
    }
    
    /**
     * @param product the product to be modified
     */
    public void setProductToModify(Product product){
        
        txtID.setText(String.valueOf(product.getId()));
        txtName.setText(product.getName());
        txtPrice.setText(String.valueOf(product.getPrice()));
        txtMax.setText(String.valueOf(product.getMax()));
        txtMin.setText(String.valueOf(product.getMin()));
        txtInv.setText(String.valueOf(product.getStock()));

        
        // set local productToUpdate to product being modified
        // set temporary parts list contents to match the current Product being modified
        setProductToUpdate(product);
        modifiedProductPartsList.clear();
        modifiedProductPartsList.setAll(product.getAllAssociatedParts());
        System.out.println(modifiedProductPartsList);
        tableProductPartList.setItems(modifiedProductPartsList);

    }
    
    private void setProductToUpdate(Product product){
        this.productToUpdate = product;
    }
    
}

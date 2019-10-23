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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author shane
 */
public class ModifyProductController implements Initializable {
    
    Stage stage;
    Parent scene;

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
    private Button btnAddProduct;
    @FXML
    private TableView<?> tableProductPartList;
    @FXML
    private TableColumn<?, ?> colProductPartId;
    @FXML
    private TableColumn<?, ?> colProductPartName;
    @FXML
    private TableColumn<?, ?> colProductPartCount;
    @FXML
    private TableColumn<?, ?> colProductPartPrice;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView<?> tablePartsList;
    @FXML
    private TableColumn<?, ?> colPartId;
    @FXML
    private TableColumn<?, ?> colPartName;
    @FXML
    private TableColumn<?, ?> colPartCount;
    @FXML
    private TableColumn<?, ?> colPartPrice;
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
        // TODO
    }    

    @FXML
    private void searchProductHandler(ActionEvent event) {
    }

    @FXML
    private void btnActionAddProduct(ActionEvent event) {
    }

    @FXML
    private void btnActionDeleteProductHandler(ActionEvent event) {
    }

    @FXML
    private void btnActionCancelHandler(ActionEvent event) throws IOException {
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private void btnActionSaveProduct(ActionEvent event) {
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
    public void setTablePartsList(TableView<?> tablePartsList) {
        this.tablePartsList = tablePartsList;
    }
    
}

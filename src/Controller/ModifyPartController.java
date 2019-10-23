/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kenneth Ham
 */
public class ModifyPartController implements Initializable {
    
    Stage stage;
    Parent scene;
    Boolean showMachineID;
    Boolean showCompanyName;

    @FXML
    private RadioButton radBtnInHouse;
    @FXML
    private ToggleGroup toggleGroupPartSource;
    @FXML
    private RadioButton radBtnOutsourced;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtInv;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtMax;
    @FXML
    private TextField txtMin;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private Label labelMachineID;
    @FXML
    private TextField txtMachineID;
    @FXML
    private Label labelCompanyName;
    @FXML
    private TextField txtCompanyName;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        this.getRadBtnInHouse().setSelected(true);
        
        showMachineID = this.getRadBtnInHouse().isSelected();
        showCompanyName = this.getRadBtnOutsourced().isSelected();
        
        initStateToggleSource();
        
    }    

    @FXML
    private void togglePartSource(ActionEvent event) {
        
        // TODO: Set Company Name / Mach ID to '' on save if toggle is changed
        
        if(this.getRadBtnInHouse().isSelected()){
            this.showMachineID = true;
        }else{
            this.showMachineID = false;
        }
        
        this.showCompanyName = !this.showMachineID;
        toggleInternalExternalFields();
    }

    @FXML
    private void btnActionCancel(ActionEvent event) throws IOException {
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private void btnActionSave(ActionEvent event) throws IOException {
        // TODO: save entry
        
        // Load Main Screen on save
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        
    }
    
    private void initStateToggleSource(){
        System.out.println("initStatePartSource called");
        if(Objects.equals(this.showMachineID, this.showCompanyName)){
            System.out.println("there's a problem, in-house & outsourced are the same!");
        }else{
            toggleInternalExternalFields();
        }
        
    }
    
    private void toggleInternalExternalFields(){

        if(this.showMachineID){
            this.labelMachineID.setMaxHeight(31);
            this.getTxtMachineID().setMaxHeight(31);
            this.labelCompanyName.setMaxHeight(0);
            this.getTxtCompanyName().setMaxHeight(0);
            this.showMachineID = true;
            this.showCompanyName = false;
        }else{
            this.labelMachineID.setMaxHeight(0);
            this.getTxtMachineID().setMaxHeight(0);
            this.labelCompanyName.setMaxHeight(31);
            this.getTxtCompanyName().setMaxHeight(31);
            this.showMachineID = false;
            this.showCompanyName = true;
        }
        
    }

    /**
     * @return the radBtnInHouse
     */
    public RadioButton getRadBtnInHouse() {
        return radBtnInHouse;
    }

    /**
     * @param radBtnInHouse the radBtnInHouse to set
     */
    public void setRadBtnInHouse(RadioButton radBtnInHouse) {
        this.radBtnInHouse = radBtnInHouse;
    }

    /**
     * @return the radBtnOutsourced
     */
    public RadioButton getRadBtnOutsourced() {
        return radBtnOutsourced;
    }

    /**
     * @param radBtnOutsourced the radBtnOutsourced to set
     */
    public void setRadBtnOutsourced(RadioButton radBtnOutsourced) {
        this.radBtnOutsourced = radBtnOutsourced;
    }

    /**
     * @return the txtID
     */
    public TextField getTxtID() {
        return txtID;
    }

    /**
     * @param txtID the txtID to set
     */
    public void setTxtID(TextField txtID) {
        this.txtID = txtID;
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
     * @return the txtMachineID
     */
    public TextField getTxtMachineID() {
        return txtMachineID;
    }

    /**
     * @param txtMachineID the txtMachineID to set
     */
    public void setTxtMachineID(TextField txtMachineID) {
        this.txtMachineID = txtMachineID;
    }

    /**
     * @return the txtCompanyName
     */
    public TextField getTxtCompanyName() {
        return txtCompanyName;
    }

    /**
     * @param txtCompanyName the txtCompanyName to set
     */
    public void setTxtCompanyName(TextField txtCompanyName) {
        this.txtCompanyName = txtCompanyName;
    }
    
}

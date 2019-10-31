/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
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
public class AddPartController implements Initializable {
    
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
    showMachineID = this.radBtnInHouse.isSelected();
    showCompanyName = this.radBtnOutsourced.isSelected();

    
        initStateToggleSource();
    }    

    @FXML
    private void togglePartSource(ActionEvent event) {
        
        if(this.radBtnInHouse.isSelected()){
            this.showMachineID = true;
        }else{
            this.showMachineID = false;
        }
        
        this.showCompanyName = !this.showMachineID;
        toggleInternalExternalFields();
        // TODO: Set Company Name / Mach ID to '' on save if toggle is changed

        
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
        Part part;
        // TODO: save entry
        int partID = Inventory.getAllParts().size() + 1; // ensure part number minimum value is 1
        String partName = this.getTxtName();
        double partPrice = this.getTxtPrice();
        int partStock = this.getTxtInv();
        int partMin = this.getTxtMin();
        int partMax = this.getTxtMax();
        String partCompanyName = this.getTxtCompanyName();
        int partMachineID = this.getTxtMachineID();
       
        if(this.showMachineID){
            part = new Outsourced(partID, partName, partPrice, partStock, partMin, partMax, partCompanyName);
            
            // add new Outsourced Part to Inventory
            Inventory.addPart(part);
        }else{
            part = new InHouse(partID, partName, partPrice, partStock, partMin, partMax, partMachineID);
            
            // add new InHouse Part to Inventory
            Inventory.addPart(part);
        }
        
        System.out.println("Part added");
        System.out.println(part);
        
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
            this.labelMachineID.setMaxHeight(21);
            this.txtMachineID.setMaxHeight(31);
            this.labelCompanyName.setMaxHeight(0);
            this.txtCompanyName.setMaxHeight(0);
            this.showMachineID = true;
            this.showCompanyName = false;
        }else{
            this.labelMachineID.setMaxHeight(0);
            this.txtMachineID.setMaxHeight(0);
            this.labelCompanyName.setMaxHeight(21);
            this.txtCompanyName.setMaxHeight(31);
            this.showMachineID = false;
            this.showCompanyName = true;
        }
        
    }

    /**
     * @return the txtName
     */
    public String getTxtName() {
        return txtName.getText();
    }

    /**
     * @return the txtInv
     */
    public int getTxtInv() {
        return Integer.parseInt(txtInv.getText());
    }

    /**
     * @return the txtPrice
     */
    public double getTxtPrice() {
        String priceString = txtPrice.getText();
        return Double.parseDouble(priceString);
    }

    /**
     * @return the txtMax
     */
    public int getTxtMax() {
        return Integer.parseInt(txtMax.getText());
    }

    /**
     * @return the txtMin
     */
    public int getTxtMin() {
        return Integer.parseInt(txtMin.getText());
    }

    /**
     * @return the txtMachineID
     */
    public int getTxtMachineID() {
        return Integer.parseInt(txtMachineID.getText());
    }

    /**
     * @return the txtCompanyName
     */
    public String getTxtCompanyName() {
        return txtCompanyName.getText();
    }
    
}

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
import java.text.NumberFormat;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableArrayList;
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
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
    private ToggleGroup toggleGroupPartSource;
    @FXML
    private RadioButton radBtnInHouse;
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

    public void setPartToModify(Part part){
        txtID.setText(String.valueOf(part.getId()));
        txtName.setText(part.getName());
        txtPrice.setText(String.valueOf(part.getPrice()));
        txtMax.setText(String.valueOf(part.getMax()));
        txtMin.setText(String.valueOf(part.getMin()));
        txtInv.setText(String.valueOf(part.getStock()));
        if(part instanceof InHouse){
            this.showCompanyName = false;
            this.showMachineID = true;
            radBtnInHouse.setSelected(true);
            radBtnOutsourced.setSelected(false);
            txtMachineID.setText(String.valueOf(((InHouse) part).getMachineId()));
            toggleInternalExternalFields();
            
        }else{
            this.showMachineID = false;
            this.showCompanyName = true;
            radBtnOutsourced.setSelected(true);
            radBtnInHouse.setSelected(false);
            txtCompanyName.setText(((Outsourced) part).getCompanyName());
            toggleInternalExternalFields();
        }
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
    private void btnActionSave(ActionEvent event) throws IOException {
        // TODO: save entry
        Boolean saveComplete = false;
        Integer idToUpdate = Integer.parseInt(txtID.getText());
        Part partToUpdate = Inventory.lookupPart(idToUpdate);
        System.out.println(partToUpdate);
        Integer partIndex = Inventory.getAllParts().indexOf(partToUpdate);
        partToUpdate.setId(Integer.parseInt(txtID.getText()));
        partToUpdate.setMax(Integer.parseInt(txtMax.getText()));
        partToUpdate.setMin(Integer.parseInt(txtMin.getText()));
        partToUpdate.setName(txtName.getText());
        partToUpdate.setStock(Integer.parseInt(txtInv.getText()));
        partToUpdate.setPrice(Double.parseDouble(txtPrice.getText()));
        if(partToUpdate.getMin() > partToUpdate.getMax()){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Min/Max Inventory Error");
            errorAlert.setHeaderText("Min Count is greater than Max Count");
            errorAlert.setContentText("Min Part Count MUST be LESS than Max Part Count");

            Optional<ButtonType> response = errorAlert.showAndWait();
                if(response.get() == ButtonType.OK){
                   // do nothing

            }
        }else if(partToUpdate.getMax() < partToUpdate.getMin()){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Min/Max Inventory Error");
            errorAlert.setHeaderText("Max Count is less than Min Count");
            errorAlert.setContentText("Max Part Count MUST be MORE than Min Part Count");

            Optional<ButtonType> response = errorAlert.showAndWait();
                if(response.get() == ButtonType.OK){
                   // do nothing

            }
        }else{
            if(this.showMachineID){
                if(this.txtMachineID.getText().trim().length() > 0){
                    try{
                        ((InHouse) partToUpdate).setMachineId(Integer.parseInt(txtMachineID.getText()));
                        Inventory.updatePart(partIndex, partToUpdate);
                        saveComplete = true;
                    }catch(ClassCastException e){
                        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                        confirmAlert.setTitle("Part Source Change Request");
                        confirmAlert.setHeaderText("Changing the part source cannot be reversed!");
                        confirmAlert.setContentText("Are you sure you want to convert this part's source to be In-house?");

                        Optional<ButtonType> response = confirmAlert.showAndWait();
                        if(response.get() == ButtonType.OK){
                            Part oldPart = partToUpdate;
                            InHouse partInHouse = new InHouse(oldPart.getId(), oldPart.getName(), oldPart.getPrice(), oldPart.getStock(), oldPart.getMin(), oldPart.getMax(), Integer.parseInt(txtMachineID.getText()));
                            Inventory.updatePart(partIndex, partInHouse);
                            saveComplete = true;
                        }else{
                            confirmAlert.hide();
                        }
                    }
                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error Saving Part!");
                    errorAlert.setHeaderText("Machine ID is Required");
                    errorAlert.setContentText("Enter a Machine ID to finish modifying this part.");

                    errorAlert.setOnCloseRequest((DialogEvent dialogEvent) -> {
                        this.txtMachineID.clear();
                        this.txtMachineID.requestFocus();
                    });
                    errorAlert.showAndWait();

                }            
            }else if(this.showCompanyName){
                if(this.txtCompanyName.getText().trim().length() > 0){
                    try{
                        ((Outsourced) partToUpdate).setCompanyName(txtCompanyName.getText());
                        Inventory.updatePart(partIndex, partToUpdate);
                        saveComplete = true;
                    }catch(ClassCastException e){
                        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                        confirmAlert.setTitle("Part Source Change Request");
                        confirmAlert.setHeaderText("Changing the part source cannot be reversed!");
                        confirmAlert.setContentText("Are you sure you want to convert this part's source to be Outsourced?");

                        Optional<ButtonType> response = confirmAlert.showAndWait();
                            if(response.get() == ButtonType.OK){
                                Part oldPart = partToUpdate;
                                Outsourced partOutsourced = new Outsourced(oldPart.getId(), oldPart.getName(), oldPart.getPrice(), oldPart.getStock(), oldPart.getMin(), oldPart.getMax(), txtCompanyName.getText());
                                Inventory.updatePart(partIndex, partOutsourced);
                                saveComplete = true;
                            }else{
                                confirmAlert.hide();
                            }
                    }
                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error Saving Part!");
                    errorAlert.setHeaderText("Company Name is Required");
                    errorAlert.setContentText("Enter the Company Name to change the part type this part.");

                    errorAlert.setOnCloseRequest((DialogEvent dialogEvent) -> {
                        this.txtCompanyName.clear();
                        this.txtCompanyName.requestFocus();
                    });
                    errorAlert.showAndWait();

                }
            } else {
                System.out.println("Something bad happened, tthis shouldn't happen");
            }

            // Load Main Screen on save
            if(saveComplete){
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }
        
    }
    
    private void initStateToggleSource(){
        if(Objects.equals(this.showMachineID, this.showCompanyName)){
            System.out.println("there's a problem, in-house & outsourced are the same!");
        }else{
            toggleInternalExternalFields();
        }
        
    }
    
    private void reloadModifyWindow(Part part) throws IOException{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/ModifyPart.fxml"));
            loader.load();

            ModifyPartController MPCController = loader.getController();
            MPCController.setPartToModify(part);

            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
    }
    
    private void toggleInternalExternalFields(){

        if(this.showMachineID){
            this.txtMachineID.setDisable(false);
            this.labelMachineID.setMaxHeight(21);
            this.txtMachineID.setMaxHeight(31);
            this.labelCompanyName.setMaxHeight(0);
            this.txtCompanyName.setMaxHeight(0);
            this.showCompanyName = !this.showMachineID;
            this.txtCompanyName.setDisable(true);
        }else{
            this.txtCompanyName.setDisable(false);
            this.labelMachineID.setMaxHeight(0);
            this.txtMachineID.setMaxHeight(0);
            this.labelCompanyName.setMaxHeight(21);
            this.txtCompanyName.setMaxHeight(31);
            this.showMachineID = !this.showCompanyName;
            this.txtMachineID.setDisable(true);
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

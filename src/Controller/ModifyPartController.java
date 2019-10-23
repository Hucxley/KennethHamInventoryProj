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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kenneth Ham
 */
public class ModifyPartController implements Initializable {
    
    Stage stage;
    Parent scene;

    @FXML
    private RadioButton radBtnInHouse;
    @FXML
    private ToggleGroup togglePartSource;
    @FXML
    private RadioButton radBtnOursourced;
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
    private Button btnAddPartCancel;
    @FXML
    private Button btnAddPartSave;
    @FXML
    private Label labelAddPartMachineId;
    @FXML
    private TextField txtMachineID;
    @FXML
    private Label labelAddPartCompanyName;
    @FXML
    private TextField txtCompanyName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void togglePartSource(MouseEvent event) {
        
        // TODO: Toggle Max Height based on which radio button is set
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
        // TODO: save entry
        
        // Load Main Screen on save
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        
    }
    
}

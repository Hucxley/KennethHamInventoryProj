/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kennethhaminventoryproj;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import Model.Product;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import static javafx.fxml.FXMLLoader.load;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author shane
 */
public class KennethHamInventoryProj extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Part partA = new InHouse(1, "part a", .30, 3, 1, 10, 5);
        Part partB = new Outsourced(2, "part b", .03, 5, 1, 10, "Company A");
        Part partC = new InHouse(3, "part c", .20, 3, 4, 100, 5);
        Part partD = new Outsourced(4, "part d", .50, 9, 1, 10, "Company B");
        Inventory.addPart(partA);
        Inventory.addPart(partB);
        Inventory.addPart(partC);
        Inventory.addPart(partD);
        
        ObservableList<Part> productParts = FXCollections.observableArrayList();
        productParts.add(partA);
        productParts.add(partB);
        productParts.add(partC);
        productParts.add(partD);
        
        Product productA = new Product(productParts, 1, "Product A", 10.00, 10, 1, 100);
        
        Inventory.addProduct(productA);
        
        Parent root = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    

    
}

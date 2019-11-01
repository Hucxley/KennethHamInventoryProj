/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author shane
 */
public class Inventory {
    
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public Inventory() {
    }

    
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }
    
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }
    
    public static Part lookupPart(int partId){ // TODO: change void to Part
        return allParts.get(partId - 1); // convert to 0-based index
        
    }
    
    public static Product lookupProduct(int productID){ // TODO: change void to Product
        return allProducts.get(productID - 1); // convert to 0-based index
        
    }
    
    public static void lookupPart(String partName){ // TODO: change void to ObsservableList<Part>
        
        
    }
    
    public static void lookupProuct(String productName){ // TODO: change void to ObservableList<Product>
        
    }
    
    public static void updatePart(int index, Part selectedPart){
        
    }
    
    public static void updateProduct(int index, Product selectedProduct){
        
    }
    
    public static void deletePart(Part selectedPart){
        
    }
    
    public static void deleteProduct(Product selectedProduct){
        
    }
    
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}

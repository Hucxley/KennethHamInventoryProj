/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Optional;
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
        ObservableList<Part> parts = Inventory.allParts;
        Part foundPart = null;
        for(Part part : parts){
            if(part.getId() == partId){
                foundPart = part;
            }
        }
        
        return foundPart;
        
        
    }
    
    public static Product lookupProduct(int productID){ // TODO: change void to Product
        return allProducts.get(productID); // convert to 0-based index
        
    }
    
    public static void lookupPart(String partName){ // TODO: change void to ObsservableList<Part>
        
        
    }
    
    public static void lookupProuct(String productName){ // TODO: change void to ObservableList<Product>
        
    }
    
    public static void updatePart(int index, Part selectedPart){
        Integer partIndex = index; // convert to zero-based index
        allParts.set(partIndex, selectedPart);
                
    }
    
    public static void updateProduct(int index, Product selectedProduct){
        
    }
    
    public static void deletePart(Part selectedPart){
        Integer partIndex = allParts.indexOf(selectedPart); // set index to zero-based index
        allParts.remove(selectedPart);
        System.out.println(allParts.size());
        
        
    }
    
    public static void deleteProduct(Product selectedProduct){
        Integer productIndex = allProducts.indexOf(selectedProduct); // set index to zero-based index
        allProducts.remove(selectedProduct);
        System.out.println(allProducts.size());
        
    }
    
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}

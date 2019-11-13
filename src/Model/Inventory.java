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
        ObservableList<Part> parts = Inventory.getAllParts();
        Part foundPart = null;
        for(Part part : parts){
            if(part.getId() == partId){
                foundPart = part;
            }
        }
        
        return foundPart;  
        
    }
        
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> searchResults = FXCollections.observableArrayList();
        ObservableList<Part> parts = Inventory.getAllParts();
        for(Part part : parts){
            if(part.getName().contains(partName)){
                searchResults.add(part);
            }
        }
        
        return searchResults;
      
    }
    
    public static Product lookupProduct(int productID){
        ObservableList<Product> products = Inventory.getAllProducts();
        Product foundProduct = null;
        for(Product product : products){
            if(product.getId() == productID){
                foundProduct = product;
            }
        }
        
        return foundProduct;    
    }
    
    public static ObservableList<Product> lookupProduct(String productName){ // TODO: change void to ObservableList<Product>
        ObservableList<Product> searchResults = FXCollections.observableArrayList();
        ObservableList<Product> products = Inventory.getAllProducts();
        for(Product product : products){
            if(product.getName().contains(productName)){
                searchResults.add(product);
            }
        }
        
        return searchResults;
        
    }
    
    public static void updatePart(int index, Part selectedPart){
        Integer partIndex = index;
        allParts.set(partIndex, selectedPart);           
    }
    
    public static void updateProduct(int index, Product selectedProduct){
        Integer productIndex = index;
        allProducts.set(productIndex, selectedProduct);   
    }
    
    public static void deletePart(Part selectedPart){
        allParts.remove(selectedPart); 
    }
    
    public static void deleteProduct(Product selectedProduct){
        allProducts.remove(selectedProduct);     
    }
    
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}

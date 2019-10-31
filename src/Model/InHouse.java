/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author shane
 */
public class InHouse extends Part {
    
    private int machineID; 

    /**
     * 
     * @param id ID of the Part to be created
     * @param name Name of Part to be created
     * @param price Price of Part to be created
     * @param stock Part stock count to be created
     * @param min Minimum stock of Part to be created
     * @param max Maximum stock of Part to be created
     * @param machineID MachineID that manufactures the Part to be created
     */
    
    
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }


    /**
     * @return the machineID
     */
    public int getMachineId() {
        return machineID;
    }

    /**
     * @param machineID the machineID to set
     */
    public void setMachineId(int machineID) {
        this.machineID = machineID;
    }
    
}

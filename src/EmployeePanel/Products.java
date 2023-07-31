/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeePanel;
/**
 *
 * @author Orkid
 */
public class Products {
    private int ID;
    private String ProductName;
    private int CategoryId;
    private int Quantity;
    private int  Price;
    private int  BranchID;

    public Products(int ID, String ProductName, int CategoryId, int Quantity, int Price,int BranchID) {
        this.ID = ID;
        this.ProductName = ProductName;
        this.CategoryId = CategoryId;
        this.Quantity = Quantity;
        this.Price = Price;
        this.BranchID=BranchID;
    }

    public int getID() {
        return ID;
    }

    public String getProductName() {
        return ProductName;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public int getQuantity() {
        return Quantity;
    }

    public int getPrice() {
        return Price;
    }

    public int getBranchID() {
        return BranchID;
    }
    
    
}

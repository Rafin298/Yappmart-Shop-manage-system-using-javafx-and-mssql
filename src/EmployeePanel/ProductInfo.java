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
public class ProductInfo {
      private int ID;
    private String ProductName;
    private String Category;
    private int Quantity;
    private int  Price;
    private String  Branch;

    public ProductInfo(int ID, String ProductName, String Category, int Quantity, int Price, String Branch) {
        this.ID = ID;
        this.ProductName = ProductName;
        this.Category = Category;
        this.Quantity = Quantity;
        this.Price = Price;
        this.Branch = Branch;
    }

    public int getID() {
        return ID;
    }

    public String getProductName() {
        return ProductName;
    }

    public String getCategory() {
        return Category;
    }

    public int getQuantity() {
        return Quantity;
    }

    public int getPrice() {
        return Price;
    }

    public String getBranch() {
        return Branch;
    }
    
    
}

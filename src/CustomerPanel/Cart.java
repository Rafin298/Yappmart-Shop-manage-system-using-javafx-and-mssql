/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerPanel;

import java.sql.Date;

/**
 *
 * @author Orkid
 */
public class Cart {
    private String productName;
    private String category;
    private int  quantity;
    private int  price;

    public Cart(String productName, String category, int quantity, int price) {
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }
    
    
}

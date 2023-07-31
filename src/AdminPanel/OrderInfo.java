/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminPanel;

import java.sql.Date;

/**
 *
 * @author Orkid
 */
public class OrderInfo {
    private int ID;
    private String customerName;
    private Date  orderDate;
    private String contactNo;
    private String gender;
    private int Amount;

    public OrderInfo(int ID, String customerName, Date orderDate, String contactNo, String gender, int Amount) {
        this.ID = ID;
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.contactNo = contactNo;
        this.gender = gender;
        this.Amount = Amount;
    }

    public int getID() {
        return ID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getGender() {
        return gender;
    }

    public int getAmount() {
        return Amount;
    }
    
    
}

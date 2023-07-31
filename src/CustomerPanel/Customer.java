/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerPanel;

/**
 *
 * @author Orkid
 */
public class Customer {
     private int ID;
    private String Name;
    private String Email;
    private String Password;
     private String ContactNo;
    private String Gender;

    public Customer(int ID, String Name, String Email, String Password, String ContactNo, String Gender) {
        this.ID = ID;
        this.Name = Name;
        this.Email = Email;
        this.Password = Password;
        this.ContactNo = ContactNo;
        this.Gender = Gender;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public String getGender() {
        return Gender;
    }
    
    
}

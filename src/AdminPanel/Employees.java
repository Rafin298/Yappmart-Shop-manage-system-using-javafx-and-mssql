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
public class Employees {
    private int ID;
    private String Name;
    private String Email;
    private String Password;
    private Date  birthDate;
    private int BranchId;
    private String Position;
    private String Sex;
    private int Salary;

    public Employees(int ID, String Name, String Email, String Password, Date birthDate, int BranchId, String Position, String Sex, int Salary) {
        this.ID = ID;
        this.Name = Name;
        this.Email = Email;
        this.Password = Password;
        this.birthDate = birthDate;
        this.BranchId = BranchId;
        this.Position = Position;
        this.Sex = Sex;
        this.Salary = Salary;
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

    public String getPosition() {
        return Position;
    }

    public String getSex() {
        return Sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public int getBranchId() {
        return BranchId;
    }

    public int getSalary() {
        return Salary;
    }

    
    
    
    
}

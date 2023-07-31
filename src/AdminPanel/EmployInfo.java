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
public class EmployInfo {
     private int ID;
    private String Name;
    private String Sex;
    private Date  birthDate;
    private String Branch;
    private String Position;
    private int Salary;

    public EmployInfo(int ID, String Name, String Sex, Date birthDate, String Branch, String Position, int Salary) {
        this.ID = ID;
        this.Name = Name;
        this.Sex = Sex;
        this.birthDate = birthDate;
        this.Branch = Branch;
        this.Position = Position;
        this.Salary = Salary;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getSex() {
        return Sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getBranch() {
        return Branch;
    }

    public String getPosition() {
        return Position;
    }

    public int getSalary() {
        return Salary;
    }
    
    
}

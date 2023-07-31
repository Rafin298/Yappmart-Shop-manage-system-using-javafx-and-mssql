/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminPanel;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import yapp.DatabaseConnection;

/**
 * FXML Controller class
 *
 * @author Orkid
 */
public class EmployInfoController implements Initializable {

    private Stage stage;
    @FXML
    private TableView<EmployInfo> tvEmployee;
    @FXML
    private TableColumn<EmployInfo, Integer> colID;
    @FXML
    private TableColumn<EmployInfo, String> ColName;
    @FXML
    private TableColumn<EmployInfo, String> colSex;
    @FXML
    private TableColumn<EmployInfo, Date> colBirthDate;
    @FXML
    private TableColumn<EmployInfo, String> colBranchID;
    @FXML
    private TableColumn<EmployInfo, String> colPosition;
    @FXML
    private TableColumn<EmployInfo, Integer> colSalary;
    @FXML
    private JFXTextField tfSex;
    @FXML
    private JFXDatePicker tfBday;
    @FXML
    private JFXTextField tfSalaryL;
    @FXML
    private JFXTextField tfName;
    @FXML
    private JFXTextField tfSalaryG;
    @FXML
    private JFXCheckBox bdayCheck;
    @FXML
    private JFXCheckBox nameCheck;
    @FXML
    private JFXCheckBox salaryGCheck;
    @FXML
    private JFXCheckBox salaryLCheck;
    @FXML
    private JFXTextField tfPosition;
    @FXML
    private JFXCheckBox sexCheck;
    @FXML
    private JFXCheckBox positionCheck;
    private JFXTextField tfBranch;
    @FXML
    private JFXCheckBox branchCheck;
    private JFXCheckBox branchMax;
    private JFXCheckBox positionMax;
    @FXML
    private JFXCheckBox maxSalaryCheck;
    @FXML
    private JFXComboBox<String> maxSalaryComboBox;
    private String[] maxes = {"1", "2","3"};
    private String[] branches = {"Dhaka", "Sylhet", "Khulna", "Comilla"};
    private String[] orderbys = {"Descending", "Ascending"};
    @FXML
    private JFXComboBox<String> orderByComboBox;
    @FXML
    private JFXCheckBox orderByCheck;
    @FXML
    private JFXComboBox<String> branchComboBox;
    @FXML
    private JFXCheckBox averageSalaryCheck;
    @FXML
    private JFXCheckBox anyMaxSalaryCheck;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        maxSalaryComboBox.getItems().addAll(maxes);
        branchComboBox.getItems().addAll(branches);
         orderByComboBox.getItems().addAll(orderbys);
        String query = "SELECT e.EmployeeId, e.Name,e.SEX,e.BirthDate,b.City,e.Position,e.Salary FROM EMPLOYEE e JOIN BRANCH b ON e.BranchId = b.BranchId;";
        showEmployee(query);
    }

    public ObservableList<EmployInfo> getEmployeesList(String querys) {
        ObservableList<EmployInfo> employeeList = FXCollections.observableArrayList();
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectDB = connectnow.getConnection();
        //  String query = "SELECT e.EmployeeId, e.Name,e.SEX,e.BirthDate,b.City,e.Position,e.Salary FROM EMPLOYEE e JOIN BRANCH b ON e.BranchId = b.BranchId;";

        try {

            Statement statement = connectDB.createStatement();
            ResultSet rs = statement.executeQuery(querys);
            EmployInfo employInfo;

            while (rs.next()) {
                employInfo = new EmployInfo(rs.getInt("EmployeeId"), rs.getString("Name"), rs.getString("SEX"), rs.getDate("BirthDate"), rs.getString("City"), rs.getString("Position"), rs.getInt("Salary"));

                employeeList.add(employInfo);
            }
        } catch (SQLException e) {
            System.out.println("oops you are having a bad day");
            e.printStackTrace();
        }
        return employeeList;
    }

    private void showEmployee(String querys) {
        ObservableList<EmployInfo> list = getEmployeesList(querys);
        colID.setCellValueFactory(new PropertyValueFactory<EmployInfo, Integer>("ID"));
        ColName.setCellValueFactory(new PropertyValueFactory<EmployInfo, String>("Name"));
        colSex.setCellValueFactory(new PropertyValueFactory<EmployInfo, String>("Sex"));
        colBirthDate.setCellValueFactory(new PropertyValueFactory<EmployInfo, Date>("birthDate"));
        colBranchID.setCellValueFactory(new PropertyValueFactory<EmployInfo, String>("Branch"));
        colPosition.setCellValueFactory(new PropertyValueFactory<EmployInfo, String>("Position"));
        colSalary.setCellValueFactory(new PropertyValueFactory<EmployInfo, Integer>("Salary"));
        tvEmployee.setItems(list);
        System.out.println("yoooooo");
    }

    @FXML
    private void tableClick(MouseEvent event) {
    }

    @FXML
    private void search(ActionEvent event) {
        String query;
        if(maxSalaryCheck.isSelected()){
            if(maxSalaryComboBox.getValue()=="3"){
            query="SELECT e.EmployeeId, e.Name,e.SEX,e.BirthDate,b.City,e.Position,e.Salary FROM EMPLOYEE e JOIN BRANCH b ON e.BranchId = b.BranchId where SALARY =(SELECT MAX(salary) FROM EMPLOYEE WHERE salary < (SELECT MAX(salary) FROM employee WHERE salary < (SELECT MAX(salary) FROM employee)))";
            }else if(maxSalaryComboBox.getValue()=="2"){
            query="SELECT e.EmployeeId, e.Name,e.SEX,e.BirthDate,b.City,e.Position,e.Salary FROM EMPLOYEE e JOIN BRANCH b ON e.BranchId = b.BranchId where SALARY =(SELECT MAX(SALARY) FROM EMPLOYEE WHERE SALARY < (SELECT MAX(SALARY) FROM EMPLOYEE))";
            }else{
                query="SELECT e.EmployeeId, e.Name,e.SEX,e.BirthDate,b.City,e.Position,e.Salary FROM EMPLOYEE e JOIN BRANCH b ON e.BranchId = b.BranchId where Salary =(SELECT MAX(salary) FROM EMPLOYEE) ";
            }
        }else if(averageSalaryCheck.isSelected()){
        query="SELECT e.EmployeeId, e.Name,e.SEX,e.BirthDate,b.City,e.Position,e.Salary FROM EMPLOYEE e JOIN BRANCH b ON e.BranchId = b.BranchId where Salary >(SELECT AVG(salary) FROM EMPLOYEE) ";
        }else if(anyMaxSalaryCheck.isSelected()){
         query="SELECT e.EmployeeId, e.Name,e.SEX,e.BirthDate,b.City,e.Position,e.Salary FROM EMPLOYEE e JOIN BRANCH b ON e.BranchId = b.BranchId where SALARY >=Any(SELECT MAX(SALARY) FROM EMPLOYEE group by BranchId)";
        }
        else{
            query = "SELECT e.EmployeeId, e.Name,e.SEX,e.BirthDate,b.City,e.Position,e.Salary FROM EMPLOYEE e JOIN BRANCH b ON e.BranchId = b.BranchId";
        }
        
        if (nameCheck.isSelected()) {
            if (query.contains(" where ")) {
                query += " and name LIKE '" + tfName.getText() + "%'";
            } else {
                query += " where name LIKE '" + tfName.getText() + "%'";
            } 
        }
        if (positionCheck.isSelected()) {
            if (query.contains(" where ")) {
                query += " and Position ='" + tfPosition.getText() + "'";
            } else {
                query += " where Position ='" + tfPosition.getText() + "'";
            } 
        }
        if (sexCheck.isSelected()) {
            if (query.contains(" where ")) {
                query += " and SEX = '" + tfSex.getText() + "'";
            } else {
                query += " where SEX = '" + tfSex.getText() + "'";
            } 
        }
        if (salaryGCheck.isSelected() && salaryLCheck.isSelected()) {
            if (query.contains(" where ")) {
                query += " and salary between " + tfSalaryG.getText() + " and "+tfSalaryL.getText();
            } else {
                query += " where salary between " + tfSalaryG.getText() + " and "+tfSalaryL.getText();
            } 
        }else if(salaryGCheck.isSelected()){
          if (query.contains(" where ")) {
                query += " and salary>=" + tfSalaryG.getText();
            } else {
                query += " where salary>=" + tfSalaryG.getText();
            } 
        }else if(salaryLCheck.isSelected()){
          if (query.contains(" where ")) {
                query += " and salary<=" + tfSalaryL.getText();
            } else {
                query += " where salary<=" + tfSalaryL.getText();
            } 
        }
        if (bdayCheck.isSelected()) {
            if (query.contains(" where ")) {
                query += " and BirthDate <='" + tfBday.getValue() + "'";
            } else {
                query += " where BirthDate <='" + tfBday.getValue() + "'";
            } 
        }
        if (branchCheck.isSelected()) {
            if (query.contains(" where ")) {
                query += " and City = '" + branchComboBox.getValue() + "'";
            } else {
                query += " where City = '" + branchComboBox.getValue() + "'";
            } 
        }
           if (orderByCheck.isSelected()) {
            String orderrr;
            if(orderByComboBox.getValue()=="Descending"){
            orderrr=" order by salary desc";
            }else{
                orderrr=" order by salary";
            }
        
                query += orderrr;
        
        }
     //   if(branchMax.isSelected() || positionMax.isSelected()){
      //  query="SELECT e.EmployeeId, e.Name,e.SEX,e.BirthDate,b.City,e.Position,e.Salary FROM EMPLOYEE e JOIN BRANCH b ON e.BranchId = b.BranchId where "+query+ ")";
         //       }
        showEmployee(query);
    }

    @FXML
    private void showAll(ActionEvent event) {
        String query = "SELECT e.EmployeeId, e.Name,e.SEX,e.BirthDate,b.City,e.Position,e.Salary FROM EMPLOYEE e JOIN BRANCH b ON e.BranchId = b.BranchId;";
        showEmployee(query);
    }

    @FXML
    private void gotoEmployee(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("Employee.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gotoEmployInfo(ActionEvent event) throws IOException {
//         Parent root = FXMLLoader.load(getClass().getResource("EmployInfo.fxml"));
//        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
    }

    @FXML
    private void LogOut(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/yapp/Login.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gotoAdminDashboard(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("AdminDashBoard.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gotoOrderInfo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("OrderInfo.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

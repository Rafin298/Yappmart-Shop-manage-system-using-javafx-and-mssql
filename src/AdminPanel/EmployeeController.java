/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminPanel;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import yapp.DatabaseConnection;

/**
 * FXML Controller class
 *
 * @author Orkid
 */
public class EmployeeController implements Initializable {
    
    private Stage stage;

    @FXML
    private JFXTextField tfName;
    @FXML
    private JFXTextField tfEmail;
     @FXML
    private JFXTextField tfPassword;
    @FXML
    private JFXDatePicker tfBirthdate;
    @FXML
    private JFXTextField tfBranchId;
    @FXML
    private JFXTextField tfPosition;
    @FXML
    private JFXTextField tfSex;
    @FXML
    private JFXTextField tfSalary;
    
    @FXML
    private TableView<Employees> tvEmployee;
     @FXML
    private TableColumn<Employees, Integer> colID;
     @FXML
    private TableColumn<Employees, String> ColName;
    @FXML
    private TableColumn<Employees, String> ColEmail;
    @FXML
    private TableColumn<Employees, String> colPassword;
    @FXML
    private TableColumn<Employees, String> colSex;
    @FXML
    private TableColumn<Employees, Date> colBirthDate;
    @FXML
    private TableColumn<Employees, Integer> colBranchID;
    @FXML
    private TableColumn<Employees, String> colPosition;
    @FXML
    private TableColumn<Employees, Integer> colSalary;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showEmployee();
    }
    
    public ObservableList<Employees> getEmployeesList(){
        ObservableList<Employees> employeeList = FXCollections.observableArrayList();
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectDB = connectnow.getConnection();
         String query = "select * from EMPLOYEE";
         
          try {

            Statement statement = connectDB.createStatement();
            ResultSet rs = statement.executeQuery(query);
            Employees employee;

            while (rs.next()) {
               employee =new Employees(rs.getInt("EmployeeId"),rs.getString("Name"),rs.getString("Email"),rs.getString("Password"),rs.getDate("BirthDate"),rs.getInt("BranchId"),rs.getString("Position"),rs.getString("SEX"),rs.getInt("Salary"));
               // System.out.println(employee.getName());,rs.getString("Email"),
               employeeList.add(employee);
               // System.out.println( rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("oops you are having a bad day");
            e.printStackTrace();
        }
          return employeeList;
    }
    
      private void showEmployee() {
           ObservableList<Employees> list = getEmployeesList();
           colID.setCellValueFactory(new PropertyValueFactory<Employees,Integer>("ID"));
           ColName.setCellValueFactory(new PropertyValueFactory<Employees,String>("Name"));
           ColEmail.setCellValueFactory(new PropertyValueFactory<Employees,String>("Email"));
           colPassword.setCellValueFactory(new PropertyValueFactory<Employees,String>("Password"));
           colSex.setCellValueFactory(new PropertyValueFactory<Employees,String>("Sex"));
           colBirthDate.setCellValueFactory(new PropertyValueFactory<Employees,Date>("birthDate"));
           colBranchID.setCellValueFactory(new PropertyValueFactory<Employees,Integer>("BranchId"));
           colPosition.setCellValueFactory(new PropertyValueFactory<Employees,String>("Position"));
           colSalary.setCellValueFactory(new PropertyValueFactory<Employees,Integer>("Salary"));
           tvEmployee.setItems(list);
           System.out.println("yoooooo");
    }

    @FXML
    private void Insert(ActionEvent event) {
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectDB = connectnow.getConnection();
        String query;
        if(tfPosition.getText().isEmpty()){
            query="INSERT INTO EMPLOYEE (Name, Email, Password,BirthDate,BranchId,SEX,Salary) VALUES('"+tfName.getText()+"','"+tfEmail.getText()+"','"+tfPassword.getText()+"','"+tfBirthdate.getValue()+"',"+tfBranchId.getText()+",'"+tfSex.getText()+"',"+tfSalary.getText()+")";
        }else{
            query="INSERT INTO EMPLOYEE VALUES('"+tfName.getText()+"','"+tfEmail.getText()+"','"+tfPassword.getText()+"','"+tfBirthdate.getValue()+"',"+tfBranchId.getText()+",'"+tfPosition.getText()+"','"+tfSex.getText()+"',"+tfSalary.getText()+")";
        }
       
        System.out.println(tfBirthdate.getValue());
        
         try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
         showEmployee();
    }

    @FXML
    private void Delete(ActionEvent event) {
        String query="DELETE FROM EMPLOYEE WHERE  Email = '"+tfEmail.getText()+"'";
         DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectDB = connectnow.getConnection();
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
         showEmployee();
    }

    @FXML
    private void Update(ActionEvent event) {
         String query="UPDATE EMPLOYEE SET Name ='"+tfName.getText()+"',Email='"+tfEmail.getText()+"',Password='"+tfPassword.getText()+"',BirthDate='"+tfBirthdate.getValue()+"',BranchId="+tfBranchId.getText()+",Position='"+tfPosition.getText()+"',SEX='"+tfSex.getText()+"',Salary="+tfSalary.getText()+" WHERE Email ='"+tfEmail.getText()+"'";
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectDB = connectnow.getConnection();
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
         showEmployee();
    }

    @FXML
    private void tableClick(MouseEvent event) {
        Employees employee = tvEmployee.getSelectionModel().getSelectedItem();
       
        String bday=employee.getBirthDate().toString();;
        LocalDate localDate = Date.valueOf(bday).toLocalDate();
        
        tfName.setText(employee.getName());
        tfEmail.setText(employee.getEmail());
        tfPassword.setText(employee.getPassword());
        tfBirthdate.setValue(localDate);
        tfBranchId.setText(""+employee.getBranchId());
        tfPosition.setText(employee.getPosition());
        tfSex.setText(employee.getSex());
        tfSalary.setText(""+employee.getSalary());
    }

    @FXML
    private void gotoEmployee(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("Employee.fxml"));
//        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
    }

    @FXML
    private void gotoEmployInfo(ActionEvent event) throws IOException {
       // Parent root = FXMLLoader.load(getClass().getResource("/AdminPanel/EmployInfo.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("EmployInfo.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
    private void gotoOrderInfo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("OrderInfo.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gotoDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashBoard.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}

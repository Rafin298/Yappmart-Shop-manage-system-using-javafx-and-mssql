/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeePanel;

import AdminPanel.OrderInfo;
import CustomerPanel.Customer;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
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
public class CustomerInfoController implements Initializable {

    @FXML
    private TableView<Customer> tvCustomer;
    @FXML
    private TableColumn<Customer, Integer> colID;
    @FXML
    private TableColumn<Customer, String> ColName;
    @FXML
    private TableColumn<Customer, String> colEmail;
    @FXML
    private TableColumn<Customer, String> colPassword;
    @FXML
    private TableColumn<Customer, String> colContactNo;
    @FXML
    private TableColumn<Customer, String> colGender;
    @FXML
    private JFXTextField tfName;
    @FXML
    private JFXTextField tfEmail;
    @FXML
    private JFXCheckBox nameCheck;
    @FXML
    private JFXCheckBox emailCheck;
    @FXML
    private JFXCheckBox operatorCheck;
    @FXML
    private JFXComboBox<String> operatorComboBox;
    @FXML
    private JFXComboBox<String> genderComboBox;
    @FXML
    private JFXCheckBox genderCheck;
    private Stage stage;

    private String[] operators = {"Teletalk", "Robi", "GrameenPhone", "Banglalink", "Airtel"};
    private String[] genders = {"Male", "Female"};

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        operatorComboBox.getItems().addAll(operators);
        genderComboBox.getItems().addAll(genders);
        String query = "SELECT * FROM CUSTOMER";
        showCustomer(query);
    }

    public ObservableList<Customer> getOrderList(String querys) {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectDB = connectnow.getConnection();
        //  String query = "SELECT e.EmployeeId, e.Name,e.SEX,e.BirthDate,b.City,e.Position,e.Salary FROM EMPLOYEE e JOIN BRANCH b ON e.BranchId = b.BranchId;";

        try {

            Statement statement = connectDB.createStatement();
            ResultSet rs = statement.executeQuery(querys);
            Customer customer;

            while (rs.next()) {
                customer = new Customer(rs.getInt("CustomerId"), rs.getString("CustomerName"), rs.getString("Email"), rs.getString("Password"), rs.getString("ContactNo"), rs.getString("Gender"));

                customerList.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("oops you are having a bad day");
            e.printStackTrace();
        }
        return customerList;
    }

    private void showCustomer(String querys) {
        ObservableList<Customer> list = getOrderList(querys);
        colID.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("ID"));
        ColName.setCellValueFactory(new PropertyValueFactory<Customer, String>("Name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Customer, String>("Email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<Customer, String>("Password"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<Customer, String>("ContactNo"));
        colGender.setCellValueFactory(new PropertyValueFactory<Customer, String>("Gender"));
        tvCustomer.setItems(list);
        System.out.println("yoooooo");
    }

    @FXML
    private void gotoProduct(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Products.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gotoProductInfo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ProductInfo.fxml"));
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
    private void gotoDashboard(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("EmployeeDashboard.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void search(ActionEvent event) {
       String query = "SELECT * FROM CUSTOMER";
        if (nameCheck.isSelected()) {
            if (query.contains(" where ")) {
                query += " and CustomerName LIKE '" + tfName.getText() + "%'";
            } else {
                query += " where CustomerName LIKE '" + tfName.getText() + "%'";
            }
        }
         if (emailCheck.isSelected()) {
            if (query.contains(" where ")) {
                query += " and Email LIKE '" + tfEmail.getText() + "%'";
            } else {
                query += " where Email LIKE '" + tfEmail.getText() + "%'";
            }
        }
        if (operatorCheck.isSelected()) {
            String operator;
            if (operatorComboBox.getValue() == "Teletalk") {
                operator = "015%";
            } else if (operatorComboBox.getValue() == "Robi") {
                operator = "018%";
            } else if (operatorComboBox.getValue() == "GrameenPhone") {
                operator = "017%";
            } else if (operatorComboBox.getValue() == "Banglalink") {
                operator = "019%";
            } else {
                operator = "016%";
            }
            if (query.contains(" where ")) {
                query += " and ContactNo LIKE '" + operator + "'";
            } else {
                query += " where ContactNo LIKE '" + operator + "'";
            }
        }
        if (genderCheck.isSelected()) {
            String genderr;
            if (query.contains(" where ")) {
                query += " and Gender ='" + genderComboBox.getValue() + "'";
            } else {
                query += " where Gender ='" + genderComboBox.getValue()  + "'";
            }

        }
        //   if(branchMax.isSelected() || positionMax.isSelected()){
        //  query="SELECT e.EmployeeId, e.Name,e.SEX,e.BirthDate,b.City,e.Position,e.Salary FROM EMPLOYEE e JOIN BRANCH b ON e.BranchId = b.BranchId where "+query+ ")";
        //       }
        showCustomer(query);
    }

    @FXML
    private void tableClick(MouseEvent event) {
    }

    @FXML
    private void showAll(ActionEvent event) {
        String query = "SELECT * FROM CUSTOMER";
        showCustomer(query);
    }

}

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
public class OrderInfoController implements Initializable {

    private JFXDatePicker tfBday;
    @FXML
    private TableColumn<OrderInfo, Integer> colID;
    @FXML
    private TableColumn<OrderInfo, String> ColName;
    @FXML
    private JFXTextField tfName;
    private JFXCheckBox bdayCheck;
    @FXML
    private JFXCheckBox nameCheck;
    @FXML
    private JFXTextField tfAmountL;
    @FXML
    private JFXTextField tfAmountG;
    @FXML
    private JFXCheckBox amountGCheck;
    @FXML
    private JFXCheckBox amountLCheck;
    @FXML
    private JFXCheckBox operatorCheck;
    @FXML
    private JFXComboBox<String> operatorComboBox;
    @FXML
    private TableView<OrderInfo> tvOrder;
    @FXML
    private TableColumn<OrderInfo, Date> colOrderDate;
    @FXML
    private TableColumn<OrderInfo, String> colContactNo;
    @FXML
    private TableColumn<OrderInfo, String> colGender;
    @FXML
    private TableColumn<OrderInfo, Integer> colAmount;
    private String[] operators = {"Teletalk", "Robi", "GrameenPhone", "Banglalink","Airtel"};
    @FXML
    private JFXComboBox<String> orderByComboBox;
    
    private String[] orderBys = {"Amount Ascending", "Amount Descending"};
    @FXML
    private JFXCheckBox orderByCheck;
    @FXML
    private JFXDatePicker tfOrderDate;
    @FXML
    private JFXCheckBox orderDateCheck;
    @FXML
    private JFXCheckBox maxOrderAmountCheck;
    @FXML
    private JFXCheckBox averageOrderCheck;
    private Stage stage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        operatorComboBox.getItems().addAll(operators);
        orderByComboBox.getItems().addAll(orderBys);
        String query="SELECT o.OrderId, c.CustomerName,o.OrderDate,c.ContactNo,c.Gender,o.OrderAmount FROM MARTORDER o JOIN CUSTOMER c ON o.CustomerId = c.CustomerId";
        showOrder(query);
    }    
    
     public ObservableList<OrderInfo> getOrderList(String querys) {
        ObservableList<OrderInfo> orderList = FXCollections.observableArrayList();
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectDB = connectnow.getConnection();
        //  String query = "SELECT e.EmployeeId, e.Name,e.SEX,e.BirthDate,b.City,e.Position,e.Salary FROM EMPLOYEE e JOIN BRANCH b ON e.BranchId = b.BranchId;";

        try {

            Statement statement = connectDB.createStatement();
            ResultSet rs = statement.executeQuery(querys);
            OrderInfo orderInfo;

            while (rs.next()) {
                orderInfo = new OrderInfo(rs.getInt("OrderId"), rs.getString("CustomerName"), rs.getDate("OrderDate"), rs.getString("ContactNo"), rs.getString("Gender"), rs.getInt("OrderAmount"));

                orderList.add(orderInfo);
            }
        } catch (SQLException e) {
            System.out.println("oops you are having a bad day");
            e.printStackTrace();
        }
        return orderList;
    }

    private void showOrder(String querys) {
        ObservableList<OrderInfo> list = getOrderList(querys);
        colID.setCellValueFactory(new PropertyValueFactory<OrderInfo, Integer>("ID"));
        ColName.setCellValueFactory(new PropertyValueFactory<OrderInfo, String>("customerName"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<OrderInfo, Date>("orderDate"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<OrderInfo, String>("contactNo"));
        colGender.setCellValueFactory(new PropertyValueFactory<OrderInfo, String>("gender"));
        colAmount.setCellValueFactory(new PropertyValueFactory<OrderInfo, Integer>("Amount"));
        tvOrder.setItems(list);
        System.out.println("yoooooo");
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
    private void search(ActionEvent event) {
        String query;
        if(maxOrderAmountCheck.isSelected()){
            query="SELECT o.OrderId, c.CustomerName,o.OrderDate,c.ContactNo,c.Gender,o.OrderAmount FROM MARTORDER o JOIN CUSTOMER c ON o.CustomerId = c.CustomerId where OrderAmount =(SELECT MAX(OrderAmount) FROM MARTORDER)";
         
        }else if(averageOrderCheck.isSelected()){
        query="SELECT o.OrderId, c.CustomerName,o.OrderDate,c.ContactNo,c.Gender,o.OrderAmount FROM MARTORDER o JOIN CUSTOMER c ON o.CustomerId = c.CustomerId where OrderAmount >=(SELECT AVG(OrderAmount) FROM MARTORDER)";
        }
        else{
            query ="SELECT o.OrderId, c.CustomerName,o.OrderDate,c.ContactNo,c.Gender,o.OrderAmount FROM MARTORDER o JOIN CUSTOMER c ON o.CustomerId = c.CustomerId";
        }
       
           
        
        
        if (nameCheck.isSelected()) {
            if (query.contains(" where ")) {
                query += " and CustomerName LIKE '" + tfName.getText() + "%'";
            } else {
                query += " where CustomerName LIKE '" + tfName.getText() + "%'";
            } 
        }
        if (operatorCheck.isSelected()) {
            String operator;
            if(operatorComboBox.getValue()=="Teletalk"){
                operator="015%";
            }else if(operatorComboBox.getValue()=="Robi"){
                operator="018%";
            }else if(operatorComboBox.getValue()=="GrameenPhone"){
                operator="017%";
            }else if(operatorComboBox.getValue()=="Banglalink"){
                operator="019%";
            }else{
                operator="016%";
            }
            if (query.contains(" where ")) {
                query += " and ContactNo LIKE '" + operator + "'";
            } else {
                query += " where ContactNo LIKE '" + operator + "'";
            } 
        }
      
        if (amountGCheck.isSelected() && amountLCheck.isSelected()) {
            if (query.contains(" where ")) {
                query += " and OrderAmount between " + tfAmountG.getText() + " and "+tfAmountL.getText();
            } else {
                query += " where OrderAmount between " + tfAmountG.getText() + " and "+tfAmountL.getText();
            } 
        }else if(amountGCheck.isSelected()){
          if (query.contains(" where ")) {
                query += " and OrderAmount>=" + tfAmountG.getText();
            } else {
                query += " where OrderAmount>=" + tfAmountG.getText();
            } 
        }else if(amountLCheck.isSelected()){
          if (query.contains(" where ")) {
                query += " and OrderAmount<=" + tfAmountL.getText();
            } else {
                query += " where OrderAmount<=" + tfAmountL.getText();
            } 
        }
        if (orderDateCheck.isSelected()) {
            if (query.contains(" where ")) {
                query += " and OrderDate <='" + tfOrderDate.getValue() + "'";
            } else {
                query += " where OrderDate <='" + tfOrderDate.getValue() + "'";
            } 
        }
        if (orderByCheck.isSelected()) {
            String orderrr;
            if(orderByComboBox.getValue()=="Amount Descending"){
            orderrr=" order by OrderAmount desc";
            }else{
                orderrr=" order by OrderAmount";
            }
        
                query += orderrr;
        
        }
     //   if(branchMax.isSelected() || positionMax.isSelected()){
      //  query="SELECT e.EmployeeId, e.Name,e.SEX,e.BirthDate,b.City,e.Position,e.Salary FROM EMPLOYEE e JOIN BRANCH b ON e.BranchId = b.BranchId where "+query+ ")";
         //       }
        showOrder(query);
    }

    @FXML
    private void tableClick(MouseEvent event) {
    }

    @FXML
    private void showAll(ActionEvent event) {
         String query="SELECT o.OrderId, c.CustomerName,o.OrderDate,c.ContactNo,c.Gender,o.OrderAmount FROM MARTORDER o JOIN CUSTOMER c ON o.CustomerId = c.CustomerId";
        showOrder(query);
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

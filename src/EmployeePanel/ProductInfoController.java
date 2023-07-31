/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeePanel;

import AdminPanel.EmployInfo;
import com.jfoenix.controls.JFXCheckBox;
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
public class ProductInfoController implements Initializable {

    @FXML
    private TableColumn<ProductInfo,Integer> colID;
    @FXML
    private TableColumn<ProductInfo, String> ColProductName;
    @FXML
    private TableColumn<ProductInfo, String> colCategory;
    @FXML
    private TableColumn<ProductInfo,Integer> colQuantity;
    @FXML
    private TableColumn<ProductInfo, Integer> colPrice;
    @FXML
    private TableColumn<ProductInfo, String> colBranch;
    @FXML
    private JFXTextField tfPriceL;
    @FXML
    private JFXTextField tfName;
    @FXML
    private JFXTextField tfPriceG;
    @FXML
    private JFXCheckBox nameCheck;
    @FXML
    private JFXTextField tfCategory;
    @FXML
    private JFXTextField tfBranch;
    @FXML
    private JFXCheckBox branchCheck;
    @FXML
    private JFXTextField tfQuantity;
    @FXML
    private TableView<ProductInfo> tvProductInfo;
    @FXML
    private JFXCheckBox PriceGCheck;
    @FXML
    private JFXCheckBox PriceLCheck;
    @FXML
    private JFXCheckBox categoryCheck;
    private JFXCheckBox branchMinCheck;
    private JFXCheckBox categoryMinCheck;
    @FXML
    private JFXCheckBox quantityCheck;

    private Stage stage;
    @FXML
    private JFXCheckBox maxPriceCheck;
    @FXML
    private JFXCheckBox averageQuantityCheck;
    @FXML
    private JFXCheckBox shirtAllbranchCheck;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String query = "SELECT p.ProductId,p.ProductName,c.CategoryName,p.Quantity, p.Price,b.City FROM PRODUCT p JOIN CATEGORY c ON p.CategoryId = c.CategoryId JOIN BRANCH b ON p.BranchId = b.BranchId";
        showProduct(query);
    }    
    
    public ObservableList<ProductInfo> getProductsList(String querys) {
        ObservableList<ProductInfo> productInfoList = FXCollections.observableArrayList();
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectDB = connectnow.getConnection();
        //  String query = "SELECT e.EmployeeId, e.Name,e.SEX,e.BirthDate,b.City,e.Position,e.Salary FROM EMPLOYEE e JOIN BRANCH b ON e.BranchId = b.BranchId;";

        try {

            Statement statement = connectDB.createStatement();  
            ResultSet rs = statement.executeQuery(querys);
            ProductInfo productInfo;

            while (rs.next()) {
                productInfo = new ProductInfo(rs.getInt("ProductId"), rs.getString("ProductName"), rs.getString("CategoryName"), rs.getInt("Quantity"), rs.getInt("Price"), rs.getString("City"));

                productInfoList.add(productInfo);
            }
        } catch (SQLException e) {
            System.out.println("oops you are having a bad day");
            e.printStackTrace();
        }
        return productInfoList;
    }

    private void showProduct(String querys) {
        ObservableList<ProductInfo> list = getProductsList(querys);
        colID.setCellValueFactory(new PropertyValueFactory<ProductInfo, Integer>("ID"));
        ColProductName.setCellValueFactory(new PropertyValueFactory<ProductInfo, String>("ProductName"));
        colCategory.setCellValueFactory(new PropertyValueFactory<ProductInfo, String>("Category"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<ProductInfo, Integer>("Quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<ProductInfo, Integer>("Price"));
        colBranch.setCellValueFactory(new PropertyValueFactory<ProductInfo, String>("Branch"));
        tvProductInfo.setItems(list);
        System.out.println("yoooooo");
    }
    

    @FXML
    private void search(ActionEvent event) {
         String query;
        if(maxPriceCheck.isSelected()){
            query="SELECT p.ProductId,p.ProductName,c.CategoryName,p.Quantity, p.Price,b.City FROM PRODUCT p JOIN CATEGORY c ON p.CategoryId = c.CategoryId JOIN BRANCH b ON p.BranchId = b.BranchId where Price =(SELECT MAX(Price) FROM PRODUCT)";
        }else if(averageQuantityCheck.isSelected()){
        query = "SELECT p.ProductId,p.ProductName,c.CategoryName,p.Quantity, p.Price,b.City FROM PRODUCT p JOIN CATEGORY c ON p.CategoryId = c.CategoryId JOIN BRANCH b ON p.BranchId = b.BranchId where Quantity <(SELECT AVG(Quantity) FROM PRODUCT)";
        }else{
            query ="SELECT p.ProductId,p.ProductName,c.CategoryName,p.Quantity, p.Price,b.City FROM PRODUCT p JOIN CATEGORY c ON p.CategoryId = c.CategoryId JOIN BRANCH b ON p.BranchId = b.BranchId";
        }
        
        if (nameCheck.isSelected()) {
            if (query.contains(" where ")) {
                query += " and ProductName LIKE '" + tfName.getText() + "%'";
            } else {
                query += " where ProductName LIKE '" + tfName.getText() + "%'";
            } 
        }
        if (quantityCheck.isSelected()) {
            if (query.contains(" where ")) {
                query += " and Quantity <=" + tfQuantity.getText();
            } else {
                query += " where Quantity <=" + tfQuantity.getText();
            } 
        }
        if (categoryCheck.isSelected()) {
            if (query.contains(" where ")) {
                query += " and CategoryName = '" + tfCategory.getText() + "'";
            } else {
                query += " where CategoryName = '" + tfCategory.getText() + "'";
            } 
        }
        if (PriceGCheck.isSelected() && PriceLCheck.isSelected()) {
            if (query.contains(" where ")) {
                query += " and Price between " + tfPriceG.getText() + " and "+tfPriceL.getText();
            } else {
                query += " where Price between " + tfPriceG.getText() + " and "+tfPriceL.getText();
            } 
        }else if(PriceGCheck.isSelected()){
          if (query.contains(" where ")) {
                query += " and Price>=" + tfPriceG.getText();
            } else {
                query += " where Price>=" + tfPriceG.getText();
            } 
        }else if(PriceLCheck.isSelected()){
          if (query.contains(" where ")) {
                query += " and Price<=" + tfPriceL.getText();
            } else {
                query += " where Price<=" + tfPriceL.getText();
            } 
        }
        if (branchCheck.isSelected()) {
            if (query.contains(" where ")) {
                query += " and City = '" + tfBranch.getText() + "'";
            } else {
                query += " where City = '" + tfBranch.getText() + "'";
            } 
        }
        showProduct(query);
    }

    @FXML
    private void tableClick(MouseEvent event) {
    }

    @FXML
    private void showAll(ActionEvent event) {
        String query = "SELECT p.ProductId,p.ProductName,c.CategoryName,p.Quantity, p.Price,b.City FROM PRODUCT p JOIN CATEGORY c ON p.CategoryId = c.CategoryId JOIN BRANCH b ON p.BranchId = b.BranchId";
        showProduct(query);
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
    private void gotoProductInfo(ActionEvent event) {
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
    private void employeeDashboard(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("EmployeeDashboard.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gotoCustomerInfo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CustomerInfo.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeePanel;

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
import AdminPanel.Employees;

/**
 * FXML Controller class
 *
 * @author Orkid
 */
public class ProductsController implements Initializable {
    @FXML
    private JFXTextField tfQuantity;
    @FXML
    private JFXTextField tfPrice;
    @FXML
    private JFXTextField tfProductName;
    @FXML
    private JFXTextField tfCategory;
    
     @FXML
    private TableView<Products> tvProduct;
    @FXML
    private TableColumn<Products, Integer> colID;
    @FXML
    private TableColumn<Products, String> ColProductName;
    @FXML
    private TableColumn<Products, Integer> ColCategory;
    @FXML
    private TableColumn<Products, Integer> colQuantity;
    @FXML
    private TableColumn<Products, Integer> colProductPrice;
    @FXML
    private TableColumn<Products, Integer> colBranchID;
    @FXML
    private JFXTextField tfBranchID;
    
   private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showProduct();
    }    
    
      public ObservableList<Products> getProductList(){
        ObservableList<Products> productList = FXCollections.observableArrayList();
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectDB = connectnow.getConnection();
         String query = "select * from PRODUCT";
         
          try {

            Statement statement = connectDB.createStatement();
            ResultSet rs = statement.executeQuery(query);
            Products product;

            while (rs.next()) {
               product =new Products(rs.getInt("ProductId"),rs.getString("ProductName"),rs.getInt("CategoryId"),rs.getInt("Quantity"),rs.getInt("Price"),rs.getInt("BranchId"));
               // System.out.println(employee.getName());,rs.getString("Email"),
               productList.add(product);
               // System.out.println( rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("oops you are having a bad day");
            e.printStackTrace();
        }
          return productList;
    }
      
       private void showProduct() {
           ObservableList<Products> list = getProductList();
           colID.setCellValueFactory(new PropertyValueFactory<Products,Integer>("ID"));
           ColProductName.setCellValueFactory(new PropertyValueFactory<Products,String>("ProductName"));
           ColCategory.setCellValueFactory(new PropertyValueFactory<Products,Integer>("CategoryId"));
           colQuantity.setCellValueFactory(new PropertyValueFactory<Products,Integer>("Quantity"));
           colProductPrice.setCellValueFactory(new PropertyValueFactory<Products,Integer>("Price"));
           colBranchID.setCellValueFactory(new PropertyValueFactory<Products,Integer>("BranchID"));
           tvProduct.setItems(list);
           System.out.println("yoooooo");
    }


    @FXML
    private void Insert(ActionEvent event) {
        String query;
         if(tfQuantity.getText().isEmpty()){
            query="INSERT INTO PRODUCT(ProductName,CategoryId,Price,BranchId) VALUES('"+tfProductName.getText()+"',"+tfCategory.getText()+","+tfPrice.getText()+","+tfBranchID.getText()+")";
        }else{
             query="INSERT INTO PRODUCT VALUES('"+tfProductName.getText()+"',"+tfCategory.getText()+","+tfQuantity.getText()+","+tfPrice.getText()+","+tfBranchID.getText()+")";
        }
       
        queryPerform(query);
    }

    @FXML
    private void Delete(ActionEvent event) {
        Products product = tvProduct.getSelectionModel().getSelectedItem();
        String query="DELETE FROM PRODUCT WHERE  ProductId = "+product.getID();
        queryPerform(query);
    }

    @FXML
    private void Update(ActionEvent event) {
         Products product = tvProduct.getSelectionModel().getSelectedItem();
         String query="UPDATE PRODUCT SET ProductName ='"+tfProductName.getText()+"',CategoryId="+tfCategory.getText()+",Quantity="+tfQuantity.getText()+",Price="+tfPrice.getText()+",BranchID="+tfBranchID.getText()+" WHERE  ProductId = "+product.getID();
          queryPerform(query);
    }
    
     private void queryPerform(String query) {
        
         DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectDB = connectnow.getConnection();
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
         showProduct();
    }

    @FXML
    private void tableClick(MouseEvent event) {
        Products product = tvProduct.getSelectionModel().getSelectedItem();
         tfProductName.setText(product.getProductName());
        tfCategory.setText(""+product.getCategoryId());
        tfQuantity.setText(""+product.getQuantity());
        tfPrice.setText(""+product.getPrice());
         tfBranchID.setText(""+product.getBranchID());
    }

    @FXML
    private void gotoProduct(ActionEvent event) {
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
    private void employeeDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("EmployeeDashboard.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gotoCustomerInfo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Products.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}

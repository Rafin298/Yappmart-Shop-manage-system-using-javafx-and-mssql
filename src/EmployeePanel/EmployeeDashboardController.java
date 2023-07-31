/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeePanel;

import CustomerPanel.ProductPanelController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import yapp.DatabaseConnection;

/**
 * FXML Controller class
 *
 * @author Orkid
 */
public class EmployeeDashboardController implements Initializable {

    @FXML
    private Label totalProductLabel;
    private Label womenLabel;
    private Label kidLabel;
    
    private Stage stage;
    @FXML
    private Label menQuantity;
    @FXML
    private Label WomenQuantity;
    @FXML
    private Label kidQuantity;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String totalProduct="select sum(Quantity) from PRODUCT";

           
            String mentypee="select count(ProductId) from PRODUCT group by CategoryId having CategoryId=1";
         String menQuantityy="select sum(Quantity) from PRODUCT group by CategoryId having CategoryId=1";
         
             String womentypee="select count(ProductId) from PRODUCT group by CategoryId having CategoryId=2";
         String womenQuantityy="select sum(Quantity) from PRODUCT group by CategoryId having CategoryId=2";
         
             String kidtypee="select count(ProductId) from PRODUCT group by CategoryId having CategoryId=3";
         String kidQuantityy="select sum(Quantity) from PRODUCT group by CategoryId having CategoryId=3";
        
           
           totalProductLabel.setText(queryExecutor(totalProduct));
           menQuantity.setText(queryExecutor(menQuantityy));
       //    menType.setText(queryExecutor(mentypee));
      //     womenType.setText(queryExecutor(womentypee));
           WomenQuantity.setText(queryExecutor(womenQuantityy));
        //   kidType.setText(queryExecutor(kidtypee));
           kidQuantity.setText(queryExecutor(kidQuantityy));
           
      
    }    
     private String queryExecutor(String query) {
           DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectDB = connectnow.getConnection();
        String ss="";
          try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(query);
            while (queryResult.next()) {
                    ss="" +queryResult.getInt(1);
                }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
          return ss;
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
    private void gotoCustomerInfo(ActionEvent event) throws IOException {
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
    private void employeeDashboard(ActionEvent event) {
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminPanel;

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
public class AdminDashBoardController implements Initializable {

    @FXML
    private Label employeeLabel;
    @FXML
    private Label customerLabel;
    @FXML
    private Label soldLabel;
    @FXML
    private Label orderLabel;
    private Stage stage;
    @FXML
    private Label dhakaEm;
    @FXML
    private Label sylhetEm;
    @FXML
    private Label khulnaEm;
    @FXML
    private Label comillaEm;
    @FXML
    private Label maleCount;
    @FXML
    private Label femaleCount;
    @FXML
    private Label maleOrderCount;
    @FXML
    private Label femaleOrderCount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         String totalEmployee="select count(EmployeeId) from EMPLOYEE";
         String totalCustomer="select count(CustomerId) from CUSTOMER";
          String totalSold="select SUM(OrderAmount) from MARTORDER";
           String totalOrder="select count(OrderId) from MARTORDER";
           
           String DhakaEmployee="select count(EmployeeId) from EMPLOYEE group by BranchId having BranchId=1";
           String SylhetEmployee="select count(EmployeeId) from EMPLOYEE group by BranchId having BranchId=2";
           String KhulnaEmployee="select count(EmployeeId) from EMPLOYEE group by BranchId having BranchId=3";
           String ComillaEmployee="select count(EmployeeId) from EMPLOYEE group by BranchId having BranchId=4";
           
           String mennCount="select count(CustomerId) from CUSTOMER where Gender='Male'";
           String womennCount="select count(CustomerId) from CUSTOMER where Gender='Female'";
           
           String mennOrderCount="select count(m.CustomerId) FROM MARTORDER m JOIN CUSTOMER c ON m.CustomerId = c.CustomerId where c.Gender='male'";
           String womennOrderCount="select count(m.CustomerId) FROM MARTORDER m JOIN CUSTOMER c ON m.CustomerId = c.CustomerId where c.Gender='female'";
           
           employeeLabel.setText(queryExecutor(totalEmployee));
           customerLabel.setText(queryExecutor(totalCustomer));
           soldLabel.setText(queryExecutor(totalSold));
           orderLabel.setText(queryExecutor(totalOrder));
            dhakaEm.setText(queryExecutor(DhakaEmployee));
            sylhetEm.setText(queryExecutor(SylhetEmployee));
            khulnaEm.setText(queryExecutor(KhulnaEmployee));
            comillaEm.setText(queryExecutor(ComillaEmployee));
            
            maleCount.setText(queryExecutor(mennCount));
            femaleCount.setText(queryExecutor(womennCount));
            maleOrderCount.setText(queryExecutor(mennOrderCount));
            femaleOrderCount.setText(queryExecutor(womennOrderCount));
     
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
    private void gotoOrderInfo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("OrderInfo.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}

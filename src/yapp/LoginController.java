/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yapp;

import CustomerPanel.ProductPanelController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Orkid
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane anPane;
    @FXML
    private Label label;
    private JFXTextField tfUserName;
    @FXML
    private JFXButton log;
    @FXML
    private JFXPasswordField tfPassword;
    @FXML
    private JFXTextField tfEmail;
    @FXML
    private JFXRadioButton selectAdmin;
    @FXML
    private ToggleGroup loginType;
    @FXML
    private JFXRadioButton selectEmployee;
    @FXML
    private JFXRadioButton selectCustomer;

    private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void close(ActionEvent event) {
    }

    @FXML
    private void loginButton(ActionEvent event) throws IOException {
        //   System.out.println(tfUserName.getText());
        //  System.out.println(tfPassword.getText());

        validateLogin(event);

    }

    private void validateLogin(ActionEvent event) throws IOException {
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectDB = connectnow.getConnection();
        String query;
        String location;
        if(selectAdmin.isSelected()){
        query= "select count(1) from ADMIN where email = '" + tfEmail.getText() + "' and Password='" + tfPassword.getText() + "'";
        location="/AdminPanel/AdminDashBoard.fxml";
        }else if(selectEmployee.isSelected()){
         query= "select count(1) from EMPLOYEE where Email = '" + tfEmail.getText() + "' and Password='" + tfPassword.getText() + "'";
          location="/EmployeePanel/EmployeeDashboard.fxml";
        }else{
        query= "select count(1) from CUSTOMER where Email = '" + tfEmail.getText() + "' and Password='" + tfPassword.getText() + "'";
         location="/CustomerPanel/ProductPanel.fxml";
        }
        //  String querys = "select * from ADMIN";

        //   String sql = "INSERT INTO Customer (FirstName, LastName)" + "VALUES ('bh', 'bh')";
        Parent root;
        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(query);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    if(selectCustomer.isSelected()){
                        FXMLLoader loader=new FXMLLoader(getClass().getResource(location));
                        root =loader.load();
                        ProductPanelController productPanelController=loader.getController();
                        productPanelController.displayName(tfEmail.getText());
                    }else{
                       root = FXMLLoader.load(getClass().getResource(location));
                    }
                    
                    //   Parent root = FXMLLoader.load(getClass().getResource("EmployInfo.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    System.out.println("Failed");
                }
            }
        } catch (SQLException e) {
            System.out.println("oops you are having a bad day");
            e.printStackTrace();
        }

    }

    public void gotoAnotherFXML(String location) throws IOException {

    }

    @FXML
    private void gotoSignUp(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("CustomerSignUp.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

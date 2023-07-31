/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yapp;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Orkid
 */
public class CustomerSignUpController implements Initializable {
    private Stage stage;
    @FXML
    private JFXTextField tfName;
    @FXML
    private JFXTextField tfEmail;
    @FXML
    private JFXPasswordField tfPassword;
    @FXML
    private JFXTextField tfContactNo;
    @FXML
    private JFXComboBox<String> tfGender;
    private String[] gender ={"Male","Female"};
    @FXML
    private Pane modal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfGender.getItems().addAll(gender);
    }    

    @FXML
    private void Select(ActionEvent event) {
    }

    @FXML
    private void signUp(ActionEvent event) {
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectDB = connectnow.getConnection();
        String query="INSERT INTO CUSTOMER VALUES('"+tfName.getText()+"','"+tfEmail.getText()+"','"+tfPassword.getText()+"','"+tfContactNo.getText()+"','"+tfGender.getValue()+"')";
        
        
         try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(query);
           modal.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
         //showEmployee();
    }

    @FXML
    private void gotoLogin(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void modalOk(ActionEvent event) {
        modal.setVisible(false);
    }
    
}

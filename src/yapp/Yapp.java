/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Orkid
 */
public class Yapp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
   //   Parent root = FXMLLoader.load(getClass().getResource("/EmployeePanel/ProductInfo.fxml"));
    //  Parent root = FXMLLoader.load(getClass().getResource("/EmployeePanel/EmployeeDashboard.fxml"));
      Parent root = FXMLLoader.load(getClass().getResource("Login.fxml")); //main
       //  Parent root = FXMLLoader.load(getClass().getResource("/AdminPanel/Employee.fxml"));
       //   Parent root = FXMLLoader.load(getClass().getResource("/AdminPanel/EmployInfo.fxml"));
    //   Parent root = FXMLLoader.load(getClass().getResource("/AdminPanel/OrderInfo.fxml"));
       // Parent root = FXMLLoader.load(getClass().getResource("/AdminPanel/AdminDashBoard.fxml"));
      //     Parent root = FXMLLoader.load(getClass().getResource("/CustomerPanel/ProductPanel.fxml"));
     //   Parent root = FXMLLoader.load(getClass().getResource("CustomerSignUp.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

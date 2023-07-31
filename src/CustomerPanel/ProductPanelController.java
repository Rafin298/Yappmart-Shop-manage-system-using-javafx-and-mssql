/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerPanel;

import AdminPanel.Employees;
import EmployeePanel.ProductInfo;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import yapp.DatabaseConnection;

/**
 * FXML Controller class
 *
 * @author Orkid
 */
public class ProductPanelController implements Initializable {

    @FXML
    private TableView<ProductInfo> tvProductInfo;
    @FXML
    private TableColumn<ProductInfo, Integer> colID;
    @FXML
    private TableColumn<ProductInfo, String> ColProductName;
    @FXML
    private TableColumn<ProductInfo, String> colCategory;
    @FXML
    private TableColumn<ProductInfo, Integer> colQuantity;
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
    private JFXCheckBox PriceGCheck;
    @FXML
    private JFXCheckBox PriceLCheck;
    @FXML
    private JFXCheckBox categoryCheck;
    @FXML
    private JFXCheckBox branchCheck;
    @FXML
    private JFXCheckBox branchMinCheck;
    @FXML
    private JFXCheckBox categoryMinCheck;
    @FXML
    private JFXComboBox<String> branchComboBox;
    @FXML
    private JFXComboBox<String> categoryComboBox;

    private String[] branches = {"Dhaka", "Sylhet", "Khulna", "Comilla"};
    private String[] categories = {"Men", "Women", "Kids"};
    @FXML
    private TableView<Cart> tvCart;
    @FXML
    private TableColumn<Cart, String> CartProductName;
    @FXML
    private TableColumn<Cart, String> cartCategory;
    @FXML
    private TableColumn<Cart, Integer> cartQuantity;
    @FXML
    private TableColumn<Cart, Integer> cartPrice;

    private Stage stage;
    @FXML
    private Label totalAmount;
    @FXML
    private Label nameLabel;
    @FXML
    private Label welcome;

    /**
     * Initializes the controller class.
     */
    String query;
    @FXML
    private Label total;
    @FXML
    private Pane modal;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        branchComboBox.getItems().addAll(branches);
        categoryComboBox.getItems().addAll(categories);
         query = "SELECT p.ProductId,p.ProductName,c.CategoryName,p.Quantity, p.Price,b.City FROM PRODUCT p JOIN CATEGORY c ON p.CategoryId = c.CategoryId JOIN BRANCH b ON p.BranchId = b.BranchId";
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

    private void showCart(String querys) {

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
    private void gotoProduct(ActionEvent event) {
    }

    @FXML
    private void search(ActionEvent event) {
       //String query;
      
            query = "SELECT p.ProductId,p.ProductName,c.CategoryName,p.Quantity, p.Price,b.City FROM PRODUCT p JOIN CATEGORY c ON p.CategoryId = c.CategoryId JOIN BRANCH b ON p.BranchId = b.BranchId";
       

        if (nameCheck.isSelected()) {
            if (query.contains(" where ")) {
                query += " and ProductName LIKE '" + tfName.getText() + "%'";
            } else {
                query += " where ProductName LIKE '" + tfName.getText() + "%'";
            }
        }

        if (categoryCheck.isSelected()) {
            if (query.contains(" where ")) {
                query += " and CategoryName = '" + categoryComboBox.getValue() + "'";
            } else {
                query += " where CategoryName = '" + categoryComboBox.getValue() + "'";
            }
        }
        if (PriceGCheck.isSelected() && PriceLCheck.isSelected()) {
            if (query.contains(" where ")) {
                query += " and Price between " + tfPriceG.getText() + " and " + tfPriceL.getText();
            } else {
                query += " where Price between " + tfPriceG.getText() + " and " + tfPriceL.getText();
            }
        } else if (PriceGCheck.isSelected()) {
            if (query.contains(" where ")) {
                query += " and Price>=" + tfPriceG.getText();
            } else {
                query += " where Price>=" + tfPriceG.getText();
            }
        } else if (PriceLCheck.isSelected()) {
            if (query.contains(" where ")) {
                query += " and Price<=" + tfPriceL.getText();
            } else {
                query += " where Price<=" + tfPriceL.getText();
            }
        }
        if (branchCheck.isSelected()) {
            if (query.contains(" where ")) {
                query += " and City = '" + branchComboBox.getValue() + "'";
            } else {
                query += " where City = '" + branchComboBox.getValue() + "'";
            }
        }
        showProduct(query);
    }

    @FXML
    private void tableClick(MouseEvent event) {
//        Employees employee = tvEmployee.getSelectionModel().getSelectedItem();
//       
//       
//        
//        tfName.setText(employee.getName());
//        tfEmail.setText(employee.getEmail());
//        tfPassword.setText(employee.getPassword());
//        tfBirthdate.setValue(localDate);
//        tfBranchId.setText(""+employee.getBranchId());
//        tfPosition.setText(employee.getPosition());
//        tfSex.setText(employee.getSex());
//        tfSalary.setText(""+employee.getSalary());
    }

    @FXML
    private void showAll(ActionEvent event) {
        query = "SELECT p.ProductId,p.ProductName,c.CategoryName,p.Quantity, p.Price,b.City FROM PRODUCT p JOIN CATEGORY c ON p.CategoryId = c.CategoryId JOIN BRANCH b ON p.BranchId = b.BranchId";
        showProduct(query);
    }

    @FXML
    private void addToCart(ActionEvent event) {
        ProductInfo productInfo = tvProductInfo.getSelectionModel().getSelectedItem();
        //  Cart cart= tvCart.getSelectionModel().getSelectedItem();

        CartProductName.setCellValueFactory(new PropertyValueFactory<Cart, String>("ProductName"));
        cartCategory.setCellValueFactory(new PropertyValueFactory<Cart, String>("Category"));
        cartQuantity.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("Quantity"));
        cartPrice.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("Price"));
        tvCart.getItems().add(new Cart(productInfo.getProductName(), productInfo.getCategory(), 1, productInfo.getPrice()));
        // tvCart.getItems().add(new Cart("Hi", "Hello",2,3));
        int amount = Integer.parseInt(totalAmount.getText());
        //  ProductInfo productInfo = tvProductInfo.getSelectionModel().getSelectedItem();
        amount = amount + productInfo.getPrice();
        totalAmount.setText("" + amount);
        
           String queryss="UPDATE PRODUCT SET Quantity=Quantity-1 where ProductId="+productInfo.getID();
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectDB = connectnow.getConnection();
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(queryss);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        showProduct(query);
    }

    @FXML
    private void LogOut(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/yapp/Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buy(ActionEvent event) {
        String id = "5";
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectDB = connectnow.getConnection();
        String queryForId = "select CustomerId from CUSTOMER where Email='" + nameLabel.getText() + "';";
        try {

            Statement statement = connectDB.createStatement();
            ResultSet rs = statement.executeQuery(queryForId);
            while (rs.next()) {
                id = "" + rs.getInt("CustomerId");
            }
        } catch (SQLException e) {
            System.out.println("oops you are having a bad day");
            e.printStackTrace();
        }
        String querys = "INSERT INTO MARTORDER(CustomerId,OrderAmount) VALUES(" + id + "," + totalAmount.getText() + ")";
        System.out.println(id);

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(querys);
            modal.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
     totalAmount.setText("00");
        tvCart.getItems().clear();
       // String query = "SELECT p.ProductId,p.ProductName,c.CategoryName,p.Quantity, p.Price,b.City FROM PRODUCT p JOIN CATEGORY c ON p.CategoryId = c.CategoryId JOIN BRANCH b ON p.BranchId = b.BranchId";
        showProduct(query);
    }

    public void displayName(String username) {
        nameLabel.setText(username);
    }

    @FXML
    private void modalOk(ActionEvent event) {
         modal.setVisible(false);
    }

}

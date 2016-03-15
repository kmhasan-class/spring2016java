/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.demo.gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author kmhasan
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private TextField distributorIdField;
    @FXML
    private TextField nameField;
    @FXML
    private TextArea addressArea;
    @FXML
    private TextField phoneField;
    
    private String DB_URL = "jdbc:mysql://172.17.0.134/cse2015db";
    private String DB_USER = "java";
    private String DB_PASS = "spring2016";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String query = "SELECT * FROM distributor";
        
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                int distributorId = resultSet.getInt("distributorId");
                String name = resultSet.getString("name");
                System.out.printf("%d %s\n", distributorId, name);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void handleResetAction(ActionEvent event) {
        distributorIdField.setText("");
        nameField.setText("");
        addressArea.setText("");
        phoneField.setText("");
    }

    @FXML
    private void handleSubmitAction(ActionEvent event) {
        int distributorId = Integer.parseInt(distributorIdField.getText());
        String name = nameField.getText();
        String address = addressArea.getText();
        String phone = phoneField.getText();
        
        String query = "INSERT INTO distributor VALUES(" + distributorId + ", '" + name + "', '" + address + "', '" + phone + "')";
        
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

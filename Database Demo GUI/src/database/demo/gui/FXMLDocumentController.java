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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
    
    private Connection connection;
    private Statement statement;
    
    private ObservableList<Distributor> distributors;
    private int currentIndex;
    @FXML
    private Button previousButton;
    @FXML
    private Button nextButton;
    @FXML
    private ListView<Distributor> distributorList;
    @FXML
    private ComboBox<Distributor> distributorCombo;
    
    // Read up on MVC architecture
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        distributors = FXCollections.observableArrayList();
        distributorList.setItems(distributors);
        distributorCombo.setItems(distributors);
        currentIndex = 0;
        
        String query = "SELECT * FROM distributor";
       
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                int distributorId = resultSet.getInt("distributorId");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                
                Distributor distributor = new Distributor(distributorId, name, address, phone);
                System.out.printf("%d %s\n", distributorId, name);
                
                distributors.add(distributor);
            }
            
            display();
            previousButton.setDisable(true);
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
//            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
//            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            Distributor distributor = new Distributor(distributorId, name, address, phone);
            distributors.add(distributor);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void display() {
        int id = distributors.get(currentIndex).getId();
        String name = distributors.get(currentIndex).getName();
        String address = distributors.get(currentIndex).getAddress();
        String phone = distributors.get(currentIndex).getPhone();
        
        nameField.setText(name);
        distributorIdField.setText(id + "");
        addressArea.setText(address);
        phoneField.setText(phone);
    }
    
    @FXML
    private void handlePreviousAction(ActionEvent event) {
        currentIndex--;
        display();
    }

    @FXML
    private void handleNextAction(ActionEvent event) {
        // ADD CODE TO STOP THE CRASHES
        currentIndex++;
        previousButton.setDisable(false);
        display();
    }

    @FXML
    private void handleSearchAction(ActionEvent event) {
        int id = Integer.parseInt(distributorIdField.getText());
        
        for (int i = 0; i < distributors.size(); i++)
            if (distributors.get(i).getId() == id) {
                currentIndex = i;
                display();
                break;
            }
        
        // ADD CODE TO REPORT ERROR WHEN DISTRIBUTOR IS NOT FOUND
        // You can add a label at the bottom to report any status
    }
    
}
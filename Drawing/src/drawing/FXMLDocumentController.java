/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 *
 * @author kmhasan
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private Canvas canvas;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double width = canvas.getWidth();
        double height = canvas.getHeight();
        double radius = 200;
//        gc.strokeRect(0, 0, 200, 100);
        gc.setFill(Color.PINK);
        gc.fillOval(width / 2 - radius, height / 2 - radius, radius * 2, radius * 2);
        gc.strokeOval(width / 2 - radius * 2, height / 2 - radius, radius * 4, radius * 2);
        gc.strokeL
    }    
    
}

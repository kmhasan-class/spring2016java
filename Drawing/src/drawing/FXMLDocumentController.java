/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author kmhasan
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Canvas canvas;
    private GraphicsContext gc;
    private double radius = 30;
    private double dx = radius + 1;
    private double dy = radius + 1;
    private int signX = +1;
    private int signY = +1;
    
    private void drawBall(double centerX, double centerY, double radius) {
        gc.setFill(Color.RED);
        gc.fillOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = canvas.getGraphicsContext2D();
        double width = canvas.getWidth();
        double height = canvas.getHeight();
        
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(5),
            event -> {
                gc.setFill(Color.YELLOW);
                gc.fillRect(0, 0, width, height);
                drawBall(dx, dy, radius);
                dx = dx + 5 * signX;
                dy = dy + 1 * signY;
                if (dx + radius >= width || dx - radius <= 0)
                    signX = -signX;
                if (dy + radius >= height || dy - radius <= 0)
                    signY = -signY;
            });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.playFromStart();
        
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pkg1.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

/**
 *
 * @author kmhasan
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {
//        System.out.println("You clicked me!");
//        label.setText("Hello World!");
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        try {
            RandomAccessFile input = new RandomAccessFile(file, "r");
            String line;

            /*
            Important/useful methods of String class for this assignment:
            length()
            charAt()
            toLowerCase()/toUpperCase()
            substring()
            */
            int aCount = 0;
            while (true) {
                line = input.readLine();
                if (line == null)
                    break;
                line = line.toLowerCase();
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == 'a')
                        aCount++;
                }
                System.out.printf("Substring from 2 to 7 [%s]\n", line.substring(2, 7));
            }
            label.setText("# of as " + aCount);
            System.out.printf("# of as %d\n", aCount);
        } catch (FileNotFoundException fnfe) {
            System.err.printf("Could not locate the file\n");
        } catch (IOException ioe) {
            System.err.printf("Some error occured\n");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

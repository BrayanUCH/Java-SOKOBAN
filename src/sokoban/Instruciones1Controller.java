/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BRAYAN
 */
public class Instruciones1Controller implements Initializable {

    @FXML
    private Button next;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void NEXT(ActionEvent event) throws IOException {
        //evento del boton Siguiente que llama al controlador Instruciones 
        Stage stage = (Stage) this.next.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("Instrucciones.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/CSS.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle("INSTRUCCIONES");
        stage.show();

    }

}

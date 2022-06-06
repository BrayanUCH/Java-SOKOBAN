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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BRAYAN
 */
public class MenuController implements Initializable {

    @FXML
    private Button iniciar;
    @FXML
    private Button salir;
    @FXML
    private Button instrucciones;
    @FXML
    private Button BtnAcercaDe;
    @FXML
    private Button BtnNiveles;
    @FXML
    private TextField nombre;
    @FXML
    private VBox Hbox;
    @FXML
    private Button BTNlogros;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void pressedIniciar(MouseEvent event) throws IOException {
        //evento del boton Nuevo Juego que llama al controlador del nivel 1
        
        String name = nombre.getText();
        if ("".equals(name)) {
            Alert dialogo = new Alert(Alert.AlertType.ERROR);
            dialogo.setTitle("ERROR");
            dialogo.setContentText("VEREFIQUE EL NOMBRE");
            dialogo.showAndWait();
        } else {
            try {
                Stage stage = (Stage) this.iniciar.getScene().getWindow();
                //carca la scene del nivel 1
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Nivel1.fxml"));
                Parent root = loader.load();

                //obtiene el controlador del la Scene del nivel 1
                Nivel1Controller scene1Controller = loader.getController();
                //pasa los datos
                scene1Controller.DatosDeTransferencia(name);

                //muestra la scena del nivel 1 en una nueva ventana 
                Scene scene = new Scene(root);
                scene.getStylesheets().add("/css/CSS.css");
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setResizable(false);
                stage.setTitle("NIVEL 1");
                stage.show();
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }

    @FXML
    private void pressedSalir(MouseEvent event) {
        //evento del boton salir que cierra el juego o programa
        Stage stage = (Stage) this.salir.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void pressedInstructions(MouseEvent event) throws IOException {
        //evento del boton Instrucciones que llama al controlador Instrucciones 1
        Stage stage = (Stage) this.instrucciones.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("Instrucciones 1.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/CSS.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle("INSTRUCCIONES");
        stage.show();

    }

    @FXML
    private void AcercaDe(ActionEvent event) throws IOException {
        //evento del boton Acerca De: que llama al controlador Acerca de
        Stage stage = (Stage) this.BtnAcercaDe.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("Acerca de.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/CSS.css");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setTitle("ACERDA DE:");
        stage.show();
    }

    @FXML
    private void Niveles(ActionEvent event) throws IOException {
        //evento del boton Niveles que llama al controlador NIVELES
        String name = nombre.getText();
        if ("".equals(name)) {
            Alert dialogo = new Alert(Alert.AlertType.ERROR);
            dialogo.setTitle("ERROR");
            dialogo.setContentText("VEREFIQUE EL NOMBRE");
            dialogo.showAndWait();
        } else {
            try {
                Stage stage = (Stage) this.iniciar.getScene().getWindow();
                //carca la scene del niveles
                FXMLLoader loader = new FXMLLoader(getClass().getResource("NIVELES.fxml"));
                Parent root = loader.load();

                //obtiene el controlador del la Scene del niveles
                NIVELESController niveles = loader.getController();
                //pasa los datos
                niveles.DatosDeTransferencia(name);

                //muestra la scena del niveles en una nueva ventana 
                Scene scene = new Scene(root);
                scene.getStylesheets().add("/css/CSS.css");
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setResizable(false);
                stage.setTitle("NIVELES");
                stage.show();
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }

    @FXML
    private void logros(ActionEvent event) throws IOException {
        //evento del boton logros que llama al controlador Logros
        Stage stage = (Stage) this.BtnAcercaDe.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("logros.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/CSS.css");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setTitle("LOGROS");
        stage.show();

    }

}

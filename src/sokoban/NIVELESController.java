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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BRAYAN
 */
public class NIVELESController implements Initializable {

    @FXML
    private ImageView imgSOKOBAN;
    @FXML
    private Button Nivel1;
    @FXML
    private Button Nivel2;
    @FXML
    private Button Nivel3;
    @FXML
    private Button Nivel4;
    @FXML
    private Button Nivel6;
    @FXML
    private Button Nivel5;
    @FXML
    private Button Nivel7;
    @FXML
    private Button BtnRegresar;

    //datos a transferir
    String NOMBRE = "";
    int DATOS1 = 29 + 15;
    int DATOS2 = 21 + 15;
    int DATOS3 = 99 + 15;
    int DATOS4 = 59 + 15;
    int DATOS5 = 160 + 15;
    int DATOS6 = 264 + 15;
    int DATOS7 = 59 + 15;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Nivel1(ActionEvent event) throws IOException {
        //evento del boton Nivel 1 que llama al metodo IniciarEnNivel()
        //el cual se encarga de definir el nivel a llamar 
        IniciarEnNivel(1);
    }

    @FXML
    private void Nivel2(ActionEvent event) throws IOException {
        //evento del boton Nivel 2 que llama al metodo IniciarEnNivel()
        //el cual se encarga de definir el nivel a llamar 
        IniciarEnNivel(2);
    }

    @FXML
    private void Nivel3(ActionEvent event) throws IOException {
        //evento del boton Nivel 3 que llama al metodo IniciarEnNivel()
        //el cual se encarga de definir el nivel a llamar 
        IniciarEnNivel(3);
    }

    @FXML
    private void Nivel4(ActionEvent event) throws IOException {
        //evento del boton Nivel 4 que llama al metodo IniciarEnNivel()
        //el cual se encarga de definir el nivel a llamar 
        IniciarEnNivel(4);
    }

    @FXML
    private void Nivel6(ActionEvent event) throws IOException {
        //evento del boton Nivel 6 que llama al metodo IniciarEnNivel()
        //el cual se encarga de definir el nivel a llamar 
        IniciarEnNivel(6);
    }

    @FXML
    private void Regresar(ActionEvent event) throws IOException {
        //evento del boton Regresar que llama al controlador Menu
        Stage stage = (Stage) this.BtnRegresar.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/CSS.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("MENU");
        stage.show();
    }

    @FXML
    private void Nivel5(ActionEvent event) throws IOException {
        //evento del boton Nivel 5 que llama al metodo IniciarEnNivel()
        //el cual se encarga de definir el nivel a llamar 
        IniciarEnNivel(5);
    }

    @FXML
    private void Nivel7(ActionEvent event) throws IOException {
        //evento del boton Nivel 7 que llama al metodo IniciarEnNivel()
        //el cual se encarga de definir el nivel a llamar 
        IniciarEnNivel(7);
    }

    private void IniciarEnNivel(int nivel) throws IOException {
        //metodo encagado de llamar a los niveles dependiendo del parametro que le manden
        switch (nivel) {
            case 1:
                try {
                    Stage stage = (Stage) this.Nivel1.getScene().getWindow();
                    //Carca la scene del nivel 1
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Nivel1.fxml"));
                    Parent root = loader.load();

                    //Obtiene el controlador de la Scene del nivel 1
                    Nivel1Controller scene1Controller = loader.getController();

                    //Pasa los datos
                    scene1Controller.DatosDeTransferencia(NOMBRE);

                    //Muestra la scena del nivel 1 en una nueva ventana 
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
                break;
            case 2:
                try {
                    Stage stage = (Stage) this.Nivel2.getScene().getWindow();
                    //Carca la scene del nivel2
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Nivel2.fxml"));
                    Parent root = loader.load();

                    //Obtiene el controlador de la Scene del nivel 2
                    Nivel2Controller scene2Controller = loader.getController();

                    //Pasa los datos
                    scene2Controller.DatosDeTransferencia(NOMBRE, DATOS1);

                    //Muestra la scena del nivel 2 en una nueva ventana  
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add("/css/CSS.css");
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.setResizable(false);
                    stage.setTitle("NIVEL 2");
                    stage.show();
                } catch (IOException ex) {
                    System.err.println(ex);
                }
                break;
            case 3:
                try {
                    Stage stage = (Stage) this.Nivel3.getScene().getWindow();
                    //Carca la scene del nivel 3
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Nivel3.fxml"));
                    Parent root = loader.load();

                    //Obtiene el controlador de la Scene del nivel 3
                    Nivel3Controller scene3Controller = loader.getController();

                    //Pasa los datos
                    scene3Controller.DatosDeTransferencia(NOMBRE, DATOS1, DATOS2);

                    //Muestra la scena del nivel 3 en una nueva ventana  
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add("/css/CSS.css");
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.setResizable(false);
                    stage.setTitle("NIVEL 3");
                    stage.show();
                } catch (IOException ex) {
                    System.err.println(ex);
                }
                break;
            case 4:
                try {
                    Stage stage = (Stage) this.Nivel4.getScene().getWindow();
                    //Carca la scene de nivel 4
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Nivel4.fxml"));
                    Parent root = loader.load();

                    //Obtiene el controlador de la Scene del nivel 4
                    Nivel4Controller scene4Controller = loader.getController();

                    //Pasa los datos
                    scene4Controller.DatosDeTransferencia(NOMBRE, DATOS1, DATOS2, DATOS3);

                    //Muestra la scena del nivel 4 en una nueva ventana  
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add("/css/CSS.css");
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.setResizable(false);
                    stage.setTitle("NIVEL 4");
                    stage.show();
                } catch (IOException ex) {
                    System.err.println(ex);
                }
                break;
            case 5:
                try {
                    Stage stage = (Stage) this.Nivel5.getScene().getWindow();
                    //Carca la scene del nivel 5
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Nivel55.fxml"));
                    Parent root = loader.load();

                    //Obtiene el controlador de la Scene del nivel 5
                    Nivel5Controller scene5Controller = loader.getController();

                    //Pasa los datos
                    scene5Controller.DatosDeTransferencia(NOMBRE, DATOS1, DATOS2, DATOS3, DATOS4);

                    //Muestra la scena del nivel 5 en una nueva ventana  
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add("/css/CSS.css");
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.setResizable(false);
                    stage.setTitle("NIVEL 5");
                    stage.show();
                } catch (IOException ex) {
                    System.err.println(ex);
                }
                break;
            case 6:
                try {
                    Stage stage = (Stage) this.Nivel6.getScene().getWindow();
                    //Carca la scene del nivel 6
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Nivel6.fxml"));
                    Parent root = loader.load();

                    //Obtiene el controlador de la Scene del nivel 6
                    Nivel6Controller scene6Controller = loader.getController();

                    //Pasa los datos
                    scene6Controller.DatosDeTransferencia(NOMBRE, DATOS1, DATOS2, DATOS3, DATOS4, DATOS5);

                    //Muestra la scena del nivel 5 en una nueva ventana  
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add("/css/CSS.css");
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.setResizable(false);
                    stage.setTitle("NIVEL 6");
                    stage.show();
                } catch (IOException ex) {
                    System.err.println(ex);
                }
                break;

            case 7:
                try {
                    Stage stage = (Stage) this.Nivel7.getScene().getWindow();
                    //Carca la scene del nivel 7
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Nivel7.fxml"));
                    Parent root = loader.load();

                    //Obtiene el controlador de la Scene del nivel 7
                    Nivel7Controller scene7Controller = loader.getController();

                    //Pasa los datos
                    scene7Controller.DatosDeTransferencia(NOMBRE, DATOS1, DATOS2, DATOS3, DATOS4, DATOS5, DATOS6);

                    //Muestra la scena del nivel 7 en una nueva ventana  
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add("/css/CSS.css");
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.setResizable(false);
                    stage.setTitle("NIVEL 7");
                    stage.show();
                } catch (IOException ex) {
                    System.err.println(ex);
                }
                break;

        }

    }

    void DatosDeTransferencia(String name) {
        //metodo encargado de resivir la transferencia de los datos
        NOMBRE = name;
    }

}

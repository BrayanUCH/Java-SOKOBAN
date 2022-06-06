/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import Clases.Jugador;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BRAYAN
 */
public class FinalizaciónDelJuegoController implements Initializable {

    @FXML
    private Button volverMenu;
    @FXML
    private Button terminar;

    //Datos a transferir
    String NOMBRE = "";
    int DATOS1 = 0;
    int DATOS2 = 0;
    int DATOS3 = 0;
    int DATOS4 = 0;
    int DATOS5 = 0;
    int DATOS6 = 0;
    int DATOS7 = 0;

    //puntaje final del jugador o usuario
    int SCORE = 0;

    //nombres para los demas lugares de la lista
    String nombresAUX[] = {"BRAYAN", "BRANDON", "EDUARDO", "MAYEY", "YOCHA", "FGL", 
        "MARIA","ODETH","AIO","WWE","QLP","JOAN","ANDREA","KKL","CHAVARRIA",
        "UGALDE","ññp","luis","nino","pocho","juan"};
    //nombres selecionados 
    String nombres[] = {"", "", "", "", "", "",""};
    //puntos de los nombres seleccionados 
    int puntos[] = {0, 0, 0, 0, 0, 0, 0};

    @FXML
    private TableColumn ColumnaNombres;
    @FXML
    private TableColumn ColumnaPuntos;
    @FXML
    private TableColumn ColumnaPuestos;
    @FXML
    private TableView<Jugador> tableview;

    private ObservableList<Jugador> jugadores;
    
    
    private Jugador jugador;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        jugadores = FXCollections.observableArrayList();
        this.tableview.setItems(jugadores);
        //asigna cada uno de los atributos de la clase jugador en cada clumna 
        //correspondiente de la tableview
        this.ColumnaPuestos.setCellValueFactory(new PropertyValueFactory("puestos"));
        this.ColumnaNombres.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.ColumnaPuntos.setCellValueFactory(new PropertyValueFactory("datos"));
        

    }

    @FXML
    private void volverMenu(ActionEvent event) throws IOException {
        //evento del boton "Volver al Menu" el cual llama al controlador Menu
        Stage stage = (Stage) this.volverMenu.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/CSS.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("GEME OVER");
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    private void terminar(ActionEvent event) throws IOException {
        //evento del boton "Terminar" el cual cierra el juego o programa 
        Stage stage = (Stage) this.terminar.getScene().getWindow();
        stage.close();
    }

    void DatosDeTransferencia(String nombre, int datos1, int datos2, int datos3, int datos4, int datos5, int datos6, int datos7) {
        //metodo encargado de resivir la transferencia de los archivos
        NOMBRE = nombre;
        DATOS1 = datos1;
        DATOS2 = datos2;
        DATOS3 = datos3;
        DATOS4 = datos4;
        DATOS5 = datos5;
        DATOS6 = datos6;
        DATOS7 = datos7;
        System.out.println("****************");
        System.out.println("nombre " + nombre);
        System.out.println("datos 1 " + datos1);
        System.out.println("datos 2 " + datos2);
        System.out.println("datos 3 " + datos3);
        System.out.println("datos 4 " + datos4);
        System.out.println("datos 5 " + datos5);
        System.out.println("datos 6 " + datos6);
        System.out.println("datos 7 " + datos7);
        System.out.println("****************");
        
        puntosObtenidos();
        inicializar();
    }

    private void puntosObtenidos() {
        //metodo se suman todos los puntos de las niveles para sacar el puntaje
        //final del jugador, ademas saca los puntajes de los demas miembros de la lista
        
//        System.out.println("*****************");
//        System.out.println("nombre " + NOMBRE);
//        System.out.println("datos 1 " + DATOS1);
//        System.out.println("datos 2 " + DATOS2);
//        System.out.println("datos 3 " + DATOS3);
//        System.out.println("datos 4 " + DATOS4);
//        System.out.println("datos 5 " + DATOS5);
//        System.out.println("datos 6 " + DATOS6);
//        System.out.println("datos 7 " + DATOS7);
//        System.out.println("****************");

        SCORE = SCORE + DATOS1 + DATOS2 + DATOS4 + DATOS5 + DATOS7;
        if (DATOS3 > 10000) {
            DATOS3 = DATOS3 - 10000;
            SCORE = SCORE + DATOS3 - 25;
        } else {
            SCORE = SCORE + DATOS3;
        }

        if (DATOS6 > 10000) {
            DATOS6 = DATOS6 - 10000;
            SCORE = SCORE + DATOS6 - 25;
        } else {
            SCORE = SCORE + DATOS6;
        }
        if (SCORE<638){
            Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
                    dialogo.setTitle("LOGRO");
                    dialogo.setHeaderText(null);
                    dialogo.setContentText("FELICIDADES A PODIDO HACER UN NUEVO RECORD..."
                            + "EL NUEVO RECORD ES DE :"+SCORE+".");
                    dialogo.showAndWait();
        }
        puntos[0] = SCORE;
        nombres[0] = NOMBRE;
        //asigna puntuaciones a los demas lugares de la lista 
        for (int i = 1; i < 7; i++) {
            puntos[i] = (int) Math.floor(Math.random()*800+638);
        }
        //asigna nombres a los demas lugares de la lista
        for (int i = 1; i < 7; i++) {
            nombres[i] = nombresAUX[(int) Math.floor(Math.random()*20+1)];
        }

        ///////////////////////////////////////
        //acomoda las puntuaciones y los nombres de menor a mayor
        String auxL;
        int aux;
        for (int p = 0; p < 7; p++) {
            for (int y = p + 1; y < 7; y++) {
                if (puntos[p] > puntos[y]) {
                    aux = puntos[p];
                    puntos[p] = puntos[y];
                    puntos[y] = aux;

                    auxL = nombres[p];
                    nombres[p] = nombres[y];
                    nombres[y] = auxL;
                }
            }
        }
        

    }

    private void inicializar() {
        //metodo encargado de asignar a los jugadores y sus puntuaciones en la 
        //tableview
        
        //asigna los atributos a los jugadores 
        Jugador p1 = new Jugador("PRIMER LUGAR", nombres[0], puntos[0]);
        Jugador p2 = new Jugador("SEGUNDO LUGAR", nombres[1], puntos[1]);
        Jugador p3 = new Jugador("TERCER LUGAR", nombres[2], puntos[2]);
        Jugador p4 = new Jugador("CUARTO LUGAR", nombres[3], puntos[3]);
        Jugador p5 = new Jugador("QUINTO LUGAR", nombres[4], puntos[4]);
        Jugador p6 = new Jugador("SEXTO LUGAR", nombres[5], puntos[5]);
        Jugador p7 = new Jugador("SEPTIMO LUGAR", nombres[6], puntos[6]);

        //se añade a los jugadores a la tableview
        this.jugadores.add(p1);
        this.jugadores.add(p2);
        this.jugadores.add(p3);
        this.jugadores.add(p4);
        this.jugadores.add(p5);
        this.jugadores.add(p6);
        this.jugadores.add(p7);

        // Refresco la tabla
        this.tableview.refresh();
    }

}

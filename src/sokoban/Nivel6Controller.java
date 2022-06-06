/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.A;
import static javafx.scene.input.KeyCode.D;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.R;
import static javafx.scene.input.KeyCode.RIGHT;
import static javafx.scene.input.KeyCode.S;
import static javafx.scene.input.KeyCode.UP;
import static javafx.scene.input.KeyCode.W;
import static javafx.scene.input.KeyCode.Z;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BRAYAN
 */
public class Nivel6Controller implements Initializable {

    @FXML
    private ImageView personaje;
    @FXML
    private ImageView caja;
    @FXML
    private ImageView caja1;
    @FXML
    private ImageView objetovo;
    @FXML
    private ImageView objetovo1;
    @FXML
    private GridPane grippane;
    @FXML
    private Button salir;
    @FXML
    private Button menu;
    @FXML
    private Button siguente;

    private int c;//posiciones anteriores del personaje en el gridpane
    private int f;//posiciones anteriores del personaje en el gridpane
    private int columna;//posisiones actuales del personaje en el gridpane
    private int fila;//posisiones actuales del personaje en el gridpane

    //las posiciones de las paredes las cuales estan restringidas para el personaje
    private final int Vcolumna[] = {11, 5, 5, 5, 6, 7, 8, 9, 10, 11, 11, 11, 11, 11, 11, 11, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 0, 0, 0, 0, 1, 2, 3, 4, 1, 2, 1, 2, 3, 4, 5, 2, 3, 5, 7, 8, 8, 8, 8, 8, 9, 11, 12, 12};
    private final int Vfila[] = {1, 3, 2, 1, 0, 0, 0, 0, 0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 9, 7, 8, 4, 3, 2, 1, 1, 1, 1, 5, 5, 6, 6, 6, 6, 6, 7, 8, 7, 3, 3, 4, 5, 6, 7, 6, 0, 0, 2};

    //contador de pasos del jugador
    private int pasos = 0;

    //matrices las cuales guardar la posiciones del jugador y las cajas para el
    //metodo de deshacer
    private final int[][] MovPersonaje = new int[10000][2];
    private final int[][] MovCaja1 = new int[10000][2];
    private final int[][] MovCaja2 = new int[10000][2];
    private final int[][] MovCaja3 = new int[10000][2];
    private final int[][] MovCaja4 = new int[10000][2];
    private final int[][] MovCaja5 = new int[10000][2];
    private final int[][] MovCaja6 = new int[10000][2];

    //contador de restringir el deshacer a solo 3 veces por nivel a menos que vuelva 
    //a reiniciar el nivel
    int restrincionZ = 0;

    //el contador para guardar las posiciones en los vectores y poder tomar las 
    //posiciones para el evento de deshacer
    int GuardaMovimientos = 0;

    @FXML
    private Text contadorPasos;

    int c1;//posiciones actuales de la caja1 en el gridpane
    int f1;//posiciones actuales de la caja1 en el gridpane
    int c2;//posiciones actuales de la caja2 en el gridpane
    int f2;//posiciones actuales de la caja2 en el gridpane
    int c3;//posiciones actuales de la caja3 en el gridpane
    int f3;//posiciones actuales de la caja3 en el gridpane
    int c4;//posiciones actuales de la caja4 en el gridpane
    int f4;//posiciones actuales de la caja4 en el gridpane
    int c5;//posiciones actuales de la caja5 en el gridpane
    int f5;//posiciones actuales de la caja5 en el gridpane
    int c6;//posiciones actuales de la caja6 en el gridpane
    int f6;//posiciones actuales de la caja6 en el gridpane

    int cOb1;//posiciones actuales del objetivo1 en el gridpane
    int fOb1;//posiciones actuales del objetivo1 en el gridpane
    int cOb2;//posiciones actuales del objetivo2 en el gridpane
    int fOb2;//posiciones actuales del objetivo2 en el gridpane
    int cOb3;//posiciones actuales del objetivo3 en el gridpane
    int fOb3;//posiciones actuales del objetivo3 en el gridpane
    int cOb4;//posiciones actuales del objetivo4 en el gridpane
    int fOb4;//posiciones actuales del objetivo4 en el gridpane
    int cOb5;//posiciones actuales del objetivo5 en el gridpane
    int fOb5;//posiciones actuales del objetivo5 en el gridpane
    int cOb6;//posiciones actuales del objetivo6 en el gridpane
    int fOb6;//posiciones actuales del objetivo6 en el gridpane

    boolean nextLeven = false;//este boolean es para poder habilitar el boton de siguente
    //nivel cuando las cajas esten en los objetivos

    @FXML
    private Button reiniciarNivel;
    @FXML
    private ImageView caja2;
    @FXML
    private ImageView caja3;
    @FXML
    private ImageView caja4;
    @FXML
    private ImageView caja5;
    @FXML
    private ImageView objetovo4;
    @FXML
    private ImageView objetovo3;
    @FXML
    private ImageView objetovo2;
    @FXML
    private ImageView objetovo5;

    Image imagen;

//datos a transferir
    String NOMBRE = "";
    int DATOS1 = 29 + 15;
    int DATOS2 = 21 + 15;
    int DATOS3 = 99 + 15;
    int DATOS4 = 59 + 15;
    int DATOS5 = 160 + 15;
    int DATOS6 = 264 + 15;


    int HDP = 0;
    int hdp = 0;

    //contador para dar el mensaje de deadlock solo una ves por nivel 
    int Dead = 0;

    //variables para deadlock
    int d1 = 0;
    int d2 = 4;
    String D1 = "";

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        grippane.setAlignment(Pos.CENTER);
        cOb1 = GridPane.getColumnIndex(objetovo);//cordenada de la columna del objetivo1
        fOb1 = GridPane.getRowIndex(objetovo);//cordenada de la fila del objetivo1
        cOb2 = GridPane.getColumnIndex(objetovo1);//cordenada de la columna del objetivo2
        fOb2 = GridPane.getRowIndex(objetovo1);//cordenada de la fila del objetivo2
        cOb3 = GridPane.getColumnIndex(objetovo2);//cordenada de la columna del objetivo3
        fOb3 = GridPane.getRowIndex(objetovo2);//cordenada de la fila del objetivo3
        cOb4 = GridPane.getColumnIndex(objetovo3);//cordenada de la columna del objetivo4
        fOb4 = GridPane.getRowIndex(objetovo3);//cordenada de la fila del objetivo4
        cOb5 = GridPane.getColumnIndex(objetovo4);//cordenada de la columna del objetivo5
        fOb5 = GridPane.getRowIndex(objetovo4);//cordenada de la fila del objetivo5
        cOb6 = GridPane.getColumnIndex(objetovo5);//cordenada de la columna del objetivo6
        fOb6 = GridPane.getRowIndex(objetovo5);//cordenada de la fila del objetivo6
    }

    @FXML
    private void Movimiento(KeyEvent event) throws FileNotFoundException, MalformedURLException {
        //Este es el encardo de todo el movimiento del juego desde este metodo se
        //llaman a la mayoria del los demas metodos, pero el principal funcionamiento
        //es el de mover el personaje y con el personaje las cajas para asi lograr 
        //los objetivos y ganar el juego.

        f = GridPane.getRowIndex(personaje);//posicion altual del personaje en la fila.
        c = GridPane.getColumnIndex(personaje);//posicion altual del personaje en la columna.

        int movimiento = 0;//contador con el proposito de dar permiso para realizar 
        //otros metodos y diferentes funcionalidades si solo si el jugador presiona 
        //una de las teclas de movimiento(A,W,S,D).

        System.out.println("____________________________________________________");
        D1 = "";
        System.out.println("Digito la tecla (" + event.getCode() + ")");//la tecla que dijito en el teclado
        if ((event.getCode() == W) || (event.getCode() == UP)) {//movimiento del personaje para ARRIBA
            System.out.println("Arriba");
            GridPane.setRowIndex(personaje, GridPane.getRowIndex(personaje) - 1);
            movimiento++;
        }
        if ((event.getCode() == S) || (event.getCode() == DOWN)) {//movimiento del personaje para ABAJO
            System.out.println("Abajo");
            GridPane.setRowIndex(personaje, GridPane.getRowIndex(personaje) + 1);
            movimiento++;
        }
        if ((event.getCode() == A) || (event.getCode() == LEFT)) {//movimiento del personaje para la DERECHA
            System.out.println("Izquierda");
            GridPane.setColumnIndex(personaje, GridPane.getColumnIndex(personaje) - 1);
            movimiento++;
            imagen = new Image("/Imagenes/Personaje Iz.png");//cambia la imagen del personaje 
            personaje.setImage(imagen);
        }
        if ((event.getCode() == D) || (event.getCode() == RIGHT)) {//movimiento del personaje para la IZQUIERDA
            System.out.println("Derecha");
            GridPane.setColumnIndex(personaje, GridPane.getColumnIndex(personaje) + 1);
            movimiento++;
            imagen = new Image("Imagenes/Personaje D.png");//cambia la imagen del personaje 
            personaje.setImage(imagen);
        }
        if (event.getCode() == Z) {//la tecla que permite deshacer
            System.out.println("Deshacer");
            RegresarPasos();//metodo encargado de deshacer los movimientos
            D1 = "Z";
        }
        if (event.getCode() == R) {//la tecla que permite reiniciar el nivel
            System.out.println("Reiniciar");
            reiniciarAuto(1);//metodo encargado de reiniciar los movimientos 
        }

        columna = GridPane.getColumnIndex(personaje);//posicion de la columan del 
        //personaje despues de haber hecho un movimiento
        fila = GridPane.getRowIndex(personaje);//posicion de la fila del 
        //personaje despues de haber hecho un movimiento

        if (movimiento != 0) {
            int permiso = 0;//contador que permite el ingreso para accerder al 
            //metodo de movimiento de cajas(MovEBox())

            if (MURO(columna, fila) == true) {
                pasos++;//contador de pasos 
                contadorPasos.setText(String.valueOf(pasos));//asigna el contador
                //de pasos al label en pantalla 
                permiso++;
                GuardaMovimientos++;
                MovPersonaje[GuardaMovimientos][0] = columna;
                MovPersonaje[GuardaMovimientos][1] = fila;

            } else {
                //si a en la nueva posicion hay un muro retrocede al peronaje a
                //la posicion antes de moverlo
                GridPane.setColumnIndex(personaje, c);
                GridPane.setRowIndex(personaje, f);
            }

            if ((detecteBox() == true) && (permiso != 0)) {//detecta si la posicion 
                //del perosnaje es la de una caja
                MoveBox();//metodo para mover la caja
            }
        }
        System.out.println("Posicion del personaje en el Gridpane:");
        System.out.print("(Columna " + GridPane.getColumnIndex(personaje) + ",");
        System.out.println("Fila " + GridPane.getRowIndex(personaje) + ")");

        HabilitarBtnNext();//metodo encargado de habilitar el boton de siguente 

        if (pasos == 1000) {
            //limmita al usuario a 1000 movimientos por nivel
            reiniciarAuto(2);
        }

        huevoDePascua();

        DEADLOCK();//metodo para el deadlock de las cajas 
        System.out.println("____________________________________________________");

    }

    @FXML
    private void pressedSalir(MouseEvent event) {
        //Boton salir cierra el programa(juego)
        Stage stage = (Stage) this.salir.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void PressedMenu(MouseEvent event) throws IOException {
        //Regresa al controlador de "MENU"
        Stage stage = (Stage) this.menu.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/CSS.css");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setTitle("MENU");
        stage.show();
    }

    @FXML
    private void pressedNext(MouseEvent event) throws IOException {
        //el boton de SIGUENTE NIVEL se activa si solo si las cajas fueron posicionadas 
        //en los objetivos, el permiso es dado por el metodo HabilitarBtnNext() el cual
        //se comunica con la variable nextLeven

        if (nextLeven == true) {
            try {
                Stage stage = (Stage) this.siguente.getScene().getWindow();
                //carca la scene del nivel 7
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Nivel7.fxml"));
                Parent root = loader.load();

                //obtiene el controlador del la Scene del nivel 7
                Nivel7Controller scene7Controller = loader.getController();

                if ((pasos == 10264) || (pasos == 264)) {
                    pasos -= 5;
                    Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
                    dialogo.setTitle("LOGRO");
                    dialogo.setHeaderText(null);
                    dialogo.setContentText("FELICIDADES HA CONSEGUIDO EL LOGRO DE PASAR "
                            + " EL NIVEL CON EL MINIMO DE PASOS (264). EL CUAL"
                            + " CORRESPONDE A 5 PUNTOS.");
                    dialogo.showAndWait();
                }

                String direccion = System.getProperty("user.dir") + "/src/Sonidos/tubo.mp3";
                File file = new File(direccion);
                Media sound = new Media(file.toURI().toURL().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();

                DATOS6 = pasos + hdp;
                //pasa los datos
                scene7Controller.DatosDeTransferencia(NOMBRE, DATOS1, DATOS2, DATOS3, DATOS4, DATOS5, DATOS6);

                //muestra la scena del nivel 7 en una nueva ventana  
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
        } else {
            Alert dialogo = new Alert(Alert.AlertType.ERROR);
            dialogo.setTitle("ERROR");
            dialogo.setContentText("VEREFIQUE QUE LAS CAJAS ESTEN EL OBJETIVO");
            dialogo.showAndWait();
        }

    }

    private boolean MURO(int columnaM, int filaM) {
        //metodo encargado de avisar o retornar un parametro booleano para saber
        //si en la posicion del personaje es la posicion de un muro si es asi retorna false 
        //y true si la posicion esta en un espasio libre o es la posicion de una caja 

        boolean permiso = true;

        for (int i = 0; i < Vcolumna.length; i++) {
            if ((Vcolumna[i] == columnaM) && (Vfila[i] == filaM)) {
                permiso = false;
            }
        }
        return permiso;
    }

    private boolean detecteBox() {
        //metodo encargo de detectar si en la posicion en el que el usuario 
        //moviò el persoaje es la misma posicion de una caja

        c1 = GridPane.getColumnIndex(caja);//posiciones actuales de la caja en el gridpane
        f1 = GridPane.getRowIndex(caja);//posiciones actuales de la caja en el gridpane
        c2 = GridPane.getColumnIndex(caja1);//posiciones actuales de la caja1 en el gridpane
        f2 = GridPane.getRowIndex(caja1);//posiciones actuales de la caja1 en el gridpane
        c3 = GridPane.getColumnIndex(caja2);//posiciones actuales de la caja2 en el gridpane
        f3 = GridPane.getRowIndex(caja2);//posiciones actuales de la caja en el gridpane
        c4 = GridPane.getColumnIndex(caja3);//posiciones actuales de la caja3 en el gridpane
        f4 = GridPane.getRowIndex(caja3);//posiciones actuales de la caja3 en el gridpane
        c5 = GridPane.getColumnIndex(caja4);//posiciones actuales de la caja4 en el gridpane
        f5 = GridPane.getRowIndex(caja4);//posiciones actuales de la caja4 en el gridpane
        c6 = GridPane.getColumnIndex(caja5);//posiciones actuales de la caja5 en el gridpane
        f6 = GridPane.getRowIndex(caja5);//posiciones actuales de la caja5 en el gridpane

        MovCaja1[GuardaMovimientos][0] = c1;//guarda la posicion de la caja en el matriz de movimientos
        MovCaja1[GuardaMovimientos][1] = f1;//guarda la posicion de la caja en el matriz de movimientos
        MovCaja2[GuardaMovimientos][0] = c2;//guarda la posicion de la caja1 en el matriz de movimientos
        MovCaja2[GuardaMovimientos][1] = f2;//guarda la posicion de la caja1 en el matriz de movimientos
        MovCaja3[GuardaMovimientos][0] = c3;//guarda la posicion de la caja2 en el matriz de movimientos
        MovCaja3[GuardaMovimientos][1] = f3;//guarda la posicion de la caja2 en el matriz de movimientos
        MovCaja4[GuardaMovimientos][0] = c4;//guarda la posicion de la caja3 en el matriz de movimientos
        MovCaja4[GuardaMovimientos][1] = f4;//guarda la posicion de la caja3 en el matriz de movimientos
        MovCaja5[GuardaMovimientos][0] = c5;//guarda la posicion de la caja4 en el matriz de movimientos
        MovCaja5[GuardaMovimientos][1] = f5;//guarda la posicion de la caja4 en el matriz de movimientos
        MovCaja6[GuardaMovimientos][0] = c6;//guarda la posicion de la caja5 en el matriz de movimientos
        MovCaja6[GuardaMovimientos][1] = f6;//guarda la posicion de la caja5 en el matriz de movimientos

        if (((columna == c1) && (fila == f1)) || ((columna == c2) && (fila == f2))) {
            System.out.println("Detecto una caja");
            return true;
        } else if (((columna == c3) && (fila == f3)) || ((columna == c4) && (fila == f4))) {
            System.out.println("Detecto una caja");
            return true;
        } else if (((columna == c5) && (fila == f5)) || ((columna == c6) && (fila == f6))) {
            System.out.println("Detecto una caja");
            return true;
        } else {
            System.out.println("No detecto ninguna caja");
            return false;
        }

    }

    private void MoveBox() throws MalformedURLException {
        //metodo encargado del movimiento de las cajas, en este metodo se entra solo 
        //si el metodo dectecteBox() da el permiso 

        c1 = GridPane.getColumnIndex(caja);//posisiones actuales de la caja en el gridpane
        f1 = GridPane.getRowIndex(caja);//posisiones actuales de la caja en el gridpane
        c2 = GridPane.getColumnIndex(caja1);//posisiones actuales de la caja1 en el gridpane
        f2 = GridPane.getRowIndex(caja1);//posisiones actuales de la caja1 en el gridpane
        c3 = GridPane.getColumnIndex(caja2);//posisiones actuales de la caja2 en el gridpane
        f3 = GridPane.getRowIndex(caja2);//posisiones actuales de la caja en el gridpane
        c4 = GridPane.getColumnIndex(caja3);//posisiones actuales de la caja3 en el gridpane
        f4 = GridPane.getRowIndex(caja3);//posisiones actuales de la caja3 en el gridpane
        c5 = GridPane.getColumnIndex(caja4);//posisiones actuales de la caja4 en el gridpane
        f5 = GridPane.getRowIndex(caja4);//posisiones actuales de la caja4 en el gridpane
        c6 = GridPane.getColumnIndex(caja5);//posisiones actuales de la caja5 en el gridpane
        f6 = GridPane.getRowIndex(caja5);//posisiones actuales de la caja5 en el gridpane

        int pasosss = 0;//contador con el proposito de arvertir si el el peronaje 
        //pudo o no mover la caja 

////////caja///////////////////////////////////////////////////////////////////
        if ((columna == c1) && (fila == f1)) {
            if (c < columna) {//detecta si el movimento del perosnaje fue para abajo
                if ((MURO(c1 + 1, f1) == true) && (BoxWithBox(c1 + 1, f1, 1) == true)) {
                    //revisa si la nueva posicion de la caja no sea una posicion de
                    //otra caja o la posicion de un muro, para asi saber si puede o no mover 
                    //la caja 
                    GridPane.setColumnIndex(caja, columna + 1);
                    GridPane.setRowIndex(caja, fila);
                } else {
                    //si es la posicion de una caja o un muro no permite el movimiento 
                    //y devuelve al perosnaje a la posicion anterior
                    GridPane.setColumnIndex(personaje, c);
                    GridPane.setRowIndex(personaje, f);
                    pasosss++;
                }
            } else if (f < fila) {//detecta si el movimento del perosnaje fue para la derecha
                if ((MURO(c1, f1 + 1) == true) && (BoxWithBox(c1, f1 + 1, 1) == true)) {
                    //revisa si la nueva posicion de la caja no sea una posicion de
                    //otra caja o la posicion de un muro, para asi saber si puede o no mover 
                    //la caja
                    GridPane.setColumnIndex(caja, columna);
                    GridPane.setRowIndex(caja, fila + 1);
                } else {
                    //si es la posicion de una caja o un muro no permite el movimiento 
                    //y devuelve al perosnaje a la posicion anterior
                    GridPane.setColumnIndex(personaje, c);
                    GridPane.setRowIndex(personaje, f);
                    pasosss++;
                }
            }
            if (c > columna) {//detecta si el movimento del perosnaje fue para arriba 
                if ((MURO(c1 - 1, f1) == true) && (BoxWithBox(c1 - 1, f1, 1) == true)) {
                    //revisa si la nueva posicion de la caja no sea una posicion de
                    //otra caja o la posicion de un muro, para asi saber si puede o no mover 
                    //la caja 
                    GridPane.setColumnIndex(caja, columna - 1);
                    GridPane.setRowIndex(caja, fila);
                } else {
                    //si es la posicion de una caja o un muro no permite el movimiento 
                    //y devuelve al perosnaje a la posicion anterior
                    GridPane.setColumnIndex(personaje, c);
                    GridPane.setRowIndex(personaje, f);
                    pasosss++;
                }
            } else if (f > fila) {//detecta si el movimento del perosnaje fue para izquierda
                if ((MURO(c1, f1 - 1) == true) && (BoxWithBox(c1, f1 - 1, 1) == true)) {
                    //revisa si la nueva posicion de la caja no sea una posicion de
                    //otra caja o la posicion de un muro, para asi saber si puede o no mover 
                    //la caja 
                    GridPane.setColumnIndex(caja, columna);
                    GridPane.setRowIndex(caja, fila - 1);
                } else {
                    //si es la posicion de una caja o un muro no permite el movimiento 
                    //y devuelve al perosnaje a la posicion anterior
                    GridPane.setColumnIndex(personaje, c);
                    GridPane.setRowIndex(personaje, f);
                    pasosss++;
                }
            }
        }
////////caja1///////////////////////////////////////////////////////////////////

        if ((columna == c2) && (fila == f2)) {
            if (c < columna) {
                if ((MURO(c2 + 1, f2) == true) && (BoxWithBox(c2 + 1, f2, 2) == true)) {
                    GridPane.setColumnIndex(caja1, columna + 1);
                    GridPane.setRowIndex(caja1, fila);
                } else {
                    GridPane.setColumnIndex(personaje, c);
                    GridPane.setRowIndex(personaje, f);
                    pasosss++;
                }
            } else if (f < fila) {
                if ((MURO(c2, f2 + 1) == true) && (BoxWithBox(c2, f2 + 1, 2) == true)) {
                    GridPane.setColumnIndex(caja1, columna);
                    GridPane.setRowIndex(caja1, fila + 1);
                } else {
                    GridPane.setColumnIndex(personaje, c);
                    GridPane.setRowIndex(personaje, f);
                    pasosss++;
                }
            }
            if (c > columna) {
                if ((MURO(c2 - 1, f2) == true) && (BoxWithBox(c2 - 1, f2, 2) == true)) {
                    GridPane.setColumnIndex(caja1, columna - 1);
                    GridPane.setRowIndex(caja1, fila);
                } else {
                    GridPane.setColumnIndex(personaje, c);
                    GridPane.setRowIndex(personaje, f);
                    pasosss++;
                }
            } else if (f > fila) {
                if ((MURO(c2, f2 - 1) == true) && (BoxWithBox(c2, f2 - 1, 2) == true)) {
                    GridPane.setColumnIndex(caja1, columna);
                    GridPane.setRowIndex(caja1, fila - 1);
                } else {
                    GridPane.setColumnIndex(personaje, c);
                    GridPane.setRowIndex(personaje, f);
                    pasosss++;

                }
            }
        }
////////caja2///////////////////////////////////////////////////////////////////
        if ((columna == c3) && (fila == f3)) {
            if (c < columna) {
                if ((MURO(c3 + 1, f3) == true) && (BoxWithBox(c3 + 1, f3, 3) == true)) {
                    GridPane.setColumnIndex(caja2, columna + 1);
                    GridPane.setRowIndex(caja2, fila);
                } else {
                    GridPane.setColumnIndex(personaje, c);
                    GridPane.setRowIndex(personaje, f);
                    pasosss++;
                }
            } else if (f < fila) {
                if ((MURO(c3, f3 + 1) == true) && (BoxWithBox(c3, f3 + 1, 3) == true)) {
                    GridPane.setColumnIndex(caja2, columna);
                    GridPane.setRowIndex(caja2, fila + 1);
                } else {
                    GridPane.setColumnIndex(personaje, c);
                    GridPane.setRowIndex(personaje, f);
                    pasosss++;
                }
            }
            if (c > columna) {
                if ((MURO(c3 - 1, f3) == true) && (BoxWithBox(c3 - 1, f3, 3) == true)) {
                    GridPane.setColumnIndex(caja2, columna - 1);
                    GridPane.setRowIndex(caja2, fila);
                } else {
                    GridPane.setColumnIndex(personaje, c);
                    GridPane.setRowIndex(personaje, f);
                    pasosss++;
                }
            } else if (f > fila) {
                if ((MURO(c3, f3 - 1) == true) && (BoxWithBox(c3, f3 - 1, 3) == true)) {
                    GridPane.setColumnIndex(caja2, columna);
                    GridPane.setRowIndex(caja2, fila - 1);
                } else {
                    GridPane.setColumnIndex(personaje, c);
                    GridPane.setRowIndex(personaje, f);
                    pasosss++;

                }
            }
        }
////////caja3///////////////////////////////////////////////////////////////////
        if ((columna == c4) && (fila == f4)) {
            if (c < columna) {
                if ((MURO(c4 + 1, f4) == true) && (BoxWithBox(c4 + 1, f4, 4) == true)) {
                    GridPane.setColumnIndex(caja3, columna + 1);
                    GridPane.setRowIndex(caja3, fila);
                } else {
                    GridPane.setColumnIndex(personaje, c);
                    GridPane.setRowIndex(personaje, f);
                    pasosss++;
                }
            } else if (f < fila) {
                if ((MURO(c4, f4 + 1) == true) && (BoxWithBox(c4, f4 + 1, 4) == true)) {
                    GridPane.setColumnIndex(caja3, columna);
                    GridPane.setRowIndex(caja3, fila + 1);
                } else {
                    GridPane.setColumnIndex(personaje, c);
                    GridPane.setRowIndex(personaje, f);
                    pasosss++;
                }
            }
            if (c > columna) {
                if ((MURO(c4 - 1, f4) == true) && (BoxWithBox(c4 - 1, f4, 4) == true)) {
                    GridPane.setColumnIndex(caja3, columna - 1);
                    GridPane.setRowIndex(caja3, fila);
                } else {
                    GridPane.setColumnIndex(personaje, c);
                    GridPane.setRowIndex(personaje, f);
                    pasosss++;
                }
            } else if (f > fila) {
                if ((MURO(c4, f4 - 1) == true) && (BoxWithBox(c4, f4 - 1, 4) == true)) {
                    GridPane.setColumnIndex(caja3, columna);
                    GridPane.setRowIndex(caja3, fila - 1);
                } else {
                    GridPane.setColumnIndex(personaje, c);
                    GridPane.setRowIndex(personaje, f);
                    pasosss++;

                }
            }
        }
////////caja4///////////////////////////////////////////////////////////////////
        if ((columna == c5) && (fila == f5)) {
            if (c < columna) {
                if ((MURO(c5 + 1, f5) == true) && (BoxWithBox(c5 + 1, f5, 5) == true)) {
                    GridPane.setColumnIndex(caja4, columna + 1);
                    GridPane.setRowIndex(caja4, fila);
                } else {
                    GridPane.setColumnIndex(personaje, c);
                    GridPane.setRowIndex(personaje, f);
                    pasosss++;
                }
            } else if (f < fila) {
                if ((MURO(c5, f5 + 1) == true) && (BoxWithBox(c5, f5 + 1, 5) == true)) {
                    GridPane.setColumnIndex(caja4, columna);
                    GridPane.setRowIndex(caja4, fila + 1);
                } else {
                    GridPane.setColumnIndex(personaje, c);
                    GridPane.setRowIndex(personaje, f);
                    pasosss++;
                }
            }
            if (c > columna) {
                if ((MURO(c5 - 1, f5) == true) && (BoxWithBox(c5 - 1, f5, 5) == true)) {
                    GridPane.setColumnIndex(caja4, columna - 1);
                    GridPane.setRowIndex(caja4, fila);
                } else {
                    GridPane.setColumnIndex(personaje, c);
                    GridPane.setRowIndex(personaje, f);
                    pasosss++;
                }
            } else if (f > fila) {
                if ((MURO(c5, f5 - 1) == true) && (BoxWithBox(c5, f5 - 1, 5) == true)) {
                    GridPane.setColumnIndex(caja4, columna);
                    GridPane.setRowIndex(caja4, fila - 1);
                } else {
                    GridPane.setColumnIndex(personaje, c);
                    GridPane.setRowIndex(personaje, f);
                    pasosss++;

                }
            }
        }
////////caja5///////////////////////////////////////////////////////////////////
        if ((columna == c6) && (fila == f6)) {
            if (c < columna) {
                if ((MURO(c6 + 1, f6) == true) && (BoxWithBox(c6 + 1, f6, 6) == true)) {
                    GridPane.setColumnIndex(caja5, columna + 1);
                    GridPane.setRowIndex(caja5, fila);
                } else {
                    GridPane.setColumnIndex(personaje, c);
                    GridPane.setRowIndex(personaje, f);
                    pasosss++;
                }
            } else if (f < fila) {
                if ((MURO(c6, f6 + 1) == true) && (BoxWithBox(c6, f6 + 1, 6) == true)) {
                    GridPane.setColumnIndex(caja5, columna);
                    GridPane.setRowIndex(caja5, fila + 1);
                } else {
                    GridPane.setColumnIndex(personaje, c);
                    GridPane.setRowIndex(personaje, f);
                    pasosss++;
                }
            }
            if (c > columna) {
                if ((MURO(c6 - 1, f6) == true) && (BoxWithBox(c6 - 1, f6, 6) == true)) {
                    GridPane.setColumnIndex(caja5, columna - 1);
                    GridPane.setRowIndex(caja5, fila);
                } else {
                    GridPane.setColumnIndex(personaje, c);
                    GridPane.setRowIndex(personaje, f);
                    pasosss++;
                }
            } else if (f > fila) {
                if ((MURO(c6, f6 - 1) == true) && (BoxWithBox(c6, f6 - 1, 6) == true)) {
                    GridPane.setColumnIndex(caja5, columna);
                    GridPane.setRowIndex(caja5, fila - 1);
                } else {
                    GridPane.setColumnIndex(personaje, c);
                    GridPane.setRowIndex(personaje, f);
                    pasosss++;

                }
            }
        }

        //vulve a recolectar las cordenadas para guardarlas en las matrices de las posiciones 
        c1 = GridPane.getColumnIndex(caja);//posiciones actuales de la caja en el gridpane
        f1 = GridPane.getRowIndex(caja);//posiciones actuales de la caja en el gridpane
        c2 = GridPane.getColumnIndex(caja1);//posiciones actuales de la caja1 en el gridpane
        f2 = GridPane.getRowIndex(caja1);//posiciones actuales de la caja1 en el gridpane
        c3 = GridPane.getColumnIndex(caja2);//posiciones actuales de la caja2 en el gridpane
        f3 = GridPane.getRowIndex(caja2);//posiciones actuales de la caja en el gridpane
        c4 = GridPane.getColumnIndex(caja3);//posiciones actuales de la caja3 en el gridpane
        f4 = GridPane.getRowIndex(caja3);//posiciones actuales de la caja3 en el gridpane
        c5 = GridPane.getColumnIndex(caja4);//posiciones actuales de la caja4 en el gridpane
        f5 = GridPane.getRowIndex(caja4);//posiciones actuales de la caja4 en el gridpane
        c6 = GridPane.getColumnIndex(caja5);//posiciones actuales de la caja5 en el gridpane
        f6 = GridPane.getRowIndex(caja5);//posiciones actuales de la caja5 en el gridpane

        //guarda las cordenadas en las matrices de las cordenas de las cajas 
        MovCaja1[GuardaMovimientos][0] = c1;//guarda la posicion de la caja en el matriz de movimientos
        MovCaja1[GuardaMovimientos][1] = f1;//guarda la posicion de la caja en el matriz de movimientos
        MovCaja2[GuardaMovimientos][0] = c2;//guarda la posicion de la caja1 en el matriz de movimientos
        MovCaja2[GuardaMovimientos][1] = f2;//guarda la posicion de la caja1 en el matriz de movimientos
        MovCaja3[GuardaMovimientos][0] = c3;//guarda la posicion de la caja2 en el matriz de movimientos
        MovCaja3[GuardaMovimientos][1] = f3;//guarda la posicion de la caja2 en el matriz de movimientos
        MovCaja4[GuardaMovimientos][0] = c4;//guarda la posicion de la caja3 en el matriz de movimientos
        MovCaja4[GuardaMovimientos][1] = f4;//guarda la posicion de la caja3 en el matriz de movimientos
        MovCaja5[GuardaMovimientos][0] = c5;//guarda la posicion de la caja4 en el matriz de movimientos
        MovCaja5[GuardaMovimientos][1] = f5;//guarda la posicion de la caja4 en el matriz de movimientos
        MovCaja6[GuardaMovimientos][0] = c6;//guarda la posicion de la caja5 en el matriz de movimientos
        MovCaja6[GuardaMovimientos][1] = f6;//guarda la posicion de la caja5 en el matriz de movimientos

        //si el personaje no puede mover la caja tiene que restar el paso que se 
        //sumo arriba
        if (pasosss != 0) {
            GuardaMovimientos--;
            pasos--;
            contadorPasos.setText(String.valueOf(pasos));
        } else {
            String direccion = System.getProperty("user.dir") + "/src/Sonidos/saltar.mp3";
            File file = new File(direccion);
            Media sound = new Media(file.toURI().toURL().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        }

    }

    private boolean BoxWithBox(int columnaBWB, int filaBWB, int NumeroCaja) {
        //metodo encardo de detectar que dos cajas no queden en la misma posicion y que 
        //el personaje no puedas enpujar mas de 1 caja a la vez 

        c1 = GridPane.getColumnIndex(caja);//posiciones actuales de la caja en el gridpane
        f1 = GridPane.getRowIndex(caja);//posiciones actuales de la caja en el gridpane
        c2 = GridPane.getColumnIndex(caja1);//posiciones actuales de la caja1 en el gridpane
        f2 = GridPane.getRowIndex(caja1);//posiciones actuales de la caja1 en el gridpane
        c3 = GridPane.getColumnIndex(caja2);//posiciones actuales de la caja2 en el gridpane
        f3 = GridPane.getRowIndex(caja2);//posiciones actuales de la caja en el gridpane
        c4 = GridPane.getColumnIndex(caja3);//posiciones actuales de la caja3 en el gridpane
        f4 = GridPane.getRowIndex(caja3);//posiciones actuales de la caja3 en el gridpane
        c5 = GridPane.getColumnIndex(caja4);//posiciones actuales de la caja4 en el gridpane
        f5 = GridPane.getRowIndex(caja4);//posiciones actuales de la caja4 en el gridpane
        c6 = GridPane.getColumnIndex(caja5);//posiciones actuales de la caja5 en el gridpane
        f6 = GridPane.getRowIndex(caja5);//posiciones actuales de la caja5 en el gridpane

        switch (NumeroCaja) {
            //revisa la cardenada mandada por el parametro para saber si no es una 
            //de las posiciones de las otras cajas 
            case 1:
                if (((columnaBWB == c2) && (filaBWB == f2)) || ((columnaBWB == c3) && (filaBWB == f3)) || ((columnaBWB == c4) && (filaBWB == f4)) || ((columnaBWB == c5) && (filaBWB == f5)) || ((columnaBWB == c6) && (filaBWB == f6))) {
                    return false;
                }
                break;
            case 2:
                if (((columnaBWB == c1) && (filaBWB == f1)) || ((columnaBWB == c3) && (filaBWB == f3)) || ((columnaBWB == c4) && (filaBWB == f4)) || ((columnaBWB == c5) && (filaBWB == f5)) || ((columnaBWB == c6) && (filaBWB == f6))) {
                    return false;
                }
                break;
            case 3:
                if (((columnaBWB == c1) && (filaBWB == f1)) || ((columnaBWB == c2) && (filaBWB == f2)) || ((columnaBWB == c4) && (filaBWB == f4)) || ((columnaBWB == c5) && (filaBWB == f5)) || ((columnaBWB == c6) && (filaBWB == f6))) {
                    return false;
                }
                break;
            case 4:
                if (((columnaBWB == c1) && (filaBWB == f1)) || ((columnaBWB == c2) && (filaBWB == f2)) || ((columnaBWB == c3) && (filaBWB == f3)) || ((columnaBWB == c5) && (filaBWB == f5)) || ((columnaBWB == c6) && (filaBWB == f6))) {
                    return false;
                }
                break;
            case 5:
                if (((columnaBWB == c1) && (filaBWB == f1)) || ((columnaBWB == c2) && (filaBWB == f2)) || ((columnaBWB == c3) && (filaBWB == f3)) || ((columnaBWB == c4) && (filaBWB == f4)) || ((columnaBWB == c6) && (filaBWB == f6))) {
                    return false;
                }
                break;

            case 6:
                if (((columnaBWB == c1) && (filaBWB == f1)) || ((columnaBWB == c2) && (filaBWB == f2)) || ((columnaBWB == c3) && (filaBWB == f3)) || ((columnaBWB == c4) && (filaBWB == f4)) || ((columnaBWB == c5) && (filaBWB == f5))) {
                    return false;
                }
                break;
        }
        return true;
    }

    private void HabilitarBtnNext() throws FileNotFoundException {
        //este metodo habilita el boton de SIGUENTE NIVEL loscalizando que las 
        //cajas esten el las posiciones de los objetivos 

        c1 = GridPane.getColumnIndex(caja);//posiciones actuales de la caja en el gridpane
        f1 = GridPane.getRowIndex(caja);//posiciones actuales de la caja en el gridpane
        c2 = GridPane.getColumnIndex(caja1);//posiciones actuales de la caja1 en el gridpane
        f2 = GridPane.getRowIndex(caja1);//posiciones actuales de la caja1 en el gridpane
        c3 = GridPane.getColumnIndex(caja2);//posiciones actuales de la caja2 en el gridpane
        f3 = GridPane.getRowIndex(caja2);//posiciones actuales de la caja en el gridpane
        c4 = GridPane.getColumnIndex(caja3);//posiciones actuales de la caja3 en el gridpane
        f4 = GridPane.getRowIndex(caja3);//posiciones actuales de la caja3 en el gridpane
        c5 = GridPane.getColumnIndex(caja4);//posiciones actuales de la caja4 en el gridpane
        f5 = GridPane.getRowIndex(caja4);//posiciones actuales de la caja4 en el gridpane
        c6 = GridPane.getColumnIndex(caja5);//posiciones actuales de la caja5 en el gridpane
        f6 = GridPane.getRowIndex(caja5);//posiciones actuales de la caja5 en el gridpane

        nextLeven = false;//variable que permite el evento del boton "Siguiente Nivel"

        int c = 0;//contador que permite saber si todas las cajas estan en algun objetivo 
        // y poder dar la autorizacion a la boton "Siguiente Nivel"

        //verifica que la caja este en alguno de los objetivos y de ser asi la cambia de color 
        //de lo contrario lo deja del mismo colar, ademas toma un contador el 
        //cual dira si todas las cajas estan en los objetivos
        //verifica que las cajas esten en los objetivos o en campos libres
        //si las cajas estan en campos libre se ponen de color amarrillo 
        //si las cajas estan en las bases o metas se ponen de color verde
        Image verde = new Image("Imagenes/caja en el objetivo.jpg");
        Image amarillo = new Image("Imagenes/caja.jpg");

        //#1
        if (((c1 == cOb1) && (f1 == fOb1)) || ((c1 == cOb2) && (f1 == fOb2)) || ((c1 == cOb3) && (f1 == fOb3)) || ((c1 == cOb4) && (f1 == fOb4)) || ((c1 == cOb5) && (f1 == fOb5)) || ((c1 == cOb6) && (f1 == fOb6))) {
            c++;
            caja.setImage(verde);
        } else {
            caja.setImage(amarillo);
        }//#2
        if (((c2 == cOb1) && (f2 == fOb1)) || ((c2 == cOb2) && (f2 == fOb2)) || ((c2 == cOb3) && (f2 == fOb3)) || ((c2 == cOb4) && (f2 == fOb4)) || ((c2 == cOb5) && (f2 == fOb5)) || ((c2 == cOb6) && (f2 == fOb6))) {
            c++;
            caja1.setImage(verde);
        } else {
            caja1.setImage(amarillo);
        }//#3
        if (((c3 == cOb1) && (f3 == fOb1)) || ((c3 == cOb2) && (f3 == fOb2)) || ((c3 == cOb3) && (f3 == fOb3)) || ((c3 == cOb4) && (f3 == fOb4)) || ((c3 == cOb5) && (f3 == fOb5)) || ((c3 == cOb6) && (f3 == fOb6))) {
            c++;
            caja2.setImage(verde);
        } else {
            caja2.setImage(amarillo);
        }//#4
        if (((c4 == cOb1) && (f4 == fOb1)) || ((c4 == cOb2) && (f4 == fOb2)) || ((c4 == cOb3) && (f4 == fOb3)) || ((c4 == cOb4) && (f4 == fOb4)) || ((c4 == cOb5) && (f4 == fOb5)) || ((c4 == cOb6) && (f4 == fOb6))) {
            c++;
            caja3.setImage(verde);
        } else {
            caja3.setImage(amarillo);
        }//#5
        if (((c5 == cOb1) && (f5 == fOb1)) || ((c5 == cOb2) && (f5 == fOb2)) || ((c5 == cOb3) && (f5 == fOb3)) || ((c5 == cOb4) && (f5 == fOb4)) || ((c5 == cOb5) && (f5 == fOb5)) || ((c5 == cOb6) && (f5 == fOb6))) {
            c++;
            caja4.setImage(verde);
        } else {
            caja4.setImage(amarillo);
        }//$6
        if (((c6 == cOb1) && (f6 == fOb1)) || ((c6 == cOb2) && (f6 == fOb2)) || ((c6 == cOb3) && (f6 == fOb3)) || ((c6 == cOb4) && (f6 == fOb4)) || ((c6 == cOb5) && (f6 == fOb5)) || ((c6 == cOb6) && (f6 == fOb6))) {
            c++;
            caja5.setImage(verde);
        } else {
            caja5.setImage(amarillo);
        }
        if (c == 6) {
            nextLeven = true;
        }

    }

    @FXML
    private void ReStartLevel(ActionEvent event) throws IOException {
        //el evento del boton "Reiniciar Nivel"
        reiniciarAuto(1);
    }

    private void reiniciarAuto(int autoOusuario) throws FileNotFoundException, MalformedURLException {
        //este metodo es el encargado de dos tipos de reinicio el del evento de boton o 
        //la tecla R que son hechos por el usuario y el reinicio automatico por exeder el 
        //limite de pasos por nivel 

        String direccion = System.getProperty("user.dir") + "/src/Sonidos/die.mp3";
        File file = new File(direccion);
        Media sound = new Media(file.toURI().toURL().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

        //mensaje de reincio automatico por exeder el limite de pasos por nivel
        if (autoOusuario == 2) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("REINICIO AUTOMATICO");
            alert.setHeaderText(null);
            alert.setContentText("HA EXCEDIDO EL LIMITE DE PASOS PERMITIDOS..."
                    + " EL SISTEMA REALIZO UN REINICIO AUTOMATICO."
                    + " PRECIONE ACCEPTAR Y CONTINUE JUGANDO");

            alert.showAndWait();
        }
        //mensaje que se muestra cuando una de las cajas estba en deadlock y no uso 
        //z para deshacer los pasos y se movio mas de 3 pasos y no puede recuperar la caja del deadlock
        if (autoOusuario == 3) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("REINICIO AUTOMATICO");
            alert.setHeaderText(null);
            alert.setContentText("UNA DE LAS CAJAS ESTABA EN DEADLOCK Y NO "
                    + "USO EL (Z) PARA DESHACER LOS PASOS Y PONER LA CAJA EN "
                    + "UNA SONA SEGURA Y NO PUDE TERMINAR EL NIVEL. SE REALIZARA UN "
                    + "REINICIO AUTOMATICO PARA PODER CONTINUAR CON EL JUEGO."
                    + " PRECIONE ACEPTAR PARA CONTINUAR.");

            alert.showAndWait();
        }

        //borrar los datos guardados 
        GuardaMovimientos = 0;
        restrincionZ = 0;
        pasos = 0;
        contadorPasos.setText(String.valueOf(pasos));

        //volver al personaje y a las cajas a sus posiciones originales
        GridPane.setColumnIndex(personaje, 1);
        GridPane.setRowIndex(personaje, 7);

        GridPane.setColumnIndex(caja, 7);
        GridPane.setRowIndex(caja, 5);

        GridPane.setColumnIndex(caja1, 6);
        GridPane.setRowIndex(caja1, 6);

        GridPane.setColumnIndex(caja2, 5);
        GridPane.setRowIndex(caja2, 8);

        GridPane.setColumnIndex(caja3, 7);
        GridPane.setRowIndex(caja3, 8);

        GridPane.setColumnIndex(caja4, 8);
        GridPane.setRowIndex(caja4, 8);

        GridPane.setColumnIndex(caja5, 9);
        GridPane.setRowIndex(caja5, 4);

        d1 = 0;
        d2 = 4;

        //se llama a este metodo para que las cajas vuelva al color de campo libre 
        //que es el de amarillo 
        HabilitarBtnNext();
    }

    private void RegresarPasos() {
        //metodo encardo de deshacer los movimientos hechos, pero solo 3 veces por 
        //nivel a menos que se reinicie el nivel y tener mas de 5 movimentos 

        if ((GuardaMovimientos > 5) && (restrincionZ < 3)) {
            GuardaMovimientos--;
            restrincionZ++;
            GridPane.setColumnIndex(personaje, MovPersonaje[GuardaMovimientos][0]);
            GridPane.setRowIndex(personaje, MovPersonaje[GuardaMovimientos][1]);

            GridPane.setColumnIndex(caja, MovCaja1[GuardaMovimientos][0]);
            GridPane.setRowIndex(caja, MovCaja1[GuardaMovimientos][1]);

            GridPane.setColumnIndex(caja1, MovCaja2[GuardaMovimientos][0]);
            GridPane.setRowIndex(caja1, MovCaja2[GuardaMovimientos][1]);

            GridPane.setColumnIndex(caja2, MovCaja3[GuardaMovimientos][0]);
            GridPane.setRowIndex(caja2, MovCaja3[GuardaMovimientos][1]);

            GridPane.setColumnIndex(caja3, MovCaja4[GuardaMovimientos][0]);
            GridPane.setRowIndex(caja3, MovCaja4[GuardaMovimientos][1]);

            GridPane.setColumnIndex(caja4, MovCaja5[GuardaMovimientos][0]);
            GridPane.setRowIndex(caja4, MovCaja5[GuardaMovimientos][1]);

            GridPane.setColumnIndex(caja5, MovCaja6[GuardaMovimientos][0]);
            GridPane.setRowIndex(caja5, MovCaja6[GuardaMovimientos][1]);

            pasos--;
            contadorPasos.setText(String.valueOf(pasos));
            d2--;
        } else {
            if ((GuardaMovimientos <= 5) && (restrincionZ < 3)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("VERIFIQUE LOS PASOS-->"
                        + "RECUERDE QUE ES NESESARIO MÁS DE 5 PASOS "
                        + "PARA PODER USAR ESTA FUNCION.");

                alert.showAndWait();
            }
            if (restrincionZ == 3) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("ESTA FUNCION SOLO SE PUEDE USAR 3 VECES"
                        + " Y YA HA EXCEDIDO EL LIMITE.");

                alert.showAndWait();
            }
        }

    }

    void DatosDeTransferencia(String nombre, int datos1, int datos2, int datos3, int datos4, int datos5) {
        //metodo encargado de resivir la transferencia de los archivos
        NOMBRE = nombre;
        DATOS1 = datos1;
        DATOS2 = datos2;
        DATOS3 = datos3;
        DATOS4 = datos4;
        DATOS5 = datos5;
        System.out.println("******************");
        System.out.println("Nombre " + nombre);
        System.out.println("Datos 1 " + datos1);
        System.out.println("Datos 2 " + datos2);
        System.out.println("Datos 3 " + datos3);
        System.out.println("Datos 4 " + datos4);
        System.out.println("Datos 5 " + datos5);
        System.out.println("******************");
    }

    private void huevoDePascua() throws MalformedURLException {
        //3.7
        //metodo encargado del huevo de pascua el cual se puede encontrar cada ver que 
        //se reinicie el nivel pero solo se puede obtener el premio una ves 

        if ((columna == 3) && (fila == 7) && (pasos == 8)) {
            HDP++;
        }
        if (HDP == 1) {
            Vcolumna[0] = 13;
            Vfila[0] = 1;
            HDP++;
        }
        //si puede desbloquear el huevo de pascua se va a borrar la restricion del muro 
        //para poder ingresar al huevo de pascua
        if ((hdp == 0) && (columna == 12) && (fila == 1)) {
            String direccion = System.getProperty("user.dir") + "/src/Sonidos/POW.mp3";
            File file = new File(direccion);
            Media sound = new Media(file.toURI().toURL().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("FELICIDADES");
            alert.setHeaderText(null);
            alert.setContentText("HAS ENCONTRADO EL SEGUNDO HUEVO DE PASCUA Y"
                    + " HAS GANADO 25 PUNTOS.");
            alert.showAndWait();
            System.out.println("HA ENCONTRADO EL HUEVO DE PASCUA.");
            hdp = 10000;
        }

    }

    private void DEADLOCK() throws FileNotFoundException, MalformedURLException {
        //metodo enargado de Deadlock el cual consiste en que si una caja queda en 
        //modo deadlock osea que no pueda llegar a la meta o objetivo entonces primero que 
        //todo se ponga en rojo y de un aviso al usuario solo la primera vez que pase 
        //para que pueda deshacer el movimiento sino no tiene movimientos de deshacer 
        //se hara un reinicio automatico en el cual se le dara un aviso al usurio

        //localizaciones del mapa donde la caja esta en deadlock
        int cc[] = {6, 7, 8, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 9, 8, 7, 6, 5, 4, 4, 3, 4, 7, 9, 9, 4, 3, 2};
        int ff[] = {1, 1, 1, 1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 9, 9, 9, 9, 9, 2, 5, 8, 4, 5, 7, 7, 9, 9};

        //contador que registra si una de las cajas esta en esta condicion 
        int registro = 0;

        c1 = GridPane.getColumnIndex(caja);//posiciones actuales de la caja en el gridpane
        f1 = GridPane.getRowIndex(caja);//posiciones actuales de la caja en el gridpane
        c2 = GridPane.getColumnIndex(caja1);//posiciones actuales de la caja1 en el gridpane
        f2 = GridPane.getRowIndex(caja1);//posiciones actuales de la caja1 en el gridpane
        c3 = GridPane.getColumnIndex(caja2);//posiciones actuales de la caja2 en el gridpane
        f3 = GridPane.getRowIndex(caja2);//posiciones actuales de la caja en el gridpane
        c4 = GridPane.getColumnIndex(caja3);//posiciones actuales de la caja3 en el gridpane
        f4 = GridPane.getRowIndex(caja3);//posiciones actuales de la caja3 en el gridpane
        c5 = GridPane.getColumnIndex(caja4);//posiciones actuales de la caja4 en el gridpane
        f5 = GridPane.getRowIndex(caja4);//posiciones actuales de la caja4 en el gridpane
        c6 = GridPane.getColumnIndex(caja5);//posiciones actuales de la caja5 en el gridpane
        f6 = GridPane.getRowIndex(caja5);//posiciones actuales de la caja5 en el gridpane

        Image deadlock = new Image("Imagenes/caja en deadlock.jpg");

        for (int i = 0; i < cc.length; i++) {
            if ((cc[i] == c1) && (ff[i] == f1)) {//si la caja  esta en solo de deadlock
                //suma uno al registro y cabia de a color rojo
                registro++;
                caja.setImage(deadlock);
            }
            if ((cc[i] == c2) && (ff[i] == f2)) {
                registro++;
                caja1.setImage(deadlock);
            }
            if ((cc[i] == c3) && (ff[i] == f3)) {
                registro++;
                caja2.setImage(deadlock);
            }
            if ((cc[i] == c4) && (ff[i] == f4)) {
                registro++;
                caja3.setImage(deadlock);
            }
            if ((cc[i] == c5) && (ff[i] == f5)) {
                registro++;
                caja4.setImage(deadlock);
            }
            if ((cc[i] == c6) && (ff[i] == f6)) {
                registro++;
                caja5.setImage(deadlock);
            }
        }
        //el aviso que solo se ejecuta una vez cuando la primer caja este en modo 
        //deadlock
        if ((Dead == 0) && (registro != 0) && (pasos > 5)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("DEADLOCK");
            alert.setHeaderText(null);
            alert.setContentText("UNA DE LAS CAJAS ESTA EN MODO DEADLOCK PRECIONE Z"
                    + " PARA DESHACER EL PASO Y PODER TERMINAR EL JUEGO");
            alert.showAndWait();
            Dead++;
        }

        if ((registro != 0) && ("Z".equals(D1))) {
            d1--;
        }
        if ((registro != 0) && (!"Z".equals(D1))) {
            d1++;
        }
        if ((registro != 0) && (pasos <= 5)) {
            reiniciarAuto(4);
        }
        if (registro == 0) {
            d1 = 0;
        }
        if (d1 == d2) {
            reiniciarAuto(3);
        }

        if ((registro != 0) && (restrincionZ >= 3)) {
            //el aviso es dado solo si el contador de registro es diferente a 0 y
            //ya no tenga mas movimientos de deshacer 

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("DEADLOCK");
            alert.setHeaderText(null);
            alert.setContentText("SE LLEGO AUN PUNTO DONDE ES IMPOSIBLE LLEVAR UNA "
                    + "DE LAS CAJAS A SU DESTINO POR ELLO SE VA A REALIZAR UN REINICIO "
                    + "AUTOMATICO DEL NIVEL, PRECIONE ´ACEPTAR´ PARA CONTINUAR");
            alert.showAndWait();

            reiniciarAuto(1);
        }

    }
}

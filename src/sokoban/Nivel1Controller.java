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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
public class Nivel1Controller implements Initializable {

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
    private int columna;//posiciones actuales del personaje en el gridpane
    private int fila;//posiciones actuales del personaje en el gridpane

    //las posiciones de las paredes las cuales estan restringidas para el personaje
    private final int Vcolumna[] = {4, 3, 3, 3, 3, 3, 4, 5, 6, 7, 8, 9, 9, 8, 7, 7, 6, 5};
    private final int Vfila[] = {2, 3, 4, 5, 6, 7, 8, 8, 7, 7, 7, 6, 5, 4, 4, 3, 2, 2};

    //contador de pasos del jugador
    private int pasos = 0;

    //matrices las cuales guardar la posiciones del jugador y las cajas para el
    //metodo de deshacer
    private final int[][] MovPersonaje = new int[1000][2];
    private final int[][] MovCaja1 = new int[1000][2];
    private final int[][] MovCaja2 = new int[1000][2];

    //contador de restringir el deshacer a solo 3 veces por nivel a menos que vuelva 
    //a reiniciar el nivel
    int restrincionZ = 0;

    //el contador para guardar las posiciones en los vectores y poder tomar las 
    //posiciones para el evento de deshacer
    int GuardaMovimientos = 0;

    @FXML
    private Text contadorPasos;

    Image imagen;

    int c1;//posiciones actuales de la caja1 en el gridpane
    int f1;//posiciones actuales de la caja1 en el gridpane
    int c2;//posiciones actuales de la caja2 en el gridpane
    int f2;//posiciones actuales de la caja2 en el gridpane

    int cOb1;//posiciones actuales del objetivo1 en el gridpane
    int fOb1;//posiciones actuales del objetivo1 en el gridpane
    int cOb2;//posiciones actuales del objetivo2 en el gridpane
    int fOb2;//posiciones actuales del objetivo2 en el gridpane

    boolean nextLeven = false;//este boolean es para poder habilitar el boton de siguente
    //nivel cuando las cajas esten en los objetivos

    @FXML
    private Button reiniciarNivel;

    //datos a transferir
    String NOMBRE = "";
    int DATOS1 = 29 + 15;

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
        cOb2 = GridPane.getColumnIndex(objetovo1);//cordenada de la fila del objetivo2
        fOb2 = GridPane.getRowIndex(objetovo1);//cordenada de la columna del objetivo2
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
//https://stackoverflow.com/questions/13011892/how-to-locate-the-path-of-the-current-project-directory-in-java-ide
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
            imagen = new Image("Imagenes/Personaje Iz.png");//cambia la imagen del personaje 
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

            if (MURO(columna, fila) == true) {//si es true la posicion del personaje no es igual a la de un muro
                pasos++;//contador de pasos 
                contadorPasos.setText(String.valueOf(pasos));//asigna el contador
                //de pasos al label en pantalla 
                permiso++;
                GuardaMovimientos++;
                MovPersonaje[GuardaMovimientos][0] = columna;
                MovPersonaje[GuardaMovimientos][1] = fila;

            } else {
                //si a en la nueva posicion del peronaje hay un muro retrocede al peronaje a
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
        stage.setTitle("MENU");
        stage.setResizable(false);
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
                //carca la scene del nivel2
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Nivel2.fxml"));
                Parent root = loader.load();

                //obtiene el controlador del la Scene del nivel 2
                Nivel2Controller scene2Controller = loader.getController();

                //verifica si consiguio el logro de pasar el nivel en movimientos minimos
                if (pasos == 29) {
                    pasos -= 5;
                    Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
                    dialogo.setTitle("LOGRO");
                    dialogo.setHeaderText(null);
                    dialogo.setContentText("FELICIDADES HA CONSEGUIDO EL LOGRO DE PASAR "
                            + " EL NIVEL CON EL MINIMO DE PASOS (29). EL CUAL"
                            + " CORRESPONDE A 5 PUNTOS.");
                    dialogo.showAndWait();
                }
                String direccion = System.getProperty("user.dir") + "/src/Sonidos/tubo.mp3";
                File file = new File(direccion);
                Media sound = new Media(file.toURI().toURL().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();
                DATOS1 = pasos;
                //pasa los datos
                scene2Controller.DatosDeTransferencia(NOMBRE, DATOS1);

                //muestra la scena del nivel 2 en una nueva ventana  
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
        //movi?? el persoaje es la misma posicion de una caja

        c1 = GridPane.getColumnIndex(caja);//posiciones actuales de la caja en el gridpane
        f1 = GridPane.getRowIndex(caja);//posiciones actuales de la caja en el gridpane
        c2 = GridPane.getColumnIndex(caja1);//posiciones actuales de la caja1 en el gridpane
        f2 = GridPane.getRowIndex(caja1);//posiciones actuales de la caja1 en el gridpane

        MovCaja1[GuardaMovimientos][0] = c1;//guarda la posicion de la caja en el matriz de movimientos
        MovCaja1[GuardaMovimientos][1] = f1;//guarda la posicion de la caja en el matriz de movimientos
        MovCaja2[GuardaMovimientos][0] = c2;//guarda la posicion de la caja1 en el matriz de movimientos
        MovCaja2[GuardaMovimientos][1] = f2;//guarda la posicion de la caja1 en el matriz de movimientos

        if (((columna == c1) && (fila == f1)) || ((columna == c2) && (fila == f2))) {
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

        c1 = GridPane.getColumnIndex(caja);//cordenada de la columna de la caja 1
        f1 = GridPane.getRowIndex(caja);//cordenada de la fila de la caja 1
        c2 = GridPane.getColumnIndex(caja1);//cordenada de la columna de la caja 2
        f2 = GridPane.getRowIndex(caja1);//cordenada de la fila de la caja 2

        int pasosss = 0;//contador con el proposito de arvertir si el el peronaje 
        //pudo o no mover la caja 

////////caja1///////////////////////////////////////////////////////////////////
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
////////caja2///////////////////////////////////////////////////////////////////
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
        //vulve a recolectar las cordenadas para guardarlas en las matrices de las posiciones 
        c1 = GridPane.getColumnIndex(caja);//cordenada de la columna de la caja 1
        f1 = GridPane.getRowIndex(caja);//cordenada de la fila de la caja 1
        c2 = GridPane.getColumnIndex(caja1);//cordenada de la columna de la caja 2
        f2 = GridPane.getRowIndex(caja1);//cordenada de la fila de la caja 2

        //guarda las cordenadas en las matrices de las cordenas de las cajas 
        MovCaja1[GuardaMovimientos][0] = c1;
        MovCaja1[GuardaMovimientos][1] = f1;
        MovCaja2[GuardaMovimientos][0] = c2;
        MovCaja2[GuardaMovimientos][1] = f2;

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

        c1 = GridPane.getColumnIndex(caja);//cordenada de la columna de la caja 1
        f1 = GridPane.getRowIndex(caja);//cordenada de la fila de la caja 1
        c2 = GridPane.getColumnIndex(caja1);//cordenada de la columna de la caja 2
        f2 = GridPane.getRowIndex(caja1);//cordenada de la fila de la caja 2

        switch (NumeroCaja) {
            //revisa la cardenada mandada por el parametro para saber si no es una 
            //de las posiciones de las otras cajas 
            case 1:
                if ((columnaBWB == c2) && (filaBWB == f2)) {
                    return false;
                }
                break;
            case 2:
                if ((columnaBWB == c1) && (filaBWB == f1)) {
                    return false;
                }
                break;
        }
        return true;
    }

    private void HabilitarBtnNext() throws FileNotFoundException {
        //este metodo habilita el boton de SIGUENTE NIVEL localizando que las 
        //cajas esten el las posiciones de los objetivos 

        c1 = GridPane.getColumnIndex(caja);//cordenada de la columna de la caja 1
        f1 = GridPane.getRowIndex(caja);//cordenada de la fila de la caja 1
        c2 = GridPane.getColumnIndex(caja1);//cordenada de la columna de la caja 2
        f2 = GridPane.getRowIndex(caja1);//cordenada de la fila de la caja 2

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

        if (((c1 == cOb1) && (f1 == fOb1)) || ((c1 == cOb2) && (f1 == fOb2))) {
            c++;
            caja.setImage(verde);
        } else {
            caja.setImage(amarillo);
        }
        if (((c2 == cOb1) && (f2 == fOb1)) || ((c2 == cOb2) && (f2 == fOb2))) {
            c++;
            caja1.setImage(verde);
        } else {
            caja1.setImage(amarillo);
        }
        if (c == 2) {
            nextLeven = true;
        }
    }

    @FXML
    private void ReStartLevel(ActionEvent event) throws MalformedURLException {
        //el evento del boton "Reiniciar Nivel"
        try {
            reiniciarAuto(1);//metodo de reinicio
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Nivel1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            Alert alert = new Alert(AlertType.INFORMATION);
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
        if (autoOusuario == 4) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("REINICIO AUTOMATICO");
            alert.setHeaderText(null);
            alert.setContentText("UNA DE LAS CAJAS ESTABA EN DEADLOCK Y NO "
                    + " SE PUDO USAR EL (Z) PARA DESHACER LOS PASOS POR LA CANTIDAD DE "
                    + "PASOS QUE TIENE. SE REALIZARA UN AUTOMATICO PARA PODER CONTINUAR CON EL JUEGO."
                    + " PRECIONE ACEPTAR PARA CONTINUAR.");

            alert.showAndWait();
        }

        //borrar los datos guardados 
        GuardaMovimientos = 0;
        restrincionZ = 0;
        pasos = 0;
        contadorPasos.setText(String.valueOf(pasos));

        //volver al personaje y a las cajas a sus posiciones originales
        GridPane.setColumnIndex(personaje, 6);
        GridPane.setRowIndex(personaje, 5);

        GridPane.setColumnIndex(caja, 4);
        GridPane.setRowIndex(caja, 6);

        GridPane.setColumnIndex(caja1, 6);
        GridPane.setRowIndex(caja1, 6);

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

            d2--;
            pasos--;
            contadorPasos.setText(String.valueOf(pasos));
        } else {
            if ((GuardaMovimientos <= 5) && (restrincionZ < 3)) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("VERIFIQUE LOS PASOS-->"
                        + "RECUERDE QUE ES NESESARIO M??S DE 5 PASOS "
                        + "PARA PODER USAR ESTA FUNCION.");

                alert.showAndWait();
            }
            if (restrincionZ == 3) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("ESTA FUNCION SOLO SE PUEDE USAR 3 VECES"
                        + " Y YA HA EXCEDIDO EL LIMITE.");

                alert.showAndWait();
            }
        }

    }

    void DatosDeTransferencia(String nombre) {
        //metodo encargado de resivir la transferencia de los datos
        NOMBRE = nombre;
        System.out.println("******************");
        System.out.println("Nombre " + nombre);
        System.out.println("******************");
    }

    private void DEADLOCK() throws FileNotFoundException, MalformedURLException {
        //metodo enargado de Deadlock el cual consiste en que si una caja queda en 
        //modo deadlock osea que no pueda llegar a la meta o objetivo entonces primero que 
        //todo se ponga en rojo y de un aviso al usuario solo la primera vez que pase 
        //para que pueda deshacer el movimiento sino no tiene movimientos de deshacer 
        //se hara un reinicio automatico en el cual se le dara un aviso al usurio

        //localizaciones del mapa donde la caja esta en deadlock
        int cc[] = {4, 4, 5, 8, 8};
        int ff[] = {3, 7, 7, 6, 5};

        //contador que registra si una de las cajas esta en esta condicion 
        int registro = 0;

        c1 = GridPane.getColumnIndex(caja);//posiciones actuales de la caja en el gridpane
        f1 = GridPane.getRowIndex(caja);//posiciones actuales de la caja en el gridpane
        c2 = GridPane.getColumnIndex(caja1);//posiciones actuales de la caja1 en el gridpane
        f2 = GridPane.getRowIndex(caja1);//posiciones actuales de la caja1 en el gridpane

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
                    + "AUTOMATICO DEL NIVEL, PRECIONE ??ACEPTAR?? PARA CONTINUAR");
            alert.showAndWait();

            reiniciarAuto(1);
        }
    }
}

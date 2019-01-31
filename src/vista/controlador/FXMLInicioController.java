/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import DAOImpl.DAOPreguntaImpl;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.Animation.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javax.swing.table.DefaultTableModel;
import pojos.Pregunta;

/**
 *
 * @author Rafael Lebron Peña rlebron1997@gmail.com
 */
public class FXMLInicioController implements Initializable {

    private DAOPreguntaImpl dao;
    private MediaPlayer mediaPlayer;
    private ObservableList<Pregunta> busqueda;
    private Task task;
    private static Thread thread;
    private boolean running = true;

    @FXML
    private TableView tblPreguntas;
    @FXML
    private TableColumn cPreguntas;
    @FXML
    private TableColumn cDificultad;
    @FXML
    private TextField txtBuscaPregunta;
    @FXML
    private TextField txtModificaPregunta;
    @FXML
    private TextField txtPregunta;
    @FXML
    private TextField txtRespUno;
    @FXML
    private TextField txtRespDos;
    @FXML
    private TextField txtRespTres;
    @FXML
    private TextField txtRespCuatro;
    @FXML
    private TextField txtRespCorrecta;
    @FXML
    private TextField txtDificultad;
    @FXML
    private AnchorPane panelPrincipal;

    @FXML
    private void salir() {
        System.exit(0);
    }

    private void rellenaTabla() {

        cPreguntas.setCellValueFactory(new PropertyValueFactory<>("pregunta"));
        cDificultad.setCellValueFactory(new PropertyValueFactory<>("dificultad"));

        tblPreguntas.setItems(dao.getPreguntas());
    }

    //evento del boton borrarPregunta
    @FXML
    private void borrarPregunta(ActionEvent event) {

        if (tblPreguntas.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Borrar Pregunta");
            alert.setHeaderText(null);
            alert.setContentText("¿Desea borrar esta pregunta?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                tblPreguntas.setItems(dao.getPreguntas());

                Iterator<Pregunta> iter = dao.getPreguntas().iterator();

                while (iter.hasNext()) {
                    Pregunta p = iter.next();

                    if (tblPreguntas.getSelectionModel().getSelectedItem() != null) {
                        if (tblPreguntas.getSelectionModel().getSelectedItem().equals(p)) {

                            iter.remove();

                            tblPreguntas.getSelectionModel().clearSelection();
                            txtModificaPregunta.setText("");
                        }
                    }

                }
            } else {

                alert.close();
            }
        }

    }

    @FXML
    private void buscarPregunta(ActionEvent event) {
        busqueda = FXCollections.observableArrayList();

        for (Pregunta p : dao.getPreguntas()) {
            if (p.getPregunta().toLowerCase().contains(txtBuscaPregunta.getText().toLowerCase())) {
                busqueda.add(p);
            }
        }

        tblPreguntas.setItems(busqueda);
    }

    @FXML
    private void modificarPregunta(ActionEvent event) {

        Iterator<Pregunta> iter = dao.getPreguntas().iterator();

        while (iter.hasNext()) {
            Pregunta p = iter.next();
            if (tblPreguntas.getSelectionModel().getSelectedItem() != null) {
                if (tblPreguntas.getSelectionModel().getSelectedItem().equals(p)) {
                    p.setPregunta(txtModificaPregunta.getText());
                }
            }
        }

        tblPreguntas.refresh();
        System.out.println(dao.getPreguntas().toString());

    }

    @FXML
    private void anadirPregunta() {

        if (!txtPregunta.getText().equals("") && !txtRespUno.getText().equals("") && !txtRespDos.getText().equals("") && !txtRespTres.getText().equals("") && !txtRespCuatro.getText().equals("") && !txtRespCorrecta.getText().equals("") && !txtDificultad.getText().equals("")) {
            tblPreguntas.setItems(dao.getPreguntas());
            String[] respuestas = {txtRespUno.getText(), txtRespDos.getText(), txtRespTres.getText(), txtRespCuatro.getText()};
            dao.getPreguntas().add(new Pregunta(txtPregunta.getText(), respuestas, Integer.parseInt(txtRespCorrecta.getText()), Integer.parseInt(txtDificultad.getText())));

            txtPregunta.setText("");
            txtRespUno.setText("");
            txtRespDos.setText("");
            txtRespTres.setText("");
            txtRespCuatro.setText("");
            txtRespCorrecta.setText("");
            txtDificultad.setText("");
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Añadir Pregunta");
            alert.setHeaderText("Error al Añadir Pregunta");
            alert.setContentText("Algún campo está vacío");

            //Poner icono
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("/img/iconerror.png").toString()));

            alert.showAndWait();
        }
    }

    @FXML
    private void eventoTabla() {
        txtModificaPregunta.setText(tblPreguntas.getSelectionModel().getSelectedItem().toString());
        //System.out.println(tblPreguntas.getSelectionModel().getSelectedIndex());
        //System.out.println(tblPreguntas.getSelectionModel().getSelectedItem().toString());
    }

    @FXML
    private void comenzarJuego() throws IOException {

        if (DAOPreguntaImpl.preguntas.size() != 15) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al Iniciar Juego");
            alert.setContentText("Debe haber 15 preguntas para poder jugar");

            //Poner icono
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("/img/iconerror.png").toString()));

            alert.showAndWait();
        } else {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/layout/FXMLJuego.fxml"));
            panelPrincipal.getChildren().setAll(pane);
        }
    }

    @FXML
    void verInstrucciones(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/layout/FXMLInstrucciones.fxml"));
        panelPrincipal.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dao = new DAOPreguntaImpl();

        rellenaTabla();
        //tblPreguntas.getSelectionModel().setCellSelectionEnabled(true);

        System.err.println(thread);

        if (thread == null) {

            thread = new Thread() {
                @Override
                public void run() {
                    if (running) {
                        String musicFile = getClass().getResource("/sound/cancion.mp3").toExternalForm();
                        Media sound = new Media(musicFile);
                        mediaPlayer = new MediaPlayer(sound);
                        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                        mediaPlayer.play();
                    }
                }
            };
            thread.start();
            /*task = new Task() {

                @Override
                protected Object call() throws Exception {

                    return null;
                }

            };
            thread = new Thread(task);
            thread.start();*/

        }

    }

}

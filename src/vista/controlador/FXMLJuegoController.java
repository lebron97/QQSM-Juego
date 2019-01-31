/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import DAOImpl.DAOPreguntaImpl;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import pojos.Pregunta;

/**
 * FXML Controller class
 *
 * @author Rafael Lebron Peña rlebron1997@gmail.com
 */
public class FXMLJuegoController implements Initializable {

    //private DAOPreguntaImpl dao;
    private Random rn;
    private int aleatorio;
    private int correcta;
    private ObservableList<Pregunta> dao;
    private ArrayList<Pregunta> d1;
    private ArrayList<Pregunta> d2;
    private ArrayList<Pregunta> d3;
    private Timeline task;
    private int contadorPregunta = 1;
    private DropShadow shadow, shadowRed;
    //Pasar datos entre diferentes scenes
    private FXMLJuegoTerminadoController jtController;
    private FXMLLoader ultimaVista;
    private Region root;
    private int myFlag = 0;

    @FXML
    private Label lblPregunta;
    @FXML
    private Button btnUno;
    @FXML
    private Button btnDos;
    @FXML
    private Button btnTres;
    @FXML
    private Button btnCuatro;
    @FXML
    private ProgressBar pBar;
    @FXML
    private AnchorPane panelJuego;
    @FXML
    private BarChart<?, ?> bChart;
    @FXML
    private CategoryAxis X;
    @FXML
    private NumberAxis Y;
    @FXML
    private Button btnComodinPersona;
    @FXML
    private Button btnComodinTiempo;
    @FXML
    private Label lblNumeroPregunta;
    @FXML
    private Button btnComodin50;
    @FXML
    private Label lbl500;

    @FXML
    private Label lbl10000;

    @FXML
    private Label lbl500000;

    @FXML
    private Label lbl50000;

    @FXML
    private Label lbl100;

    @FXML
    private Label lbl1000000;

    @FXML
    private Label lbl100000;

    @FXML
    private Label lbl15000;

    @FXML
    private Label lbl30000;

    @FXML
    private Label lbl1000;

    @FXML
    private Label lb2000;

    @FXML
    private Label lbl200000;

    @FXML
    private Label lbl5000;

    public void responder(ActionEvent e) throws IOException, InterruptedException {

        if (e.getSource() == btnUno) {
            //System.out.println((Button)e.getSource());
            if (!d1.isEmpty() && d1.get(aleatorio).getCorrecta() == 0) {
                siguientePreguntaFacil();
                task.playFromStart();
            } else if (!d2.isEmpty() && d2.get(aleatorio).getCorrecta() == 0 && d1.isEmpty()) {
                siguientePreguntaMedia();
                task.playFromStart();
            } else if (!d3.isEmpty() && d3.get(aleatorio).getCorrecta() == 0 && d2.isEmpty()) {
                siguientePreguntaDificil();
                task.playFromStart();
            } else {
                task.stop();

                myFlag = 1;

            }

        }
        if (e.getSource() == btnDos) {
            if (!d1.isEmpty() && d1.get(aleatorio).getCorrecta() == 1) {
                siguientePreguntaFacil();
                task.playFromStart();
            } else if (!d2.isEmpty() && d2.get(aleatorio).getCorrecta() == 1 && d1.isEmpty()) {
                siguientePreguntaMedia();
                task.playFromStart();
            } else if (!d3.isEmpty() && d3.get(aleatorio).getCorrecta() == 1 && d2.isEmpty()) {
                siguientePreguntaDificil();
                task.playFromStart();
            } else {
                task.stop();
               myFlag = 1;
            }

        }
        if (e.getSource() == btnTres) {
            if (!d1.isEmpty() && d1.get(aleatorio).getCorrecta() == 2) {
                siguientePreguntaFacil();
                task.playFromStart();
            } else if (!d2.isEmpty() && d2.get(aleatorio).getCorrecta() == 2 && d1.isEmpty()) {
                siguientePreguntaMedia();
                task.playFromStart();
            } else if (!d3.isEmpty() && d3.get(aleatorio).getCorrecta() == 2 && d2.isEmpty()) {
                siguientePreguntaDificil();
                task.playFromStart();
            } else {
                task.stop();
                myFlag = 1;
            }

        }
        if (e.getSource() == btnCuatro) {
            if (!d1.isEmpty() && d1.get(aleatorio).getCorrecta() == 3) {
                siguientePreguntaFacil();
                task.playFromStart();
            } else if (!d2.isEmpty() && d2.get(aleatorio).getCorrecta() == 3 && d1.isEmpty()) {
                siguientePreguntaMedia();
                task.playFromStart();
            } else if (!d3.isEmpty() && d3.get(aleatorio).getCorrecta() == 3 && d2.isEmpty()) {

                siguientePreguntaDificil();
                task.playFromStart();
            } else {
                task.stop();
                myFlag = 1;
            }

        }

        btnUno.setDisable(false);
        btnDos.setDisable(false);
        btnTres.setDisable(false);
        btnCuatro.setDisable(false);
        contadorPregunta++;
        marcaPremio();
        lblNumeroPregunta.setText(contadorPregunta + "");
        bChart.setVisible(false);

    }

    public void salirDelJuego() throws IOException {
        task.stop();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/layout/FXMLInicio.fxml"));
        panelJuego.getChildren().add(pane);
    }

    @FXML
    public void plantarse() throws IOException {
        /*AnchorPane pane = FXMLLoader.load(getClass().getResource("/vista/FXMLJuegoTerminado.fxml"));
        panelJuego.getChildren().setAll(pane);*/
        task.stop();
        makeFadeOut();
    }

    @FXML
    public void lanzarComodinPublico() {
        btnComodinPersona.setDisable(true);
        bChart.setVisible(true);
        int correcto;
        Random rn = new Random();
        int porcentaje1 = rn.nextInt(40) + 40;
        int porcentaje2 = 80 - porcentaje1;
        int porcentaje3 = 90 - (porcentaje1 + porcentaje2);
        int porcentaje4 = 100 - (porcentaje1 + porcentaje2 + porcentaje3);

        int[] porcentajes = {porcentaje1, porcentaje2, porcentaje3, porcentaje4};

        XYChart.Series set1 = new XYChart.Series();

        if (!d1.isEmpty()) {
            correcto = d1.get(aleatorio).getCorrecta();
        } else if (!d2.isEmpty()) {
            correcto = d2.get(aleatorio).getCorrecta();
        } else {
            correcto = d3.get(aleatorio).getCorrecta();
        }

        if (correcto == 0) {
            set1.getData().add(new XYChart.Data("a)", porcentajes[0]));
            set1.getData().add(new XYChart.Data("b)", porcentajes[1]));
            set1.getData().add(new XYChart.Data("c)", porcentajes[3]));
            set1.getData().add(new XYChart.Data("d)", porcentajes[2]));
        }
        if (correcto == 1) {
            set1.getData().add(new XYChart.Data("a)", porcentajes[1]));
            set1.getData().add(new XYChart.Data("b)", porcentajes[0]));
            set1.getData().add(new XYChart.Data("c)", porcentajes[3]));
            set1.getData().add(new XYChart.Data("d)", porcentajes[2]));
        }
        if (correcto == 2) {
            set1.getData().add(new XYChart.Data("a)", porcentajes[3]));
            set1.getData().add(new XYChart.Data("b)", porcentajes[1]));
            set1.getData().add(new XYChart.Data("c)", porcentajes[0]));
            set1.getData().add(new XYChart.Data("d)", porcentajes[2]));
        }
        if (correcto == 3) {
            set1.getData().add(new XYChart.Data("a)", porcentajes[2]));
            set1.getData().add(new XYChart.Data("b)", porcentajes[1]));
            set1.getData().add(new XYChart.Data("c)", porcentajes[3]));
            set1.getData().add(new XYChart.Data("d)", porcentajes[0]));
        }

        bChart.getData().addAll(set1);
    }

    @FXML
    public void lanzarComodinTiempo() {
        btnComodinTiempo.setDisable(true);
        task.stop();
    }

    @FXML
    public void lanzarComodin50() {
        btnComodin50.setDisable(true);
        int correcto;

        if (!d1.isEmpty()) {
            correcto = d1.get(aleatorio).getCorrecta();
        } else if (!d2.isEmpty()) {
            correcto = d2.get(aleatorio).getCorrecta();
        } else {
            correcto = d3.get(aleatorio).getCorrecta();
        }

        if (correcto == 0) {
            btnDos.setDisable(true);
            btnCuatro.setDisable(true);
        }
        if (correcto == 1) {
            btnUno.setDisable(true);
            btnCuatro.setDisable(true);
        }
        if (correcto == 2) {
            btnDos.setDisable(true);
            btnCuatro.setDisable(true);
        }
        if (correcto == 3) {
            btnUno.setDisable(true);
            btnTres.setDisable(true);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        d1 = new ArrayList<>();
        d2 = new ArrayList<>();
        d3 = new ArrayList<>();

        //dao = new DAOPreguntaImpl();
        dao = DAOPreguntaImpl.preguntas;

        rn = new Random();

        for (int i = 0; i < dao.size(); i++) {
            if (dao.get(i).getDificultad() == 1) {
                d1.add(dao.get(i));
            }
            if (dao.get(i).getDificultad() == 2) {
                d2.add(dao.get(i));
            }
            if (dao.get(i).getDificultad() == 3) {
                d3.add(dao.get(i));
            }
        }
        System.out.println(d1.get(aleatorio).getCorrecta());
        aleatorio = rn.nextInt(d1.size());
        rellenoD1(aleatorio);

        task = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(pBar.progressProperty(), 0)
                ),
                new KeyFrame(
                        Duration.seconds(20),
                        new KeyValue(pBar.progressProperty(), 1)
                )
        );

        task.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                /*AnchorPane pane = FXMLLoader.load(getClass().getResource("/vista/FXMLJuegoTerminado.fxml"));
                panelJuego.getChildren().setAll(pane);*/
                makeFadeOut();
            }
        });
        task.playFromStart();

        shadow = new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(43, 179, 252), 30.63, 0.77, 0, 0);
        shadowRed = new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(240, 20, 20), 30.63, 0.77, 0, 0);

        //Cambio de scene con tipoFXMLLoader
        //Pasar datos entre diferentes scenes
        ultimaVista = new FXMLLoader(getClass().getResource("/layout/FXMLJuegoTerminado.fxml"));
        try {
            root = (Region) ultimaVista.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLJuegoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Obtengo el controlador de otro FXML
        jtController = ultimaVista.<FXMLJuegoTerminadoController>getController();

    }

    public void rellenoD1(int aleatorio) {
        lblPregunta.setText(d1.get(aleatorio).getPregunta());
        btnUno.setText(d1.get(aleatorio).getRespuestas()[0]);
        btnDos.setText(d1.get(aleatorio).getRespuestas()[1]);
        btnTres.setText(d1.get(aleatorio).getRespuestas()[2]);
        btnCuatro.setText(d1.get(aleatorio).getRespuestas()[3]);
    }

    public void rellenoD2(int aleatorio) {
        lblPregunta.setText(d2.get(aleatorio).getPregunta());
        btnUno.setText(d2.get(aleatorio).getRespuestas()[0]);
        btnDos.setText(d2.get(aleatorio).getRespuestas()[1]);
        btnTres.setText(d2.get(aleatorio).getRespuestas()[2]);
        btnCuatro.setText(d2.get(aleatorio).getRespuestas()[3]);
    }

    public void rellenoD3(int aleatorio) {
        lblPregunta.setText(d3.get(aleatorio).getPregunta());
        btnUno.setText(d3.get(aleatorio).getRespuestas()[0]);
        btnDos.setText(d3.get(aleatorio).getRespuestas()[1]);
        btnTres.setText(d3.get(aleatorio).getRespuestas()[2]);
        btnCuatro.setText(d3.get(aleatorio).getRespuestas()[3]);
    }

    public void siguientePreguntaFacil() {
        d1.remove(aleatorio);
        if (d1.size() == 0) {
            aleatorio = rn.nextInt(d2.size());
            rellenoD2(aleatorio);
        } else {
            aleatorio = rn.nextInt(d1.size());
            System.out.println(d1.get(aleatorio).getCorrecta());
            rellenoD1(aleatorio);

        }
    }

    public void siguientePreguntaMedia() {
        d2.remove(aleatorio);
        if (d2.isEmpty()) {
            aleatorio = rn.nextInt(d3.size());
            rellenoD3(aleatorio);
        } else {
            aleatorio = rn.nextInt(d2.size());
            rellenoD2(aleatorio);
        }

    }

    public void siguientePreguntaDificil() throws InterruptedException, IOException {
        d3.remove(aleatorio);
        if (d3.isEmpty()) {
            task.stop();
            lbl1000000.setEffect(shadowRed);
            jtController.setTitulo(lbl1000000.getText() + " de Rupias");

            makeFadeOut();
        } else {
            aleatorio = rn.nextInt(d3.size());
            rellenoD3(aleatorio);
        }
    }

    public void marcaPremio() {

        if (myFlag == 1) {
            if (contadorPregunta < 6) {
                jtController.setTitulo("3 Gallinas");
            } else if (contadorPregunta >= 6 && contadorPregunta < 11) {
                jtController.setTitulo(lbl5000.getText() + " Rupias");
            } else if (contadorPregunta >= 11) {
                jtController.setTitulo(lbl100000.getText() + " Rupias");
            }
            makeFadeOut();
        } else {
            switch (contadorPregunta) {

                case 2:
                    lbl100.setEffect(shadow);
                    //Pasar datos entre diferentes scenes
                    jtController.setTitulo(lbl100.getText() + " Rupias");
                    break;
                case 3:
                    lbl500.setEffect(shadow);
                    jtController.setTitulo(lbl500.getText() + " Rupias");
                    break;
                case 4:
                    lbl1000.setEffect(shadow);
                    jtController.setTitulo(lbl1000.getText() + " Rupias");
                    break;
                case 5:
                    lb2000.setEffect(shadow);
                    jtController.setTitulo(lb2000.getText() + " Rupias");
                    break;
                case 6:
                    lbl5000.setEffect(shadowRed);
                    jtController.setTitulo(lbl5000.getText() + " Rupias");
                    break;
                case 7:
                    lbl10000.setEffect(shadow);
                    jtController.setTitulo(lbl10000.getText() + " Rupias");
                    break;
                case 8:
                    lbl15000.setEffect(shadow);
                    jtController.setTitulo(lbl15000.getText() + " Rupias");
                    break;
                case 9:
                    lbl30000.setEffect(shadow);
                    jtController.setTitulo(lbl30000.getText() + " Rupias");
                    break;
                case 10:
                    lbl50000.setEffect(shadow);
                    jtController.setTitulo(lbl50000.getText() + " Rupias");
                    break;
                case 11:
                    lbl100000.setEffect(shadowRed);
                    jtController.setTitulo(lbl100000.getText() + " Rupias");
                    break;
                case 12:
                    lbl200000.setEffect(shadow);
                    jtController.setTitulo(lbl200000.getText() + " Rupias");
                    break;

                case 13:
                    lbl500000.setEffect(shadow);
                    jtController.setTitulo(lbl500000.getText() + " Rupias");
                    break;

            }
        }
    }

    //Esto es para hacer una transicion entre scenes
    private void makeFadeOut() {
        FadeTransition fTransition = new FadeTransition();
        fTransition.setDuration((Duration.millis(1000)));
        fTransition.setNode(panelJuego);
        fTransition.setFromValue(1);
        fTransition.setToValue(0);

        fTransition.setOnFinished((ActionEvent event) -> {
            try {
                ultimaEscena();
            } catch (IOException ex) {
                Logger.getLogger(FXMLJuegoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        fTransition.play();
    }

    //Aqui se cambia de escena, en los otros solo se cambia el contenido.
    private void ultimaEscena() throws IOException {

        Scene newScene = new Scene(root);

        Stage curStage = (Stage) panelJuego.getScene().getWindow();

        curStage.setScene(newScene);
    }

}

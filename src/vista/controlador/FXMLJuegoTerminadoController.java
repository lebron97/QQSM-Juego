/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Rafael Lebron Peña rlebron1997@gmail.com
 */
public class FXMLJuegoTerminadoController implements Initializable {

    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblPremio;
    @FXML
    private AnchorPane panelFinal;

    public void setTitulo(String titulo) {
        lblPremio.setText(titulo);
    }

    public void volverAEmpezar() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/layout/FXMLInicio.fxml"));
        panelFinal.getChildren().setAll(pane);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

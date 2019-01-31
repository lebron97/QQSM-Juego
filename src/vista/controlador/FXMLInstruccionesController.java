/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Rafael Lebron Peña rlebron1997@gmail.com
 */
public class FXMLInstruccionesController implements Initializable {
    
    
    @FXML
    private AnchorPane panelInstrucciones;

    @FXML
    void irAtras(ActionEvent event) {

        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/layout/FXMLInicio.fxml"));
            panelInstrucciones.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLInstruccionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

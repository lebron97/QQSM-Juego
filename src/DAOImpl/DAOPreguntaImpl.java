/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImpl;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pojos.Pregunta;

/**
 *
 * @author Rafael Lebron Peña rlebron1997@gmail.com
 */
public class DAOPreguntaImpl {
    
    public static ObservableList<Pregunta> preguntas;
    
    public DAOPreguntaImpl(){
        
        preguntas = FXCollections.observableArrayList();
        
        String [] respuestaUNO = {"a) Sufragio", " b) Adagio", " c) Naufragio" , " d) Prestigio"};
        preguntas.add(new Pregunta("Sistema electoral para determinar las personas que ocuparan cargos publicos:", respuestaUNO, 0, 1));
        
        String [] respuestaDOS = {"a) Irritable", "b) Hambrienta", "c) Furiosa" , "d) Asustada"};
        preguntas.add(new Pregunta("Una persona famelica esta:", respuestaDOS, 1, 1));
        
        String [] respuestaCUATRO   = {"a) Cejas", "b) Pupilas" , "c) Parpados", "d) Anteojos"};
        preguntas.add(new Pregunta("Son las membranas movibles cubiertas de piel que resguardan los ojos:", respuestaCUATRO, 2, 1));
        
        String [] respuestaCINCO = {"a) Calcio", "b) Pigmentación", "c) Vitamina A" , "d) Oxígeno"};
        preguntas.add(new Pregunta("El albinismo se presenta por la carencia de:", respuestaCINCO, 1, 1));
        
        String [] respuestaSEIS = {"a) Dinamometro", "b) Micrometro", "c) Oleometro" , "d) Holometro"};
        preguntas.add(new Pregunta("¿Cual de estos instrumentos mide la densidad de los aceites?", respuestaSEIS, 2, 1));
        
        String [] respuestaTRES = {"a) Carabobo", "b) Urica", "c) Mucuritas" , "d) Calabozo"};
        preguntas.add(new Pregunta("Jose Tomas Boves murio en la Batalla de:", respuestaTRES, 1, 2));
        
        String [] respuestaSIETE = {"a) Sicario", "b) Mecanicas celestes", "c) Tokio Paraguaipoa" , "d) Santera"};
        preguntas.add(new Pregunta("Fina Torres dirigio una de las siguientes películas:", respuestaSIETE, 1, 2));
        
        String [] respuestaOCHO = {"a) Croacia", "b) Alemania", "c) Portugal" , "d) Turquía"};
        preguntas.add(new Pregunta("¿Cuál de los siguientes países limita con Francia?", respuestaOCHO, 1, 2));
        
        String [] respuestaNUEVE = {"a) Carla", "b) Alba", "c) Marta" , "d) Laura"};
        preguntas.add(new Pregunta("Según la canción de los Hombres G, ¿quien tiene un marcapasos?", respuestaNUEVE, 2, 2));
        
        String [] respuestaDIEZ = {"a) Frío", "b) Nostalgia", "c) Calor" , "d) Sueño"};
        preguntas.add(new Pregunta("En venezolano \"pacheco\" significa", respuestaDIEZ, 0, 2));
        
        String [] respuestaONCE = {"a) Alegría", "b) Flojera", "c) Fama" , "d) Sueño"};
        preguntas.add(new Pregunta("Según el refran, el que se acuesta a dormir, es porque cría:", respuestaONCE, 2, 3));
        
        String [] respuestaDOCE = {"a) Henrique V", "b) Ricardo I", "c) Henrique I" , "d) Henrique II"};
        preguntas.add(new Pregunta("¿Cuál de estos reyes estuvo casado con Leonor de Aquitania?", respuestaDOCE, 3, 3));
        
        String [] respuestaTRECE = {"a) Granos", "b) Flores", "c) Arboles" , "d) Vegetales"};
        preguntas.add(new Pregunta("Si plantaras las semilla de 'Quercus Robur', ¿qué crecería? ", respuestaTRECE, 2, 3));
        
        String [] respuestaCATORCE = {"a) Preakness Stakes", "b) Arlington Million", "c) Derby de Kentucky" , "d) Belmont Stakes"};
        preguntas.add(new Pregunta("¿Cuál de las siguientes no es una de las carreras de caballos de la Triple Corona Americana?", respuestaCATORCE, 1, 3));
        
        String [] respuestaQUINCE = {"a) Freddie Mills", "b) Terry Spinks", "c) Billy Wells el Bombardero" , "d) Don Cockell"};
        preguntas.add(new Pregunta("¿Qué boxeador fue famoso por sonar el gong en la introducción de las películas de J. Arthur Rank?", respuestaQUINCE, 2, 3));
        
    }
    
    
     public String toString(){
        
          String visualizar = "";
        
        for(int i = 0; i < preguntas.size(); i++){
            
            visualizar  += preguntas.get(i) + "\n";
        }
        
        return visualizar;
        
    }

    public ObservableList<Pregunta> getPreguntas() {
        return preguntas;
    }
    
    
    
}

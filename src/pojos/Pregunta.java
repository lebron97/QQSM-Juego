/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author Rafael Lebron Peña rlebron1997@gmail.com
 */
public class Pregunta {
    
    private String pregunta;
    private String[] respuestas;
    private int correcta, dificultad;

    public Pregunta(String pregunta, String[] respuestas, int correcta, int dificultad) {
        this.pregunta = pregunta;
        this.respuestas = respuestas;
        this.correcta = correcta;
        this.dificultad = dificultad;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public int getDificultad() {
        return dificultad;
    }

    public int getCorrecta() {
        return correcta;
    }

    public String[] getRespuestas() {
        return respuestas;
    }
    
    
    @Override
    public String toString() {
        return pregunta;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pregunta other = (Pregunta) obj;
        if (!Objects.equals(this.pregunta, other.pregunta)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloPersona;
import Vista.MenúPrincipal;
import Vista.Persona;

/**
 *
 * @author LENOVO
 */
public class Inicio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MenúPrincipal inicio = new MenúPrincipal();
        ControladorMenuprincipal menu1 = new ControladorMenuprincipal(inicio);
        menu1.IniciaBotones();
    }
    
}

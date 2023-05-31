/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloPersona;
import Vista.Persona;
import Vista.MenúPrincipal;

/**
 *
 * @author LENOVO
 */
public class ControladorMenuprincipal {
    private MenúPrincipal menu;
    
    public ControladorMenuprincipal(MenúPrincipal menu){
        this.menu = menu;
    }
    
    public void IniciaBotones(){
        menu.setVisible(true);
        menu.getBtn_clientes().addActionListener(l-> Menupersonas());
    }
    
    private void Menupersonas(){
    Persona vistaInicio = new Persona();
        ModeloPersona modelo = new ModeloPersona();
        
        ControladorPersona controladorIincio = new ControladorPersona(modelo,vistaInicio);
        controladorIincio.Iniciar();
        controladorIincio.Botones();
        menu.getjDesktopPane1().add(vistaInicio);
}
}



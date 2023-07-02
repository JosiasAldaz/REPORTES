/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloPersona;
import Modelo.ModeloProducto;
import Vista.Persona;
import Vista.MenúPrincipal;
import Vista.Producto;
import Vista.Factura;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        menu.getBtn_productos().addActionListener(l->Menuproductos());
        menu.getBtn_factura().addActionListener(l->{
            try {
                MenuFactura();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorMenuprincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    private void Menupersonas(){
        Persona vistaInicio = new Persona();
        ModeloPersona modelo = new ModeloPersona();
        
        ControladorPersona controladorIincio = new ControladorPersona(modelo,vistaInicio);
        controladorIincio.Iniciar();
        controladorIincio.Botones();
        menu.getjDesktopPane1().add(vistaInicio);
}
    
    private void Menuproductos(){
        Producto VistaProducto = new Producto();
        ModeloProducto modeloProd = new ModeloProducto();
        ControladorProducto controladorPrincipal = new ControladorProducto(VistaProducto,modeloProd);
        controladorPrincipal.Iniciar();
        controladorPrincipal.IniciarBotones();
        menu.getjDesktopPane1().add(VistaProducto);
    }
    
    private void MenuFactura() throws SQLException{
        Factura vista = new Factura();
        ModeloProducto mp = new ModeloProducto();
        ControladorFactura modeloFact = new ControladorFactura(vista,mp);
        menu.getjDesktopPane1().add(vista);
    }
}



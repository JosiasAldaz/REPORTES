/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloPersona;
import Modelo.ModeloProducto;
import Vista.Producto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class ControladorProducto {
    private ModeloProducto modeloProd = new ModeloProducto();
    private Producto vista;
    validaciones validar = new validaciones();
    int fila ;
    
    public ControladorProducto(Producto vista,ModeloProducto modeloProd) {
        this.vista = vista;
        this.modeloProd = modeloProd;
    }
    
    public void Iniciar(){
        vista.setVisible(true);
    }
    
    public void IniciarBotones(){
        vista.getBtn_ingresar().addActionListener(l->{
            try {
                
                llenardo();

                JOptionPane.showMessageDialog(null,"SE HA REGISTRADO CORRECTAMENTE");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"HA OCURRIDO UN ERROR EN EL INGRESO");
                Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        vista.getTxt_precio().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                validar.Decimales(evt);
            }
        });
        
        vista.getTxt_nombre().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                validar.letras(evt);
            }
        });
        
        vista.getTxt_stock().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                validar.Numeros(evt);
            }
        });
        
        vista.getTxardescripcion().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                validar.letras(evt);
            }
        });
        
        vista.getBtn_mostrar().addActionListener(l->{
            try {
                Mostrar(modeloProd.Listar());
            } catch (SQLException ex) {
                Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        vista.getBtn_buscar().addActionListener(l->{
            try {
                reserch(vista.getTxt_buscarprod().getText());
            } catch (SQLException ex) {
                Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        vista.getTbalaProductos().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProyectarProducto();
            }
        });
        
        vista.getBtn_modificar().addActionListener(l->{
            try {
                ValidacionModificar();
                JOptionPane.showMessageDialog(null,"SE HAN GUARDADO LOS CAMBIOS CORRECTAMENTE");
                borrar();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        vista.getBtn_eliminar().addActionListener(l->{
            try {
                int result = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres salir?", "Salir", JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION){
                  Eliminar();  
                }else{
                    JOptionPane.showMessageDialog(null,"SE HA CANCELADO EL PROCESO DE ELIMINACIÓN");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        vista.getJlabel_salir().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vista.dispose();
            }
        });
    }
    
    public void setear(){
        modeloProd.setPrecio(Double.parseDouble(vista.getTxt_precio().getText()));
        modeloProd.setDescipcion(vista.getTxardescripcion().getText());
        modeloProd.setNombre(vista.getTxt_nombre().getText());
        modeloProd.setStock(Integer.parseInt(vista.getTxt_stock().getText()));
    }
    
    public void borrar(){
        vista.getTxt_nombre().setText("");
        vista.getTxardescripcion().setText("");
        vista.getTxt_precio().setText("");
        vista.getTxt_stock().setText("");
    }
    
    public void Mostrar(ArrayList <ModeloProducto> indice){
        DefaultTableModel mTabla;
        mTabla = (DefaultTableModel) vista.getTbalaProductos().getModel();
        mTabla.setNumRows(0);
        // Uso de una expresion landa
        if(indice.isEmpty()){
            JOptionPane.showMessageDialog(null,"NO HAY PRODUCTOS REGISTRADOS");
        }else{
            indice.stream().forEach(cam -> {
            String[] filaNueva = {String.valueOf(cam.getCódigo()), cam.getNombre(), String.valueOf(cam.getPrecio()),String.valueOf(cam.getStock()),cam.getDescipcion()};
            mTabla.addRow(filaNueva);
        });
        }
        
    }
    
    public void llenardo() throws SQLException{
        if(vista.getTxt_nombre().getText().equals("") || vista.getTxt_precio().getText().equals("") || vista.getTxt_stock().getText().equals("") || vista.getTxardescripcion().getText().equals("")){
            JOptionPane.showMessageDialog(null, "DEBE LLENAR TODOS LOS CAMPOS PARA PODER REALIZAR EL REGISTRO");
        }else{
            setear();
            modeloProd.Ingresar();
            borrar();
        }
    }
    
    public void reserch(String codigo) throws SQLException{
        if(vista.getTxt_buscarprod().getText().equals("")){
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR UN CODIGO DE 10 DIGITOS");
        }else{
            ModeloProducto producto = modeloProd.busqueda(codigo);
            DefaultTableModel mTabla;
            mTabla = (DefaultTableModel) vista.getTbalaProductos().getModel();
            mTabla.setNumRows(0);
            String[] filaNueva = {String.valueOf(producto.getCódigo()), producto.getNombre(), String.valueOf(producto.getPrecio()),String.valueOf(producto.getStock()),producto.getDescipcion()};
            mTabla.addRow(filaNueva);
        }
        
    }
    
    public void ProyectarProducto(){
        
        vista.getTxt_nombre().setText(vista.getTbalaProductos().getValueAt(fila, 1).toString());
        vista.getTxt_precio().setText(vista.getTbalaProductos().getValueAt(fila, 2).toString());
        vista.getTxt_stock().setText(vista.getTbalaProductos().getValueAt(fila, 3).toString());
        vista.getTxardescripcion().setText(vista.getTbalaProductos().getValueAt(fila, 4).toString());
    }
    
    public void ValidacionModificar() throws SQLException{
        if(vista.getTxt_precio().getText().length()==0 || vista.getTxt_nombre().getText().length()==0 || vista.getTxt_stock().getText().length()==0 || vista.getTxardescripcion().getText().length()==0){
            JOptionPane.showMessageDialog(null, "NO PUEDE DEJAR LOS CAMPOS VACIOS, REVISE Y LLENE TODOS LOS CAMPOS");
        }else{
            int fila = vista.getTbalaProductos().getSelectedRow();
            modeloProd.Update(Double.parseDouble(vista.getTxt_precio().getText()), vista.getTxt_nombre().getText(), vista.getTxardescripcion().getText(), Integer.parseInt(vista.getTxt_stock().getText()),Integer.parseInt(vista.getTbalaProductos().getValueAt(fila, 0).toString()));
        }
    }
    
    public void Eliminar() throws SQLException{
        fila = vista.getTbalaProductos().getSelectedRow();
        String codigo = vista.getTbalaProductos().getValueAt(fila, 0).toString();
        System.out.println(codigo);
        modeloProd.Delete(codigo);
    }
}

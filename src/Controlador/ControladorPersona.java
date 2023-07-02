/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ConeexionBD;
import Modelo.ModeloPersona;
import Vista.Persona;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.postgresql.util.PSQLException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author LENOVO
 */
public class ControladorPersona {
    private ModeloPersona modeloP = new ModeloPersona();
    private Persona vistaP;

    public ControladorPersona(ModeloPersona modeloP, Persona vistaP) {
        this.modeloP = modeloP;
        this.vistaP = vistaP;
    }

    public void Iniciar(){
        vistaP.setVisible(true);
    }
    
    public void Botones(){
        vistaP.getBtn_ingresar().addActionListener(l->Ingresar());
        vistaP.getBtn_buscar().addActionListener(l->{
            try {
                validar();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        vistaP.getTbalaPersonas().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Proyectar();
            }
        });
        
        vistaP.getTxt_cédula().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                if (evt.getKeyChar() <= 57 && evt.getKeyChar() >= 48) {

                } else {
                    if (evt.getKeyChar() == 8) {

                    } else {
                        JOptionPane.showMessageDialog(null, "SOLO PUEDE INGRESAR NÚMEROS");
                        evt.consume();
                    }
                }
            }
        });
        
        vistaP.getTxt_nombre().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                if (evt.getKeyChar() <= 122 && evt.getKeyChar() >= 97 ) {

                } else {
                    if (evt.getKeyChar() == 8) {

                    } else {
                        if(evt.getKeyChar() <= 90 && evt.getKeyChar() >= 65){
                            
                        }else{
                            JOptionPane.showMessageDialog(null, "SOLO PUEDE INGRESAR LETRAS");
                            evt.consume();
                        }
                        
                    }
                }
            }
        });
        
        vistaP.getTxt_apellido().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                if (evt.getKeyChar() <= 122 && evt.getKeyChar() >= 97 ) {

                } else {
                    if (evt.getKeyChar() == 8) {

                    } else {
                        if(evt.getKeyChar() <= 90 && evt.getKeyChar() >= 65){
                            
                        }else{
                            JOptionPane.showMessageDialog(null, "SOLO PUEDE INGRESAR LETRAS");
                            evt.consume();
                        }
                        
                    }
                }
            }
        });
        
        vistaP.getBtn_modificar().addActionListener(l->{
            try {
                Modificar();
                vistaP.getTxt_nombre().setText("");
                vistaP.getTxt_apellido().setText("");
                vistaP.getTxt_cédula().setText("");
                
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        vistaP.getBtn_mostrar().addActionListener(l-> {
            try {
                mostrar();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        vistaP.getBtn_eliminar().addActionListener(l->{
            try {
                Eliminar();
                JOptionPane.showMessageDialog(null,"SE HA ELIMINADO CORRECTAMENTE AL USUARIO");
                vistaP.getTxt_nombre().setText("");
                vistaP.getTxt_apellido().setText("");
                vistaP.getTxt_cédula().setText("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"HA OCURRIDO UN ERROR AL MOMENTO DE ELIMINAR");
                Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        vistaP.getBtn_mostrar_reporte().addActionListener(l-> printReport());
        
        vistaP.getBtn_mostrar_estylo().addActionListener(l->printReportStylos());
    }
    
    
    
    public void Ingresar(){
        try {
                Date fecha = vistaP.getFecha_nacimiento().getDate();
                Long d = fecha.getTime();
                java.sql.Date nac = new java.sql.Date(d);
                modeloP.setNombre(vistaP.getTxt_nombre().getText());
                modeloP.setApellidoi(vistaP.getTxt_apellido().getText());
                modeloP.setCedula(vistaP.getTxt_cédula().getText());
                modeloP.setFechanacimiento(nac);
                modeloP.insertar();
                vistaP.getTxt_cédula().setText("");
                vistaP.getTxt_nombre().setText("");
                vistaP.getTxt_apellido().setText("");
                JOptionPane.showMessageDialog(null,"SE HA INGRESADO CORRECTAMENTE LA PERSONA");
                } catch (PSQLException ex) {
                    Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null,"HA OCURRIDO UN ERROR AL MOMENTO DEL INGRESO");
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    public void Buscar() throws SQLException{
        ModeloPersona buscar = new ModeloPersona();
        buscar.setCedula(vistaP.getTxt_buscar().getText());
        buscar = buscar.select();
        System.out.println();
        if(buscar.getCedula() == null){
            JOptionPane.showMessageDialog(null,"NO SE HA ENCONTRADO NINGUN CLIENTE CON ESTA CÉDULA");
            vistaP.getTxt_buscar().setText("");
        }else{
        DefaultTableModel mTabla;
        mTabla = (DefaultTableModel) vistaP.getTbalaPersonas().getModel();
        mTabla.setNumRows(0);
        String[] filaNueva = {String.valueOf(buscar.getCedula()), buscar.getNombre(), buscar.getApellidoi(),String.valueOf(ObtenerEdad(buscar.getFechanacimiento()))};
        mTabla.addRow(filaNueva);
        }
        
    }
    public long ObtenerEdad(Date fechaNacimiento){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaNacimiento);
        LocalDate selectedDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        long años;
        LocalDate birthday = LocalDate.now();
        años = ChronoUnit.DAYS.between(selectedDate,birthday);
        años = años / 365;
        return años;
    }
    
    public void Proyectar(){
        int seleccionado = vistaP.getTbalaPersonas().getSelectedRow();
        vistaP.getTxt_cédula().setText(vistaP.getTbalaPersonas().getValueAt(seleccionado,0).toString());
        vistaP.getTxt_cédula().setEditable(false);
        vistaP.getTxt_nombre().setText(vistaP.getTbalaPersonas().getValueAt(seleccionado,1).toString());
        vistaP.getTxt_apellido().setText(vistaP.getTbalaPersonas().getValueAt(seleccionado,2).toString());
    }
    
    public void validar() throws SQLException{
        if(vistaP.getTxt_buscar().getText().matches("\\d{10}")){
            Buscar();
        }else{
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR 10 NÚMEROS");
        }
        
    }
    
    public void Modificar() throws SQLException{
        modeloP.Modificar(vistaP.getTxt_nombre().getText(), vistaP.getTxt_apellido().getText(), vistaP.getTxt_cédula().getText());
    }
    
    public void mostrar() throws SQLException{
        DefaultTableModel mTabla;
        mTabla = (DefaultTableModel) vistaP.getTbalaPersonas().getModel();
        mTabla.setNumRows(0);
        ArrayList<ModeloPersona>  listapersonas = modeloP.Listar();
        // Uso de una expresion landa

        listapersonas.stream().forEach(cam -> {
            String[] filaNueva = {cam.getCedula(), cam.getNombre(), cam.getApellidoi(),String.valueOf(ObtenerEdad(cam.getFechanacimiento()))};
            mTabla.addRow(filaNueva);
        });
    }
    
    public void Eliminar() throws SQLException{
        int seleccionado = vistaP.getTbalaPersonas().getSelectedRow();
        modeloP.Delete(vistaP.getTbalaPersonas().getValueAt(seleccionado,0).toString());
    }
    
    public void printReport() {
    try {
        ConeexionBD conmsql=new ConeexionBD();
        JasperReport jr =(JasperReport)JRLoader.loadObject(getClass().getResource("/Reportes/Personas.jasper"));
        System.out.println(conmsql.conectar());
        JasperPrint print = JasperFillManager.fillReport(jr, new HashMap<>(),conmsql.conectar());
        JasperViewer pv=new JasperViewer(print,false);
        pv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pv.setVisible(true);
    } catch (JRException e) {
        e.printStackTrace();
    }
}
    
    public void printReportStylos(){
        try {
        ConeexionBD conmsql=new ConeexionBD();
        JasperReport jr =(JasperReport)JRLoader.loadObject(getClass().getResource("/Reportes/Reporte_facturas.jasper"));
        System.out.println(conmsql.conectar());
        Map<String, Object> map = new HashMap<>();
        String id = JOptionPane.showInputDialog(null, "Ingrese el ID de la factura que desea generar");
        map.put("ID_enc", Integer.parseInt(id));
        JasperPrint print = JasperFillManager.fillReport(jr, map,conmsql.conectar());
        JasperViewer pv=new JasperViewer(print,false);
        pv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pv.setVisible(true);
    } catch (JRException e) {
        e.printStackTrace();
    }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloEnc_Factura;
import Modelo.ModeloPersona;
import Modelo.ModeloProducto;
import Modelo.Modelo_Detalle_Factura;
import Modelo.producto;
import Vista.Factura;
import java.awt.Button;
import java.awt.Color;
import java.awt.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author LENOVO
 */
public class ControladorFactura {
    private String cédiaFK = "";
    public ModeloPersona personas = new ModeloPersona();
    private Factura vistaFact;
    private ModeloEnc_Factura modeloFact = new ModeloEnc_Factura();
    private ArrayList<ModeloProducto> productos= new ArrayList<>();
    JButton btnEliminar = new JButton();
    JButton btnEnviar = new JButton();
    String[] columnas_productos= {"ID producto","Nombre","Precio","Stock", "Acción"};
    String[] columnas_Clientes= {"CÉDULA","NOMBRE","APELLIDO","EDAD", "ACCIÓN"};
    DefaultTableModel dtm2,dtm,dtmP;
    ArrayList<Integer> productos_agregados = new ArrayList();
    double total ;
    double totalENCABE ;
    private final ModeloProducto mdlProd;
    private ArrayList<String> ProductosDetalleInsetar= new ArrayList<>();
    
    public ControladorFactura(Factura vistaFact, ModeloProducto mdlProd) throws SQLException {
        this.vistaFact = vistaFact;
        this.mdlProd = mdlProd;
        Iniciar();
    }
    
    public void Iniciar() throws SQLException{
        vistaFact.getTbalaPersonas();
        vistaFact.getTbalaPersonas();
        btnEnviar.setBackground(Color.GRAY);
        btnEliminar.setBackground(Color.GRAY);
        InsertarIcono(btnEnviar,"/Vista/ICONOS/plus.png");
        InsertarIcono(btnEliminar,"/Vista/ICONOS/bin.png");
        ManipularTabla(vistaFact.getTbalaProductos(),vistaFact.getTabla_Detalle_fact(), vistaFact.getTbalaPersonas());
        
        dtm2 = new DefaultTableModel(null, new Object[]{"ID producto", "Nombre","Precio", "Cantidad", "Subtotal", "Acción"});
        vistaFact.getTabla_Detalle_fact().setRowHeight(40);
        vistaFact.getTabla_Detalle_fact().setModel(dtm2);
        vistaFact.setVisible(true);
        visualizarProductos();
        IniciarPersonas();
        
        vistaFact.getJBtn_guardar_semillas().addActionListener(l->{
            try {
                Ingresar();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorFactura.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        vistaFact.getJlabl_salir().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vistaFact.dispose();
            }
        });
        
        
    }
    
    public void ManipularTabla(JTable t, JTable t2, JTable t3){
        t.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                
                if (me.getClickCount() == 1) {
                    int id_pro = Integer.parseInt(t.getValueAt(t.getSelectedRow(), 0).toString());
                    String nombre_pro = t.getValueAt(t.getSelectedRow(), 1).toString();
                    double precio_pro = Double.parseDouble(t.getValueAt(t.getSelectedRow(), 2).toString());
                    int existencias = Integer.parseInt(t.getValueAt(t.getSelectedRow(), 3).toString());
                    int xcolum = t.getColumnModel().getColumnIndexAtX(me.getX());
                    int xrow = me.getY() / t.getRowHeight();
                    if (xcolum <= t.getColumnCount() && xcolum >= 0 && xrow <= t.getRowCount() && xrow >= 0) {
                        Object obj = t.getValueAt(xrow, xcolum);
                        if (obj instanceof JButton) {
                            System.out.println("a");
                            agregarProducto(id_pro, nombre_pro, precio_pro, existencias);
                        } 
                    }
                }
            }
        });
        
        t2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (me.getClickCount() == 1) {
                    int id_pro = Integer.parseInt(t2.getValueAt(t2.getSelectedRow(), 0).toString());
                        double precio_pro = Double.parseDouble(t2.getValueAt(t2.getSelectedRow(), 2).toString());
                        int cantidad = Integer.parseInt(t2.getValueAt(t2.getSelectedRow(), 3).toString());
                        int xcolum = t2.getColumnModel().getColumnIndexAtX(me.getX());
                        int xrow = me.getY() / t2.getRowHeight();
                        if (xcolum <= t2.getColumnCount() && xcolum >= 0 && xrow <= t2.getRowCount() && xrow >= 0) {
                            Object obj = t2.getValueAt(xrow, xcolum);
                            if (obj instanceof JButton) {
                                eliminarProducto(id_pro, precio_pro, cantidad);
                            }
                        }

                }
            }
        });
        
        t3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (me.getClickCount() == 1){
                    String cedula = t3.getValueAt(t3.getSelectedRow(), 0).toString();
                    String nombre_cli = t3.getValueAt(t3.getSelectedRow(), 1).toString();
                    String apellido = t3.getValueAt(t3.getSelectedRow(), 2).toString();
                    int edad = Integer.parseInt(t3.getValueAt(t3.getSelectedRow(), 3).toString());
                    int xcolum = t3.getColumnModel().getColumnIndexAtX(me.getX());
                    int xrow = me.getY() / t3.getRowHeight();
                    if (xcolum <= t3.getColumnCount() && xcolum >= 0 && xrow <= t3.getRowCount() && xrow >= 0) {
                        Object obj = t3.getValueAt(xrow, xcolum);
                        if (obj instanceof JButton) {
                            System.out.println("a");
                            AgregarPersona(cedula,nombre_cli,apellido,edad);
                        } 
                    }
                }
            }
        });
    }
    
    public void agregarProducto(int id_pro, String nombre_pro, Double precio_pro, int existencias) {
        if(cédiaFK.equals("")){
            JOptionPane.showMessageDialog(null, "PRIMERO DEBE SELECCIONAR UN CLIENTE AL CUAL SE LE ASIGNARÁ LA FACTURA", null, JOptionPane.WARNING_MESSAGE);
        }else{
            if (existencias > 0) {
            boolean repetido = false;
            for (int i = 0; i < productos_agregados.size(); i++) {
                if (productos_agregados.get(i)==id_pro) {
                    repetido = true;
                    break;
                }
            }
            if (repetido) {
                JOptionPane.showMessageDialog(null, "¡Este producto ya fué seleccionado!, Seleccione otro!", null, JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    int cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad:", 1));
                    if (cantidad > 0 && cantidad <= existencias) {
                        vistaFact.getTabla_Detalle_fact().setDefaultRenderer(Object.class, new Botones());
                        Object detalle[] = {id_pro, nombre_pro, precio_pro, cantidad, precio_pro * cantidad, btnEliminar};
                        dtm2.addRow(detalle);
                        ProductosDetalleInsetar.add(cédiaFK);
                        vistaFact.getTabla_Detalle_fact().setModel(dtm2);
                        total = (precio_pro * cantidad);
                        totalENCABE = total + totalENCABE;
                        vistaFact.getTxt_PrecioTotal().setText(String.valueOf(totalENCABE)); 
                        System.out.println(totalENCABE);
                        productos_agregados.add(id_pro);
                    }else{
                        if (cantidad > existencias) {
                            JOptionPane.showMessageDialog(null, "¡Solo existen '" + existencias + "' de este producto!", null, JOptionPane.WARNING_MESSAGE);
                        }
                        if (cantidad <= 0) {
                            JOptionPane.showMessageDialog(null, "¡El mínimo de venta es de 1!", null, JOptionPane.WARNING_MESSAGE);
                        }
                    }
                } catch (NumberFormatException e) {
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "¡Producto agotado!, Seleccione otro!", null, JOptionPane.WARNING_MESSAGE);
        }
        }
        
    }
    
    public void InsertarIcono(JButton bot, String ruta){ //insertar icono en boton:
        bot.setIcon(new javax.swing.ImageIcon(getClass().getResource(ruta)));
    }
    
    public void eliminarProducto(int id_pro, Double precio_pro, int cantidad) {
        int valor = JOptionPane.showConfirmDialog(null, "¿Desea remover este producto?", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (valor == JOptionPane.YES_OPTION) { 
            total -= (precio_pro * cantidad);
            dtm2.removeRow(vistaFact.getTabla_Detalle_fact().getSelectedRow());
            vistaFact.getTabla_Detalle_fact().setModel(dtm2);
            for (int i = 0; i < productos_agregados.size(); i++) {
                if (productos_agregados.get(i).equals(id_pro)) {
                    productos_agregados.remove(id_pro);
                    i = productos_agregados.size();
                }
            }
        }
    }
    
    public void visualizarProductos() throws SQLException {
        dtm = new DefaultTableModel(null, columnas_productos);
        productos = mdlProd.Listar();
        vistaFact.getTbalaProductos().setDefaultRenderer(Object.class, new Botones());
        productos.stream().forEach(p -> dtm.addRow(new Object[]{p.getCódigo(), p.getNombre(), p.getPrecio(),
            p.getStock(),btnEnviar}));
        vistaFact.getTbalaProductos().setModel(dtm);
        vistaFact.getTbalaProductos().setRowHeight(40);
    }
    
    public void AgregarPersona(String cédula, String nombre, String apellido, int edad){
        int valor = JOptionPane.showConfirmDialog(null, "¿DESEA CREAR LA FACTURA A NOMBRE DE ESTA PERSONA?", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (valor == JOptionPane.YES_OPTION) { 
            cédiaFK = cédula;
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha = LocalDate.now();
            vistaFact.getTxt_fecha().setText(fecha.format(formato).toString());
            vistaFact.getTxt_cédula().setText(cédula);
        }
    }
    
    public void IniciarPersonas() throws SQLException{
        ArrayList<ModeloPersona> Clientes= new ArrayList<>();
        Clientes = personas.Listar();
        dtmP = new DefaultTableModel (null,columnas_Clientes);
        vistaFact.getTbalaPersonas().setDefaultRenderer(Object.class, new Botones());
        Clientes.stream().forEach(cli->dtmP.addRow(new Object[]{
            cli.getCedula(),cli.getNombre(),cli.getApellidoi(),ObtenerEdad(cli.getFechanacimiento()),btnEnviar
        }));
         vistaFact.getTbalaPersonas().setModel(dtmP);
         vistaFact.getTbalaPersonas().setRowHeight(40);
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
    
    public void Ingresar() throws SQLException{
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        modeloFact.setCedula_FK(cédiaFK);
        modeloFact.setFecha(date);
        modeloFact.setTotal(totalENCABE);
        modeloFact.InsertEncabezado();
        int FK_ID_encezado = modeloFact.FK_detalle();
        if(vistaFact.getTabla_Detalle_fact().getRowCount()>0){
            for(int a=0; a<vistaFact.getTabla_Detalle_fact().getRowCount();a++){
                Modelo_Detalle_Factura detalle = new Modelo_Detalle_Factura();
                detalle.setFK_Id_producto(Integer.parseInt(vistaFact.getTabla_Detalle_fact().getValueAt(a, 0).toString()));
                detalle.setUnidades(Integer.parseInt(vistaFact.getTabla_Detalle_fact().getValueAt(a, 3).toString()));
                detalle.setID_FK_encabezado(FK_ID_encezado);
                detalle.Insertar();
            }
            JOptionPane.showMessageDialog(null, "SE HA INGRESADO CORRECTAMENTE LA FACTURA");
        }
    }
}

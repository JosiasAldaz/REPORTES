/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class Enc_Factura {
    private int id_encabezado;
    private Date fecha;
    private String cedula_FK;
    private double total;

    public Enc_Factura(int id_encabezado, Date fecha, String cedula_FK, double total) {
        this.id_encabezado = id_encabezado;
        this.fecha = fecha;
        this.cedula_FK = cedula_FK;
        this.total = total;
    }

    public Enc_Factura() {
    }

    public int getId_encabezado() {
        return id_encabezado;
    }

    public void setId_encabezado(int id_encabezado) {
        this.id_encabezado = id_encabezado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCedula_FK() {
        return cedula_FK;
    }

    public void setCedula_FK(String cedula_FK) {
        this.cedula_FK = cedula_FK;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}

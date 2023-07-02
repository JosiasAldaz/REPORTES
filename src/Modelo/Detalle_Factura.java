/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author LENOVO
 */
public class Detalle_Factura {
    private int ID_Detalle;
    private int ID_FK_encabezado;
    private int FK_Id_producto;
    private int unidades;

    public Detalle_Factura() {
    }

    public Detalle_Factura(int ID_Detalle, int ID_FK_encabezado, int FK_Id_producto, int unidades) {
        this.ID_Detalle = ID_Detalle;
        this.ID_FK_encabezado = ID_FK_encabezado;
        this.FK_Id_producto = FK_Id_producto;
        this.unidades = unidades;
    }

    public int getID_Detalle() {
        return ID_Detalle;
    }

    public void setID_Detalle(int ID_Detalle) {
        this.ID_Detalle = ID_Detalle;
    }

    public int getID_FK_encabezado() {
        return ID_FK_encabezado;
    }

    public void setID_FK_encabezado(int ID_FK_encabezado) {
        this.ID_FK_encabezado = ID_FK_encabezado;
    }

    public int getFK_Id_producto() {
        return FK_Id_producto;
    }

    public void setFK_Id_producto(int FK_Id_producto) {
        this.FK_Id_producto = FK_Id_producto;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }
    
    
}

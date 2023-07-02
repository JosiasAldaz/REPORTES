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
public class producto {
    private int código;
    private double precio;
    private String nombre;
    private int stock;
    private String descipcion;

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescipcion() {
        return descipcion;
    }

    public void setDescipcion(String descipcion) {
        this.descipcion = descipcion;
    }

    public int getCódigo() {
        return código;
    }

    public void setCódigo(int código) {
        this.código = código;
    }

    public producto(double precio, String nombre, int stock, String descipcion) {
        this.precio = precio;
        this.nombre = nombre;
        this.stock = stock;
        this.descipcion = descipcion;
    }

    public producto() {
    }

    @Override
    public String toString() {
        return "producto{" + "c\u00f3digo=" + código + ", precio=" + precio + ", nombre=" + nombre + ", stock=" + stock + ", descipcion=" + descipcion + '}';
    }
    
    
}

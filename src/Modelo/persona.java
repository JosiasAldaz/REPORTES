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
public class persona {
    private String cedula;
    private String nombre;
    private String apellidoi;
    private Date fechanacimiento;
    private byte foto;

    public persona(String cedula, String nombre, String apellidoi, Date fechanacimiento, byte foto) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellidoi = apellidoi;
        this.fechanacimiento = fechanacimiento;
        this.foto = foto;
    }

    public persona() {
    }

    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoi() {
        return apellidoi;
    }

    public void setApellidoi(String apellidoi) {
        this.apellidoi = apellidoi;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public byte getFoto() {
        return foto;
    }

    public void setFoto(byte foto) {
        this.foto = foto;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class ModeloPersona extends persona{
    ConeexionBD conexion = new ConeexionBD();

    public ModeloPersona() {
    }
    
    public void insertar() throws SQLException{
        String sql = "INSERT INTO dolar (cedula,nombre,apellido,fechanacimiento) VALUES('"+super.getCedula()+"','"+super.getNombre()+"','"+super.getApellidoi()+"','"+super.getFechanacimiento()+"')";
        conexion.accion(sql);
    }
    
    public ModeloPersona select() throws SQLException{
        String select = "SELECT * FROM dolar WHERE cedula = '"+super.getCedula()+"'";
        ResultSet parseo = conexion.Consulta(select);
        ModeloPersona retorno = new ModeloPersona();
        while(parseo.next()){
            retorno.setCedula(parseo.getString("cedula"));
            retorno.setNombre(parseo.getString("nombre"));
            retorno.setApellidoi(parseo.getString("apellido"));
            retorno.setFechanacimiento(parseo.getDate("fechanacimiento"));
            
            
        }
        return retorno;
    }
    
    public void Modificar(String nombre,String apellido,String cedula) throws SQLException{
        String modificar = "UPDATE dolar SET nombre = '"+nombre+"', apellido = '"+apellido+"' "
                + "WHERE cedula = '"+cedula+"'";
        conexion.accion(modificar);
    }
    
    public ArrayList<ModeloPersona> Listar() throws SQLException{
        String Select = "SELECT * FROM dolar";
        ResultSet parseo = conexion.Consulta(Select);
        ArrayList<ModeloPersona> contenedor = new ArrayList();
        while(parseo.next()){
            ModeloPersona ingreso = new ModeloPersona();
            ingreso.setNombre(parseo.getString("nombre"));
            ingreso.setApellidoi(parseo.getString("apellido"));
            ingreso.setCedula(parseo.getString("cedula"));
            ingreso.setFechanacimiento(parseo.getDate("fechanacimiento"));
            contenedor.add(ingreso);
        }
        return contenedor;
    }
    
    public void Delete(String cedula) throws SQLException{
        String delete = "DELETE FROM dolar WHERE cedula = '"+cedula+"'";
        conexion.accion(delete);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class ModeloProducto extends producto{
    ConeexionBD conext = new ConeexionBD();
    
    
    public void Ingresar() throws SQLException{
        String insert = "INSERT INTO productos (precio,nombre,stock,descripcion) VALUES("+super.getPrecio()+",'"+super.getNombre()+"',"+super.getStock()+",'"+super.getDescipcion()+"')";
        conext.accion(insert);
    }
    public ArrayList<ModeloProducto> Listar() throws SQLException{
        String Select = "SELECT * FROM productos";
        ResultSet parseo = conext.Consulta(Select);
        ArrayList<ModeloProducto> contenedor = new ArrayList();
        while(parseo.next()){
            ModeloProducto ingreso = new ModeloProducto();
            ingreso.setNombre(parseo.getString("nombre"));
            ingreso.setCódigo(parseo.getInt("codigo"));
            ingreso.setStock(Integer.parseInt(parseo.getString("stock")));
            ingreso.setPrecio(parseo.getDouble("precio"));
            ingreso.setDescipcion(parseo.getString("descripcion"));
            contenedor.add(ingreso);
            
        }
        return contenedor;
    }
    
    public ModeloProducto busqueda(String codigo) throws SQLException{
        String busqueda = "SELECT * FROM productos WHERE codigo ='"+codigo+"'";
        ResultSet resultado = conext.Consulta(busqueda);
        ModeloProducto regreso = new ModeloProducto();
        while(resultado.next()){
            regreso.setNombre(resultado.getString("nombre"));
            regreso.setPrecio(resultado.getDouble("precio"));
            regreso.setStock(resultado.getInt("stock"));
            regreso.setCódigo(resultado.getInt("codigo"));
            regreso.setDescipcion(resultado.getString("descripcion"));
        }
        return regreso;
    }
    
    public void Update(double precio,String nombre, String descripcion,int stock, int codigo) throws SQLException{
        String cambiar = "UPDATE productos SET nombre = '"+nombre+"', precio ="+precio+", stock = "+stock+", descripcion = '"+descripcion+"'"
                + "WHERE codigo = "+codigo+"";
        conext.accion(cambiar);
    }
    
    public void Delete(String codigo) throws SQLException{
        String delete = "DELETE FROM productos WHERE codigo='"+codigo+"'";
        conext.accion(delete);
    }
    
}

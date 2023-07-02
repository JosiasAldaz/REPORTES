/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.SQLException;

/**
 *
 * @author LENOVO
 */
public class Modelo_Detalle_Factura extends Detalle_Factura{
    ConeexionBD conect = new ConeexionBD();
    
    public void Insertar() throws SQLException{
        String sql = "INSERT INTO detalle_factura(pk_encabezado_id, pk_id_prod, unidades) VALUES("+super.getID_FK_encabezado()+","+super.getFK_Id_producto()+","+super.getUnidades()+")";
        conect.accion(sql);
        
    }
}

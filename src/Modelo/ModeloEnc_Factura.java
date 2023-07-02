/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author LENOVO
 */
public class ModeloEnc_Factura extends Enc_Factura {
    ConeexionBD conec = new ConeexionBD();

    public ModeloEnc_Factura() {
    }
    
    public void InsertEncabezado() throws SQLException{
        String inset = "INSERT INTO encabe_factura (fecha_fact,cedula_cli,total) VALUES('"+super.getFecha()+"','"+super.getCedula_FK()+"',"+super.getTotal()+")";
        conec.accion(inset);
    }

    public int FK_detalle() throws SQLException{
        String select = "SELECT id_encabezado FROM encabe_factura WHERE cedula_cli = '"+super.getCedula_FK()+"' AND total = "+super.getTotal()+"";
        ResultSet retorno = conec.Consulta(select);
        int id_encabezado = 0;
        while(retorno.next()){
            id_encabezado = retorno.getInt("id_encabezado");
        }
        System.out.println(id_encabezado);
        return id_encabezado; 
    }
}


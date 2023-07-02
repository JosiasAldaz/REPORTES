/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class ConeexionBD{
    Connection con;
    //PONGANLE EL MISMO NOMMBRE A SU BASE
    String url = "jdbc:postgresql://localhost:5432/guiaProgramacion";
    String user = "postgres";
    //TIENE QUE PONER SU CONTRASEÑA
    String password = "Nahofumi2001";
    Statement st;
    
    public ConeexionBD() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConeexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(ConeexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //SOLO SE USA CON SELECT 
    public ResultSet Consulta(String sql) {
        try {
            st = con.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConeexionBD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    //SOLO SE USA CON DELETE, UPDATE, INSERT INTO
    public SQLException accion(String nsql) throws SQLException{
        System.out.println(nsql);
        try {
            st = con.createStatement();
            st.execute(nsql);
            st.close();
            return null;
        } catch (SQLException ex){
            Logger.getLogger(ConeexionBD.class.getName()).log(Level.SEVERE, null, ex);            
            return ex;
        }
    }
       
    public Connection Ingres(){
        return con;
    }
    
    public  Connection conectar() {
        
        try {
            Class.forName("org.postgresql.Driver");
            con = (Connection) DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "¡No se pudo conectar a la base de datos!", "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
    

        return con;
    }
}


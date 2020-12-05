/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author carlo
 */
public class Conexion {

    static Object getConnection;
     //definimos el driver del motor que vamos a utilizar
    private final String DRIVER = "org.postgresql.Driver";  
    //Cadena de conexión
    private final String URL = "jdbc:postgresql://localhost:5432/MyLoot";  
    //usuario de la base de datos
    private final String USERNAME = "postgres";    
    // contraseña de la base de datos
    private final String PASSWORD = "root"; 
    
    private Connection connection;

    //metodo que establece la conexion
    public Conexion() {
        connection = null;
        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }catch(Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");

    }
    
    public Connection getConnection(){
        return connection;
    }
    public ResultSet consulta (String sql){
        ResultSet res = null;
        try{
            PreparedStatement pstm = connection.prepareStatement(sql);
            res = pstm.executeQuery();
        }catch(SQLException e){
            System.err.println("Error consulta :" +e.getMessage());
        }
        return res;
    }
    
    public DefaultComboBoxModel Obt_Categoria(){
        DefaultComboBoxModel ListaModelo = new DefaultComboBoxModel();
        ListaModelo.addElement("Seleccione una opcion");
        ResultSet res = this.consulta("SELECT * FROM \"Categoria\";");
        
        try{
            while(res.next()){
                ListaModelo.addElement(res.getString("nombre"));
            }
            res.close();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return ListaModelo;
    }
    public DefaultComboBoxModel Obt_Cuenta(){
        DefaultComboBoxModel ListaModelo = new DefaultComboBoxModel();
        ListaModelo.addElement("Seleccione una opcion");
        ResultSet res = this.consulta("SELECT * FROM \"Cuenta\";");
        
        try{
            while(res.next()){
                ListaModelo.addElement(res.getString("nombre"));
            }
            res.close();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return ListaModelo;
    }    
    public DefaultComboBoxModel Obt_Tipo(){
        DefaultComboBoxModel ListaModelo = new DefaultComboBoxModel();
        ListaModelo.addElement("Seleccione una opcion");
        ResultSet res = this.consulta("SELECT * FROM \"TipoCuenta\";");
        
        try{
            while(res.next()){
                ListaModelo.addElement(res.getString("nombreTipo"));
            }
            res.close();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return ListaModelo;
    }
}

package exercici.pkg1.m3.uf6.Model;
import java.sql.*;
import java.util.logging.Logger;

public class ConfigConnection {

    private Connection con;
    private String cadenaConnection;
    private String usuari;
    private String contrasenya;

        

    public ConfigConnection() {
        cadenaConnection = "jdbc:oracle:thin:@192.168.180.10:1521:INSLAFERRERI";
        usuari = "DAVIDBLANCO";
        contrasenya = "1423";
    }

    public ConfigConnection(String cadenaConnection, String usuari, String contrasenya) {
        this.cadenaConnection = cadenaConnection;
        this.usuari = usuari;
        this.contrasenya = contrasenya;
    }

    public Connection getCon() {
        
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(cadenaConnection, usuari, contrasenya);
            System.out.println("Connexió oberta.");
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
            //Logger.getLogger(ConfigConnection.class.getName()).log(level.);
        }
        
        return con;
    }
    
    public boolean tancar() throws SQLException{
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException ex) {
            System.out.println("Connexió ja tancada.");
            return false;
        }
        return true;
    }
    
    
}
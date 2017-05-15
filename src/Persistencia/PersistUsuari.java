package Persistencia;

import Vista.VistaText;
import exercici.pkg1.m3.uf6.Model.Usuari;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PersistUsuari {

    private Connection con;
    VistaText vista = new VistaText();

    public PersistUsuari(Connection con) {
        this.con = con;
    }

    /**
     * Metode per afegir usuaris a la BBDD.
     * @param u
     * @return 
     */
    public boolean afegirUsuari(Usuari u) {
        boolean afegit = true;

        PreparedStatement pt = null;
        String sentencia = "INSERT INTO Usuaris (NOM,COGNOM,NIF)"
                + "VALUES(?,?,?)";

        try {
            pt = con.prepareStatement(sentencia);
            pt.setString(1, u.getNom());
            pt.setString(2, u.getCognom());
            pt.setString(3, u.getNif());

            if (pt.executeUpdate() == 0) {
                afegit = false;
            }
            System.out.println("És tancat: " + pt.isClosed());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            afegit = false;
        }
        return afegit;
    }
    
    /**
     * Metode per afegir arrays a la BBDD.
     * @param llista
     * @return 
     */
    public boolean afegirArray (List<Usuari> llista) {
        boolean afegit = true;
        
        
        
        return afegit;
    }
    
    
}

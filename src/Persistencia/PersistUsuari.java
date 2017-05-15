package Persistencia;

import Vista.VistaText;
import exercici.pkg1.m3.uf6.Model.Usuari;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
     *
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
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            afegit = false;
        }
        return afegit;
    }

    /**
     * Metode per afegir arrays a la BBDD.
     *
     * @param llista
     * @return
     */
    public boolean afegirArray(List<Usuari> llista) {
        boolean afegit = true;

        try {
            con.setAutoCommit(false);
            String sentencia = "Insert into Usuaris (NOM,COGNOM, NIF) VALUES (?,?,?)";
            PreparedStatement pt = con.prepareStatement(sentencia);
            for (Usuari u : llista) {
                pt = con.prepareStatement(sentencia);
                pt.setString(1, u.getNom());
                pt.setString(2, u.getCognom());
                pt.setString(3, u.getNif());
            }

            pt.close();
            con.commit();
            afegit = true;
        } catch (SQLException ex) {

        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {

            }
        }

        return afegit;
    }

    /**
     * Metode per a esborrar usuaris de la BBDD.
     *
     * @param nif
     * @return
     */
    public boolean esborrarUsuari(String nif) {
        boolean borrat = true;

        String sentencia = "Delete from Usuari Where nif = ?";
        try {
            PreparedStatement pt = con.prepareStatement(sentencia);
            pt.setString(1, nif);
            pt.executeUpdate();

            borrat = true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            borrat = false;
        }
        return borrat;
    }

    /**
     * Metode per a modificar la taula usuari en la BBDD.
     * @param u
     * @return 
     */
    public boolean modificat(Usuari u) {
        boolean modificat = true;

        String sentencia = "UPDATE Usuaris SET NOM = ?, COGNOM = ? WHERE NIF = ? ";
        try {
            PreparedStatement pt = con.prepareStatement(sentencia);
            pt = con.prepareStatement(sentencia);
            pt.setString(1, u.getNom());
            pt.setString(2, u.getCognom());
            pt.setString(3, u.getNif());
            modificat = pt.executeUpdate() > 0;
            
        } catch (SQLException ex) {

        }

        return modificat;
    }
    
    /**
     * Metode per a cercar usuaris a la BBDD.
     * @param nif
     * @return 
     */
    public Usuari cercar (String nif){
        String sentencia = "SELECT * FROM Usuaris WHERE nif = ?";
        String nom = null, cognom = null;
        try {
            PreparedStatement pt = con.prepareStatement(sentencia);
            pt.setString(1, nif);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                nom = rs.getString("nom");
                cognom = rs.getString("cognom");
            }
        } catch (SQLException ex) {
            
        }
        return new Usuari(nom, cognom, nif);

    }

}

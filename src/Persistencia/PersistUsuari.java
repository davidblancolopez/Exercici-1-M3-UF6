package Persistencia;

import Vista.VistaText;
import exercici.pkg1.m3.uf6.Model.Usuari;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        //Sentencia a executar.
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
        //Retornem el valor indicant si es correcte.
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
            //Sentencia a executar.
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
            System.out.println(ex.getMessage());
            afegit = false;
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                afegit = false;
            }
        }
        //Retornem el valor indicant si es correcte.
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
        //Sentencia a executar.
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
        //Retornem el valor indicant si es correcte.
        return borrat;
    }

    /**
     * Metode per a modificar la taula usuari en la BBDD.
     *
     * @param u
     * @return
     */
    public boolean modificat(Usuari u) {
        boolean modificat = true;
        //Sentencia a executar.
        String sentencia = "UPDATE Usuaris SET NOM = ?, COGNOM = ? WHERE NIF = ? ";
        try {
            PreparedStatement pt = con.prepareStatement(sentencia);
            pt = con.prepareStatement(sentencia);
            pt.setString(1, u.getNom());
            pt.setString(2, u.getCognom());
            pt.setString(3, u.getNif());
            modificat = pt.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            modificat = false;
        }
        //Retornem el valor indicant si es correcte.
        return modificat;
    }

    /**
     * Metode per a cercar usuaris a la BBDD.
     *
     * @param nif
     * @return
     */
    public Usuari cercar(String nif) {
        //Sentencia a executar.
        String sentencia = "SELECT * FROM Usuaris WHERE nif = ?";
        String nom = null, cognom = null;
        try {
            PreparedStatement pt = con.prepareStatement(sentencia);
            pt.setString(1, nif);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                //Obtenim nom, cognom de usuari.
                nom = rs.getString("nom");
                cognom = rs.getString("cognom");
            }
            rs.close();
            pt.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
        //Retornem un nou objecte usuari.
        return new Usuari(nom, cognom, nif);

    }

    public List<Usuari> cercarTots() {
        List<Usuari> llista = new ArrayList<>();
        //Sentencia a executar.
        String sentencia = "SELECT * FROM Usuaris";
        String nom = null, cognom = null, nif = null;
        try {
            PreparedStatement pt = con.prepareStatement(sentencia);
            pt.setString(1, nif);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                //Obtenim nom, cognom i nif de usuari.
                nom = rs.getString("nom");
                cognom = rs.getString("cognom");
                nif = rs.getString("nif");
                //Afegim un nou objecte usuari a la llista.
                llista.add(new Usuari(nom, cognom, nif));
            }
            rs.close();
            pt.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        //Retornem la llista d'usuaris.
        return llista;
    }

}

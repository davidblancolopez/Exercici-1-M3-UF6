
package Vista;

import exercici.pkg1.m3.uf6.Model.Usuari;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class VistaText {
    
    Scanner lector = new Scanner(System.in);
    
    /**
     * Metode per a msotrar el menú principal del programa.
     */
    public int MostrarMenu(){
        int opcio = 0;
        System.out.println("Menú:"
                + "\n 1. Afegir un usuari."
                + "\n 2. Afegir llista usuaris."
                + "\n 3. Eliminar un usuari."
                + "\n 4. Modificar un usuari."
                + "\n 5. Buscar un usuari(NIF)."
                + "\n 6. Retornar tots els usuaris."
                + "\n 7. Sortir.");
        opcio = lector.nextInt();
        return opcio;
    }
    
    /**
     * Metode per a demanar el nou usuari a introduir.
     * Retorna el nou usuari.
     * 
     * @return 
     */
    public Usuari DemanarUsuari(){
        System.out.println("Introdueix el nom del usuari: ");
        String nom = lector.next();
        System.out.println("Introdueix el cognom del usuari: ");
        String cognom = lector.next();
        System.out.println("Introdueix el NIF del usuari: ");
        String nif = lector.next();
        
        return new Usuari(nom, cognom, nif);
    }
    
    /**
     * Metode per a demanar la llista d'usuaris a afegir a la BBDD.
     * Demana el numero d'usaris que s'afegiran i fa un bucle introduint usuaris a la llista
     * cridant al metode DemanarUsuari().
     * 
     * @return 
     */
    public List<Usuari> DemanarUsuaris(){
        System.out.println("Quants usuaris vas a introduir?: ");
        int tamany = lector.nextInt();
        List<Usuari> llista = new ArrayList<>();
        for (int i = 0; i < tamany; i++) {
            llista.add(DemanarUsuari());
        }
        
        return llista;
    }
    
    /**
     * Metode per a demanar el NIF del usuari que es vol esborrar.
     * @return 
     */
    public String DemanarNif(){
        System.out.println("Introdueix el NIF de l'usuari a esborrar: ");
        String nif = lector.next();
        return nif;
    }
    
    
}

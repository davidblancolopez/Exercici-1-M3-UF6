
package Vista;

import exercici.pkg1.m3.uf6.Model.Usuari;
import java.sql.Connection;
import java.util.Scanner;


public class VistaText {
    
    Scanner lector = new Scanner(System.in);
    
    /**
     * Metode per a msotrar el menú principal del programa.
     */
    public void MostrarMenu(){
        System.out.println("Menú:"
                + "\n 1. Afegir un usuari."
                + "\n 2. Afegir llista usuaris."
                + "\n 3. Eliminar un usuari."
                + "\n 4. Modificar un usuari."
                + "\n 5. Buscar un usuari(NIF)."
                + "\n 6. Retornar tots els usuaris."
                + "\n 7. Sortir.");
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
    
    
    
    
}

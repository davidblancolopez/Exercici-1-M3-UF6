package Control;

import Persistencia.ConfigConnection;
import Persistencia.PersistUsuari;
import Vista.VistaText;
import java.sql.Connection;
import java.util.Scanner;

public class GestioUsuaris {

    ConfigConnection cc;
    Connection con;
    PersistUsuari persist;
    VistaText vista = new VistaText();
    Scanner lector = new Scanner(System.in);

    public GestioUsuaris() {
        cc = new ConfigConnection();
        con = cc.getCon();
        persist = new PersistUsuari(con);
    }

    public void menu(int n) {
        switch (n) {
            case 1:
                persist.afegirUsuari(vista.DemanarUsuari());
                break;
            case 2:
                persist.afegirArray(vista.DemanarUsuaris());
                break;
            case 3:
                persist.esborrarUsuari(vista.DemanarNif());
                break;
            case 4:
                persist.modificat(vista.DemanarUsuari());
                break;    
            case 5:
                persist.cercar(vista.DemanarNif());
                break;    
            case 6:
                persist.cercarTots();
                break;   
            case 7:
                System.exit(0);
                break;
        }

    }
    
    
    
    public int demanarOpcio() {
        int num;
        while (true) {
            if (!lector.hasNextInt()) {
                System.out.println("El valor introduit no es un enter");
                lector.next();
            } else {
                num = Integer.parseInt(lector.nextLine());
                if (num < 8 && num > 0) {
                    break;
                } else {
                    System.out.println("No es un valor entre 0 i 7");
                    lector.nextLine();
                }
            }
        }
        return num;
    }
    
    
    public void iniciarPrograma() {
        while (true) {
            menu(vista.MostrarMenu());
        }
    }
}

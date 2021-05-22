package es.ceu.gisi.modcomp.webcrawler.jflex;

import es.ceu.gisi.modcomp.webcrawler.jflex.lexico.Tipo;
import es.ceu.gisi.modcomp.webcrawler.jflex.lexico.Token;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Stack;

/**
 * Esta clase encapsula toda la lógica de interacción con el parser HTML.
 *
 * @author Sergio Saugar García <sergio.saugargarcia@ceu.es>
 */
public class JFlexScraper {
    
    HTMLParser analizador;

    ArrayList<String> enlacesA = new ArrayList<>();
    Stack<String> etiquetasAbiertas = new Stack();
    
    public JFlexScraper(File fichero) throws FileNotFoundException, IOException {
        Reader reader = new BufferedReader(new FileReader(fichero));
        analizador = new HTMLParser(reader);
        
        int estado = 0;
        Token tk = analizador.nextToken();
        boolean etiquetaA = false;
        boolean etiquetaIMG = false;
        boolean valorHREF = false;
        
        
        while ( tk != null){
            switch(estado){
                case 0:
                    //Estando en el estado 0 
                    if(tk.getTipo()== Tipo.OPEN) {
                        estado = 1;
                    }
                    break;
                
                case 1:
                    //Estamos en el estado 1
                    //En este estado estamos si hemos leido un OPEN <
                    if(tk.getTipo()==Tipo.PALABRA){
                        estado = 2;
                        etiquetasAbiertas.push(tk.getValor().toLowerCase());
                        
                        if(tk.getValor().equalsIgnoreCase("a")){ //<A
                            etiquetaA = true;
                        }else if(tk.getValor().equalsIgnoreCase("img")){ //<IMG
                            etiquetaIMG = true;
                        }
                    }else if(tk.getTipo() == Tipo.SLASH){ //</
                        estado = 6;
                    }
                    break;
                
            }
        }
    }

    // Esta clase debe contener tu automata programado...
    public ArrayList<String> obtenerHiperenlaces() {
        // Habrá que programarlo..
        return new ArrayList<String>();
    }

    public ArrayList<String> obtenerHiperenlacesImagenes() {
        // Habrá que programarlo..
        return new ArrayList<String>();
    }

    public boolean esDocumentoHTMLBienBalanceado() {
        // Habrá que programarlo..
        return false;
    }
}

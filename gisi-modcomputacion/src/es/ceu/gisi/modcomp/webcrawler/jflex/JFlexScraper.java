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
    ArrayList<String> enlacesIMG = new ArrayList<>();
    
    public JFlexScraper(File fichero) throws FileNotFoundException, IOException {
        Reader reader = new BufferedReader(new FileReader(fichero));
        analizador = new HTMLParser(reader);
        
        int estado = 0;
        Token tk = analizador.nextToken();
        boolean etiquetaA = false;
        boolean etiquetaIMG = false;
        boolean valorHREF = false;
        boolean valorSRC = false;
        boolean bienBalanceado = false;
        
        
        while ( tk != null){
            switch(estado){
                case 0:
                    //Estando en el estado 0 
                    if(tk.getTipo()== Tipo.OPEN) { // <
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
                case 2:
                    //Estando en el estado 2
                    //Ahora debemos leer att=valor o fin de etiqueta
                    if(tk.getTipo() == Tipo.PALABRA){
                        estado = 3;
                        if(etiquetaA){
                            if(tk.getValor().equalsIgnoreCase("href")){ // <A href
                                valorHREF = true;
                            }
                        }else if(etiquetaIMG){
                            if (tk.getValor().equalsIgnoreCase("src")){ //<IMG src 
                                valorSRC = true;
                            }
                        }
                    }if (tk.getTipo() == Tipo.CLOSE){ // <palabra>
                        bienBalanceado = true;
                    }else if(tk.getTipo() == Tipo.SLASH){ // <palabra/
                        estado = 5;
                    }                 
                    break;
                case 3:
                    //Estando en el estado 3
                    //Ahora debemos leer "="
                    if(tk.getTipo()==Tipo.IGUAL){ // <A href=   // <IMG src=
                        estado = 4;
                    }
                    break;
                case 4:
                    //Estando en el estado 4
                    //Esto es un valor de un atributo
                    if(tk.getTipo() == Tipo.VALOR){
                        if(valorHREF){
                            enlacesA.add(tk.getValor()); //<A href= enlace
                        }
                        if(valorSRC){
                            enlacesIMG.add(tk.getValor());//<IMG src= enlace
                        }
                        estado = 2;
                    }
                    break;
            }
            tk = analizador.nextToken();
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

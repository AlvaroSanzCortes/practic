package es.ceu.gisi.modcomp.webcrawler;

import es.ceu.gisi.modcomp.webcrawler.jflex.JFlexScraper;
import java.io.File;
import java.io.IOException;
import es.ceu.gisi.modcomp.webcrawler.jsoup.JsoupScraper;

/**
 * Esta aplicación contiene el programa principal que ejecuta ambas partes del
 * proyecto de programación.
 *
 * @author Alvaro Sanz
 */
public class WebCrawler {
    
    private final static String PATH_PRUEBAS = new java.io.File("").getAbsolutePath()
            + "/test/es/ceu/gisi/modcomp/webcrawler/jflex/test/";

    private static File ficheroPrueba1 = new File(PATH_PRUEBAS + "fichero1.html");
    
    public static void main(String[] args) throws IOException {
        
        // Deberá inicializar JFlexScraper con el fichero HTML a analizar
        // Y creará un fichero con todos los hiperenlaces que encuentre.
        // También deberá indicar, mediante un mensaje en pantalla que
        // el fichero HTML que se ha pasado está bien balanceado.
        
        JFlexScraper j = new JFlexScraper(ficheroPrueba1);
        j.obtenerHiperenlaces();
        j.obtenerHiperenlacesImagenes();
        j.esDocumentoHTMLBienBalanceado();
       System.out.println(j.esDocumentoHTMLBienBalanceado());
       System.out.println("\nEnlacesA: " + j.obtenerHiperenlaces());
       System.out.println("\nEnlacesIMG: " + j.obtenerHiperenlacesImagenes());
       
        // Deberá inicializar JsoupScraper con la DIRECCIÓN HTTP de una página
        // web a analizar. Creará un fichero con todos los hiperenlaces que
        // encuentre en la página web. También obtendrá estadísticas de uso 
        // de las etiquetas HTML más comunes: a, br, div, li, ul, p, span, table, td, tr
    }
}

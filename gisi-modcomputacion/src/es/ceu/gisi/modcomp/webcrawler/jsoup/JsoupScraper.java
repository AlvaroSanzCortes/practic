/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ceu.gisi.modcomp.webcrawler.jsoup;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Esta clase encapsula toda la lógica de interacción con el analizador Jsoup.
 *
 * @author Alvaro Sanz
 */
public class JsoupScraper {

    private Document doc;
     
    /**
     * Este constructor crea un documento a partir de la URL de la página web a
     * analizar.
     *
     * @param url Una URL que apunte a un documento HTML (p.e.
     *            http://www.servidor.com/index.html)
     */
    public JsoupScraper(URL url) throws IOException {
        // La variable deberá inicializarse de alguna manera utilizando una URL...
        // De momento, se inicializa a null para que compile...
        try{
            URL url1 = new URL("http://www.servidor.com/index.html");
            doc = Jsoup.connect(url1.toString()).get();
        }catch(IOException e){
            System.out.println(e);
        }
    }

    /**
     * Este constructor crea un documento a partir del contenido HTML del String
     * que se pasa como parámetro.
     *
     * @param html Un documento HTML contenido en un String.
     */
    public JsoupScraper(String html) throws IOException {
        doc = Jsoup.parse(html);
    }

    /**
     * Realiza estadísticas sobre el número de etiquetas de un cierto tipo.
     *
     * @param etiqueta La etiqueta sobre la que se quieren estadísticas
     *
     * @return El número de etiquetas de ese tipo que hay en el documento HTML
     */
    public int estadisticasEtiqueta(String etiqueta) {
        
        /*HTMLParser analizador = new HTMLParser(fichero1.html);
        if(analizador = etiqueta){
            ArrayList<> etiqueta = new ArrayList<>();
            for(int i = 0, i < etiqueta.size(), i++ ){
                    i += 1;
                System.out.println(i);
        }else if{
            System.out.print("false");
        }*/
        return 0;
    }

    /**
     * Obtiene todos los hiperenlaces que se encuentran en el documento creado.
     *
     * @return Una lista con todas las URLs de los hiperenlaces
     */
    public List<String> obtenerHiperenlaces() {
        /*if(etiqueta = "href"){
            ArrayList<> hiperenlaces = new ArrayList<>();
            hiperenlaces.add(href.next());
        }*/
       return new ArrayList<String>();
    }

    /**
     * Obtiene todos los hiperenlaces de las imágenes incrustadas.
     *
     * @return Una lista con todas las URLs de los hiperenlaces
     */
    public List<String> obtenerHiperenlacesImagenes() {
        /*if(etiqueta = "src"){
            ArrayList<> hiperenlacesImagenes = new ArrayList<>();
            hiperenlacesImagenes.add(src.next());
        }*/
        return new ArrayList<String>();
    }


    /**
     * Obtiene la imagen a la que hace referencia LA PRIMERA etiqueta IMG que
     * encontramos.
     *
     * @return El nombre (o ruta) de la primera imagen insertada en el documento
     *         HTML.
     */
    public String obtenerContenidoImg() {
        Element elemento = doc.select("IMG").first();
        String imagen = elemento.attr("src");
        return imagen;
    }
}

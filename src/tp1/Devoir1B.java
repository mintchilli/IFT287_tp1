// Travail fait par :
//   Marcel Michel - 17 081 685
//   Francois Brisson - 18106979

package tp1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import bodyParts.MainBody;

/**
 * Fichier de base pour le Devoir1B du cours IFT287
 *
 * <pre>
 * 
 * Vincent Ducharme
 * Universite de Sherbrooke
 * Version 1.0 - 6 août 2016
 * IFT287 - Exploitation de BD relationnelles et OO
 * 
 * Ce programme permet de convertir un fichier JSON en son équivalent en XML.
 *
 * Paramètres du programme
 * 0- Nom du fichier JSON
 * 1- Nom du fichier XML
 * 
 * </pre>
 */
public class Devoir1B
{

    public static void main(String[] args)
    {
        if (args.length < 2)
        {
            System.out.println("Usage: java tp1.Devoir1B <fichierJSON> <fichierXML>");
            return;
        }
        
        String nomFichierJSON = args[0];
        String nomFichierXML = args[1];
        
        System.out.println("Debut de la conversion du fichier " + nomFichierJSON + " vers le fichier " + nomFichierXML);

        // Lecture
        MainBody mainBody = null;
        try
        {
            mainBody = lecture(args[0]);
        }
        catch (FileNotFoundException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Écriture
        try
        {
            ecriture(args[1], "HumanBody.dtd", mainBody);
        }
        catch (FileNotFoundException | ParserConfigurationException | TransformerException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Conversion terminee.");

    }
    
    private static MainBody lecture(String nomFichier) throws FileNotFoundException
    {
        JsonReader reader = Json.createReader(new FileReader(nomFichier));
        JsonStructure jsonst = reader.read();
        return new MainBody(((JsonObject) jsonst).getJsonObject("MainBody"));

    }
    
    private static void ecriture(String nomFichier, String dtdName, MainBody mainBody)
            throws ParserConfigurationException, FileNotFoundException, TransformerException
    {

        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        Document document = f.newDocumentBuilder().newDocument();
        document.appendChild(mainBody.toXML(document));

        FileOutputStream output = new FileOutputStream(nomFichier);
        PrintStream out = new PrintStream(output);
        TransformerFactory transFacto = TransformerFactory.newInstance();
        Transformer transformer = transFacto.newTransformer();
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, dtdName);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(out);
        transformer.transform(source, result);

    }

}

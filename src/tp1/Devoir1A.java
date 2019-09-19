// Travail fait par :
//   Marcel Michel - 17 081 685
//   Francois Brisson - 18106979

package tp1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Fichier de base pour le Devoir1A du cours IFT287
 *
 * <pre>
 * 
 * Vincent Ducharme
 * Universite de Sherbrooke
 * Version 1.0 - 6 août 2016
 * IFT287 - Exploitation de BD relationnelles et OO
 * 
 * Ce programme permet de convertir un fichier XML en son équivalent en JSON.
 *
 * Paramètres du programme
 * 0- Nom du fichier XML
 * 1- Nom du fichier JSON
 * 
 * </pre>
 */
public class Devoir1A
{

    public static void main(String[] args)
    {
        if (args.length < 2)
        {
            System.out.println("Usage: java tp1.Devoir1A <fichierXML> <fichierJSON>");
            return;
        }

        String nomFichierXML = args[0];
        String nomFichierJSON = args[1];

        System.out.println("Debut de la conversion du fichier " + nomFichierXML + " vers le fichier " + nomFichierJSON);

        try
        {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(true);
            SAXParser saxParser = factory.newSAXParser();
            SAXHandler handler = new SAXHandler();
            saxParser.parse(nomFichierXML, handler);

            Map<String, Object> config = new HashMap<String, Object>(1);
            config.put(JsonGenerator.PRETTY_PRINTING, true);
            StringWriter w = new StringWriter();
            JsonGeneratorFactory f = Json.createGeneratorFactory(config);
            JsonGenerator jsonGenerator = f.createGenerator(w);

            jsonGenerator.writeStartObject();
            jsonGenerator = handler.returnMainBody().generateJson(jsonGenerator);
            jsonGenerator.writeEnd();
            jsonGenerator.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichierJSON));
            writer.write(w.toString());
            writer.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("Conversion terminee.");
    }

}

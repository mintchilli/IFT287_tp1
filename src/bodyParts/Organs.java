package bodyParts;

import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Organs {

	private ArrayList<Organ> organList;

	public Organs(ArrayList<Organ> organs) {
		this.organList = organs;
	}

	public Organs(JsonObject jsonObject) {
		organList = new ArrayList<Organ>();
		JsonArray jsonArray = jsonObject.getJsonArray("Organ");
		for (int i = 0; i < jsonArray.size(); i++) {
			organList.add(new Organ(jsonArray.getJsonObject(i)));
		}
	}

	public JsonValue generateJson() {
		JsonObjectBuilder job = Json.createObjectBuilder();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Organ organ : organList) {
			jab.add(organ.generateJson());
		}
		job.add("Organ", jab.build());

		return job.build();
	}
	
	public Element toXML(Document doc) {
        Element element = doc.createElement("Organs");
        
        for (Organ organ : organList)
        {
            element.appendChild(organ.toXML(doc));
        }
        
        return element;
        
    }

}

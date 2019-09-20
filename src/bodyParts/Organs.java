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

	private ArrayList<Organ> organArrayList;

	public Organs(ArrayList<Organ> organs) {
		this.organArrayList = organs;
	}

	public Organs(JsonObject jsonObject) {
		organArrayList = new ArrayList<Organ>();
		JsonArray jsonArray = jsonObject.getJsonArray("Organ");
		for (int i = 0; i < jsonArray.size(); ++i)
			organArrayList.add(new Organ(jsonArray.getJsonObject(i)));
	}

	public JsonValue toJson() {
		JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

		for (Organ organ : organArrayList)
			jsonArrayBuilder.add(organ.toJson());
		jsonObjectBuilder.add("Organ", jsonArrayBuilder.build());
		
		return jsonObjectBuilder.build();
	}
	
	public Element toXML(Document document) {
        Element element = document.createElement("Organs");
        for (Organ organ : organArrayList)
            element.appendChild(organ.toXML(document));
        
        return element;
        
    }

}

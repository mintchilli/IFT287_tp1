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

public class Systems {

	private ArrayList<System> systems;

	public Systems(ArrayList<System> systems) {
		this.systems = systems;
	}

	public Systems(JsonObject jsonObject) {
		systems = new ArrayList<System>();

		JsonArray jsonArray = jsonObject.getJsonArray("System");
		for (int i = 0; i < jsonArray.size(); i++) {
			systems.add(new System(jsonArray.getJsonObject(i)));
		}
	}

	public JsonValue generateJson() {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		JsonObjectBuilder job = Json.createObjectBuilder();

		for (System system : systems) {
			jab.add(system.generateJson());
		}

		job.add("System", jab.build());
		return job.build();
	}
	
    public Element toXML(Document doc)
    {
        Element element = doc.createElement("Systems");

        for (System systemType : systems)
        {
            element.appendChild(systemType.toXML(doc));
        }

        return element;
    }

}

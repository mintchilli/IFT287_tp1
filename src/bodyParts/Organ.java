package bodyParts;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Organ {
	private int id;
	private int systemID;
	private String name;

	public Organ(String name, int id, int systemID) {
		this.id = id;
		this.systemID = systemID;
		this.name = name;
	}

	public Organ(JsonObject jsonObject) {
		this.id = jsonObject.getInt("id");
		this.systemID = jsonObject.getInt("systemID");
		this.name = jsonObject.getString("name");
	}

	public JsonValue toJson() {
		JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
		jsonObjectBuilder
			.add("id", id)
			.add("systemID", systemID)
			.add("name", name);

		return jsonObjectBuilder.build();
	}
	
    public Element toXML(Document document)
    {
        Element element = document.createElement("Organ");
        element.setAttribute("id", String.valueOf(id));
        element.setAttribute("systemID", String.valueOf(systemID));
        element.setAttribute("name", name);

        return element;

    }
}

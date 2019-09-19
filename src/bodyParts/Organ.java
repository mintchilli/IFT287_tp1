package bodyParts;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Organ {
	private String name;
	private int id;
	private int sysId;

	public Organ(String name, int id, int systemID) {
		this.name = name;
		this.id = id;
		this.sysId = systemID;
	}

	public Organ(JsonObject jsonObject) {
		this.name = jsonObject.getString("name");
		this.id = jsonObject.getInt("id");
		this.sysId = jsonObject.getInt("systemID");
	}

	public JsonValue generateJson() {
		JsonObjectBuilder job = Json.createObjectBuilder();

		job.add("name", name).add("id", id).add("systemID", sysId);

		return job.build();
	}
	
    public Element toXML(Document doc)
    {
        Element element = doc.createElement("Organ");

        element.setAttribute("name", name);
        element.setAttribute("id", String.valueOf(id));
        element.setAttribute("systemID", String.valueOf(sysId));

        return element;

    }
}

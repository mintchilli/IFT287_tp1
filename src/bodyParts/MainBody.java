package bodyParts;

import javax.json.JsonObject;
import javax.json.stream.JsonGenerator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MainBody {

	private String mainBodyName;
	private int id;
	private Systems systems;
	private Organs organs;

	public MainBody(String bodyName, int bodyID) {
		this.mainBodyName = bodyName;
		this.id = bodyID;
	}

	public MainBody(JsonObject jsonObject) {
		this.mainBodyName = jsonObject.getString("bodyName");
		this.id = jsonObject.getInt("bodyID");

		this.systems = new Systems(jsonObject.getJsonObject("Systems"));
		this.organs = new Organs(jsonObject.getJsonObject("Organs"));
	}

	public void setSystems(Systems systems) {
		this.systems = systems;
	}

	public void setOrgans(Organs organs) {
		this.organs = organs;
	}

	public JsonGenerator generateJson(JsonGenerator generator) {
		return generator.writeStartObject("MainBody").write("bodyName", mainBodyName).write("bodyID", id).write("Systems", systems.generateJson()).write("Organs", organs.generateJson()).writeEnd();
	}
	
    public Element toXML(Document doc)
    {
        Element element = doc.createElement("MainBody");
        element.setAttribute("bodyName", mainBodyName);
        element.setAttribute("bodyID", String.valueOf(id));

        element.appendChild(systems.toXML(doc));
        element.appendChild(organs.toXML(doc));

        return element;
    }
    
    

}

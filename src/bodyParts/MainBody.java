package bodyParts;

import javax.json.JsonObject;
import javax.json.stream.JsonGenerator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MainBody {

	private int bodyID;
	private String bodyName;
	private Systems systems;
	private Organs organs;

	public MainBody(String bodyName, int bodyID) {
		this.bodyID = bodyID;
		this.bodyName = bodyName;
	}

	public MainBody(JsonObject jsonObject) {
		this.bodyID = jsonObject.getInt("bodyID");
		this.bodyName = jsonObject.getString("bodyName");
		this.systems = new Systems(jsonObject.getJsonObject("Systems"));
		this.organs = new Organs(jsonObject.getJsonObject("Organs"));
	}

	public void setSystems(Systems systems) {
		this.systems = systems;
	}

	public void setOrgans(Organs organs) {
		this.organs = organs;
	}

	public JsonGenerator toJson(JsonGenerator generator) {
		return generator
				.writeStartObject("MainBody")
				.write("bodyID", bodyID)
				.write("bodyName", bodyName)
				.write("Systems", systems.toJson())
				.write("Organs", organs.toJson())
				.writeEnd();
	}
	
    public Element toXML(Document document)
    {
        Element element = document.createElement("MainBody");
        element.setAttribute("bodyName", bodyName);
        element.setAttribute("bodyID", String.valueOf(bodyID));
        element.appendChild(systems.toXML(document));
        element.appendChild(organs.toXML(document));

        return element;
    }
    
    

}

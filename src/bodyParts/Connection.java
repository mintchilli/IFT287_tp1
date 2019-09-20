package bodyParts;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Connection {	
	
	int id;
	
	public Connection(int id) {
		this.id = id;
	}

	public Connection(JsonObject jsonObject) {
		id = jsonObject.getInt("id");
	}

	public JsonValue toJson() {
		JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
		jsonObjectBuilder.add("id", id);
		return jsonObjectBuilder.build();
	}
	
    public Element toXML(Document document)
    {
        Element element = document.createElement("Connection");
        element.setAttribute("id", String.valueOf(id));
        return element;

    }

}

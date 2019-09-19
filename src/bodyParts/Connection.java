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

public class Connection {
	private int id;
	private ArrayList<To> toList;

	public Connection(JsonObject jsonObject) {
		id = jsonObject.getInt("id");

		toList = new ArrayList<To>();
		if (jsonObject.containsKey("to")) {
			JsonArray jsonArray = jsonObject.getJsonArray("to");
			for (int i = 0; i < jsonArray.size(); i++) {
				toList.add(new To(jsonArray.getJsonObject(i)));
			}
		}
	}

	public Connection(int id) {
		this.id = id;
	}


	public void setTos(ArrayList<To> tos) {
		this.toList = tos;
	}
	
	public JsonValue generateJson() {
		JsonObjectBuilder job = Json.createObjectBuilder();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		job.add("id", id);
		for (To to : toList) {
			jab.add(to.generateJson());
		}
		job.add("to", jab.build());

		return job.build();
	}
	
    public Element toXML(Document doc)
    {
        Element element = doc.createElement("Connection");

        element.setAttribute("id", String.valueOf(id));
        for (To to : toList)
        {
            element.appendChild(to.toXML(doc));
        }
        return element;

    }

}

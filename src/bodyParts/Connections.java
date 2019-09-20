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

public class Connections {
	private ArrayList<Connection> connectionList;

	public Connections(ArrayList<Connection> connections) {
		this.connectionList = connections;
	}

	public Connections(JsonObject jsonObject) {
		connectionList = new ArrayList<Connection>();
		if (jsonObject.containsKey("Connection")) {
			JsonArray jsonArray = jsonObject.getJsonArray("Connection");
			for (int i = 0; i < jsonArray.size(); i++) {
				connectionList.add(new Connection(jsonArray.getJsonObject(i)));
			}
		}
	}

	public JsonValue generateJson() {
		JsonObjectBuilder job = Json.createObjectBuilder();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Connection connection : connectionList) {
			jab.add(connection.generateJson());
		}

		job.add("Connection", jab.build());

		return job.build();
	}
	
    public Element toXML(Document doc)
    {
        Element element = doc.createElement("Connections");

        for (Connection connection : connectionList)
        {
            element.appendChild(connection.toXML(doc));
        }

        return element;

    }
}

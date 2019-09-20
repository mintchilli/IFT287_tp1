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
	private ArrayList<Connection> connectionArrayList;

	public Connections(ArrayList<Connection> connections) {
		this.connectionArrayList = connections;
	}

	public Connections(JsonObject jsonObject) {
		connectionArrayList = new ArrayList<Connection>();
		if (jsonObject.containsKey("Connection")) {
			JsonArray jsonArray = jsonObject.getJsonArray("Connection");
			for (int i = 0; i < jsonArray.size(); ++i) {
				connectionArrayList.add(new Connection(jsonArray.getJsonObject(i)));
			}
		}
	}

	public JsonValue generateJson() {
		JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

		for (Connection connection : connectionArrayList)
			jsonArrayBuilder.add(connection.toJson());

		jsonObjectBuilder.add("Connection", jsonArrayBuilder.build());
		return jsonObjectBuilder.build();
	}
	
    public Element toXML(Document doc)
    {
        Element element = doc.createElement("Connections");

        for (Connection connection : connectionArrayList)
            element.appendChild(connection.toXML(doc));

        return element;

    }
}

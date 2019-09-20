package bodyParts;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Flow {
	private int id;
	private String name;
	private Connectible connectible;
	private Connections connections;

	public Flow(JsonObject jsonObject) {
		id = jsonObject.getInt("id");
		name = jsonObject.getString("name");
		connectible = new Connectible(jsonObject.getJsonObject("Connectible"));

		if (jsonObject.containsKey("Connections"))
			connections = new Connections(jsonObject.getJsonObject("Connections"));
	}

	public Flow(String name, int id) {
		this.id = id;
		this.name = name;
	}

	public void setConnectible(Connectible connectible) {
		this.connectible = connectible;
	}

	public void setConnections(Connections connections) {
		this.connections = connections;
	}

	public JsonValue toJson() {
		JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
		jsonObjectBuilder
			.add("id", id)
			.add("name", name)
			.add("Connectible", connectible.toJson())
			.add("Connections", connectible.toJson());

		return jsonObjectBuilder.build();
	}

	public Element toXML(Document document) {
		Element element = document.createElement("Flow");
		element.setAttribute("id", String.valueOf(id));
		element.setAttribute("name", name);
		element.appendChild(connectible.toXML(document));
		if (connections != null)
			element.appendChild(connections.toXML(document));

		return element;

	}
}

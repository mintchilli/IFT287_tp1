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
	private Connections connections;
	private Connectible connectible;

	public Flow(JsonObject jsonObject) {
		name = jsonObject.getString("name");
		id = jsonObject.getInt("id");
		connectible = new Connectible(jsonObject.getJsonObject("Connectible"));

		if (jsonObject.containsKey("Connections"))
			connections = new Connections(jsonObject.getJsonObject("Connections"));
	}

	public Flow(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public void setConnectible(Connectible connectible) {
		this.connectible = connectible;
	}

	public void setConnections(Connections connections) {
		this.connections = connections;
	}

	public JsonValue generateJson() {
		JsonObjectBuilder job = Json.createObjectBuilder();

		job.add("id", id).add("name", name).add("Connectible", connectible.generateJson()).add("Connections",
				connectible.generateJson());

		return job.build();
	}

	public Element toXML(Document doc) {
		Element element = doc.createElement("Flow");

		element.setAttribute("name", name);
		element.setAttribute("id", String.valueOf(id));

		element.appendChild(connectible.toXML(doc));
		if (connections != null)
			element.appendChild(connections.toXML(doc));

		return element;

	}
}

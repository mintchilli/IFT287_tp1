package bodyParts;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class To {
	private int id;

	public To(int id) {
		this.id = id;
	}

	public To(JsonObject jsonObject) {
		id = jsonObject.getInt("id");
	}

	public JsonValue generateJson() {
		JsonObjectBuilder job = Json.createObjectBuilder();

		job.add("id", id);

		return job.build();
	}

	public Element toXML(Document doc) {
		Element element = doc.createElement("to");
		element.setAttribute("id", String.valueOf(id));
		return element;
	}
}

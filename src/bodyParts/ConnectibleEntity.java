package bodyParts;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ConnectibleEntity {
	private int id;
	private String name;
	private double endRadius;
	private double startRadius;
	private double volume;
	private double length;

	public ConnectibleEntity(String type, JsonObject jsonObject) {
		id = jsonObject.getInt("id");
		name = jsonObject.getString("name");

		if (jsonObject.containsKey("endRadius"))
			endRadius = jsonObject.getJsonNumber("endRadius").doubleValue();
		if (jsonObject.containsKey("startRadius"))
			startRadius = jsonObject.getJsonNumber("startRadius").doubleValue();
		if (jsonObject.containsKey("volume"))
			volume = jsonObject.getJsonNumber("volume").doubleValue();
		if (jsonObject.containsKey("length"))
			length = jsonObject.getJsonNumber("length").doubleValue();
	}

	public ConnectibleEntity(String name, int id) {
		this.id = id;
		this.name = name;
	}


	public void setVolume(double volume) {
		this.volume = volume;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public void setStartRadius(double startRadius) {
		this.startRadius = startRadius;
	}

	public void setEndRadius(double endRadius) {
		this.endRadius = endRadius;
	}
	
	public JsonValue toJson() {
		JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();

		jsonObjectBuilder
			.add("id", id)
			.add("name", name);

		if (endRadius != 0) {
			jsonObjectBuilder.add("endRadius", endRadius);
		}
		if (startRadius != 0) {
			jsonObjectBuilder.add("startRadius", startRadius);
		}
		if (volume != 0) {
			jsonObjectBuilder.add("volume", volume);
		}
		if (length != 0) {
			jsonObjectBuilder.add("length", length);
		}

		return jsonObjectBuilder.build();
	}
	
    public Element toXML(String type, Document document)
    {
        Element element = document.createElement(type);
        element.setAttribute("id", String.valueOf(id));
        element.setAttribute("name", name);
        if (endRadius != 0)
        	element.setAttribute("endRadius", String.valueOf(endRadius));
        if (startRadius != 0)
        	element.setAttribute("startRadius", String.valueOf(startRadius));
        if (volume != 0)
            element.setAttribute("volume", String.valueOf(volume));
        if (length != 0)
            element.setAttribute("length", String.valueOf(length));

        return element;
    }
}

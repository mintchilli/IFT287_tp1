package bodyParts;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ConnectibleChild {
	private String name;
	private int id;
	private double volume;
	private double length;
	private double startRadius;
	private double endRadius;

	public ConnectibleChild(String type, JsonObject jsonObject) {
		name = jsonObject.getString("name");
		id = jsonObject.getInt("id");

		if (jsonObject.containsKey("volume"))
			volume = jsonObject.getJsonNumber("volume").doubleValue();
		if (jsonObject.containsKey("length"))
			length = jsonObject.getJsonNumber("length").doubleValue();
		if (jsonObject.containsKey("startRadius"))
			startRadius = jsonObject.getJsonNumber("startRadius").doubleValue();
		if (jsonObject.containsKey("endRadius"))
			endRadius = jsonObject.getJsonNumber("endRadius").doubleValue();
	}

	public ConnectibleChild(String name, int id) {
		this.name = name;
		this.id = id;
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
	
	public JsonValue generateJson() {
		JsonObjectBuilder job = Json.createObjectBuilder();

		job.add("name", name).add("id", id);

		if (volume != 0) {
			job.add("volume", volume);
		}
		if (length != 0) {
			job.add("length", length);
		}
		if (startRadius != 0) {
			job.add("startRadius", startRadius);
		}
		if (endRadius != 0) {
			job.add("endRadius", endRadius);
		}

		return job.build();
	}
	
    public Element toXML(String type, Document doc)
    {
        Element element = doc.createElement(type);
        element.setAttribute("name", name);
        element.setAttribute("id", String.valueOf(id));
        if (volume != 0)
            element.setAttribute("volume", String.valueOf(volume));
        if (length != 0)
            element.setAttribute("length", String.valueOf(length));
        if (startRadius != 0)
            element.setAttribute("startRadius", String.valueOf(startRadius));
        if (endRadius != 0)
            element.setAttribute("endRadius", String.valueOf(endRadius));

        return element;
    }
}

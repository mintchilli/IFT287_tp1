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

public class System {
	private String name;
	private int id;
	private int type;
	private ArrayList<Flow> flowList;

	public System(String name, int id, int type) {
		this.name = name;
		this.id = id;
		this.type = type;
	}

	public System(JsonObject jsonObject) {
		this.name = jsonObject.getString("name");
		this.id = jsonObject.getInt("id");
		this.type = jsonObject.getInt("type");
		if (jsonObject.containsKey("Flow")) {
			flowList = new ArrayList<Flow>();
			JsonArray jsonArray = jsonObject.getJsonArray("Flow");
			for (int i = 0; i < jsonArray.size(); i++) {
				flowList.add(new Flow(jsonArray.getJsonObject(i)));
			}
		}
	}

	public void setFlows(ArrayList<Flow> flows) {
		this.flowList = flows;
	}

	public JsonValue generateJson() {
		JsonObjectBuilder job = Json.createObjectBuilder();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		job.add("name", name).add("id", id).add("type", type);

		for (Flow flow : flowList) {
			jab.add(flow.generateJson());
		}
		;

		job.add("Flow", jab.build());
		return job.build();
	}
	
    public Element toXML(Document doc)
    {
        Element element = doc.createElement("System");

        element.setAttribute("name", name);
        element.setAttribute("id", String.valueOf(id));
        element.setAttribute("type", String.valueOf(type));

        for (Flow flow : flowList)
        {
            element.appendChild(flow.toXML(doc));
        }

        return element;

    }

}

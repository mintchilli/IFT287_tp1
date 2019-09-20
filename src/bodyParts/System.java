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
	private int id;
	private String name;
	private int type;
	private ArrayList<Flow> flowArrayList;

	public System(String name, int id, int type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public System(JsonObject jsonObject) {
		this.id = jsonObject.getInt("id");
		this.name = jsonObject.getString("name");
		this.type = jsonObject.getInt("type");
		if (jsonObject.containsKey("Flow")) {
			flowArrayList = new ArrayList<Flow>();
			JsonArray jsonArray = jsonObject.getJsonArray("Flow");
			for (int i = 0; i < jsonArray.size(); ++i) {
				flowArrayList.add(new Flow(jsonArray.getJsonObject(i)));
			}
		}
	}

	public void setFlows(ArrayList<Flow> flows) {
		this.flowArrayList = flows;
	}

	public JsonValue toJson() {
		JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

		jsonObjectBuilder
			.add("id", id)
			.add("name", name)
			.add("type", type);

		for (Flow flow : flowArrayList)
			jsonArrayBuilder.add(flow.toJson());

		jsonObjectBuilder.add("Flow", jsonArrayBuilder.build());
		return jsonObjectBuilder.build();
	}
	
    public Element toXML(Document document)
    {
        Element element = document.createElement("System");
        element.setAttribute("id", String.valueOf(id));
        element.setAttribute("name", name);
        element.setAttribute("type", String.valueOf(type));
        for (Flow flow : flowArrayList)
            element.appendChild(flow.toXML(document));

        return element;

    }

}

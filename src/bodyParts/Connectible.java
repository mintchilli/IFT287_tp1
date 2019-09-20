package bodyParts;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Connectible {
	private Hashtable<String, List<ConnectibleEntity>> connectibleEntityList;

	public Connectible(Hashtable<String, List<ConnectibleEntity>> connectibleEntityList) {
		this.connectibleEntityList = connectibleEntityList;
	}

	public Connectible(JsonObject jsonObject) {
		connectibleEntityList = new Hashtable<String, List<ConnectibleEntity>>();
		Iterator<String> keys = jsonObject.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			JsonArray jsonArray = jsonObject.getJsonArray(key);
			for (int i = 0; i < jsonArray.size(); i++) {
				if (!connectibleEntityList.containsKey(key)) {
					connectibleEntityList.put(key, new ArrayList<ConnectibleEntity>());
				}
				connectibleEntityList.get(key).add(new ConnectibleEntity(key, jsonArray.getJsonObject(i)));
			}
		}
	}

	public JsonValue toJson() {
		JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();

		connectibleEntityList.forEach((connectible, connectibleList) -> {
			JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
			for (ConnectibleEntity entity : connectibleList) {
				jsonArrayBuilder.add(entity.toJson());
			}
			jsonObjectBuilder.add(connectible, jsonArrayBuilder);
		});

		return jsonObjectBuilder.build();
	}
	
    public Element toXML(Document document)
    {
        Element element = document.createElement("Connectible");

        connectibleEntityList.forEach((key, connectibleList) -> {
            for (ConnectibleEntity connectibleEntity : connectibleList)
                element.appendChild(connectibleEntity.toXML(key, document));
        });

        return element;
    }
}

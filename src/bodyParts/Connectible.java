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
	private Hashtable<String, List<ConnectibleEntity>> connectibleChildList;

	public Connectible(Hashtable<String, List<ConnectibleEntity>> connectibleChildList) {
		this.connectibleChildList = connectibleChildList;
	}

	public Connectible(JsonObject jsonObject) {
		connectibleChildList = new Hashtable<String, List<ConnectibleEntity>>();
		Iterator<String> keys = jsonObject.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			JsonArray jsonArray = jsonObject.getJsonArray(key);
			for (int i = 0; i < jsonArray.size(); i++) {
				if (!connectibleChildList.containsKey(key)) {
					connectibleChildList.put(key, new ArrayList<ConnectibleEntity>());
				}
				connectibleChildList.get(key).add(new ConnectibleEntity(key, jsonArray.getJsonObject(i)));
			}

		}
	}

	public Hashtable<String, List<ConnectibleEntity>> getConnectibleChildList() {
		return connectibleChildList;
	}

	public void setConnectibleChildList(Hashtable<String, List<ConnectibleEntity>> connectibleChildList) {
		this.connectibleChildList = connectibleChildList;
	}

	public JsonValue generateJson() {
		JsonObjectBuilder job = Json.createObjectBuilder();

		getConnectibleChildList().forEach((part, connectibleList) -> {
			JsonArrayBuilder tempJab = Json.createArrayBuilder();
			for (ConnectibleEntity child : connectibleList) {
				tempJab.add(child.generateJson());
			}
			job.add(part, tempJab);
		});

		return job.build();
	}
	
    public Element toXML(Document doc)
    {
        Element element = doc.createElement("Connectible");

        getConnectibleChildList().forEach((key, connectibleList) -> {
            for (ConnectibleEntity connectibleChild : connectibleList)
            {
                element.appendChild(connectibleChild.toXML(key, doc));
            }
        });

        return element;
    }
}

package tp1;

import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import bodyParts.ConnectibleEntity;
import bodyParts.Connection;
import bodyParts.Connectible;
import bodyParts.Connections;
import bodyParts.MainBody;
import bodyParts.Systems;
import bodyParts.System;
import bodyParts.Flow;
import bodyParts.Organs;
import bodyParts.Organ;

public class SAXHandler extends DefaultHandler {

	private String connectibleName;
	private Connection connection;
	private ArrayList<Connection> connectionArrayList = new ArrayList<Connection>();
	private MainBody mainBody;
	private System system;
	private ArrayList<System> systemArrayList = new ArrayList<System>();
	private Flow flow;
	private boolean newFlow;
	private ArrayList<Flow> flowArrayList = new ArrayList<Flow>();
	private ConnectibleEntity connectibleEntity;
	private Organ organ;
	private ArrayList<Organ> organArrayList = new ArrayList<Organ>();
	private List<ConnectibleEntity> connectibleEntityArrayList = new ArrayList<ConnectibleEntity>();
	private Hashtable<String, List<ConnectibleEntity>> connectibleEntityListHashtable = new Hashtable<String, List<ConnectibleEntity>>();

	@Override
    public void startElement(String uri, String localName, String element, Attributes attributes) throws SAXException
    {
		switch (element) {
		case "MainBody":
			mainBody = new MainBody(attributes.getValue("bodyName"), Integer.parseInt(attributes.getValue("bodyID")));
			break;
			
		case "System":
			system = new System(attributes.getValue("name"), Integer.parseInt(attributes.getValue("id")),
                    Integer.parseInt(attributes.getValue("type")));
			break;
			
		case "Flow":
			flow = new Flow(attributes.getValue("name"), Integer.parseInt(attributes.getValue("id")));
            newFlow = true;
			break;
			
		case "Connection":
			connection = new Connection(Integer.parseInt(attributes.getValue("id")));
			break;
			
		case "Organ":
			organ = new Organ(attributes.getValue("name"), Integer.parseInt(attributes.getValue("id")),
                    Integer.parseInt(attributes.getValue("systemID")));
            organArrayList.add(organ);
            break;
		}

        if (element == "Atrium" || element == "Ventricle" || element == "Artery" 
        		|| element == "Vein" || element == "Capillaries" || element == "Alveoli" 
        		|| element == "AirConnectible" || element == "Nose" || element == "DeversingDuct" 
        		|| element == "StomachTract" || element == "RectumTract" || element == "DigestiveTract" 
        		|| element == "InnerGallbladder" || element == "Duct" || element == "BiDuct"
                || element == "DuodenumTract" || element == "DuctOverflowableJunction" || element == "SalivaryDuct")
        {
            if (newFlow || element != connectibleName)
            {
                connectibleName = element;
                connectibleEntityArrayList = new ArrayList<ConnectibleEntity>();
                newFlow = false;
            }

            connectibleEntity = new ConnectibleEntity(attributes.getValue("name"),
                    Integer.parseInt(attributes.getValue("id")));

            if (attributes.getValue("volume") != null)
                connectibleEntity.setVolume(Double.parseDouble(attributes.getValue("volume")));
            
            if (attributes.getValue("length") != null)
                connectibleEntity.setLength(Double.parseDouble(attributes.getValue("length")));
            
            if (attributes.getValue("startRadius") != null)
                connectibleEntity.setStartRadius(Double.parseDouble(attributes.getValue("startRadius")));
            
            if (attributes.getValue("endRadius") != null)
                connectibleEntity.setEndRadius(Double.parseDouble(attributes.getValue("endRadius")));

            connectibleEntityArrayList.add(connectibleEntity);
        }
    }

	@Override
	public void endElement(String uri, String localName, String element) throws SAXException {
		
		switch (element)
		{
		case "System":
			system.setFlows(flowArrayList);
			systemArrayList.add(system);
			flowArrayList = new ArrayList<Flow>();
			break;
		
		case "Systems":
			mainBody.setSystems(new Systems(systemArrayList));
			break;
			
		case "Flow":
			flowArrayList.add(flow);
			break;
			
		case "Connections":
			flow.setConnections(new Connections(connectionArrayList));
			connectionArrayList = new ArrayList<Connection>();
			break;
			
		case "Connectible":
			flow.setConnectible(new Connectible(connectibleEntityListHashtable));
			connectibleEntityListHashtable = new Hashtable<String, List<ConnectibleEntity>>();
			break;
		
		case "Organs":
			mainBody.setOrgans(new Organs(organArrayList));
			break;
		}
		
		if (element == "Atrium" || element == "Ventricle" || element == "Artery" 
        		|| element == "Vein" || element == "Capillaries" || element == "Alveoli" 
        		|| element == "AirConnectible" || element == "Nose" || element == "DeversingDuct" 
        		|| element == "StomachTract" || element == "RectumTract" || element == "DigestiveTract" 
        		|| element == "InnerGallbladder" || element == "Duct" || element == "BiDuct"
                || element == "DuodenumTract" || element == "DuctOverflowableJunction" || element == "SalivaryDuct")
			connectibleEntityListHashtable.put(element, connectibleEntityArrayList);
	}

	public MainBody getMainBody() {
		return mainBody;
	}

}

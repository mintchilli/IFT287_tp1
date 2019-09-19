package tp1;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import bodyParts.Connectible;
import bodyParts.ConnectibleChild;
import bodyParts.Connection;
import bodyParts.Connections;
import bodyParts.Flow;
import bodyParts.MainBody;
import bodyParts.Organ;
import bodyParts.Organs;
import bodyParts.Systems;
import bodyParts.System;
import bodyParts.To;

public class SAXHandler extends DefaultHandler {

	private MainBody mainBody;
	private ArrayList<System> systemList = new ArrayList<System>();
	private System system;
	private ArrayList<Flow> flowList = new ArrayList<Flow>();
	private Flow flow;
	private Hashtable<String, List<ConnectibleChild>> connectibleChildListList = new Hashtable<String, List<ConnectibleChild>>();
	private boolean newFlow;
	private String connectibleName;
	private List<ConnectibleChild> connectibleChildList = new ArrayList<ConnectibleChild>();
	private ConnectibleChild connectibleChild;
	private ArrayList<Connection> connectionList = new ArrayList<Connection>();
	private Connection connection;
	private ArrayList<To> toList = new ArrayList<To>();
	private To to;
	private ArrayList<Organ> organList = new ArrayList<Organ>();
	private Organ organ;

	@Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        if (qName == "MainBody")
        {
            mainBody = new MainBody(attributes.getValue("bodyName"), Integer.parseInt(attributes.getValue("bodyID")));

        }

        else if (qName == "System")
        {
            system = new System(attributes.getValue("name"), Integer.parseInt(attributes.getValue("id")),
                    Integer.parseInt(attributes.getValue("type")));
        }

        else if (qName == "Flow")
        {
            flow = new Flow(attributes.getValue("name"), Integer.parseInt(attributes.getValue("id")));
            newFlow = true;
        }

        else if (qName == "Atrium" || qName == "Ventricle" || qName == "Artery" || qName == "Vein"
                || qName == "Capillaries" || qName == "Nose" || qName == "AirConnectible" || qName == "Alveoli"
                || qName == "DigestiveTract" || qName == "StomachTract" || qName == "DuodenumTract"
                || qName == "DigestiveTract" || qName == "RectumTract" || qName == "BiDuct" || qName == "Duct"
                || qName == "DuctOverflowableJunction" || qName == "DeversingDuct" || qName == "InnerGallbladder"
                || qName == "SalivaryDuct")
        {
            if (newFlow || qName != connectibleName)
            {
                connectibleName = qName;
                connectibleChildList = new ArrayList<ConnectibleChild>();
                newFlow = false;
            }

            connectibleChild = new ConnectibleChild(attributes.getValue("name"),
                    Integer.parseInt(attributes.getValue("id")));

            if (attributes.getValue("volume") != null)
            {
                connectibleChild.setVolume(Double.parseDouble(attributes.getValue("volume")));
            }
            if (attributes.getValue("length") != null)
            {
                connectibleChild.setLength(Double.parseDouble(attributes.getValue("length")));
            }
            if (attributes.getValue("startRadius") != null)
            {
                connectibleChild.setStartRadius(Double.parseDouble(attributes.getValue("startRadius")));
            }
            if (attributes.getValue("endRadius") != null)
            {
                connectibleChild.setEndRadius(Double.parseDouble(attributes.getValue("endRadius")));
            }

            connectibleChildList.add(connectibleChild);
        }

        else if (qName == "Connection")
        {
            connection = new Connection(Integer.parseInt(attributes.getValue("id")));
        }

        else if (qName == "to")
        {
            to = new To(Integer.parseInt(attributes.getValue("id")));
            toList.add(to);
        }

        else if (qName == "Organ")
        {
            organ = new Organ(attributes.getValue("name"), Integer.parseInt(attributes.getValue("id")),
                    Integer.parseInt(attributes.getValue("systemID")));

            organList.add(organ);
        }
    }

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName == "Systems") {
			mainBody.setSystems(new Systems(systemList));
		} else if (qName == "System") {
			system.setFlows(flowList);
			systemList.add(system);
			flowList = new ArrayList<Flow>();
		} else if (qName == "Flow") {
			flowList.add(flow);
		} else if (qName == "Connectible") {
			flow.setConnectible(new Connectible(connectibleChildListList));
			connectibleChildListList = new Hashtable<String, List<ConnectibleChild>>();
		} else if (qName == "Atrium" || qName == "Ventricle" || qName == "Artery" || qName == "Vein"
				|| qName == "Capillaries" || qName == "Nose" || qName == "AirConnectible" || qName == "Alveoli"
				|| qName == "DigestiveTract" || qName == "StomachTract" || qName == "DuodenumTract"
				|| qName == "DigestiveTract" || qName == "RectumTract" || qName == "BiDuct" || qName == "Duct"
				|| qName == "DuctOverflowableJunction" || qName == "DeversingDuct" || qName == "InnerGallbladder"
				|| qName == "SalivaryDuct") {
			connectibleChildListList.put(qName, connectibleChildList);
		} else if (qName == "Connection") {
			connection.setTos(toList);
			connectionList.add(connection);
			toList = new ArrayList<To>();
		} else if (qName == "To") {
			toList.add(to);
		} else if (qName == "Connections") {
			flow.setConnections(new Connections(connectionList));
			connectionList = new ArrayList<Connection>();
		} else if (qName == "Organs") {
			mainBody.setOrgans(new Organs(organList));
		}
	}

	public MainBody returnMainBody() {
		return mainBody;
	}

}

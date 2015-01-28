package Game;

import java.util.Scanner;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 



import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
/**
 * 
 */

/**
 * @author Archana
 *
 */
public class Board {
		public static RegionStatus objDSisters; 
		public static RegionStatus objUEstate;
		public static RegionStatus objDLanding;
		public static RegionStatus objSGods;
		public static RegionStatus objTScours;
		public static RegionStatus objTHippo;
		public static RegionStatus objTShades;
		public static RegionStatus objDimwell;
		public static RegionStatus objLongwall;
		public static RegionStatus objIGods;
		public static RegionStatus objSSleepers;
		public static RegionStatus objNHill;
	
	public static void main(String[] args) {
		objDSisters = new RegionStatus("Dolly Sisters", 1, 6);
		objUEstate = new RegionStatus("Unreal Estate", 2, 18);
		objDLanding = new RegionStatus("Dragon's Landing", 3, 12);
		objSGods = new RegionStatus("Small Gods", 4, 18);
		objTScours = new RegionStatus("The Scours", 5, 6);
		objTHippo = new RegionStatus("The Hippo", 6, 12);
		objTShades = new RegionStatus("The Shades", 7, 6);
		objDimwell = new RegionStatus("Dimwell", 8, 6);
		objLongwall = new RegionStatus("Longwall", 9, 12);
		objIGods = new RegionStatus("Isle of Gods", 10, 12);
		objSSleepers = new RegionStatus("Seven Sleepers", 11, 18);
		objNHill = new RegionStatus("Nap Hill", 12, 12);
		
		createXML();
		
		Scanner s1 = new Scanner(System.in);
		System.out.println("Enter Number of Players");
		int numPlayers = s1.nextInt();
		// check user >1 <=4
		for (int loop = 1; loop <= numPlayers; loop++) {
			switch (loop) {
			case 1:
				Player pRed = new Player("red", "A");
				objTScours.placeDefaultMinion("red");
				objTShades.placeDefaultMinion("red");
				objDSisters.placeDefaultMinion("red");
				pRed.initialPlayerStatus();
				break;
			case 2:
				Player pGreen = new Player("green", "B");
				objTScours.placeDefaultMinion("green");
				objTShades.placeDefaultMinion("green");
				objDSisters.placeDefaultMinion("green");
				break;
			case 3:
				Player pYellow = new Player("yellow", "C");
				objTScours.placeDefaultMinion("yellow");
				objTShades.placeDefaultMinion("yellow");
				objDSisters.placeDefaultMinion("yellow");
				break;
			case 4:
				Player pBlue = new Player("blue", "D");
				objTScours.placeDefaultMinion("blue");
				objTShades.placeDefaultMinion("blue");
				objDSisters.placeDefaultMinion("blue");
				break;
			}
		}
		createXML();
	}
	

	private static void createXML()
	{
		DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder icBuilder;
        try {
            icBuilder = icFactory.newDocumentBuilder();
            Document doc = icBuilder.newDocument();
            Element mainRootElement = doc.createElementNS("CreateXMLDOM", "Regions");
            doc.appendChild(mainRootElement);
 
            // append child elements to root element
            mainRootElement.appendChild(getRegion(doc, objDSisters.rName, objDSisters.rNumber, objDSisters.buildingCost, objDSisters.rMinionNum, objDSisters.troubleMarker, objDSisters.demon, objDSisters.troll));
            mainRootElement.appendChild(getRegion(doc, objUEstate.rName, objUEstate.rNumber, objUEstate.buildingCost, objUEstate.rMinionNum, objUEstate.troubleMarker,objUEstate.demon, objUEstate.troll));
            mainRootElement.appendChild(getRegion(doc, objDLanding.rName, objDLanding.rNumber, objDLanding.buildingCost, objDLanding.rMinionNum, objDLanding.troubleMarker,objDLanding.demon, objDLanding.troll));
            mainRootElement.appendChild(getRegion(doc, objSGods.rName, objSGods.rNumber, objSGods.buildingCost, objSGods.rMinionNum, objSGods.troubleMarker,objSGods.demon,objSGods.troll));
            mainRootElement.appendChild(getRegion(doc, objTScours.rName, objTScours.rNumber, objTScours.buildingCost, objTScours.rMinionNum, objTScours.troubleMarker,objTScours.demon, objTScours.troll));
            mainRootElement.appendChild(getRegion(doc, objTHippo.rName, objTHippo.rNumber, objTHippo.buildingCost, objTHippo.rMinionNum, objTHippo.troubleMarker,objTHippo.demon, objTHippo.troll));
            mainRootElement.appendChild(getRegion(doc, objTShades.rName, objTShades.rNumber, objTShades.buildingCost, objTShades.rMinionNum, objTShades.troubleMarker,objTShades.demon, objTShades.troll));
            mainRootElement.appendChild(getRegion(doc, objDimwell.rName, objDimwell.rNumber, objDimwell.buildingCost, objDimwell.rMinionNum, objDimwell.troubleMarker,objDimwell.demon, objDimwell.troll));
            mainRootElement.appendChild(getRegion(doc, objLongwall.rName, objLongwall.rNumber, objLongwall.buildingCost, objLongwall.rMinionNum, objLongwall.troubleMarker,objLongwall.demon, objLongwall.troll));
            mainRootElement.appendChild(getRegion(doc, objIGods.rName, objIGods.rNumber, objIGods.buildingCost, objIGods.rMinionNum, objIGods.troubleMarker,objIGods.demon, objIGods.troll));
            mainRootElement.appendChild(getRegion(doc, objSSleepers.rName, objSSleepers.rNumber, objSSleepers.buildingCost, objSSleepers.rMinionNum, objSSleepers.troubleMarker,objSSleepers.demon, objSSleepers.troll));
            mainRootElement.appendChild(getRegion(doc, objNHill.rName, objNHill.rNumber, objNHill.buildingCost, objNHill.rMinionNum, objNHill.troubleMarker,objNHill.demon, objNHill.troll));
 
            // output DOM XML to console 
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
            DOMSource source = new DOMSource(doc);
            StreamResult file = new StreamResult(new File("/G:/APPProject/xmlFileFinal2.xml"));
            transformer.transform(source, file);
 
            System.out.println("\nXML DOM Created Successfully..");
 
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
	}
	

	
	
	 private static Node getRegion(Document doc, String name, int rNumber, int buildingCost, int rMinionNum, int troubleMarker, int demon, int troll) 
	 {
	        Element region = doc.createElement("Region");
	        region.setAttribute("Name", name);
	        region.appendChild(getRegionElements(doc, region, "RegionNumber", rNumber));
	        region.appendChild(getRegionElements(doc, region, "BuildingCost", buildingCost));
	        region.appendChild(getRegionElements(doc, region, "NumberOfMinions", rMinionNum));
	        region.appendChild(getRegionElements(doc, region, "NumberOfTroubleMarkers", troubleMarker));
	        region.appendChild(getRegionElements(doc, region, "NumberOfDemons", demon));
	        region.appendChild(getRegionElements(doc, region, "NumberOfTrolls", troll));
	        return region;
	 }
	 
	    // utility method to create text node
	    private static Node getRegionElements(Document doc, Element element, String name, int value) 
	    {
	        Element node = doc.createElement(name);
	       // node.appendChild(doc.createTextNode(value));
	        node.appendChild(doc.createTextNode(Integer.toString(value)));
	        return node;
	    }
}

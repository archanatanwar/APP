package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.Node;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Game.Region;
import Game.GameEngine;

/**
 * Test Game Engine Class
 * 
 * @author nav_k
 *
 */
public class TestGameEngine {
	private Region objDSisters;
	private Document doc;
	private static final String Green = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Set up
	 * 
	 * @throws Exception
	 *             if an error
	 */
	@Before
	public void setUp() throws Exception {
		objDSisters = new Region("Dolly Sisters", 1, 6, Arrays.asList(2, 3, 12));
		DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder icBuilder;
		icBuilder = icFactory.newDocumentBuilder();
		// create Document object dom to write to file
		doc = icBuilder.newDocument();
	}

	/**
	 * Tear down
	 * 
	 * @throws Exception
	 *             if an error
	 */
	@After
	public void tearDown() throws Exception {
		this.objDSisters = null;
		this.doc = null;
	}

	/**
	 * Test initialization of methods should not throw exception
	 */
	@Test
	public void testInitializeGameEngine() {
		try {
			// Region objDSisters = new Region("Dolly Sisters", 1, 6);
			String rName = objDSisters.getRegionName();
			assertTrue(rName == "Dolly Sisters");
		} catch (Exception e) {
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}

	/**
	 * Test addition of objects to list should not throw exception
	 */
	@Test
	public void testAddToRList() {
		try {
			// Region objDSisters = new Region("Dolly Sisters", 1, 6);
			List<Region> regionObjList = new ArrayList<Region>();
			regionObjList.add(objDSisters);
			assertTrue(regionObjList.size() == 1);
		} catch (Exception e) {
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}

	/**
	 * Test getRegion() method should not throw exception
	 */
	// @Test
	public void testgetRegion() {
		try {
			Element mainRootElement = doc.createElement("abc");
			org.w3c.dom.Node node = GameEngine.getRegion(doc, "DollySisters",
					1, 12, 6, 4, 1, 2, 1, mainRootElement);
			// Element e = (Element)node;
			// Node fChild = e.getFirstChild();
			String name = node.getNodeName();
			assertTrue(node.getNodeName() == "Region");
		} catch (Exception e) {
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}

	/**
	 * Test getPlayer() method should not throw exception
	 * 
	 * 
	 */
	// @Test
	public void testgetPlayer() {
		try {
			Element mainRootElement = doc.createElement("abc");
			HashMap<String, List<String>> pCards = new HashMap<String, List<String>>();
			List<String> pList = new ArrayList<String>();
			pList.add("MRS_CAKE");
			pList.add("MR_BENT");
			pCards.put("Green", pList);
			org.w3c.dom.Node node = GameEngine.getPlayer(doc, "red", 1, 12, 4,
					"Lord_Rust", pCards, 1, 2, mainRootElement);
			assertTrue(node.hasChildNodes());
			assertTrue(node.getChildNodes().getLength() == 8);
		} catch (Exception e) {
			fail("Should not throw exception. Message: " + e.getMessage());
		}
	}
}

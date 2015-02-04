package Game;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class TestGameEngine {
	
	
	@Test
	public void testXMLCreation() {
		GameEngine.createXML();
		assertTrue(new File("./src/Saved_Game.xml").exists());
	}

}

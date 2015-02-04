package Game;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestRegion {

	
	Region objDSisters = new Region("Dolly Sisters", 1, 6);
	Region objUEstate = new Region("Unreal Estate", 2, 18);
	
	@Test
	/*
	 * Comparing the regions
	 */
	public void testRegion() {
		assertNotSame(objDSisters,objUEstate);
	}
	
	@Test
	public void testRegionConstructor() {
		try{
			
			Region objDLanding = new Region("Dragon's Landing", 3, 12);
			assertTrue(objDLanding.rName.equals("Dragon's Landing") && objDLanding.rNumber==3 && objDLanding.rBuildingCost==12);
			
		}
		catch(Exception e)
		{
			e.printStackTrace(); 
			fail("should not throw exception");
		}
	}

}

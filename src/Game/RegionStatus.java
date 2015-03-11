

package Game;
/** 
 * <h1>Keeps track of which region has minion or building of which player </h1>
 * <p>RegionStatus class is used to store the number of minions 
 * and presence of building in the region for the particular Player.</p>
 */
public class RegionStatus {
		 int regionNumber;
		int placedMinion;
		int placedbuilding;
		
		public int getRegionNumber()
		{
			return regionNumber;
		}
		
		public int getPlacedMinion()
		{
			return placedMinion;
		}
		
		public int getPlacedBuilding()
		{
			return placedbuilding;
		}
}

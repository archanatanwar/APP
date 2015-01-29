package Jatin;

public class Area {
	public Minion minion;
	public Building building;
	static int minionCount = 0;
	static int buildingCount = 0;
	static int troubleMarkerCount =0 ;
	
	public void addBuilding(int noOfBuildings){
		buildingCount = buildingCount +1;
	}
	
	public void addMinion(int noOfMinions){
		minionCount = minionCount +1;
	}
	
	public void addTroubleMarker(){
		troubleMarkerCount = 1;
	}
	
	public void removeTroubleMarker(){
		troubleMarkerCount = 0;
	}
	
}

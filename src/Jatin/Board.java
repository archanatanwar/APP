package Jatin;



public class Board {
	public Minion minions;
	public Building buildings;
	public int demonCount;
	public int trollCount;
	static public int troubleMarkerCount;
	public Die die;
	static public int dollars;
	public PlayerAidCard playerAidCards;
	public RandomEventCard randomEventCards;
	public PersonalityCard personalityCards;
	public CityAreaCard cityAreaCards;
	public GreenPlayerCard greenPlayerCards;
	public BrownPlayerCard brownPlayerCards;
	static public int greenPlayerCardCount = 48;
	static public int brownPlayerCardCount = 53;
	static public int discardPlayerCardCount = 0;
	public Area areas;
	public Player players;
	
	public static void main(String[] args) {
		
	}
	
	public static void loadGame(){
		Area theShades = new Area();
		Area dollySisters = new Area();
		Area theScours = new Area()	;
		Area dimWell = new Area();
		Area napHill = new Area();
		Area longWall = new Area();
		Area theHippo = new Area();
		Area dragonsLanding = new Area();
		Area isleOfGods = new Area();
		Area smallGods = new Area();
		Area sevenSleepers = new Area();
		Area unrealEstate = new Area();
		
		troubleMarkerCount = 12;
		dollars = 120;
		
		
		Player playerRed = new Player("Red","A");
		Player playerGreen = new Player("Green","B");
		Player playerYellow = new Player("Yellow","C");
		Player playerBlue = new Player("Blue","D");
		
		greenPlayerCardCount = 28;
		
		
		theShades.addMinion(4);
		theShades.addTroubleMarker();
		
		theScours.addMinion(4);
		theScours.addTroubleMarker();
		
		dollySisters.addMinion(4);
		dollySisters.addTroubleMarker();
		
		troubleMarkerCount =9;
		
	}
}

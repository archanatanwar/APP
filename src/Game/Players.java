//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//import org.jdom2.Document;
//import org.jdom2.Element;
//import org.jdom2.JDOMException;
//import org.jdom2.Text;
//import org.jdom2.input.SAXBuilder;
//import org.jdom2.output.Format;
//import org.jdom2.output.XMLOutputter;
//
//
//public class Players {
//	
//
//static void Write_XML(){
//	
//	try{
//	
//		Document doc = new Document();
//		Element players = new Element("players");
//		doc.setRootElement(players);
//		
//							////////////////////////////// First Player ////////////////////////////////
//			Element player1 = new Element("player1");
//		
//			Element Player1_Color = new Element("Player1_Color");
//			Player1_Color.setAttribute("color", "Red");
//						
//			Element Player1_personality = new Element("Player1_personality");
//			Player1_personality.setAttribute("person", "Dragon King of Arms");
//			
//			Element Player1_CardNo = new Element("Player1_CardNo");
//			Player1_CardNo.setAttribute("Card_No", "23,12,14,34,42");
//			
//			Element Player1_No_Minions = new Element("Player1_No_Minions");
//			Player1_No_Minions.setAttribute("minions_No", "3 minions");
//			
//			Element Player1_No_Buildings = new Element("Player1_No_Buildings");
//			Player1_No_Buildings.setAttribute("Buildings_No", "4 buildings");
//			
//			Element Player1_Money = new Element("Player1_Money");
//			Player1_Money.setAttribute("Money", "10 Ankh-Morpork $");
//			
//						
//			player1.addContent(Player1_Color);
//			player1.addContent(Player1_personality);
//			player1.addContent(Player1_CardNo);
//			player1.addContent(Player1_No_Minions);
//			player1.addContent(Player1_No_Buildings);
//			player1.addContent(Player1_Money);
//			
//			players.addContent(player1);
//		
//		///////////////////////////////////// Second Player /////////////////////////////////////////////////
//		
//			Element player2 = new Element("player2");
//			
//			Element Player2_Color1 = new Element("Player2_Color");
//			Player2_Color1.setAttribute("color", "Green");
//			
//			Element Player2_personality1 = new Element("Player2_personality");
//			Player2_personality1.setAttribute("person", "Lord Selachii");
//			
//			Element Player2_CardNo1 = new Element("Player2_CardNo");
//			Player2_CardNo1.setAttribute("Card_No", "27,33,16,45,3");
//			
//			Element Player2_No_Minions1 = new Element("Player2_No_Minions");
//			Player2_No_Minions1.setAttribute("minions_No", "7 minions");
//			
//			Element Player2_No_Buildings1 = new Element("Player2_No_Buildings");
//			Player2_No_Buildings1.setAttribute("Buildings_No", "1 buildings");
//			
//			Element Player2_Money1 = new Element("Player2_Money");
//			Player2_Money1.setAttribute("Money", "10 Ankh-Morpork $");
//			
//						
//			player2.addContent(Player2_Color1);
//			player2.addContent(Player2_personality1);
//			player2.addContent(Player2_CardNo1);
//			player2.addContent(Player2_No_Minions1);
//			player2.addContent(Player2_No_Buildings1);
//			player2.addContent(Player2_Money1);
//			
//			players.addContent(player2);
//			
//			
//			///////////////////////////////////// Third Player /////////////////////////////////////////////////
//						
//			Element player3 = new Element("player3");
//			
//			Element Player3_Color1 = new Element("Player3_Color");
//			Player3_Color1.setAttribute("color", "Black");
//			
//			Element Player3_personality1 = new Element("Player3_personality");
//			Player3_personality1.setAttribute("person", "Commander Vimes");
//			
//			Element Player3_CardNo1 = new Element("Player3_CardNo");
//			Player3_CardNo1.setAttribute("Card_No", "31,6,7,26,44");
//			
//			Element Player3_No_Minions1 = new Element("Player3_No_Minions");
//			Player3_No_Minions1.setAttribute("minions_No", "3 minions");
//			
//			Element Player3_No_Buildings1 = new Element("Player3_No_Buildings");
//			Player3_No_Buildings1.setAttribute("Buildings_No", "4 buildings");
//			
//			Element Player3_Money1 = new Element("Player3_Money");
//			Player3_Money1.setAttribute("Money", "10 Ankh-Morpork $");
//			
//			
//			player3.addContent(Player3_Color1);
//			player3.addContent(Player3_personality1);
//			player3.addContent(Player3_CardNo1);
//			player3.addContent(Player3_No_Minions1);
//			player3.addContent(Player3_No_Buildings1);
//			player3.addContent(Player3_Money1);
//			
//			players.addContent(player3);
//			
//			
//			///////////////////////////////////// Fourth Player /////////////////////////////////////////////////
//			
//			Element player4 = new Element("player4");
//			
//			Element Player4_Color1 = new Element("Player4_Color");
//			Player4_Color1.setAttribute("color", "Yellow");
//			
//			Element Player4_personality1 = new Element("Player4_personality");
//			Player4_personality1.setAttribute("person", "character4");
//			
//			Element Player4_CardNo1 = new Element("Player4_CardNo");
//			Player4_CardNo1.setAttribute("Card_No", "47,48,1,57,65");
//			
//			Element Player4_No_Minions1 = new Element("Player4_No_Minions");
//			Player4_No_Minions1.setAttribute("minions_No", "3 minions");
//			
//			Element Player4_No_Buildings1 = new Element("Player4_No_Buildings");
//			Player4_No_Buildings1.setAttribute("Buildings_No", "4 buildings");
//			
//			Element Player4_Money1 = new Element("Player4_Money");
//			Player4_Money1.setAttribute("Money", "10  Ankh-Morpork $");
//			
//			
//			player4.addContent(Player4_Color1);
//			player4.addContent(Player4_personality1);
//			player4.addContent(Player4_CardNo1);
//			player4.addContent(Player4_No_Minions1);
//			player4.addContent(Player4_No_Buildings1);
//			player4.addContent(Player4_Money1);
//			
//			players.addContent(player4);
//		
//	
//			
//			
//			
//			
//			
//			
//	XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat()) ;
//	
//	outputter.output(doc, new FileOutputStream(new File("./src/players_info.xml"))); 
//	}
//	
//		catch(Exception e) {
//			e.printStackTrace();
//		
//		}
//	
//	}
//
//static void Save_Game(){
//	
//	try{
//	
//		Document doc = new Document();
//		Element players = new Element("players");
//		doc.setRootElement(players);
//		
//		NewGame saved_game = new NewGame();
//		saved_game.Players_Info.getValueAt(3, 4);
//		
//							////////////////////////////// First Player ////////////////////////////////
//		//System.out.println("test1");
//		
//			Element player1 = new Element("test");
//		
//			Element Player1_Color = new Element("Player1_Color");
//			Player1_Color.setAttribute("color", saved_game.Players_Info.getValueAt(0, 1).toString());
//						
//			Element Player1_personality = new Element("Player1_personality");
//			Player1_personality.setAttribute("person",  saved_game.Players_Info.getValueAt(0, 2).toString());
//			
//			Element Player1_CardNo = new Element("Player1_CardNo");
//			Player1_CardNo.setAttribute("Card_No",  saved_game.Players_Info.getValueAt(0, 6).toString());
//			
//			Element Player1_No_Minions = new Element("Player1_No_Minions");
//			Player1_No_Minions.setAttribute("minions_No",  saved_game.Players_Info.getValueAt(0, 3).toString());
//			
//			Element Player1_No_Buildings = new Element("Player1_No_Buildings");
//			Player1_No_Buildings.setAttribute("Buildings_No",  saved_game.Players_Info.getValueAt(0, 4).toString());
//			
//			Element Player1_Money = new Element("Player1_Money");
//			Player1_Money.setAttribute("Money",  saved_game.Players_Info.getValueAt(0, 5).toString());
//			
//						
//			player1.addContent(Player1_Color);
//			player1.addContent(Player1_personality);
//			player1.addContent(Player1_CardNo);
//			player1.addContent(Player1_No_Minions);
//			player1.addContent(Player1_No_Buildings);
//			player1.addContent(Player1_Money);
//			
//			players.addContent(player1);
//		
//		///////////////////////////////////// Second Player /////////////////////////////////////////////////
//		
//			Element player2 = new Element("test2");
//			
//			Element Player2_Color1 = new Element("Player2_Color");
//			Player2_Color1.setAttribute("color",  saved_game.Players_Info.getValueAt(1, 1).toString());
//			
//			Element Player2_personality1 = new Element("Player2_personality");
//			Player2_personality1.setAttribute("person",  saved_game.Players_Info.getValueAt(1, 2).toString());
//			
//			Element Player2_CardNo1 = new Element("Player2_CardNo");
//			Player2_CardNo1.setAttribute("Card_No",  saved_game.Players_Info.getValueAt(1, 6).toString());
//			
//			Element Player2_No_Minions1 = new Element("Player2_No_Minions");
//			Player2_No_Minions1.setAttribute("minions_No",  saved_game.Players_Info.getValueAt(1, 3).toString());
//			
//			Element Player2_No_Buildings1 = new Element("Player2_No_Buildings");
//			Player2_No_Buildings1.setAttribute("Buildings_No",  saved_game.Players_Info.getValueAt(1, 4).toString());
//			
//			Element Player2_Money1 = new Element("Player2_Money");
//			Player2_Money1.setAttribute("Money",  saved_game.Players_Info.getValueAt(1, 5).toString());
//			
//						
//			player2.addContent(Player2_Color1);
//			player2.addContent(Player2_personality1);
//			player2.addContent(Player2_CardNo1);
//			player2.addContent(Player2_No_Minions1);
//			player2.addContent(Player2_No_Buildings1);
//			player2.addContent(Player2_Money1);
//			
//			players.addContent(player2);
//			
//			
//			///////////////////////////////////// Third Player /////////////////////////////////////////////////
//						
//			Element player3 = new Element( "test3");
//			
//			Element Player3_Color1 = new Element("Player3_Color");
//			Player3_Color1.setAttribute("color",  saved_game.Players_Info.getValueAt(2, 1).toString());
//			
//			Element Player3_personality1 = new Element("Player3_personality");
//			Player3_personality1.setAttribute("person",  saved_game.Players_Info.getValueAt(2, 2).toString());
//			
//			Element Player3_CardNo1 = new Element("Player3_CardNo");
//			Player3_CardNo1.setAttribute("Card_No",  saved_game.Players_Info.getValueAt(2, 6).toString());
//			
//			Element Player3_No_Minions1 = new Element("Player3_No_Minions");
//			Player3_No_Minions1.setAttribute("minions_No",  saved_game.Players_Info.getValueAt(2, 3).toString());
//			
//			Element Player3_No_Buildings1 = new Element("Player3_No_Buildings");
//			Player3_No_Buildings1.setAttribute("Buildings_No",  saved_game.Players_Info.getValueAt(2, 4).toString());
//			
//			Element Player3_Money1 = new Element("Player3_Money");
//			Player3_Money1.setAttribute("Money",  saved_game.Players_Info.getValueAt(2, 5).toString());
//			
//			
//			player3.addContent(Player3_Color1);
//			player3.addContent(Player3_personality1);
//			player3.addContent(Player3_CardNo1);
//			player3.addContent(Player3_No_Minions1);
//			player3.addContent(Player3_No_Buildings1);
//			player3.addContent(Player3_Money1);
//			
//			players.addContent(player3);
//			
//			
//			///////////////////////////////////// Fourth Player /////////////////////////////////////////////////
//			
//			Element player4 = new Element("test4");
//			
//			Element Player4_Color1 = new Element("Player4_Color");
//			Player4_Color1.setAttribute("color",  saved_game.Players_Info.getValueAt(3, 1).toString());
//			
//			Element Player4_personality1 = new Element("Player4_personality");
//			Player4_personality1.setAttribute("person",  saved_game.Players_Info.getValueAt(3, 2).toString());
//			
//			Element Player4_CardNo1 = new Element("Player4_CardNo");
//			Player4_CardNo1.setAttribute("Card_No",  saved_game.Players_Info.getValueAt(3, 6).toString());
//			
//			Element Player4_No_Minions1 = new Element("Player4_No_Minions");
//			Player4_No_Minions1.setAttribute("minions_No",  saved_game.Players_Info.getValueAt(3, 3).toString());
//			
//			Element Player4_No_Buildings1 = new Element("Player4_No_Buildings");
//			Player4_No_Buildings1.setAttribute("Buildings_No",  saved_game.Players_Info.getValueAt(3, 4).toString());
//			
//			Element Player4_Money1 = new Element("Player4_Money");
//			Player4_Money1.setAttribute("Money",  saved_game.Players_Info.getValueAt(3, 5).toString());
//			
//			
//			player4.addContent(Player4_Color1);
//			player4.addContent(Player4_personality1);
//			player4.addContent(Player4_CardNo1);
//			player4.addContent(Player4_No_Minions1);
//			player4.addContent(Player4_No_Buildings1);
//			player4.addContent(Player4_Money1);
//			
//			players.addContent(player4);
//	
//	XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat()) ;
//	
//	outputter.output(doc, new FileOutputStream(new File("./src/Saved_Game.xml"))); 
//	}
//	
//		catch(Exception e) {
//			e.printStackTrace();
//			}
//	
//	}
//
//static void Read_XML(){
//				SAXBuilder builder = new SAXBuilder();
//				try {
//			             
//			            // Parses the file supplied into a JDOM document
//			            Document readDoc = builder.build(new File("./src/jdomMade3.xml"));
//			                // Returns the root element for the document
//						System.out.println("Player1_color: " + readDoc.getRootElement().getChild("player1").getChild("Player1_Color").getAttributeValue("color"));
//						System.out.println("Player1_personality: " + readDoc.getRootElement().getChild("player1").getChild("Player1_personality").getAttributeValue("person"));
//						System.out.println("Player1_CardNo: " + readDoc.getRootElement().getChild("player1").getChild("Player1_CardNo").getAttributeValue("Card_No"));
//						System.out.println("Player1_No_Minions: " + readDoc.getRootElement().getChild("player1").getChild("Player1_No_Minions").getAttributeValue("minions_No"));
//						System.out.println("Player1_No_Buildings: " + readDoc.getRootElement().getChild("player1").getChild("Player1_No_Buildings").getAttributeValue("Buildings_No"));
//						System.out.println("Player1_Money: " + readDoc.getRootElement().getChild("player1").getChild("Player1_Money").getAttributeValue("Money"));
//
//			        }
//	
//						catch (JDOMException e) {
//				            e.printStackTrace();
//				        }
//				         
//				        catch (IOException e) {
//				            // TODO Auto-generated catch block
//				            e.printStackTrace();
//			        }
//
//			}
//}
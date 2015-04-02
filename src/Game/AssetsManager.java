package Game;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class AssetsManager {
	
	String[][] Locations = new String[][]{
			{"444,121","530,126","495,175","550,200","590,215","630,240","680,190","580,135","630,185","490,140"},
 			{"390,395","435,380","480,375","490,340","440,335","515,300","575,300","590,260","480,305","510,260"},
			{"715,400","705,365","745,365","775,295","750,260","720,230","785,260","675,300","780,360","750,330"},
			{"620,320","640,360","650,400","630,405","675,440","675,530","720,540","770,550","725,420","780,485"},
			{"425,580","465,600","510,605","595,650","630,610","660,570","620,480","560,475","585,515","595,565"},
			{"710,580","750,600","795,610","780,660","675,635","620,740","654,780","690,780","735,650","660,690"},
			{"600,820","555,830","510,820","585,780","530,780","560,740","475,725","525,650","515,735","460,690"},
			{"360,840","310,835","260,810","190,775","144,732","114,690","160,660","340,770","290,770","225,715"},
			{"75,650","120,630","165,600","205,570","255,545","315,500","260,490","120,485","190,490","120,565"},
			{"400,520","435,515","475,507","505,475","505,435","465,440","426,450","396,486","415,510","450,470"},
			{"85,439","100,400","150,430","150,390","195,430","240,430","290,430","250,300","290,340","225,340"},
			{"100,340","150,340","110,300","165,300","120,255","175,260","155,205","310,260","300,170","270,210"}};
	
	//static int count =0;
	//static boolean frame_exist=false;
	//public JFrame frame = buildFrame();
	
	
	
	public int[][] getUpdates(String[][] regions_info ){
		//String[][] regions_info = new String[12][3];
		int[][] peices = new int[12][11];
		for(int m=0; m<12; m++){
			for(int n=0; n<11; n++){
				peices[m][n]=0;
			}
		}
		//NewGame regions = new NewGame();
		//regions_info = regions.regionsUpdates;
		
		
							//////////  Loop through 12 Regions //////////////////////////
		for(int j=0; j<12; j++){
			
								/////////////////////////////////// Get Minions info     ////////////////////////////////
			for(int i=0; i< regions_info[j][0].length(); i++){
				if(Character.isDigit(regions_info[0][0].charAt(i))){
					if(regions_info[j][0].charAt(i+1)=='r') peices[j][0]=Integer.parseInt(regions_info[j][0].substring(i, i+1));
					if(regions_info[j][0].charAt(i+1)=='g') peices[j][1]=Integer.parseInt(regions_info[j][0].substring(i, i+1));
					if(regions_info[j][0].charAt(i+1)=='b') peices[j][2]=Integer.parseInt(regions_info[j][0].substring(i, i+1));
					if(regions_info[j][0].charAt(i+1)=='y') peices[j][3]=Integer.parseInt(regions_info[j][0].substring(i, i+1));
				}
			}
			
								/////////////////////////////////// Get Buildings info     ////////////////////////////////
		
			for(int n=0; n< regions_info[j][1].length(); n++){
				if(regions_info[j][1].contains("red")) 		peices[j][5]= 1;
				if(regions_info[j][1].contains("green")) 	peices[j][6]= 1;
				if(regions_info[j][1].contains("blue")) 	peices[j][7]= 1;
				if(regions_info[j][1].contains("yellow")) 	peices[j][8]= 1;
	
			}
		
								/////////////////////////////////// Get Trouble Markers info     ////////////////////////////////
			for(int m=0; m< 12; m++){
				if(Character.isDigit(regions_info[m][2].charAt(0))) peices[m][4]= Integer.parseInt(regions_info[m][2]);
				if(Character.isDigit(regions_info[m][3].charAt(0))) peices[m][9]= Integer.parseInt(regions_info[m][3]);
				if(Character.isDigit(regions_info[m][4].charAt(0))) peices[m][10]= Integer.parseInt(regions_info[m][4]);
			}
			
		}
		return 	peices;
		
	}

	
	public  void updateBoard(final int[][] peices, JFrame frame){
		AssetsDepot assets = new AssetsDepot();
		assets.Set_Asset();
		
		frame.setTitle("Ankh Morpork Build_3");
        frame.setSize(1000, 1500);
        frame.setVisible(true);
		
		
		
		
		//frame.dispose();
        final BufferedImage board = assets.board; final BufferedImage header = assets.title1; final BufferedImage footer = assets.title2; 
        final BufferedImage[] items ={assets.minion_r,assets.minion_g,assets.minion_b,assets.minion_y,assets.troubleMarker,assets.building_r,assets.building_g,assets.building_b,assets.building_y,assets.demon,assets.troll} ;

        JPanel pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int[] slots ={0,0,0,0,0,0,0,0,0,0,0,0};
               
                g.clearRect(0, 0, getWidth(), getHeight() );
                g.drawImage(board, 0, 0, null);
                g.drawImage(header, 120, -190, null);
                g.drawImage(footer, 150, 730, null);
                
                
             
                for(int j=0; j<12; j++){
                	for(int i=0; i<11; i++ ){
                		if(peices[j][i]>0){
	                		for(int k=0; k<peices[j][i]; k++){
	                			g.drawImage(items[i], Integer.parseInt(Locations[j][slots[j]].substring(0, Locations[j][slots[j]].indexOf(","))) , Integer.parseInt(Locations[j][slots[j]].substring(Locations[j][slots[j]].indexOf(",")+1, Locations[j][slots[j]].length())), null);
	                			slots[j]=slots[j]+1;
	                		}
                		}
                	}
                }
   
            }
        };

       
            frame.getContentPane().removeAll();
        	frame.add(pane);
        	frame.getContentPane().repaint();
            frame.getContentPane().validate();
           
           
        
        
	}

	
	private static JFrame buildFrame() {
        JFrame frame = new JFrame();
        //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Ankh Morpork Build_3");
        frame.setSize(1000, 1500);
        frame.setVisible(true);
        return frame;
    }

}

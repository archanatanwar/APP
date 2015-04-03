package Game;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * <h1>Crops image</h1>
 * <p>
 * Crops the image to create images<br>
 * for minions, trolls, trouble markers,</br>
 * demons and trolls</p>
 * 
 * @author nav_k
 *
 */
public class AssetsDepot {
private static  int width = 30, height = 25;
	
	public  BufferedImage board,minion_g,minion_b,minion_r,minion_y, troubleMarker,title1,title2,building_b,building_y,building_r,building_g,demon,troll;

	public void Set_Asset(){
		Assets_Extractor collection = new Assets_Extractor(Assets_Loader.loadImage("/Assets_New.png"));
		
		board = Assets_Loader.loadImage("/Board_New.png");
		title1 = Assets_Loader.loadImage("/Title_1.png");
		title2 = Assets_Loader.loadImage("/Title_2.png");
		minion_r = collection.crop(0, 0, width, height);
		minion_g = collection.crop(width, 0, width, height);
		minion_b = collection.crop(width*2, 0, width, height);
		minion_y = collection.crop(width*3, 0, width, height);
		troubleMarker = collection.crop(width*4, 0, width, height);
		building_b=collection.crop(width*5, 0, width, height);
		building_y=collection.crop(width*6, 0, width, height);
		building_r=collection.crop(width*7, 0, width, height);
		building_g=collection.crop(width*8, 0, width, height);
		demon = Assets_Loader.loadImage("/demon.jpg");
		troll = Assets_Loader.loadImage("/troll.jpg");

	}
	

}

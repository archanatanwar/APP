import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class AssetsDepot {
private static final int width = 30, height = 25;
	
	public static BufferedImage board,minion_g,minion_b,minion_r,minion_y, troubleMarker,title1,title2;

	public static void Set_Asset(){
		Assets_Extractor collection = new Assets_Extractor(Assets_Loader.loadImage("/Assets.png"));
		
		board = Assets_Loader.loadImage("/Board.png");
		title1 = Assets_Loader.loadImage("/Title_1.png");
		title2 = Assets_Loader.loadImage("/Title_2.png");
		minion_r = collection.crop(0, 0, width, height);
		minion_g = collection.crop(width, 0, width, height);
		minion_b = collection.crop(width*2, 0, width, height);
		minion_y = collection.crop(width*3, 0, width, height);
		troubleMarker = collection.crop(width*4, 0, width, height);

	}
	

}

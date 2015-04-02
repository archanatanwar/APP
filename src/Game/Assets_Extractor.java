package Game;
import java.awt.image.BufferedImage;


public class Assets_Extractor {
private BufferedImage assets;
	
	public Assets_Extractor(BufferedImage assets){
		this.assets = assets;
	}
	
	public BufferedImage crop(int x, int y, int width, int height){
		return assets.getSubimage(x, y, width, height);
	}

}

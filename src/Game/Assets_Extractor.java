package Game;
import java.awt.image.BufferedImage;

/**
 * <h1>Memory management regarding images</h1>
 * <p> Uses spreadsheets to load the image in one file<br>
 * and crops images to get any one.
 * </p>
 * @author nav_k
 *
 */
public class Assets_Extractor {
private BufferedImage assets;
	
	public Assets_Extractor(BufferedImage assets){
		this.assets = assets;
	}
	
	public BufferedImage crop(int x, int y, int width, int height){
		return assets.getSubimage(x, y, width, height);
	}

}

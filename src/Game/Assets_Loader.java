package Game;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * <h1>Loads image</h1>
 * <p>
 * Class helps to load the images using File I/O stream<br>
 * for images
 * </p>
 * @author nav_k
 *
 */
public class Assets_Loader {
	
	/**
	 * Method returns the image by calling AssetsDepot class
	 * @param path String path to an image
	 * @return returns image
	 */
	public static BufferedImage loadImage(String path){
		try {
			return ImageIO.read(AssetsDepot.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	

}

package de.hexagonsoftware.engine.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import de.hexagonsoftware.engine.util.ErrorHandler;
import de.hexagonsoftware.engine.util.HEResourceLoadException;

public class ImageLoader {
	public static BufferedImage loadImage(URL path) throws HEResourceLoadException {
		BufferedImage img = null;
		
		if (path == null) {
			ErrorHandler.reportException(new HEResourceLoadException());
		}
		
		try {
			img = ImageIO.read(path);
		} catch (IOException e) {
			ErrorHandler.reportException(new HEResourceLoadException());
		}
		
		if (img != null) {
			return img;
		} else {
			return null;
		}
	}
}

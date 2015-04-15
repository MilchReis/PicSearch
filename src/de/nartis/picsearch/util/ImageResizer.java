package de.nartis.picsearch.util;

import java.awt.image.BufferedImage;

public class ImageResizer {

	public static BufferedImage resize( int factor, BufferedImage image ) {

		int width = (int) ( image.getWidth() * ( (float) factor / 100.0f ) );
		int height = (int) ( image.getHeight() * ( (float) factor / 100.0f ) );

		return ImageLoader.toBufferedImage( image.getScaledInstance( width,
				height, BufferedImage.SCALE_FAST ) );			
	}
	
	public static BufferedImage resize( int width, int height, BufferedImage img) {
		return ImageLoader.toBufferedImage( img.getScaledInstance( width, height, BufferedImage.SCALE_FAST ) );	
	}
}

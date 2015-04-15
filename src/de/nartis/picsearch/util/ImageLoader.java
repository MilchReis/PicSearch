package de.nartis.picsearch.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.sun.jimi.core.Jimi;

public class ImageLoader {

	public static BufferedImage load( String source ) {
		Image img = Jimi.getImage( source );
		img = new ImageIcon( img ).getImage();
		return toBufferedImage( img );
	}
	
	public static Image loadImage( String source ) {
		Image img = Jimi.getImage( source );
		return new ImageIcon( img ).getImage();
	}
	
	public static BufferedImage loadResource( String resource ) {
		ImageIcon image = new ImageIcon( ClassLoader.getSystemResource( resource ) );
		return toBufferedImage( image.getImage() );
	}
	
	public static BufferedImage toBufferedImage( Image img ) {
		BufferedImage tmp = new BufferedImage( 
				img.getWidth( null ), 
				img.getHeight( null ), 
				Transparency.TRANSLUCENT );

		Graphics g = tmp.getGraphics();
		g.drawImage( img, 0, 0,  null );
		g.dispose();
		
		return tmp;
	}
}

package de.nartis.picsearch.model;

import java.awt.image.BufferedImage;
import java.io.File;

import de.nartis.picsearch.util.ImageLoader;

public class Picture {

	public static final int PREVIEW_WIDTH = 50;
	public static final int PREVIEW_HEIGHT = 50;
	
	private File path;
	private BufferedImage preview;
	private int width, height;
	private float similarity;
	
	public Picture( String pathForPicture ) {
		path = new File( pathForPicture );
		
		if( !path.exists() )
			throw new IllegalArgumentException( "Picture does not exist" );
		
		BufferedImage tmp = ImageLoader.load( pathForPicture );
		width = tmp.getWidth();
		height = tmp.getHeight();
		
		preview = ImageLoader.toBufferedImage( tmp.getScaledInstance( 
				PREVIEW_WIDTH, PREVIEW_HEIGHT, BufferedImage.SCALE_FAST ) );
		tmp = null;
	}
	
	
	
	public String getName() {
		return path.getName();
	}
	
	public String getPath() {
		return path.getAbsolutePath();
	}
	
	public BufferedImage getPreview() {
		return preview;
	}
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }

	public float getSimilarity() {
		return similarity;
	}

	public void setSimilarity(float similarity) {
		this.similarity = similarity;
	}
}

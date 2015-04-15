package de.nartis.picsearch.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import de.nartis.picsearch.core.Analyser;
import de.nartis.picsearch.core.Settings;
import de.nartis.picsearch.util.ImageLoader;

public class AppModel extends Observable {

	private BufferedImage paintedImage;
	private String imgPath;
	private int brushSize = 30;
	private Color brushColor = Color.BLUE;
	
	private List<Picture> previewlist;
	
	public AppModel() {
		paintedImage = new BufferedImage(
				Settings.INTERNAL_WIDTH,
				Settings.INTERNAL_HEIGHT, 
				BufferedImage.TRANSLUCENT);
		
		previewlist = new ArrayList<Picture>();
	}
	
	public void startSearch() {
		
		previewlist.clear();
		
		if(imgPath != null) {
			File dir = new File(imgPath);
			
			for(File f : dir.listFiles()) {
				if(f.isFile() && f.getAbsolutePath().toLowerCase().endsWith(".jpg")) {
//					System.out.println(f.getAbsolutePath());
					BufferedImage img = ImageLoader.load(f.getAbsolutePath());
					float sim = Analyser.getSimilarity(this.paintedImage, img);
//					System.out.println(sim);
					if(sim >= Settings.MIN_SIMIMLARITY) {
						System.out.println(f.getName() +" is similar with "+(sim*100.0f)+"%");
						Picture p = new Picture(f.getAbsolutePath());
						p.setSimilarity(sim);
						previewlist.add(p);
						notifyObservers(null);
					}
				}
			}
		}
		
		System.gc();
	}

	public void increaseBrush() {
		brushSize += 10;
	}

	public int getBrushSize() {
		return brushSize;
	}

	public void decreaseBrush() {
		if(brushSize > 10)
			brushSize -= 10;
	}

	public Color getColor() {
		return brushColor;
	}

	public void setColor(Color color) {
		brushColor = color;
	}

	public void setSourcePath(String imgPath) {
		this.imgPath = imgPath;
	}

	public BufferedImage getImage() {
		return paintedImage;
	}
	
	public List<Picture> getPreviews() {
		return previewlist;
	}
	
	@Override
	public void notifyObservers( Object arg ) {
		setChanged();
		super.notifyObservers( arg );
	}
}

package de.nartis.picsearch.core;

import java.awt.Color;
import java.awt.image.BufferedImage;

import de.nartis.picsearch.util.ImageResizer;

public class Analyser {
	
	public static Color averageColor(BufferedImage bi, int x0, int y0, int w, int h) {
	    int x1 = x0 + w;
	    int y1 = y0 + h;
	    long sumr = 0, sumg = 0, sumb = 0;
	    for (int x = x0; x < x1; x++) {
	        for (int y = y0; y < y1; y++) {
	            Color pixel = new Color(bi.getRGB(x, y));
	            sumr += pixel.getRed();
	            sumg += pixel.getGreen();
	            sumb += pixel.getBlue();
	        }
	    }
	    int num = w * h;
	    return new Color((int) sumr / num, (int) sumg / num, (int) sumb / num);
	}
	
	public static Color[] getColorSet(BufferedImage img) {
		Color[] set = new Color[Settings.GRID_X * Settings.GRID_Y];
		int gridx = Settings.GRID_X;
		int gridy = Settings.GRID_Y;

		int w = img.getWidth()/gridx;
		int h = img.getHeight()/gridy;
		
		int i=0;
		for(int x=0; x<gridx; x++) {
			for(int y=0; y<gridy; y++) {
				int tx = x*w;
				int ty = y*h;
				set[i++] = Analyser.averageColor(img, tx, ty, w, h);
			}
		}
		return set;
	}
	
	public static String print(float[] vals) {
		StringBuilder sb = new StringBuilder();
		sb.append("h=").
			append(360*vals[0]).
			append(", s=").
			append(vals[1]).
			append(", b=").
			append(vals[2]);
		
		return sb.toString();
	}
	
	public static float getSimilarity(BufferedImage img1, BufferedImage img2) {
		
		BufferedImage imgpaint = ImageResizer.resize(Settings.WORKING_HEIGHT, Settings.WORKING_HEIGHT, img1);
		BufferedImage imgsearch = ImageResizer.resize(Settings.WORKING_HEIGHT, Settings.WORKING_HEIGHT, img2);
		
		Color[] csPaint = getColorSet(imgpaint);
		Color[] csSearch = getColorSet(imgsearch);
		
		int parts = Settings.GRID_X * Settings.GRID_Y;
		int matchedParts = 0;
		int skippedParts = 0;
		
		for(int i=0; i<csPaint.length; i++) {
			float[] hsbPaint = Color.RGBtoHSB(csPaint[i].getRed(), csPaint[i].getGreen(), csPaint[i].getBlue(), null);
			float[] hsbSearch = Color.RGBtoHSB(csSearch[i].getRed(), csSearch[i].getGreen(), csSearch[i].getBlue(), null);
			
			// Skip area (no painting)
			if(hsbPaint[0] == 0f && hsbPaint[1] == 0f && hsbPaint[1] == 0f) {
				skippedParts++;
				continue;
			}
			
			if(isNear(hsbPaint, hsbSearch)) {
				matchedParts++;
			}
			
//			System.out.println("Paint  ["+i+"]: "+print(hsbPaint));
//			System.out.println("Search ["+i+"]: "+print(hsbSearch));
		}
		
		return (float) matchedParts / (float) (parts - skippedParts);
	}

	public static boolean inRange(int value, int min, int max) {
		if(value > min && value <= max)
			return true;
		return false;
	}
	
	public static boolean isFuzzyEqual(int value1, int value2, int min, int max) {
		if(inRange(value1, min, max) && inRange(value2, min, max))
			return true;
		return false;
	}
	
	public static int getPartIndex(int value, int parts, int max) {
		int pW = max / parts;
		for(int i=0; i<parts; i++)
			if(inRange(value, i*pW, (i+1)*pW))
				return i;
		return -1;
	}

	public static boolean isFuzzyEqualPart(int value1, int value2, int max, int steps) {
		int i1 = getPartIndex(value1, steps, max);
		int i2 = getPartIndex(value2, steps, max);
		
		if(i1 == -1 || i2 == -1)
			return false;
	
		if(i1 == i2)
			return true;
		
		return false;
	}
	
	private static boolean isNear(float[] hsb1, float[] hsb2) {
		int[] vals = {Settings.RED_1, Settings.ORANGE, Settings.YELLOW, Settings.GREEN, 
				Settings.BLUE, Settings.PURPLE, Settings.PINK, Settings.RED_2};
		
		int lastColor = 0;
		for(int colorThreshold : vals) {
			
			if( isFuzzyEqual((int)(hsb1[0]*360.0f), (int)(hsb2[0]*360.0f), lastColor, colorThreshold) ){ //&& 			// Check color
//				isFuzzyEqualPart((int)(hsb1[1]*100.0f), (int)(hsb2[1]*100.0f), 100, Settings.SATURATION_STEPS) ){	// Check saturation 
				//isFuzzyEqualPart((int)(hsb1[2]*100.0f), (int)(hsb2[2]*100.0f), 100, Settings.BRIGHTNESS_STEPS)) {	// Check brightness				
				return true;
			}
			lastColor = colorThreshold;
		}
		return false;
	}
}

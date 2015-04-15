package de.nartis.picsearch.core;

import java.io.InputStream;
import java.util.Properties;

public class Settings {

	public static int INTERNAL_WIDTH = 400;
	public static int INTERNAL_HEIGHT = 400;
	
	public static int WORKING_WIDTH = 60;
	public static int WORKING_HEIGHT = 60;
	
	public static int GRID_X = 3;
	public static int GRID_Y = 3;
	
	public static int RED_1 = 30;
	public static int ORANGE = 50;
	public static int YELLOW = 60;
	public static int GREEN = 170;
	public static int BLUE = 270;
	public static int PURPLE = 299;
	public static int PINK = 335;
	public static int RED_2 = 360;

	public static int SATURATION_STEPS = 3;
	public static int BRIGHTNESS_STEPS = 3;
	
	public static float MIN_SIMIMLARITY = 0.30f;
	
	public static void load() {
		try {
			Properties probs = new Properties();
			InputStream in = Settings.class.getClassLoader().getResourceAsStream("config.properties");
			probs.load(in);
			
			INTERNAL_WIDTH = Integer.parseInt(probs.getProperty("INTERNAL_WIDTH"));
			INTERNAL_HEIGHT = Integer.parseInt(probs.getProperty("INTERNAL_HEIGHT"));

			WORKING_WIDTH = Integer.parseInt(probs.getProperty("WORKING_WIDTH"));
			WORKING_HEIGHT = Integer.parseInt(probs.getProperty("WORKING_HEIGHT"));
			
			GRID_X = Integer.parseInt(probs.getProperty("GRID_X"));
			GRID_Y = Integer.parseInt(probs.getProperty("GRID_Y"));

			MIN_SIMIMLARITY = Float.parseFloat(probs.getProperty("MIN_SIMIMLARITY"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

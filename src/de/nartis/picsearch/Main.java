package de.nartis.picsearch;

import de.nartis.picsearch.core.Settings;
import de.nartis.picsearch.gui.Window;
import de.nartis.picsearch.model.AppModel;

public class Main {

	public static void main(String[] args) {	
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				Settings.load();
				Window frame = new Window(new AppModel());
				frame.setVisible(true);
			}
		});
	}
}
